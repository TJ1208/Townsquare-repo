package com.timesquare.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "work")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Work {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "workplaceId")
	private long workplaceId;
	
	@Column(name = "company", nullable = false)
	private String company;
	
	@Column(name = "position", nullable = false)
	private String position;
	
	@Column(name = "city", nullable = false)
	private String city;
	
	@Column(name = "description")
	@Lob
	private String description;
	
	@Column(name = "startDate")
	private java.sql.Date startDate;
	
	@Column(name = "endDate")
	private java.sql.Date endDate;
	
	@ManyToOne
	@JoinColumn(name = "userId", nullable = false)
	private User user;
	
}
