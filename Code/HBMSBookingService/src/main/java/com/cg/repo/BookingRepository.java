package com.cg.repo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.entity.Booking;
@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer>{

	Booking findByRoomIdAndHotelId(Integer roomId,Integer hotelId);
	List<Booking> findByHotelNameIgnoreCase(String hotelName);
	List<Booking> findByCheckInDate(LocalDate checkInDate);
}
