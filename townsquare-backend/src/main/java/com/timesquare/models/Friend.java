package com.timesquare.models;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "friend")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Friend {
	
	@EmbeddedId
	private FriendId friendId;
	
	@ManyToOne
	@MapsId("userId")
	private User user;
	
	@ManyToOne
	@MapsId("friendId")
	private User friend;

}
