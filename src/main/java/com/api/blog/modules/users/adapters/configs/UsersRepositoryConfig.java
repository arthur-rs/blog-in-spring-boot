package com.api.blog.modules.users.adapters.configs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Getter
@Setter
@Configuration
@AllArgsConstructor
@PropertySource("classpath:modules/users.properties")
public class UsersRepositoryConfig {
	@Autowired
	private Environment env;
}
