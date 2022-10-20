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
public class RequestId implements Serializable {
	
	@Column(name = "receiverId")
	private long receiverId;

	@Column(name = "requesterId")
	private long requesterId;
	
	
}
