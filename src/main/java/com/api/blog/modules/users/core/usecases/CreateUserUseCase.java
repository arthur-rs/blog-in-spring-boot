package com.api.blog.modules.users.core.usecases;

import com.api.blog.modules.users.core.models.dtos.CreateUserDto;
import com.api.blog.modules.users.core.models.entities.UserEntity;
import com.api.blog.modules.users.core.models.exceptions.EmailAlreadyExistsException;
import com.api.blog.modules.users.core.ports.in.CreateUserPort;
import com.api.blog.modules.users.core.ports.out.HashPort;
import com.api.blog.modules.users.core.ports.out.UsersRepositoryPort;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;

import javax.validation.Valid;
import java.util.Optional;

@AllArgsConstructor
public class CreateUserUseCase implements CreateUserPort {

	UsersRepositoryPort usersRepository;
	HashPort hash;

	@Override
	public UserEntity create(@Valid @NotNull CreateUserDto input) throws EmailAlreadyExistsException {
		var emailAlreadyExists = this.usersRepository.findByEmail(
			input.getEmail()
		);

		if(emailAlreadyExists.isPresent()) {
			throw new EmailAlreadyExistsException();
		}

		var passwordHashed = this.hash.make(input.getPassword());

		var userEntity = new UserEntity(
				Optional.empty(),
				input.getName(),
				input.getEmail(),
				passwordHashed,
				Optional.empty()
		);

		this.usersRepository.save(userEntity);

		return userEntity;
	}
}
