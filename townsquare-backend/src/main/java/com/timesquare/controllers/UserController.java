package com.timesquare.controllers;

import java.text.ParseException;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.timesquare.dto.UserDTO;
import com.timesquare.models.User;
import com.timesquare.services.UserService;
	
@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping()
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}
	
	@GetMapping("/{userId}")
	public User getUserById(@PathVariable Long userId) 
			throws Exception {
		return userService.getUserById(userId);
	}
	
	@GetMapping("/name/{username}")
	public List<User> getUsersByUsername(@PathVariable String username)
			throws Exception {
		return userService.getUserByUsername(username);
	}
	
	@GetMapping("/username/{username}")
	public User findByUsername(@PathVariable String username) throws Exception {
		return userService.findByUsername(username);
	}
	
	@PostMapping("/register")
	@ResponseStatus(HttpStatus.CREATED)
	public void registerUser(@RequestBody UserDTO userDTO) throws ParseException {
		User user = dtoToEntity(userDTO);
		userService.registerUser(user);
	}
	
	@PutMapping("/update")
	public void updateUser(@RequestBody UserDTO userDTO) throws ParseException {
		User user = dtoToEntity(userDTO);
		userService.updateUser(user);
	}
	
	@DeleteMapping("/delete/{userId}")
	public void deleteUser(@PathVariable Long userId) {
		userService.deleteUserById(userId);
	}
	
	private User dtoToEntity(UserDTO userDTO) throws ParseException {
		User user = modelMapper.map(userDTO, User.class);
		return user;
	}

}
