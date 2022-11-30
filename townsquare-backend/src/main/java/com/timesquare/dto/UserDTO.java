package com.timesquare.dto;

import java.util.List;

import javax.persistence.Lob;

import com.timesquare.models.Address;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

	private long userId;
	private String firstName;
	private String lastName;
	private String username;
	@Lob
	private String password;
	@Lob
	private String profileImg;
	private String email;
	@Lob
	private String profileBio;
	@Lob
	private String backgroundImg;
	private java.sql.Date birthDate;
	private String birthplace;
	private String homeTown;
	private List<Address> addresses;
	
}
