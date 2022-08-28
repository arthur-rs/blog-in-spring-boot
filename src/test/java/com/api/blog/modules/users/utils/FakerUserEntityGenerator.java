package com.api.blog.modules.users.utils;

import com.api.blog.modules.users.core.models.entities.UserEntity;
import com.github.javafaker.Faker;
import org.jetbrains.annotations.NotNull;

public class FakerUserEntityGenerator {
	public static @NotNull UserEntity generate() {
		var faker = new Faker();

		return new UserEntity(
			faker.name().name(),
			faker.internet().emailAddress(),
			faker.internet().password()
		);
	}
}
