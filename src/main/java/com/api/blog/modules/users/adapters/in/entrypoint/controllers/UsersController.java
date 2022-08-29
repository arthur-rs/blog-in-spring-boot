package com.api.blog.modules.users.adapters.in.entrypoint.controllers;

import com.api.blog.modules.users.adapters.in.entrypoint.dtos.CreateUserControllerDto;
import com.api.blog.modules.users.adapters.in.entrypoint.models.HttpException;
import com.api.blog.modules.users.adapters.in.entrypoint.services.UsersService;
import com.api.blog.modules.users.core.models.dtos.CreateUserDto;
import com.api.blog.modules.users.core.models.exceptions.EmailAlreadyExistsException;
import com.api.blog.modules.users.core.models.exceptions.UserNotFoundException;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.UUID;

@Controller
@RestController()
@AllArgsConstructor
@CrossOrigin(origins = "*", methods = {
		RequestMethod.POST,
		RequestMethod.GET,
})
public class UsersController {
	@Autowired
	private UsersService usersService;

	@PostMapping(value = "/users")
	public ResponseEntity<Object> createUser(
			@RequestBody @Valid CreateUserControllerDto createUserControllerDto
	) {
		try {
			var createUserDto = new CreateUserDto(
					createUserControllerDto.getName(),
					createUserControllerDto.getEmail(),
					createUserControllerDto.getPassword()
			);
			var userEntity = this.usersService.createUser(createUserDto);
			return ResponseEntity.ok().body(userEntity);
		} catch (EmailAlreadyExistsException e) {
			return ResponseEntity.badRequest().body(new HttpException<>(
					"Bad Request",
					401,
					"Email already exists",
					"EMAIL_ALREADY_EXISTS",
					new Date()
			));
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body(new HttpException<>(
					"Internal Server Error",
					500,
					"There was an unexpected error internally",
					"EMAIL_ALREADY_EXISTS",
					new Date()
			));
		}
	}

	@GetMapping(value = "/users/{id}")
	public ResponseEntity<Object> findUserById(
			@PathVariable("id") @NotNull UUID id
	) {
		try {
			var userEntity = this.usersService.findUserById(id);
			return ResponseEntity.ok().body(userEntity);
		} catch (UserNotFoundException e) {
			return ResponseEntity.badRequest().body(new HttpException<>(
					"Bad Request",
					401,
					"User not found",
					"USER_NOT_FOUND",
					new Date()
			));
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body(new HttpException<>(
					"Internal Server Error",
					500,
					"There was an unexpected error internally",
					"EMAIL_ALREADY_EXISTS",
					new Date()
			));
		}
	}

	@GetMapping(value = "/users")
	public ResponseEntity<Object> findAllUsers() {
		try {
			var users = this.usersService.findAllUsers();
			return ResponseEntity.ok().body(users);
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body(new HttpException<>(
					"Internal Server Error",
					500,
					"There was an unexpected error internally",
					"EMAIL_ALREADY_EXISTS",
					new Date()
			));
		}
	}
}
