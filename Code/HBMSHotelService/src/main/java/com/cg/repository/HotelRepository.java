package com.cg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.entity.Hotel;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Integer> {
	
	Hotel findByHotelNameAndCityAndAddress(String hotelName, String city, String address);
	List<Hotel> findByCityIgnoreCase(String city);
}
