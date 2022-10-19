package com.timesquare.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.timesquare.models.Friend;
import com.timesquare.services.FriendService;

@RestController
@RequestMapping("/api/friend")
public class FriendController {
	
	@Autowired
	private FriendService friendService;

	@GetMapping("/{userId}")
	public List<Friend> getAllFriends(@PathVariable Long userId) {
		return friendService.getAllFriends(userId);
	}
	
	@GetMapping("/{userId}/{friendId}")
	public Friend getFriendById(@PathVariable Long userId,
			@PathVariable Long friendId) throws Exception {
		return friendService.getFriendById(userId, friendId);
	}
	
	@PostMapping("/add")
	public String addFriend(@RequestBody Friend friend) {
		return friendService.addFriend(friend);
	}
	
	@PutMapping("/update")
	public String updateFriend(@RequestBody Friend friend) {
		return friendService.updateFriend(friend);
	}
	
	@DeleteMapping("/delete/{userId}/{friendId}")
	public String removeFriend(@PathVariable Long userId,
			@PathVariable Long friendId) {
		return friendService.removeFriend(userId, friendId);
	}
}
