package com.api.blog.modules.users.core.models.entities;

import com.api.blog.modules.users.core.models.enums.UserStatusEnum;

import lombok.Data;
import lombok.Getter;
import lombok.NonNull;

import java.util.UUID;

@Data
@Getter
public class UserEntity {
	private UUID id;

	private String name;

	private String email;

	private String password;

	private UserStatusEnum status;

	public UserEntity(
		@NonNull UUID id,
		@NonNull String name,
		@NonNull String email,
		@NonNull String password,
		@NonNull UserStatusEnum status
	) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.status = status;
	}

	public UserEntity(
		@NonNull String name,
		@NonNull String email,
		@NonNull String password
	) {
		this.id = UUID.randomUUID();
		this.name = name;
		this.email = email;
		this.password = password;
		this.status = UserStatusEnum.ACTIVE;
	}
}
