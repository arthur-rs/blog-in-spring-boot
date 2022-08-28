package com.api.blog.modules.users.core.ports.in;

import com.api.blog.modules.users.core.models.dtos.CreateUserDto;
import com.api.blog.modules.users.core.models.entities.UserEntity;
import com.api.blog.modules.users.core.models.exceptions.EmailAlreadyExistsException;

public interface CreateUserPort {
	UserEntity create(CreateUserDto input) throws EmailAlreadyExistsException;
}
