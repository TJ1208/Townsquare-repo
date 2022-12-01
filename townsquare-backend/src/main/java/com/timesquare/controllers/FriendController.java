package com.timesquare.controllers;

import java.text.ParseException;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.timesquare.dto.FriendDTO;
import com.timesquare.models.Friend;
import com.timesquare.services.FriendService;

@RestController
@RequestMapping("/api/friend")
public class FriendController {
	
	@Autowired
	private FriendService friendService;
	
	@Autowired
	private ModelMapper modelMapper;

	@GetMapping()
	public List<Friend> getAllFriends() {
		return friendService.getAllFriends();
	}
	
	@GetMapping("/{userId}")
	public List<Friend> getAllUserFriends(@PathVariable Long userId) {
		return friendService.getAllUserFriends(userId);
	}
	
	@GetMapping("/{userId}/{friendId}")
	public Friend getFriendById(@PathVariable Long userId,
			@PathVariable Long friendId) throws Exception {
		return friendService.getFriendById(userId, friendId);
	}
	
	@PostMapping("/add")
	@ResponseStatus(HttpStatus.CREATED)
	public void addFriend(@RequestBody FriendDTO friendDTO) throws ParseException {
		Friend friend = dtoToEntity(friendDTO);
		friendService.addFriend(friend);
	}
	
	@PutMapping("/update")
	public String updateFriend(@RequestBody FriendDTO friendDTO) throws ParseException {
		Friend friend = dtoToEntity(friendDTO);
		return friendService.updateFriend(friend);
	}
	
	@DeleteMapping("/delete/{userId}/{friendId}")
	public void removeFriend(@PathVariable Long userId,
			@PathVariable Long friendId) {
		friendService.removeFriend(userId, friendId);
	}
	
	private Friend dtoToEntity(FriendDTO friendDTO) throws ParseException {
		Friend friend = modelMapper.map(friendDTO, Friend.class);
		return friend;
	}
	
}
