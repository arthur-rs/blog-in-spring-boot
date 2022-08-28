package com.api.blog.modules.users.core.usecases;

import com.api.blog.modules.users.core.models.exceptions.UserNotFoundException;
import com.api.blog.modules.users.core.ports.out.UsersRepositoryPort;
import com.api.blog.modules.users.utils.FakerUserEntityGenerator;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;

import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FindUserByIdUseCaseTests {

	@Mock UsersRepositoryPort usersRepository;

	FindUserByIdUseCase useCase;

	@Before
	public void setUp() {
		this.useCase = new FindUserByIdUseCase(this.usersRepository);
	}

	@Test
	public void throwExceptionWhenUserNotFound() {
		var id = UUID.randomUUID();
		when(this.usersRepository.findById(id))
			.thenReturn(Optional.empty());

		Assertions.assertThrows(UserNotFoundException.class, () ->
			this.useCase.findById(id)
		);
	}

	@Test
	public void returnUserEntityByRepository() throws UserNotFoundException {
		var id = UUID.randomUUID();
		var userEntity = FakerUserEntityGenerator.generate();
		when(this.usersRepository.findById(id))
			.thenReturn(Optional.of(userEntity));

		var foundUserEntity = this.useCase.findById(id);

		assertEquals(userEntity, foundUserEntity);
	}
}