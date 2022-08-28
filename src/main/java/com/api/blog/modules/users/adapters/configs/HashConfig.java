package com.api.blog.modules.users.adapters.configs;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Getter
@Setter
@Configuration("HashConfig")
@PropertySource(value = "modules.users.hash")
public class HashConfig {
	private String adapter;
}
