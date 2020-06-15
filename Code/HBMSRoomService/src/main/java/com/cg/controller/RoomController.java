package com.cg.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.entity.Room;
import com.cg.exception.RoomAlreadyExistException;
import com.cg.exception.RoomNotFoundException;
import com.cg.service.RoomService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RequestMapping("/room")
public class RoomController {

	@Autowired
	RoomService roomService;
	
	//http://localhost:9092/room/all
		@GetMapping("/all")
		public List<Room> getAllhotel(){
			return roomService.getAllRooms();
		}
		
		//http://localhost:9092/room/roomId/101
		@GetMapping("/roomId/{roomId}")
		@HystrixCommand(fallbackMethod = "whenRoomNotFoundByRoomId")
		public Room getRoomById(@Valid@PathVariable int roomId) throws RoomNotFoundException{
			Room room= roomService.getRoomByRoomId(roomId);
			return room;
		}
		
		//http://localhost:9092/room/add
		@PostMapping("/add")
		@HystrixCommand(fallbackMethod = "whenRoomNotAdd")
		public Room addRooms(@Valid@RequestBody Room room) throws RoomAlreadyExistException{
			Room roomDetails=roomService.addRoom(room);
			return roomDetails;
			
		}
		
		//http://localhost:9092/room/update
		@PutMapping("/update")
		@HystrixCommand(fallbackMethod = "whenRoomNotUpdate")
		public Room updateRoom(@Valid@RequestBody Room room)throws RoomNotFoundException {
			Room roomDetails = roomService.updateRoomById(room);
			return roomDetails;
		}
		
		//http://localhost:9092/room/delete/roomId/101
		@DeleteMapping("/delete/roomId/{roomId}")
		@HystrixCommand(fallbackMethod = "whenRoomNotFound")
		public Room deleteRoomById(@Valid@PathVariable int roomId )throws RoomNotFoundException{
			Room room = roomService.deleteRoomByRoomId(roomId);
			return room;
		}
		
		//http://localhost:9092/room/roomType/AC
		@GetMapping("/roomType/{roomType}")
		@HystrixCommand(fallbackMethod = "whenRoomTypeNotFound")
		public List<Room> getRoomsByRoomType(@Valid@PathVariable String roomType) throws RoomNotFoundException{
			List<Room> roomDetails= roomService.getRoomDetailsByRoomType(roomType);
			return roomDetails;
		}
		
		public Room whenRoomNotFoundByRoomId(int roomId) {
			return new Room();
		}
		
		public Room whenRoomNotAdd(Room room) {
			return new Room();
		}
		
		public List<Room> whenRoomTypeNotFound(String roomType) {
			return new ArrayList<>();
		}
		
		public Room whenRoomNotFound(int roomId) {
			return new Room();
		}
		
		public Room whenRoomNotUpdate(Room room) {
			return new Room();
		}
}
