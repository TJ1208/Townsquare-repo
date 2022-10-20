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
@Table(name = "contact")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Contact {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "contactId")
	private long contactId;
	
	@Column(name = "businessNum")
	private String businessNum;
	
	@Column(name = "mobileNum")
	private String mobileNum;
	
	@Column(name = "phoneNum")
	private String phoneNum;
	
	@ManyToOne
	@JoinColumn(name = "userId")
	private User user;
}
