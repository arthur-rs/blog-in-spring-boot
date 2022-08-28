package com.api.blog.modules.users.core.models.exceptions;

public class UserNotFoundException extends Exception {
	public UserNotFoundException() {
		super("User not found exception.");
	}
}
