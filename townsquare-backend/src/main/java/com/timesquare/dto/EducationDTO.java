package com.timesquare.dto;

import com.timesquare.models.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EducationDTO {

	private long educationId;
	private boolean educationType;
	private boolean isGraduated;
	private java.sql.Date startDate;
	private java.sql.Date endDate;
	private String description;
	private String school;
	private String degree;
	private User user;
}
