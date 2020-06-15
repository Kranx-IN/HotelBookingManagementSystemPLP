package com.cg.service;

import java.util.List;

import com.cg.entity.Hotel;
import com.cg.exception.HotelAlreadyExistsException;
import com.cg.exception.HotelNotFoundException;

public interface HotelService {
	
	public List<Hotel> getAllHotels();

	public Hotel getHotelByHotelId(int hotelId) throws HotelNotFoundException;

	public Hotel addHotel(Hotel hotel) throws HotelAlreadyExistsException;

	public Hotel updateHotelById(Hotel hotel) throws HotelNotFoundException;

	public Hotel deleteHotelById(int hotelId) throws HotelNotFoundException;
	
	public List<Hotel> getHotelDetailsByCity(String city) throws HotelNotFoundException;

}
