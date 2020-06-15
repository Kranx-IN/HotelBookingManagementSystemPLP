package com.cg.exception;

public class UserNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	@Override
	public String getMessage() {
		return "User not found";
	}
}
