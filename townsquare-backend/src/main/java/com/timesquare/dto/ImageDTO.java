package com.timesquare.dto;

import com.timesquare.models.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageDTO {

	private long imageId;
	private String imageUrl;
	private java.sql.Date imageDate;
	private User user;
}
