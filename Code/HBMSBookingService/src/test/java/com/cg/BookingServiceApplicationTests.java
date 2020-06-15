package com.cg;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.time.LocalDate;
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

import com.cg.entity.Booking;
import com.cg.exception.BookingAlreadyExistsException;
import com.cg.exception.BookingNotFoundException;
import com.cg.repo.BookingRepository;
import com.cg.service.BookingService;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class BookingServiceApplicationTests {

	@MockBean
	private BookingRepository repo;
	
	@Autowired
	private BookingService bookingService;
	
	Booking booking;
	List<Booking> list;
	List<Booking> emptyList;
	
	@Test
	public void contextLoads() {
		
	}
	
	@Before
	public void setUp() throws Exception{
		booking= new Booking(201, 1, 100, 301, "Taj",LocalDate.of(2020, 02, 12),LocalDate.of(2020, 02, 14), 1, 2,3000.0);
		list=new ArrayList<Booking>();
		emptyList = new ArrayList<Booking>();
		list.add(booking);
	}
	
	@Test
	public void getAllBookingsShouldReturnListOfBooking() {
		Mockito.when(repo.findAll()).thenReturn(list);
		assertEquals(list,bookingService.getAllBookings());
	}
	
	@Test
	public void getAllBookingsForZeroValuesPresent_Failure() {
		Mockito.when(repo.findAll()).thenReturn(new ArrayList<>());
		assertNotEquals(list, bookingService.getAllBookings());
	}
	
	@Test
	public void testGetBookingByIdFound_Success() throws BookingNotFoundException{
		int bookingId = 201;
		Optional<Booking> opt = Optional.of(booking);
		Mockito.when(repo.findById(bookingId)).thenReturn(opt);
		assertEquals(booking, bookingService.getBookingByBookingId(bookingId));
	}
	
	@Test(expected = BookingNotFoundException.class)
	public void bookingGetBookingByIdNotFound_Failure() throws BookingNotFoundException {
		int bookingId = 1;
		bookingService.getBookingByBookingId(bookingId);
	}
	@Test
	public void getBookingsByHotelNameShouldReturnBookingList() throws BookingNotFoundException{
		String hotelName = "Taj";
		Mockito.when(repo.findByHotelNameIgnoreCase(hotelName)).thenReturn(list);
		assertEquals(list, bookingService.getBookingDetailsByHotelName(hotelName));
	}
	
	@Test(expected = BookingNotFoundException.class)
	public void getBookingsByHotelNameShouldThrowBookingNotFoundException() throws BookingNotFoundException{
		String hotelName = "Taj1";
		Mockito.when(repo.findByHotelNameIgnoreCase(hotelName)).thenReturn(emptyList);
		bookingService.getBookingDetailsByHotelName(hotelName);
		
	}
	
	@Test
	public void testDeletebookinglt_Success() throws BookingNotFoundException{
		
		int bookingId = 201;
		Mockito.when(repo.existsById(bookingId)).thenReturn(true);
		Mockito.when(repo.findById(bookingId)).thenReturn(Optional.of(booking));
		assertEquals(booking,bookingService.deleteBookingById(bookingId));
	}
	
	@Test(expected=BookingNotFoundException.class)
	public void testDeleteHotelForNotFound_Failure() throws BookingNotFoundException{
		int bookingId=201;
		Mockito.when(repo.existsById(bookingId)).thenReturn(false);
		bookingService.deleteBookingById(bookingId);
	}
	@Test
	public void testAddBooing_Success() throws BookingAlreadyExistsException {
		booking=new Booking(201, 1, 100, 301, "Taj",LocalDate.of(2020, 02, 12),LocalDate.of(2020, 02, 14), 1, 2,3000.0);
		Mockito.when(repo.existsById(booking.getBookingId())).thenReturn(false);
		Mockito.when(repo.save(booking)).thenReturn(booking);
		assertEquals(booking,bookingService.addBooking(booking));
	}

	@Test(expected = BookingAlreadyExistsException.class)
	public void testAddHotelForAlreadyExists_Failure() throws BookingAlreadyExistsException {
		Booking bookingNew = new Booking(201, 1, 100, 301, "Taj",LocalDate.of(2020, 02, 12),LocalDate.of(2020, 02, 14), 1, 2,3000.0);
		Mockito.when(repo.findByRoomIdAndHotelId(bookingNew.getRoomId(), bookingNew.getHotelId())).thenReturn(bookingNew);
		bookingService.addBooking(bookingNew);
		//assertThrows(HotelAlreadyExistsException.class, () -> hotelService.addHotel(hotel));
	}
    
}
