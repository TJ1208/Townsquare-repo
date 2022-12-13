package com.timesquare.dto;

import com.timesquare.models.FriendId;
import com.timesquare.models.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FriendDTO {
	
	private FriendId friendId;
	private User user;
	private User friend;
	private boolean relationship;
}
