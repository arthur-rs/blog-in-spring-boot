package com.api.blog.modules.users.core.models.exceptions;

public class EmailAlreadyExistsException extends Exception {
	public EmailAlreadyExistsException() {
		super("Email already exists ");
	}
}
