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
@Table(name = "request")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Request {
	
	@EmbeddedId
	private RequestId requestId;
	
	@ManyToOne
	@MapsId("receiverId")
	private User receiver;
	
	@ManyToOne
	@MapsId("requesterId")
	private User requester;
	
	

}
