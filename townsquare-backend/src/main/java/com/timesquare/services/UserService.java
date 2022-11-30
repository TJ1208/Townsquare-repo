package com.timesquare.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.timesquare.models.User;
import com.timesquare.repos.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public UserService(UserRepository userRepo2) {
		this.userRepo = userRepo2;
	}

	public List<User> getAllUsers() {
		return userRepo.findAll();
	}
	
	public User getUserById(Long userId) throws Exception {
		return userRepo.findById(userId).orElseThrow(
				() -> new Exception("User not found with id: " + userId));
	}

	public List<User> getUserByUsername(String username) throws Exception {
		return userRepo.getUsersByName(username).orElseThrow(
				() -> new Exception("User not found with name: " + username));
	}
	
	public User findByUsername(String username) throws Exception {
		return userRepo.findByUsername(username);
	}
	
	public void registerUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepo.save(user);
//		return user.getFirstName() + " " + user.getLastName()
//			+ "'s acount is now registered!";
	}
	
	public void updateUser(User user) {
		if (userRepo.findById(user.getUserId()).isPresent()) {
			userRepo.save(user);
		}
	}
	
	public String deleteUserById(Long userId) {
		if (userRepo.findById(userId).isPresent()) {
			userRepo.deleteById(userId);
			return "User with id " + userId + " has been deleted.";
		}
		return "User with id " + userId + " does not exist.";
	}
}