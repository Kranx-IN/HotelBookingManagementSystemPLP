package com.cg.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cg.entity.Booking;
import com.cg.entity.Hotel;
import com.cg.entity.Room;
import com.cg.entity.User;
import com.cg.exception.UserNotFoundException;

@SuppressWarnings({ "rawtypes", "unchecked" })
@Service
public class AdminServiceImpl implements AdminService {

	//Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);

	@Autowired
	private RestTemplate temp;
	
	@Autowired
	private MyUserDetailsService userService;
	

	@Override
	public ResponseEntity addHotel(Hotel hotel) {
		String url = "http://hotel-service/hotel/add";
		Hotel res = temp.postForObject(url,hotel, Hotel.class);
		if(res.getHotelId() == 0)
			return new ResponseEntity("Hotel could not be added",HttpStatus.OK);
		else
			return new ResponseEntity("Hotel has been added"+hotel,HttpStatus.OK);
	}

	@Override
	public ResponseEntity updateHotel(Hotel hotel) {
		String url = "http://hotel-service/hotel/update";
		
		ResponseEntity<Hotel> responseEntity = temp.postForEntity(url, hotel, Hotel.class);	
		if(responseEntity.getBody().getHotelId() == 0)
			return new ResponseEntity("Hotel could not be updated",HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity("Hotel details have been updated"+hotel,HttpStatus.OK);
	}

	@Override
	public ResponseEntity getAllHotelsByCity(String city) {
		String url="http://hotel-service/hotel/city/"+city;
		List<Hotel> res =  temp.getForObject(url, List.class);
		if(res != null && !res.isEmpty())
			return new ResponseEntity(res,HttpStatus.OK);
		else
			return new ResponseEntity("No hotels found in the city"+city,HttpStatus.NOT_FOUND);
	}

	@Override
	public ResponseEntity getAllHotels() {
		String url = "http://hotel-service/hotel/all";
		List<Hotel> res = temp.getForObject(url, List.class);
		if(res != null && res.size() > 0)
			return new ResponseEntity(res,HttpStatus.OK);
		else
			return new ResponseEntity("No hotels found",HttpStatus.NOT_FOUND);
	}

	@Override
	public ResponseEntity deleteHotelById(int id) {
		String url = "http://hotel-service/hotel/delete/id/"+id;
		Hotel res = temp.getForObject(url, Hotel.class);
		if(res != null && res.getHotelId() == 0)
			return new ResponseEntity("Could not delete hotel of the id"+id,HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity(res,HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity deleteRoomById(int id) {
		String url="http://Room-service/room/delete/roomId/"+id;
		Room res = temp.getForObject(url, Room.class);
		if(res.getHotelId() == 0)
			return new ResponseEntity("Room could not be deleted",HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity("Room has been deleted!"+res,HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity addRoom(Room room) {
		String url="http://Room-service/room/add";
		Room roomDetails = temp.postForObject(url, room, Room.class);
		if(roomDetails.getRoomId() == 0) {
			return new ResponseEntity("Room Could Not Be Added!",HttpStatus.BAD_REQUEST);
		}
		else
			return new ResponseEntity("Room has been added"+room,HttpStatus.OK);
	}

	@Override
	public ResponseEntity updateRoom(Room room) {
		String url="http://Room-service/room/update";
		
		ResponseEntity<Room> responseEntity = temp.postForEntity(url, room, Room.class);
		
		if(responseEntity.getBody().getHotelId() == 0)
			return new ResponseEntity("Room could not be updated",HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity("Room details have been updated"+room,HttpStatus.OK);		
	}

	@Override
	public ResponseEntity getAllRoomsByType(String roomType) {
		String url = "http://Room-service/room/roomType/"+roomType;
		List<Room> res = temp.getForObject(url, List.class);
		if(res.isEmpty() || res==null)
			return new ResponseEntity("No rooms of type "+roomType+" found!",HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity(res,HttpStatus.OK);		

	}

	@Override
	public ResponseEntity getAllRooms() {
		String url = "http://Room-service/room/all";
		List<Room> res = temp.getForObject(url, List.class);
		if(res.isEmpty() || res==null)
			return new ResponseEntity("No rooms found",HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity(res,HttpStatus.OK);
	}

	@Override
	public ResponseEntity getAllBookingsByHotel(String hotel) {
		String url="http://booking-service/booking/hotelName/"+hotel;
		List<Booking> res = temp.getForObject(url, List.class);
		if(res.isEmpty() || res==null)
			return new ResponseEntity("Could not get room details of the hotel "+hotel,HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity(res,HttpStatus.OK);
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

	@Override
	public ResponseEntity getAllBookings() {
		String url = "http://booking-service/booking/all";
		List<Booking> res = temp.getForObject(url, List.class);
		if(res==null || res.isEmpty()) {
			return new ResponseEntity(res,HttpStatus.NOT_FOUND);
		}
		else {
			return new ResponseEntity(res,HttpStatus.OK);
		}
			
	}
	
	@Override
	public ResponseEntity getBookingById(int id) {
		String url = "http://booking-service/booking/bookingId/"+id;
		Booking booking = temp.getForObject(url, Booking.class);
		return new ResponseEntity(booking,HttpStatus.OK);
	}
	
	
	
	

}
