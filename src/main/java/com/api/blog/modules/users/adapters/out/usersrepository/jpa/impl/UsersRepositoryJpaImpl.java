package com.api.blog.modules.users.adapters.out.usersrepository.jpa.impl;

import com.api.blog.modules.users.adapters.out.usersrepository.jpa.entities.UserJpaEntity;
import com.api.blog.modules.users.adapters.out.usersrepository.jpa.repositories.UsersRepositoryJpa;
import com.api.blog.modules.users.core.models.entities.UserEntity;
import com.api.blog.modules.users.core.ports.out.UsersRepositoryPort;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Service(UsersRepositoryJpaImpl.ADAPTER_TOKEN)
@AllArgsConstructor
public class UsersRepositoryJpaImpl implements UsersRepositoryPort {
	public static final String ADAPTER_TOKEN = "JpaUsersRepositoryAdapter";
	private final UsersRepositoryJpa usersRepositoryJpa;
	@Override
	public Optional<UserEntity> findByEmail(String email) {
		var userJpaEntity = usersRepositoryJpa.findByEmail(email);
		if(userJpaEntity.isPresent()) {
			var userEntity = new UserEntity(
				userJpaEntity.get().getName(),
				userJpaEntity.get().getEmail(),
				userJpaEntity.get().getPassword()
			);
			return Optional.of(userEntity);
		}
		return Optional.empty();
	}

	@Override
	public Optional<UserEntity> findById(UUID id) {
		var userJpaEntity = usersRepositoryJpa.findById(id);
		if(userJpaEntity.isPresent()) {
			var userEntity = new UserEntity(
				userJpaEntity.get().getName(),
				userJpaEntity.get().getEmail(),
				userJpaEntity.get().getPassword()
			);
			return Optional.of(userEntity);
		}
		return Optional.empty();
	}

	@Override
	@Transactional
	public void save(UserEntity userToSave) {
		var userJpaEntity = new UserJpaEntity();
		BeanUtils.copyProperties(userToSave, userJpaEntity);
		usersRepositoryJpa.save(userJpaEntity);
    }
}
