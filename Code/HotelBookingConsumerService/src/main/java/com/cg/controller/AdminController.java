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

import com.cg.entity.Hotel;
import com.cg.entity.Room;
import com.cg.exception.UserNotFoundException;
import com.cg.service.AdminService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@SuppressWarnings("rawtypes")
@RestController
@RequestMapping(value = "/admin")
public class AdminController {

	Logger logger = LoggerFactory.getLogger(AdminController.class);

	@Autowired
	private AdminService adminService;
	
	
	@PostMapping("/addhotel")
	@HystrixCommand(fallbackMethod = "HotelAdditionErroHandler")
	public ResponseEntity addNewHotel(@Valid@RequestBody Hotel hotel) {
		return adminService.addHotel(hotel);		
	}
	
	@PutMapping("/updatehotel")
	@HystrixCommand(fallbackMethod = "HotelUpdateErrorHandler")
	public ResponseEntity updateHotel(@Valid@RequestBody Hotel hotel) {
		return adminService.updateHotel(hotel);
	}
	
	@GetMapping("/hotelsbycity/{city}")
	public ResponseEntity getAllHotelsByCity(@Valid@PathVariable String city) {
		return adminService.getAllHotelsByCity(city);
	}
	
	@GetMapping("/getallhotels")
	public ResponseEntity getAllHotels() {
		return adminService.getAllHotels();
	}
	
	@DeleteMapping("/deletehotelbyid/{id}")
	@HystrixCommand(fallbackMethod = "HotelNotFoundErrorHandler")
	public ResponseEntity deleteHotelById(@Valid@PathVariable int id) {
		return adminService.deleteHotelById(id);
	}
	
	@PostMapping("/addroom")
	@HystrixCommand(fallbackMethod = "RoomAdditionErrorHandler")
	public ResponseEntity addNewRoom(@Valid@RequestBody Room room) {
		return adminService.addRoom(room);		
	}
	
	@PutMapping("/updateroom")
	@HystrixCommand(fallbackMethod = "RoomUpdateErrorHandler")
	public ResponseEntity updateRoom(@Valid@RequestBody Room room) {
		return adminService.updateRoom(room);
	}
	
	@GetMapping("/roomsbytype/{roomtype}")
	public ResponseEntity getAllRoomsByType(@Valid@PathVariable String roomtype) {
		return adminService.getAllRoomsByType(roomtype);
	}
	
	@GetMapping("/getallrooms")
	public ResponseEntity getAllRooms() {
		return adminService.getAllRooms();
	}
	
	@DeleteMapping("/deleteroombyid/{id}")
	@HystrixCommand(fallbackMethod = "RoomNotFoundErroHandler")
	public ResponseEntity deleteRoomById(@Valid@PathVariable int id) {
		return adminService.deleteRoomById(id);
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/allusers")
	public ResponseEntity getAllUsers() {
		return new ResponseEntity(adminService.getAllUsers(),HttpStatus.OK);
	}
	
	@GetMapping("/userbyid/{id}")
	@HystrixCommand(fallbackMethod = "whenUserNotFound")
	public ResponseEntity getUserById(int id) throws UserNotFoundException {
		return adminService.getUserById(id);
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/allbookings")
	public ResponseEntity getAllBookings() {
		return new ResponseEntity(adminService.getAllBookings(),HttpStatus.OK);
	}
	
	@GetMapping("/bookingbyid/{id}")
	public ResponseEntity getBookingsById(int id) {
		return adminService.getBookingById(id);
	}
	
	// hotel addition error handler
	public ResponseEntity HotelAdditionErroHandler(Hotel hotel) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	}
	
	//hotel update error handler
	public ResponseEntity HotelUpdateErrorHandler(Hotel hotel) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}
	
	// not found hotel error handler
	public ResponseEntity HotelNotFoundErroHandler(int id) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}
	
	// room addition error handler
	public ResponseEntity RoomAdditionErrorHandler(Room room) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	}
	
	// room update error handler
	public ResponseEntity RoomUpdateErrorHandler(Room room) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}
	
	// not found room error handler
	public ResponseEntity RoomNotFoundErroHandler(int id) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}
}

