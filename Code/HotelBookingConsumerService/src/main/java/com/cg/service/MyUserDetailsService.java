package com.cg.service;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cg.entity.MyUserDetails;
import com.cg.entity.User;
import com.cg.exception.UserAlreadyExistsException;
import com.cg.exception.UserNotFoundException;
import com.cg.repo.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {

	Logger logger = LoggerFactory.getLogger(MyUserDetailsService.class);
	
	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUserName(username)
				.orElseThrow(() -> new UsernameNotFoundException(username + " not found"));
		logger.info(user.toString());
		return new MyUserDetails(user);
	}
	
	public User addUser(User user) throws UserAlreadyExistsException{
		if(userRepository.findByUserName(user.getUserName())==null) {
			userRepository.save(user);
			return user;
		}
		else
			throw new UserAlreadyExistsException();
	}
	
	public User updateUser(User user) throws UserNotFoundException {
		if(!userRepository.existsById(user.getUserId())) {
			throw new UserNotFoundException();
		}
		else {
			userRepository.save(user);
			return user;
		} 
	}
	
	public User deleteUserById(int id) throws UserNotFoundException{
		if(!userRepository.existsById(id)) {
			throw new UserNotFoundException();
		}
		else {
			User userInfo = userRepository.findById(id).get();
			User res = new User(userInfo.getUserName(), userInfo.getPassword(), userInfo.getPhoneNumber(), userInfo.getRole(), userInfo.getEmailId());
			userRepository.deleteById(id);
			return res;
		}
	}
	
	public User getUserById(int id) throws UserNotFoundException {
		return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException());
	}
	
	public List<User> getAllUsers(){
		return userRepository.findAll();
	}
	
	
}
