package com.api.blog.modules.users.core.ports.out;

import com.api.blog.modules.users.core.models.entities.UserEntity;

import java.util.Optional;

public interface UsersRepositoryPort {
	Optional<UserEntity> findByEmail(String email);
	UserEntity save(UserEntity userToSave);
}
