package com.cg.exception;

public class HotelAlreadyExistsException extends Exception {

	private static final long serialVersionUID = 1L;
	
	@Override
	public String getMessage() {
		return "Hotel already exists";
	}

}
