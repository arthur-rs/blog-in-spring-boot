package com.api.blog.modules.users.core.usecases;

import com.api.blog.modules.users.core.ports.out.UsersRepositoryPort;
import com.api.blog.modules.users.utils.FakerUserEntityGenerator;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FindAllUsersUseCaseTests {
	@Mock
	UsersRepositoryPort usersRepository;
	private FindAllUsersUseCase useCase;

	@Before
	public void setUp() {
		this.useCase = new FindAllUsersUseCase(
			this.usersRepository
		);
	}

	@Test
	public void verifyIfFindAllUsersInRepository(){
		var fakerUserEntity = FakerUserEntityGenerator.generate();
		when(this.usersRepository.findAll())
			.thenReturn(List.of(fakerUserEntity));

		var result = this.useCase.findAll();

		Assertions.assertEquals(result.get(0), fakerUserEntity);

	}
}