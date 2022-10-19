package com.timesquare.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "address")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "addressId")
	private long addressId;
	
	@Column(name = "city", nullable = false)
	private String city;
	
	@Column(name = "state", nullable = false)
	private String state;
	
	@Column(name = "street", nullable = false)
	private String street;
	
	@Column(name = "zipcode", nullable = false)
	private String zipcode;
	
	@Column(name = "country", nullable = false)
	private String country;
	
	@Column(name = "apartmentNum")
	private String apartmentNum;
	
	@ManyToOne()
	@JoinColumn(name = "userId", nullable = false)
	private User user;
	

}
