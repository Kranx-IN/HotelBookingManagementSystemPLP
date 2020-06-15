package com.cg.exception;

public class RoomAlreadyExistException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String getMessage() {
		return "Room already exists";
	}

	
}
