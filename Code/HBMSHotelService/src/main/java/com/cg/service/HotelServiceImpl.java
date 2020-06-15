package com.cg.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.entity.Hotel;
import com.cg.exception.HotelAlreadyExistsException;
import com.cg.exception.HotelNotFoundException;
import com.cg.repository.HotelRepository;

@Service
public class HotelServiceImpl implements HotelService {
	
	Logger logger = LoggerFactory.getLogger(HotelServiceImpl.class);

	@Autowired
	private HotelRepository hotelRepo;

	@Override
	public List<Hotel> getAllHotels() {
		return hotelRepo.findAll();
	}

	@Override
	public Hotel getHotelByHotelId(int hotelId) throws HotelNotFoundException {
			return hotelRepo.findById(hotelId).orElseThrow(() -> new HotelNotFoundException());
	}

	@Override
	public Hotel addHotel(Hotel hotel) throws HotelAlreadyExistsException {
		
		if(!(hotelRepo.findByHotelNameAndCityAndAddress(hotel.getHotelName(), hotel.getCity(), hotel.getAddress())==null))
			throw new HotelAlreadyExistsException();
		else
			return hotelRepo.save(hotel);
	}

	@Override
	public Hotel updateHotelById(Hotel hotel) throws HotelNotFoundException {
		if (!hotelRepo.existsById(hotel.getHotelId()))
			throw new HotelNotFoundException();
		else
			return hotelRepo.save(hotel);
	}

	@Override
	public Hotel deleteHotelById(int hotelId) throws HotelNotFoundException {
		if (hotelRepo.existsById(hotelId)) {
			Hotel hotelDetails = hotelRepo.findById(hotelId).orElse(null);
			Hotel res = new Hotel(hotelDetails.getHotelId(),hotelDetails.getHotelName(), hotelDetails.getCity(),
					hotelDetails.getAddress(), hotelDetails.getAverageFare(), hotelDetails.getEmail(), hotelDetails.getPhoneNumber()
					,hotelDetails.getDesrciption());
			hotelRepo.deleteById(hotelId);
			return res;
		}else {
			throw new HotelNotFoundException();
		}
	}
	
	public List<Hotel>  getHotelDetailsByCity(String city) throws HotelNotFoundException{
		//Hotel hotel = null;
		if((hotelRepo.findByCityIgnoreCase(city).isEmpty())) {
			throw new HotelNotFoundException();
		}
		else {
			return hotelRepo.findByCityIgnoreCase(city);
		}
		//return hotelRepo.findByCityIgnoreCase(city);
		//return null;
	}
	
}
