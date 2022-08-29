package com.api.blog.modules.users.adapters.in.entrypoint.services;

import com.api.blog.modules.users.adapters.configs.HashConfig;
import com.api.blog.modules.users.adapters.configs.UsersRepositoryConfig;
import com.api.blog.modules.users.core.models.dtos.CreateUserDto;
import com.api.blog.modules.users.core.models.entities.UserEntity;
import com.api.blog.modules.users.core.models.exceptions.EmailAlreadyExistsException;
import com.api.blog.modules.users.core.models.exceptions.UserNotFoundException;
import com.api.blog.modules.users.core.ports.in.CreateUserPort;
import com.api.blog.modules.users.core.ports.in.FindAllUsersPort;
import com.api.blog.modules.users.core.ports.in.FindUserByIdPort;
import com.api.blog.modules.users.core.ports.out.HashPort;
import com.api.blog.modules.users.core.ports.out.UsersRepositoryPort;
import com.api.blog.modules.users.core.usecases.CreateUserUseCase;
import com.api.blog.modules.users.core.usecases.FindAllUsersUseCase;
import com.api.blog.modules.users.core.usecases.FindUserByIdUseCase;
import lombok.NonNull;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class UsersService {
	@Autowired
	UsersRepositoryConfig usersRepositoryConfig;
	@Autowired
	HashConfig hashConfig;
	@Autowired
	BeanFactory beans;
	UsersRepositoryPort usersRepositoryPort;
	HashPort hashPort;
	CreateUserPort createUserPort;
	FindUserByIdPort findUserByIdPort;
	FindAllUsersPort findAllUsersPort;

	public UsersService(
			@NotNull UsersRepositoryConfig usersRepositoryConfig,
			@NotNull HashConfig hashConfig,
			@NotNull BeanFactory beans
	){
		this.usersRepositoryConfig = usersRepositoryConfig;
		this.hashConfig = hashConfig;
		this.beans = beans;

		this.usersRepositoryPort = beans.getBean(
			Objects.requireNonNull(usersRepositoryConfig.getEnv().getProperty("USERS_REPOSITORY_ADAPTER")),
			UsersRepositoryPort.class
		);
		this.hashPort = beans.getBean(
			Objects.requireNonNull(usersRepositoryConfig.getEnv().getProperty("HASH_ADAPTER")),
			HashPort.class
		);
		this.createUserPort = new CreateUserUseCase(
			this.usersRepositoryPort,
			this.hashPort
		);
		this.findUserByIdPort = new FindUserByIdUseCase(
			this.usersRepositoryPort
		);
		this.findAllUsersPort = new FindAllUsersUseCase(
			this.usersRepositoryPort
		);
	}

	public UserEntity createUser(@NonNull @Valid CreateUserDto createUserDto) throws EmailAlreadyExistsException {
		return this.createUserPort.create(createUserDto);
	}

	public UserEntity findUserById(@NonNull UUID id) throws UserNotFoundException {
		return this.findUserByIdPort.findById(id);
	}

	public List<UserEntity> findAllUsers() {
		return this.findAllUsersPort.findAll();
	}
}
