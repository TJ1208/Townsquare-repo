package com.timesquare.dto;

import com.timesquare.models.RequestId;
import com.timesquare.models.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestDTO {
	
	private RequestId requestId;
	private User receiver;
	private User requester;

}
