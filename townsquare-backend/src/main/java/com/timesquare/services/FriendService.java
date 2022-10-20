package com.timesquare.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.timesquare.models.Friend;
import com.timesquare.models.FriendId;
import com.timesquare.repos.FriendRepository;

@Service
public class FriendService {

	@Autowired
	private FriendRepository friendRepo;
	
	public List<Friend> getAllFriends() {
		return friendRepo.findAll();
	}
	
	public List<Friend> getAllUserFriends(Long userId) {
		return friendRepo.findAll().stream()
					.filter((user) -> user.getUser().getUserId() == userId)
					.collect(Collectors.toList());
	}
	
	public Friend getFriendById(Long userId, Long friendId) throws Exception {
		return friendRepo.findById(new FriendId(userId, friendId)).orElseThrow(
				() -> new Exception("User with the id " + userId + ", does not"
						+ " have a friend with id " + friendId));
	}
	
	public String addFriend(Friend friend) {
		friendRepo.save(friend);
		return friend.getFriend().getFirstName() + " " + friend.getFriend().getLastName()
				+ " is now your friend!";
	}
	
	public String updateFriend(Friend friend) {
		if(friendRepo.findById(friend.getFriendId()).isPresent()) {
			friendRepo.save(friend);
			return "Your friend, " + 
			friend.getUser().getFirstName() + " " + friend.getUser().getLastName()
			+ " has been updated.";
		}
		return "No friend found with the name " +
			friend.getUser().getFirstName() + " " + friend.getUser().getLastName();

	}
	
	public String removeFriend(Long userId, Long friendId) {
		if(friendRepo.findById(new FriendId(userId, friendId)).isPresent()) {
			friendRepo.deleteById(new FriendId(userId, friendId));
			return "Your friend has been removed.";
		}
		return "No friend exists with id " + friendId;
	}
}
