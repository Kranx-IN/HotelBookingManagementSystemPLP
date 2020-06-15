package com.cg.service;

import java.time.LocalDate;
import java.util.List;

import com.cg.entity.Booking;
import com.cg.exception.BookingAlreadyExistsException;
import com.cg.exception.BookingNotFoundException;

public interface BookingService {
	
	public List<Booking> getAllBookings();

	public Booking getBookingByBookingId(int bookingId) throws BookingNotFoundException;
	//done	
	public Booking addBooking(Booking booking) throws BookingAlreadyExistsException;
	//done
	public Booking deleteBookingById(int bookingId) throws BookingNotFoundException;
	//done
	public List<Booking> getBookingDetailsByHotelName(String hotelName) throws BookingNotFoundException;
	
	
	
	
	
	
	
	
	public List<Booking> getBookingByDate(LocalDate date) throws BookingNotFoundException;
	
}
