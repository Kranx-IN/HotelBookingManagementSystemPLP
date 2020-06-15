package com.cg.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.entity.Hotel;
import com.cg.exception.HotelAlreadyExistsException;
import com.cg.exception.HotelNotFoundException;
import com.cg.service.HotelService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RequestMapping("/hotel")
public class HotelController {

	Logger logger = LoggerFactory.getLogger(HotelController.class);
	
	@Autowired
	private HotelService hotelService;
	
	//http://hotel-service/hotel/all
	@GetMapping("/all")
	public List<Hotel> getAllHotels() {
		return hotelService.getAllHotels();
	}
	
	//http://localhost:9091/hotel/id/303
	@GetMapping("/id/{hotelId}")
	@HystrixCommand(fallbackMethod = "whenHotelNotFoundByHotelId")
	public Hotel getHotelById(@Valid@PathVariable int hotelId) throws HotelNotFoundException {
		Hotel hotelDetails = hotelService.getHotelByHotelId(hotelId);
		//logger.info("Hotel found in database : "+ hotelDetails);
		return hotelDetails;
	}
	//http://localhost:9091/hotel/add
	@PostMapping("/add")
	@HystrixCommand(fallbackMethod = "whenHotelNotAdd")
	public Hotel addHotel(@Valid@RequestBody Hotel hotel) throws HotelAlreadyExistsException {
		Hotel hotelDetails = hotelService.addHotel(hotel);
		//logger.info("Hotel added to database");
		return hotelDetails;
	}
	//http://localhost:9091/hotel/delete/id/302
	@DeleteMapping("/delete/id/{hotelId}")
	@HystrixCommand(fallbackMethod = "whenHotelNotFound")
	public Hotel deleteHotelById(@Valid@PathVariable int hotelId) throws HotelNotFoundException {
		Hotel hotelDetails = hotelService.deleteHotelById(hotelId);
		//logger.info("Hotel deleted from database : "+hotelDetails);
		return hotelDetails;
	}
	//http://localhost:9091/hotel/update
	@PutMapping("/update")
	@HystrixCommand(fallbackMethod = "whenHotelNotUpdate")
	public Hotel updateHotel(@Valid@RequestBody Hotel hotel) throws HotelNotFoundException {
		Hotel hotelDetails = hotelService.updateHotelById(hotel);
		
		return hotelDetails;
	}
	
	//http://localhost:9091/hotel/city/pune
	@GetMapping("/city/{city}")
	@HystrixCommand(fallbackMethod = "whenCityNotFound")
	public List<Hotel> getHotelByCity(@Valid@PathVariable String city) throws HotelNotFoundException {
		List<Hotel> hotelDetails = hotelService.getHotelDetailsByCity(city);
		//logger.info("Hotel found in database : "+ hotelDetails);
		return hotelDetails;
	}
	
	
	public List<Hotel> whenCityNotFound(String city) {
		return new ArrayList<>();
	}
		
	public Hotel whenHotelNotAdd(Hotel hotel) {
		return new Hotel();	
	}		
	public Hotel whenHotelNotUpdate(Hotel hotel) {
		return new Hotel();
	}
	public Hotel whenHotelNotFoundByHotelId(int hotelId) {
		return new Hotel();
	}	
	public Boolean whenHotelNotFound(int hotelId) {
		return false;
	}
	
}
