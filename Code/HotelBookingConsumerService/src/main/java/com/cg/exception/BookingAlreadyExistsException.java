package com.cg.exception;

public class BookingAlreadyExistsException extends Exception {

	private static final long serialVersionUID = 1L;
	
	@Override
	public String getMessage() {
		return "Booking already exists";
	}

}
