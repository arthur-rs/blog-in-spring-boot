package com.api.blog.modules.users.core.usecases;

import com.api.blog.modules.users.core.models.entities.UserEntity;
import com.api.blog.modules.users.core.ports.in.FindAllUsersPort;
import com.api.blog.modules.users.core.ports.out.UsersRepositoryPort;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class FindAllUsersUseCase implements FindAllUsersPort {
	private final UsersRepositoryPort usersRepository;

	@Override
	public List<UserEntity> findAll() {
		return this.usersRepository.findAll();
	}
}
