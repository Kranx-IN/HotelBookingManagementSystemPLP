package com.cg.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import com.cg.entity.Booking;
import com.cg.entity.Hotel;
import com.cg.entity.Room;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class AdminServiceImplTest {

	
	@MockBean
	private RestTemplate rest;
	
	@Autowired
	private AdminService target;
	
	Hotel hotel;
	Room room;
	Booking booking;
	List<Hotel> hotelList;
	List<Room> roomList;
	List<Booking> bookingList;
	
	@Test
	public void contextLoads() {
	}
	

	@Test
	public void testAdminForGetAllHotels_Sucess() {
		hotel=new Hotel(301, "Taj", "Pune", "101, Hinjewadi", 2000.0, "t@gmail.com", 9874563210L, "Lake view");
		hotelList = new ArrayList<>();
		hotelList.add(hotel);
		Mockito.when(rest.getForObject("http://hotel-service/hotel/all", List.class)).thenReturn(hotelList);
		assertEquals(hotelList,target.getAllHotels().getBody());
	}
	
	@Test
	public void testAdminForGetAllHotels_Fail() {
		hotel=new Hotel(301, "Taj", "Pune", "101, Hinjewadi", 2000.0, "t@gmail.com", 9874563210L, "Lake view");
		hotelList = new ArrayList<>();
		Mockito.when(rest.getForObject("http://hotel-service/hotel/all", List.class)).thenReturn(hotelList);
		assertEquals(HttpStatus.NOT_FOUND,target.getAllHotels().getStatusCode());
	}
	
	@Test
	public void testAdminForGetAllHotelsByCity_Sucess() {
		hotel=new Hotel(301, "Taj", "Pune", "101, Hinjewadi", 2000.0, "t@gmail.com", 9874563210L, "Lake view");
		hotelList = new ArrayList<>();
		hotelList.add(hotel);
		String city = "Pune";
		Mockito.when(rest.getForObject("http://hotel-service/hotel/city"+city, List.class)).thenReturn(hotelList);
		assertEquals(hotelList,target.getAllHotelsByCity(city).getBody());
	}
	
	@Test
	public void testAdminForGetAllHotelsByCityNotPresent_Fail() {
		hotel=new Hotel(301, "Taj", "Pune", "101, Hinjewadi", 2000.0, "t@gmail.com", 9874563210L, "Lake view");
		hotelList = new ArrayList<>();
		String city = "Chennai";
		Mockito.when(rest.getForObject("http://hotel-service/hotel/city"+city, List.class)).thenReturn(hotelList);
		assertEquals(HttpStatus.NOT_FOUND,target.getAllHotelsByCity(city).getStatusCode());
	}
	
	@Test
	public void testAdminForAddHotel_Success() {
		hotel=new Hotel(301, "Taj", "Pune", "101, Hinjewadi", 2000.0, "t@gmail.com", 9874563210L, "Lake view");
		Mockito.when(rest.postForObject("http://hotel-service/hotel/add", hotel, Hotel.class)).thenReturn(hotel);
		assertEquals("Hotel has been added"+hotel, target.addHotel(hotel).getBody());
	}
		
	@Test
	public void testAdminForUpdateHotel_Success() {
		hotel=new Hotel(301, "Taj", "Pune", "101, Hinjewadi", 2000.0, "t@gmail.com", 9874563210L, "Lake view");		
		Mockito.when(rest.postForEntity("http://hotel-service/hotel/update",hotel,Hotel.class)).thenReturn(new ResponseEntity<Hotel>(hotel,HttpStatus.OK));
		assertEquals("Hotel details have been updated"+hotel, target.updateHotel(hotel).getBody());
	}
	
	@Test
	public void testAdminForUpdateRoom_Success() {
		room= new Room(101, 301, "AC", 3000.0, true);

		Mockito.when(rest.postForEntity("http://Room-service/room/update",room, Room.class)).thenReturn(new ResponseEntity<Room>(room,HttpStatus.OK));
		assertEquals("Room details have been updated"+room, target.updateRoom(room).getBody());
	}
	
	@Test
	public void testAdminForDeleteHotel_Success() {
		hotel=new Hotel(301, "Taj", "Pune", "101, Hinjewadi", 2000.0, "t@gmail.com", 9874563210L, "Lake view");
		int id = 301;
		Mockito.when(rest.getForObject("http://hotel-service/hotel/delete/id/"+id, Hotel.class)).thenReturn(hotel);
		assertEquals(hotel, target.deleteHotelById(id).getBody());
	}
		
	@Test
	public void testAdminForDeleteRoom_Success() {
		room= new Room(101, 301, "AC", 3000.0, true);
		int id = 101;				   
		Mockito.when(rest.getForObject("http://Room-service/room/delete/roomId/"+id, Room.class)).thenReturn(room);
		assertEquals("Room has been deleted!"+room, target.deleteRoomById(id).getBody());
	}
		
	@Test
	public void testAdminForAddRooms_Success() {
		room= new Room(101, 301, "AC", 3000.0, true);
		Mockito.when(rest.postForObject("http://Room-service/room/add", room, Room.class)).thenReturn(room);
		assertEquals("Room has been added"+room,target.addRoom(room).getBody());
	}
	
	@Test
	public void testAdminForGetAllRooms_Success() {
		room= new Room(101, 301, "AC", 3000.0, true);
		roomList = new ArrayList<>();
		roomList.add(room);
		Mockito.when(rest.getForObject("http://Room-service/room/all", List.class)).thenReturn(roomList);
		assertEquals(roomList,target.getAllRooms().getBody());
	}
	
	@Test
	public void testAdminForGetAllRooms_Fail() {
		room= new Room(101, 301, "AC", 3000.0, true);
		roomList = new ArrayList<>();
		Mockito.when(rest.getForObject("http://Room-service/room/all", List.class)).thenReturn(roomList);
		assertEquals(HttpStatus.NOT_FOUND,target.getAllRooms().getStatusCode());
	}
		
	@Test
	public void testAdminForGetAllRoomsByTypes_Success() {
		room= new Room(101, 301, "AC", 3000.0, true);
		roomList = new ArrayList<>();
		roomList.add(room);
		String type = "AC";            
		Mockito.when(rest.getForObject("http://Room-service/room/roomType/"+type, List.class)).thenReturn(roomList);
		assertEquals(roomList,target.getAllRoomsByType(type).getBody());
	}
	
	@Test
	public void testAdminForGetAllRoomsByTypes_Fail() {
		room= new Room(101, 301, "AC", 3000.0, true);
		roomList = new ArrayList<>();
		String type = "AC";
		Mockito.when(rest.getForObject("http://Room-service/room/roomType/"+type, List.class)).thenReturn(roomList);
		assertEquals(HttpStatus.NOT_FOUND,target.getAllHotelsByCity(type).getStatusCode());
	}
	
	@Test
	public void testAdminForGetAllBookingsByHotel_Success() {
		booking= new Booking(201, 1, 100, 301, "Taj",LocalDate.of(2020, 02, 12),LocalDate.of(2020, 02, 14), 1, 2,3000.0);
		bookingList = new ArrayList<>();
		bookingList.add(booking);
		String hName = "Taj";	       
		Mockito.when(rest.getForObject("http://booking-service/booking/hotelName/"+hName, List.class)).thenReturn(bookingList);
		assertEquals(bookingList,target.getAllBookingsByHotel(hName).getBody());
	}
	
	@Test
	public void testAdminForGetAllBookingsByHotel_Fail() {
		booking= new Booking(201, 1, 100, 301, "Taj",LocalDate.of(2020, 02, 12),LocalDate.of(2020, 02, 14), 1, 2,3000.0);
		bookingList = new ArrayList<>();
		String hName = "Taj";
		Mockito.when(rest.getForObject("http://booking-service/booking/hotelName/"+hName, List.class)).thenReturn(bookingList);
		assertEquals(HttpStatus.NOT_FOUND, target.getAllBookingsByHotel(hName).getStatusCode());
	}
}

