package com.cg.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cg.entity.Booking;
import com.cg.entity.User;
import com.cg.exception.UserAlreadyExistsException;
import com.cg.exception.UserNotFoundException;
@SuppressWarnings({ "rawtypes", "unchecked" })
@Service
public class CustomerServiceImpl implements CustomerService {

	Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

	@Autowired
	private RestTemplate rest;
	
	@Autowired
	private MyUserDetailsService userService;

	@Override
	public ResponseEntity addUser(User user) throws UserAlreadyExistsException {
		User userInfo = userService.addUser(user);
		if(userInfo.getUserId() == 0) {
			return new ResponseEntity("User already registered!",HttpStatus.BAD_REQUEST);
		}
		else
			return new ResponseEntity(user,HttpStatus.OK);
	}

	@Override
	public ResponseEntity updateUser(User user) throws UserNotFoundException {
		User userInfo = userService.updateUser(user);
		if(userInfo.getUserId() != 0) {
			return new ResponseEntity(userInfo,HttpStatus.OK);
		}
		else
			return new ResponseEntity(userInfo,HttpStatus.NOT_FOUND);
	}

	@Override
	public ResponseEntity deleteUser(int id) throws UserNotFoundException{
		User userInfo = userService.deleteUserById(id);
		if(userInfo.getUserId() != 0) {
			return new ResponseEntity(userInfo,HttpStatus.OK);
		}
		else
			return new ResponseEntity(userInfo,HttpStatus.NOT_FOUND);
	}


	@Override
	public ResponseEntity addBooking(Booking booking) {
		String url = "http://booking-service/booking/add";
		Booking res = rest.postForObject(url, booking, Booking.class);
		
		if(res.getBookingId() !=0) {
			return new ResponseEntity(res,HttpStatus.OK);
		}
		else {
			return new ResponseEntity(res,HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public ResponseEntity deleteBookingByBookingId(int id) {
		String url = "http://booking-service/booking/delete/id/"+id;
		Booking res = rest.postForObject(url, id, Booking.class);
		
		if(res.getBookingId() !=0) {
			return new ResponseEntity(res,HttpStatus.OK);
		}
		else {
			return new ResponseEntity(res,HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public ResponseEntity getUserById(int id) throws UserNotFoundException {
		User user = userService.getUserById(id);
		return new ResponseEntity(user,HttpStatus.OK);
	}

	@Override
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}
	
	

}
