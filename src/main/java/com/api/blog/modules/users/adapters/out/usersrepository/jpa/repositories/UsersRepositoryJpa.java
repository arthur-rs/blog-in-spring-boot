package com.api.blog.modules.users.adapters.out.usersrepository.jpa.repositories;

import com.api.blog.modules.users.adapters.out.usersrepository.jpa.entities.UserJpaEntity;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UsersRepositoryJpa extends JpaRepository<UserJpaEntity, UUID> {
	@NotNull Optional<UserJpaEntity> findByEmail(@NotNull String email);
	@NotNull Optional<UserJpaEntity> findById(@NotNull UUID id);
}
