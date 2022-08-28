package com.api.blog.modules.users.core.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@Getter
@AllArgsConstructor
public class CreateUserDto {
	@NotBlank
	String name;
	@NotBlank
	@Email
	String email;
	@NotBlank
	@Min(6)
	String password;
}
