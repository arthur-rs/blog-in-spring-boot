package com.api.blog.modules.users.core.usecases;

import com.api.blog.modules.users.core.models.entities.UserEntity;
import com.api.blog.modules.users.core.models.exceptions.UserNotFoundException;
import com.api.blog.modules.users.core.ports.in.FindUserByIdPort;
import com.api.blog.modules.users.core.ports.out.UsersRepositoryPort;

import lombok.AllArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
public class FindUserByIdUseCase implements FindUserByIdPort {
	UsersRepositoryPort usersRepositoryPort;

	@Override
	public UserEntity findById(UUID id) throws UserNotFoundException {
		var result = this.usersRepositoryPort.findById(id);

		return result.orElseThrow(UserNotFoundException::new);
	}
}
