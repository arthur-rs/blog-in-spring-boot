package com.api.blog.modules.users.core.ports.in;

import com.api.blog.modules.users.core.models.entities.UserEntity;
import com.api.blog.modules.users.core.models.exceptions.UserNotFoundException;

import java.util.UUID;

public interface FindUserByIdPort {
	UserEntity findById(UUID id) throws UserNotFoundException;
}
