package com.cg.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.cg.entity.Hotel;
import com.cg.entity.Room;
import com.cg.entity.User;
import com.cg.exception.UserNotFoundException;

@SuppressWarnings("rawtypes")
public interface AdminService {
	
	public ResponseEntity addHotel(Hotel hotel);

	public ResponseEntity updateHotel(Hotel hotel);

	public ResponseEntity getAllHotelsByCity(String city);

	public ResponseEntity getAllHotels();
	
	public ResponseEntity deleteHotelById(int id);
	
	public ResponseEntity addRoom(Room room);

	public ResponseEntity updateRoom(Room room);

	public ResponseEntity getAllRoomsByType(String roomType);

	public ResponseEntity getAllRooms();
	
	public ResponseEntity deleteRoomById(int id);
	
	public ResponseEntity getAllBookingsByHotel(String hotel);
	
	public ResponseEntity getBookingById(int id);
	
	public ResponseEntity getAllBookings();
	
	public ResponseEntity getUserById(int id) throws UserNotFoundException;
	
	public List<User> getAllUsers();
	
}
