package com.api.blog.modules.users.core.ports.in;

import com.api.blog.modules.users.core.models.entities.UserEntity;

import java.util.List;

public interface FindAllUsersPort {
	List<UserEntity> findAll();
}
