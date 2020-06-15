package com.cg.exception;

public class RoomNotFoundException extends Exception {

private static final long serialVersionUID = 1L;
	
	@Override
	public String getMessage() {
		return "Room not found";
	}

	
}
