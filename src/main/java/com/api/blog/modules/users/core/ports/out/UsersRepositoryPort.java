package com.api.blog.modules.users.core.ports.out;

import com.api.blog.modules.users.core.models.entities.UserEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UsersRepositoryPort {
	Optional<UserEntity> findByEmail(String email);
	Optional<UserEntity> findById(UUID id);
	List<UserEntity> findAll();
	void save(UserEntity userToSave);
}
