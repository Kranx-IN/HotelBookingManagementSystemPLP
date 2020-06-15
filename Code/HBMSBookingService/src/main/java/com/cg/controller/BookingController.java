package com.cg.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.entity.Booking;
import com.cg.exception.BookingAlreadyExistsException;
import com.cg.exception.BookingNotFoundException;
import com.cg.service.BookingService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RequestMapping("/booking")
public class BookingController {

	Logger logger = LoggerFactory.getLogger(BookingController.class);
	
	@Autowired
	private BookingService bookingService;
	
	//http://localhost:9093/booking/all
	@GetMapping("/all")
	@HystrixCommand(fallbackMethod = "BookingGetAllErrorHandler")
	public List<Booking> getAllBookings() {
		return bookingService.getAllBookings();
	}
	
	//http://localhost:9093/booking/bookingid/202
	@GetMapping("/bookingid/{bookingId}")
	@HystrixCommand(fallbackMethod = "whenBookingNotFoundByBookingId")
	public Booking getBookingsByBId(@Valid@PathVariable int bookingId) throws BookingNotFoundException {
		Booking booking = bookingService.getBookingByBookingId(bookingId);
		logger.info("Booking found in database : "+booking);
		return booking;
	}

	//http://localhost:9093/booking/add
	@PostMapping("/add")
	@HystrixCommand(fallbackMethod = "whenBookingNotAdded")
	public Booking addBooking(@Valid@RequestBody Booking booking) throws BookingAlreadyExistsException {
		Booking bookingDetails = bookingService.addBooking(booking);
		logger.info("Booking added to database");
		return bookingDetails;
	}
	
	//http://localhost:9093/booking/delete/id/201
	@DeleteMapping("/delete/id/{bookingId}")
	@HystrixCommand(fallbackMethod = "whenBookingNotFound")
	public Booking deleteBooking(@PathVariable int bookingId) throws BookingNotFoundException {
		Booking bookingDetails = bookingService.deleteBookingById(bookingId);
		logger.info("Booking deleted from database : "+bookingDetails);
		return bookingDetails;
	}

	//http://localhost:9093/booking/hotelName/Taj
	@GetMapping("/hotelName/{hotelName}")
	@HystrixCommand(fallbackMethod = "whenHotelNameNotFound")
	public List<Booking> getBookingByhotelName(@Valid@PathVariable String hotelName) throws BookingNotFoundException {
		List<Booking> bookingDetails = bookingService.getBookingDetailsByHotelName(hotelName);
		logger.info("Booking found in database : "+ bookingDetails);
		return bookingDetails;
	}
	
	public List<Booking> BookingGetAllErrorHandler(){
		return new ArrayList<>();
	}
	
	public List<Booking> whenHotelNameNotFound(String hotelName) {
		logger.info("hotelName not present in database : " + hotelName);
		return new ArrayList<>();
	}
	
	public Booking whenBookingNotFoundByBookingId(int bookingId) {
		return new Booking();
	}	
	
	public Booking whenBookingNotAdded (Booking booking) {
		return new Booking();
	}
	
	public Booking whenBookingNotFound(int bookingId) {
		return new Booking();
	}
}
