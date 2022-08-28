package com.api.blog.modules.users.adapters.configs;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Getter
@Setter
@PropertySource(value = "modules.users.users-repository")
@Configuration("UsersRepositoryConfig")
public class UsersRepositoryConfig {
	private String adapter;
}
