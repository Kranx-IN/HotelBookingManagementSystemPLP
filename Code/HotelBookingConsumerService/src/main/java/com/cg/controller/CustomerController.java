package com.cg.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.entity.Booking;
import com.cg.entity.User;
import com.cg.exception.UserAlreadyExistsException;
import com.cg.exception.UserNotFoundException;
import com.cg.service.CustomerService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
@SuppressWarnings({ "unchecked", "rawtypes" })
@RestController
@RequestMapping(value = "/customer")
public class CustomerController {

	Logger logger = LoggerFactory.getLogger(CustomerController.class);

	@Autowired
	private CustomerService service;

	@PostMapping("/adduser")
	@HystrixCommand(fallbackMethod = "UserAdditionErrorHandler")
	public ResponseEntity addNewUser(@Valid@RequestBody User user) throws UserAlreadyExistsException {
		return service.addUser(user);
	}
	
	@PutMapping("/updateuser")
	@HystrixCommand(fallbackMethod = "UserUpdateErrorHandler")
	public ResponseEntity updateUser(@Valid@RequestBody User user) throws UserNotFoundException {
		return service.updateUser(user);
	}
	
	@DeleteMapping("/deleteUser/{id}")
	@HystrixCommand(fallbackMethod = "UserDeleteErrorHandler")
	public ResponseEntity deleteUser(@Valid@PathVariable int id) throws UserNotFoundException{
		return service.deleteUser(id);
	}
	
	@PostMapping("/addbooking")
	public ResponseEntity addBooking(@Valid@RequestBody Booking booking) {
		return service.addBooking(booking);
	}
	
	@DeleteMapping("/deletebooking/{id}")
	public ResponseEntity deleteBookingByBookingId(@Valid@PathVariable int id) {
		return service.deleteBookingByBookingId(id);
	}
	
	public ResponseEntity UserAdditionErrorHandler() {
		return new ResponseEntity(new User(),HttpStatus.BAD_REQUEST);
	}
	
	public ResponseEntity UserUpdateErrorHandler() {
		return new ResponseEntity(new User(),HttpStatus.NOT_FOUND);
	}
	
	public ResponseEntity UserDeleteErrorHandler() {
		return new ResponseEntity(new User(),HttpStatus.NOT_FOUND);
	}
	
	public ResponseEntity whenUserNotFound(int id) {
		return new ResponseEntity(new User(),HttpStatus.NOT_FOUND);
	}
}
