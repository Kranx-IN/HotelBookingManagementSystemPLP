package com.cg.service;

import static org.junit.Assert.assertEquals;

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

import com.cg.entity.User;
import com.cg.exception.UserAlreadyExistsException;
import com.cg.exception.UserNotFoundException;
import com.cg.repo.UserRepository;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class MyUserDetailsServiceTest {

	@MockBean
	private UserRepository repo;
	
	@Autowired
	private MyUserDetailsService target;
	
	User user;
	List<User> userList;
	
	@Before
	public void setup(){
		user = new User(1,"sashank", "alaska1234", 7894561230l, "customer","sasha@gmail.com");
		userList = new ArrayList<>();
	}
	
	@Test
	public void testMyUserDetailsForAddUser_Success() throws UserAlreadyExistsException {
		Mockito.when(repo.findByUserName(user.getUserName())).thenReturn(null);
		assertEquals(user, target.addUser(user));
	}
	
	@Test(expected = UserAlreadyExistsException.class)
	public void testMyUserDetailsForAddUserThrowUserAlreadyExistsException_Fail() throws UserAlreadyExistsException {
		User u = new User(1,"sashank", "alaska1234", 7894561230l, "customer","sasha@gmail.com");
		Mockito.when(repo.findByUserName(u.getUserName())).thenReturn(Optional.of(user));
		target.addUser(u);
	}
	
	@Test
	public void testMyUserDetailsForUpdateUser_Success() throws UserNotFoundException {
		Mockito.when(repo.existsById(user.getUserId())).thenReturn(true);
		Mockito.when(repo.save(user)).thenReturn(user);
		assertEquals(user, target.updateUser(user));
	}

	@Test(expected = UserNotFoundException.class)
	public void testMyUserDetailsForUpdateUser_Fail() throws UserNotFoundException {
		Mockito.when(repo.existsById(user.getUserId())).thenReturn(false);
		target.updateUser(user);
	}
	
	@Test
	public void testMyUserDetailsForDeleteUser_Success() throws UserNotFoundException {
		Mockito.when(repo.existsById(user.getUserId())).thenReturn(true);
		assertEquals(user, target.updateUser(user));
	}

	@Test(expected = UserNotFoundException.class)
	public void testMyUserDetailsForDeleteUser_Fail() throws UserNotFoundException {
		Mockito.when(repo.existsById(user.getUserId())).thenReturn(false);
		target.updateUser(user);
	}
	
	@Test
	public void testMyUserForGetAllUsers_Success() {
		userList.add(user);
		Mockito.when(repo.findAll()).thenReturn(userList);
		assertEquals(userList, target.getAllUsers());
	}
	
	@Test
	public void testMyUserForGetAllUsers_Fail() {
		Mockito.when(repo.findAll()).thenReturn(userList);
		assertEquals(userList,target.getAllUsers());
	}
	
	@Test
	public void testMyUserForGetUserById_Success() throws UserNotFoundException {
		Mockito.when(repo.findById(user.getUserId())).thenReturn(Optional.of(user));
		assertEquals(user,target.getUserById(user.getUserId()));
	}
	
	@Test(expected = UserNotFoundException.class)
	public void testMyUserForGetUserById_Fail() throws UserNotFoundException {
		User u = new User();
		int id = 0;
		Mockito.when(repo.findById(id)).thenReturn(Optional.of(u));
		target.getUserById(user.getUserId());
	}
	
}
