package com.cg.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.cg.entity.Booking;
import com.cg.entity.User;
import com.cg.exception.UserAlreadyExistsException;
import com.cg.exception.UserNotFoundException;

@SuppressWarnings("rawtypes")
public interface CustomerService {
	
	public ResponseEntity getUserById(int id) throws UserNotFoundException;
	
	public List<User> getAllUsers();
	
	public ResponseEntity addUser(User user) throws UserAlreadyExistsException;
	
	public ResponseEntity updateUser(User user) throws UserNotFoundException;
	
	public ResponseEntity deleteUser(int id) throws UserNotFoundException;
	
	public ResponseEntity addBooking(Booking booking);
	
	public ResponseEntity deleteBookingByBookingId(int id);
}
