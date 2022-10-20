package com.timesquare.dto;

import com.timesquare.models.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactDTO {

	private long contactId;
	private String businessNum;
	private String mobileNum;
	private String phoneNum;
	private User user;

}
