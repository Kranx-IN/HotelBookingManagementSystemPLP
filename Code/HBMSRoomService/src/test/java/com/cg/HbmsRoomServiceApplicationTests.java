package com.cg;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cg.entity.Room;
import com.cg.exception.RoomAlreadyExistException;
import com.cg.exception.RoomNotFoundException;
import com.cg.repository.RoomRepository;
import com.cg.service.RoomService;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class HbmsRoomServiceApplicationTests {

	@MockBean
	private RoomRepository repo;
	
	@Autowired
	private RoomService roomService;
	
	Room room;
	List<Room> list;
	List<Room> emptyList;
	
	
	@Test
	public void contextLoads() {
	}
	
	@Before
	public void setUp() throws Exception{
		room= new Room(101, 301, "AC", 3000.0, true);
		list = new ArrayList<Room>();
		emptyList = new ArrayList<Room>();
		list.add(room);
	}
	@Test
	public void getAllRoomsShouldReturnListOfRooms(){
		Mockito.when(repo.findAll()).thenReturn(list);
		assertEquals(list, roomService.getAllRooms());
	}
	@Test
	public void testGetAllRoomForWithZeroValuesPresentFailure(){
		Mockito.when(repo.findAll()).thenReturn(new ArrayList<>());
		assertNotEquals(list, roomService.getAllRooms());
	}

	@Test
	public void getRoomByIdShouldReturnRoom() throws RoomNotFoundException{
		int roomId = 101;
		Optional<Room> opt = Optional.of(room);
		Mockito.when(repo.findById(roomId)).thenReturn(opt);
		assertEquals(room, roomService.getRoomByRoomId(roomId));
	}
	
	@Test(expected = RoomNotFoundException.class)
	public void getRoomByIdShouldThrowHotelNotFoundException() throws RoomNotFoundException {
		int roomId = 101;
		roomService.getRoomByRoomId(roomId);
	
	}
	
	@Test
	public void updateHotelByIdShouldReturnRoom() throws RoomNotFoundException{
		room= new Room(101, 301, "AC", 3000.0, true);
		Mockito.when(repo.existsById(room.getRoomId())).thenReturn(true);
		Mockito.when(repo.save(room)).thenReturn(room);
		assertEquals(room, roomService.updateRoomById(room));
	}
	
	
	@Test(expected = RoomNotFoundException.class)
	public void updateRoomByIdShouldThrowHotelNotFoundException() throws RoomNotFoundException{
		room= new Room(101, 301, "AC", 3000.0, true);
		Mockito.when(repo.existsById(room.getRoomId())).thenReturn(false);
		roomService.updateRoomById(room);
	}
	
	@Test
	public void deleteRoomShouldReturnRoom() throws RoomNotFoundException{
		int roomId = 101;
		Mockito.when(repo.existsById(roomId)).thenReturn(true);
		Mockito.when(repo.findById(roomId)).thenReturn(Optional.of(room));
		assertEquals(room, roomService.deleteRoomByRoomId(roomId));
	}
	
	@Test(expected = RoomNotFoundException.class)
	public void deleteRoomShouldThrowHotelNotFoundException() throws RoomNotFoundException{
		int roomId = 101;
		Mockito.when(repo.existsById(roomId)).thenReturn(false);
		 roomService.deleteRoomByRoomId(roomId);
	}
	
	@Test
	public void testAddRoomSuccess() throws RoomAlreadyExistException{
		room= new Room(101, 301, "AC", 3000.0, true);
		Mockito.when(repo.existsById(room.getRoomId())).thenReturn(false);
		Mockito.when(repo.save(room)).thenReturn(room);
		assertEquals(room, roomService.addRoom(room));
	}
	
	@Test(expected = RoomAlreadyExistException.class)
	public void testAddRoomShouldThrowRoomAlreadyExistException() throws RoomAlreadyExistException{
		Room roomNew = new Room(101, 301, "AC", 3000.0, true);
		Mockito.when(repo.findByRoomIdAndHotelId(roomNew.getRoomId(), roomNew.getHotelId())).thenReturn(roomNew);
		roomService.addRoom(roomNew);
	}
	@Test
	public void getRoomByCityShouldReturnHotelList() throws RoomNotFoundException{
		String roomType = "AC";
		Mockito.when(repo.findByRoomTypeIgnoreCase(roomType)).thenReturn(list);
		assertEquals(list,roomService.getRoomDetailsByRoomType(roomType));
		
	}
	
	@Test(expected = RoomNotFoundException.class)
	public void getRoomByCityShouldThrowHotelNotFoundException() throws RoomNotFoundException{
		String roomType = "AC";
		Mockito.when(repo.findByRoomTypeIgnoreCase(roomType)).thenReturn(emptyList);
		roomService.getRoomDetailsByRoomType(roomType);
	}
	
}
