package com.api.blog.modules.users.core.models.entities;

import com.api.blog.modules.users.core.models.enums.UserStatusEnum;

import lombok.Data;
import lombok.Getter;
import lombok.NonNull;

import java.util.Optional;
import java.util.UUID;

@Data
@Getter
public class UserEntity {
	UUID id;

	String name;

	String email;

	String password;

	UserStatusEnum status;

	public UserEntity(
			Optional<UUID> id,
			@NonNull String name,
			@NonNull String email,
			@NonNull String password,
			Optional<UserStatusEnum> status
	) {
		this.id = id.orElseGet(UUID::randomUUID);
		this.name = name;
		this.email = email;
		this.password = password;
		this.status = status.orElse(UserStatusEnum.ACTIVE);
	}
}
