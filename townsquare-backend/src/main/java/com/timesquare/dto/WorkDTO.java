package com.timesquare.dto;

import com.timesquare.models.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkDTO {

	private long workplaceId;
	private String company;
	private String position;
	private String city;
	private String description;
	private java.sql.Date startDate;
	private java.sql.Date endDate;
	private User user;

}
