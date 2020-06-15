package com.cg.service;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.entity.Booking;
import com.cg.exception.BookingAlreadyExistsException;
import com.cg.exception.BookingNotFoundException;
import com.cg.repo.BookingRepository;

@Service
public class BookingServiceImpl implements BookingService {

	Logger logger = LoggerFactory.getLogger(BookingServiceImpl.class);

	@Autowired
	private BookingRepository bookingRepository;

	@Override
	public List<Booking> getAllBookings() {
		return bookingRepository.findAll();
	}

	@Override
	public Booking getBookingByBookingId(int bookingId) throws BookingNotFoundException {
		return bookingRepository.findById(bookingId).orElseThrow(() -> new BookingNotFoundException());
	}
	
	
	public List<Booking> getBookingDetailsByHotelName(String hotelName) throws BookingNotFoundException{
		if((bookingRepository.findByHotelNameIgnoreCase(hotelName).isEmpty())) {
			throw new BookingNotFoundException();
		}
		else {
			return bookingRepository.findByHotelNameIgnoreCase(hotelName);
		}
	}

	
	@Override
	public Booking addBooking(Booking booking) throws BookingAlreadyExistsException {
		//if (bookingRepository.existsById(booking.getBookingId()))
		if(!(bookingRepository.findByRoomIdAndHotelId(booking.getRoomId(), booking.getHotelId())==null))
			throw new BookingAlreadyExistsException();
		else
			return bookingRepository.save(booking);
	}
	

	@Override
	public Booking deleteBookingById(int bookingId) throws BookingNotFoundException {
		if (bookingRepository.existsById(bookingId)) {
			Booking bookingDetails = bookingRepository.findById(bookingId).orElse(null);
			Booking result = new Booking(bookingDetails.getBookingId(), bookingDetails.getUserId(), bookingDetails.getRoomId(),
					bookingDetails.getHotelId(),bookingDetails.getHotelName(), bookingDetails.getCheckInDate(), bookingDetails.getCheckOutDate(),
					bookingDetails.getNoOfAdults(), bookingDetails.getNoOfChildren(),bookingDetails.getAmount());
			bookingRepository.deleteById(bookingId);
			return result;
		}else {
			throw new BookingNotFoundException();
		}
	}

	@Override
	public List<Booking> getBookingByDate(LocalDate date) throws BookingNotFoundException{
		if(bookingRepository.findByCheckInDate(date).isEmpty())
			throw new BookingNotFoundException();
		else
			return bookingRepository.findByCheckInDate(date);
	}
	

}
