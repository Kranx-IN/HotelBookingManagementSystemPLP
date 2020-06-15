package com.cg.service;

import java.util.List;

import com.cg.entity.Room;
import com.cg.exception.RoomAlreadyExistException;
import com.cg.exception.RoomNotFoundException;

public interface RoomService {

	public List<Room>getAllRooms();
	
	public Room getRoomByRoomId(int roomId )throws RoomNotFoundException;
	
	public Room addRoom(Room room) throws RoomAlreadyExistException;
	
	public Room updateRoomById(Room room) throws RoomNotFoundException;
	
	public Room deleteRoomByRoomId(int roomId) throws RoomNotFoundException;
	
	public List<Room> getRoomDetailsByRoomType(String roomType) throws RoomNotFoundException;
	
}
