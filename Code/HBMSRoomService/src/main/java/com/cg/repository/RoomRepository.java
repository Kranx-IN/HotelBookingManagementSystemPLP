package com.cg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.entity.Room;
@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {

	Room findByRoomIdAndHotelId(Integer roomId,Integer hotelId);
	List<Room> findByRoomTypeIgnoreCase(String roomType );
}
