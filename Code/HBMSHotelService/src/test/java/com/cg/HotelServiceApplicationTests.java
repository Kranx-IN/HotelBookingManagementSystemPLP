package com.cg;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cg.entity.Hotel;
import com.cg.exception.HotelAlreadyExistsException;
import com.cg.exception.HotelNotFoundException;
import com.cg.repository.HotelRepository;
import com.cg.service.HotelService;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class HotelServiceApplicationTests {

	@MockBean
	private HotelRepository repo;
	
	@Autowired
	private HotelService hotelService;
	
	Hotel hotel;
	List<Hotel> list;
	List<Hotel> emptyList;
		
	@Test
	public void contextLoads() {
	}

	@Before
	public void setUp() throws Exception{
		hotel=new Hotel(301, "Taj", "Pune", "101, Hinjewadi", 2000.0, "t@gmail.com", 9874563210L, "Lake view");
		list=new ArrayList<Hotel>();
		emptyList = new ArrayList<Hotel>();
		list.add(hotel);
		
	}
	@Test
	public void getAllHotelsShouldReturnListOfHotels(){
		Mockito.when(repo.findAll()).thenReturn(list);
		assertEquals(list,hotelService.getAllHotels());
	}
	
	@Test
	public void getAllHotelsForWithZeroValuesPresentFailure(){
		Mockito.when(repo.findAll()).thenReturn(new ArrayList<>());
		assertNotEquals(list, hotelService.getAllHotels());
	}
	
	@Test
	public void testGetHotelByIdShouldReturnHotel() throws HotelNotFoundException{
		int hotelId = 301;
		Optional<Hotel> opt = Optional.of(hotel);
		Mockito.when(repo.findById(hotelId)).thenReturn(opt);
		assertEquals(hotel, hotelService.getHotelByHotelId(hotelId));
	}
	
	@Test(expected = HotelNotFoundException.class)
	public void testGetHotelByIdShouldThrowHotelNotFoundException() throws HotelNotFoundException {
		int hotelId = 301;
		hotelService.getHotelByHotelId(hotelId);
	
	}
	
	@Test
	public void testUpdateHotelByIdShouldReturnHotel() throws HotelNotFoundException  {
		hotel=new Hotel(301, "Taj", "Pune", "101, Hinjewadi", 2000.0, "t@gmail.com", 8978563210L, "Lake view");
		Mockito.when(repo.existsById(hotel.getHotelId())).thenReturn(true);
		Mockito.when(repo.save(hotel)).thenReturn(hotel);
		assertEquals(hotel, hotelService.updateHotelById(hotel));
	}
	
	@Test(expected = HotelNotFoundException.class)
	public void testUpdateHotelByIdShouldThrowHotelNotFoundException() throws HotelNotFoundException {
		hotel=new Hotel(301, "Taj", "Pune", "101, Hinjewadi", 2000.0, "t@gmail.com", 9874563210L, "Lake view");
		Mockito.when(repo.existsById(hotel.getHotelId())).thenReturn(false);
		hotelService.updateHotelById(hotel);
	}
	

	
	
	@Test
	public void testDeleteHotelByHotelIdShouldReturnHotel() throws HotelNotFoundException{
		
		int hotelId = 301;
		Mockito.when(repo.existsById(hotelId)).thenReturn(true);
		Mockito.when(repo.findById(hotelId)).thenReturn(Optional.of(hotel));
		assertEquals(hotel, hotelService.deleteHotelById(hotelId));
	}
	
	@Test(expected=HotelNotFoundException.class)
	public void testDeleteHotelByHotelIdShouldThrowHotelNotFoundException() throws HotelNotFoundException{
		int hotelId=301;
		Mockito.when(repo.existsById(hotelId)).thenReturn(false);
		hotelService.deleteHotelById(hotelId);
	}

	
	@Test
	public void testAddHotelShouldReturnHotel() throws HotelAlreadyExistsException {
		hotel=new Hotel(301, "Taj", "Pune", "101 Hinjewadi", 2000.0, "t@gmail.com", 8978563210L, "Lake view");
		Mockito.when(repo.existsById(hotel.getHotelId())).thenReturn(false);
		Mockito.when(repo.save(hotel)).thenReturn(hotel);
		assertEquals(hotel, hotelService.addHotel(hotel));
	}

	@Test(expected = HotelAlreadyExistsException.class)
	public void testAddHotelShouldThrowHotelAlreadyExistsException() throws HotelAlreadyExistsException {
		Hotel hotelNew = new Hotel(301, "Taj", "Pune", "101 Hinjewadi", 2000.0, "t@gmail.com", 8978563210L, "Lake view");
		Mockito.when(repo.findByHotelNameAndCityAndAddress(hotelNew.getHotelName(), hotelNew.getCity(), hotelNew.getAddress())).thenReturn(hotelNew);
		hotelService.addHotel(hotelNew);
		
	}
	
	@Test
	public void getHotelsByCityShouldReturnHotelList() throws HotelNotFoundException{
		String city = "Pune";
		Mockito.when(repo.findByCityIgnoreCase(city)).thenReturn(list);
		assertEquals(list, hotelService.getHotelDetailsByCity(city));
	}
	
	@Test(expected = HotelNotFoundException.class)
	public void getHotelsByCityShouldThrowHotelNotFoundException() throws HotelNotFoundException{
		String city = "Mumbai";
		Mockito.when(repo.findByCityIgnoreCase(city)).thenReturn(emptyList);
		hotelService.getHotelDetailsByCity(city);
		
	}
}
