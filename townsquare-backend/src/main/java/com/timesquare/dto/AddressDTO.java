package com.timesquare.dto;

import com.timesquare.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {

	private long addressId;
	private String city;
	private String state;
	private String street;
	private String zipcode;
	private String country;
	private String apartmentNum;
	private User user;
}
