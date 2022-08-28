package com.api.blog.modules.users.core.usecases;

import com.api.blog.modules.users.core.models.dtos.CreateUserDto;
import com.api.blog.modules.users.core.models.entities.UserEntity;
import com.api.blog.modules.users.core.models.exceptions.EmailAlreadyExistsException;
import com.api.blog.modules.users.core.ports.out.HashPort;
import com.api.blog.modules.users.core.ports.out.UsersRepositoryPort;

import com.api.blog.modules.users.utils.FakerUserEntityGenerator;

import com.github.javafaker.Faker;

import org.jetbrains.annotations.NotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;

import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CreateUserUseCaseTests {
	@Mock HashPort hash;
	@Mock UsersRepositoryPort usersRepository;
	CreateUserUseCase useCase;

	private @NotNull CreateUserDto generateCreateUserDto() {
		var faker = new Faker();
		return new CreateUserDto(
			faker.name().name(),
			faker.internet().emailAddress(),
			faker.internet().password()
		);
	}

	@Before
	public void setUp() {
		this.useCase = new CreateUserUseCase(
			this.usersRepository,
			this.hash
		);
	}

	@Test
	public void throwExceptionWhenEmailAlreadyExists() {
		var fakerCreateUserDto = this.generateCreateUserDto();
		var fakerUserEntity = FakerUserEntityGenerator.generate();
		when(this.usersRepository.findByEmail(fakerCreateUserDto.getEmail()))
			.thenReturn(Optional.of(fakerUserEntity));

		Assertions.assertThrows(EmailAlreadyExistsException.class, () -> {
			this.useCase.create(fakerCreateUserDto);
		});
	}

	@Test
	public void verifyIfCallMakeFunctionInHashWithPasswordSent() throws EmailAlreadyExistsException {
		var fakerCreateUserDto = this.generateCreateUserDto();
		when(this.hash.make(fakerCreateUserDto.getPassword()))
			.thenReturn("PASSWORD_HASH");

		this.useCase.create(fakerCreateUserDto);

		verify(this.hash, times(1)).make(
			fakerCreateUserDto.getPassword()
		);
	}

	@Test
	public void verifyIfUserHashBeenSaveInRepository() throws EmailAlreadyExistsException {
		String PASSWORD_HASH_RETURN = "PASSWORD_HASH";
		var fakerCreateUserDto = this.generateCreateUserDto();
		when(this.hash.make(fakerCreateUserDto.getPassword()))
			.thenReturn(PASSWORD_HASH_RETURN);

		this.useCase.create(fakerCreateUserDto);


		ArgumentCaptor<UserEntity> userEntityArgumentCaptor = ArgumentCaptor
			.forClass(UserEntity.class);
		verify(this.usersRepository).save(userEntityArgumentCaptor.capture());
		Assertions.assertEquals(
			userEntityArgumentCaptor.getValue().getName(),
			fakerCreateUserDto.getName()
		);
		Assertions.assertEquals(
			userEntityArgumentCaptor.getValue().getEmail(),
			fakerCreateUserDto.getEmail()
		);
		Assertions.assertEquals(
			userEntityArgumentCaptor.getValue().getPassword(),
			PASSWORD_HASH_RETURN
		);
	}
}
