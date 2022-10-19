package com.timesquare.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FriendId implements Serializable {

	@Column(name = "userId")
	private Long userId;
	
	@Column(name = "friendId")
	private Long friendId;
}
