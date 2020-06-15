package com.cg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.entity.Room;
import com.cg.exception.RoomAlreadyExistException;
import com.cg.exception.RoomNotFoundException;
import com.cg.repository.RoomRepository;

@Service
public class RoomServiceImpl  implements RoomService {

	@Autowired
	RoomRepository roomRepository;
	
	@Override
	public List<Room>getAllRooms(){
		return roomRepository.findAll();
	}
	
	@Override
	public Room getRoomByRoomId(int roomId )throws RoomNotFoundException{
		return roomRepository.findById(roomId).orElseThrow(() -> new RoomNotFoundException());
	}
	@Override
	public Room addRoom(Room room) throws RoomAlreadyExistException{
		if(!(roomRepository.findByRoomIdAndHotelId(room.getRoomId(),room.getHotelId())==null))
			throw new RoomAlreadyExistException();
		else
			return roomRepository.save(room);
	}
	@Override
	public Room updateRoomById(Room room) throws RoomNotFoundException{
		if (!roomRepository.existsById(room.getRoomId()))
			throw new RoomNotFoundException();
		else
			return roomRepository.save(room);
	}
	
	@Override
	public Room deleteRoomByRoomId(int roomId) throws RoomNotFoundException{
		if(roomRepository.existsById(roomId)) {
			Room roomDetails = roomRepository.findById(roomId).orElse(null);
			Room r=new Room(roomDetails.getRoomId(),roomDetails.getHotelId(),roomDetails.getRoomType(),roomDetails.getPerNightFare(),roomDetails.isAvailability());
			roomRepository.deleteById(roomId);
			return r;
		}
		else {
			throw new RoomNotFoundException();
		}
	}
	
	@Override
	public List<Room> getRoomDetailsByRoomType(String roomType) throws RoomNotFoundException{
		if(roomRepository.findByRoomTypeIgnoreCase(roomType).isEmpty()) {
			throw new RoomNotFoundException();
		}
		else {
			return roomRepository.findByRoomTypeIgnoreCase(roomType);
		}
	}
}
