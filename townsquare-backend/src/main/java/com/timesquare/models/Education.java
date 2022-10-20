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
@Table(name = "education")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Education {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "educationId")
	private long educationId; 
	
	@Column(name = "educationType", nullable = false)
	private boolean educationType;
	
	@Column(name = "isGraduated", nullable = false)
	private boolean isGraduated;
	
	@Column(name = "startDate")
	private java.sql.Date startDate;

	@Column(name = "endDate")
	private java.sql.Date endDate;
	
	@Column(name = "description")
	@Lob
	private String description;
	
	@Column(name = "school", nullable = false)
	private String school;
	
	@Column(name = "degree", nullable = false)
	private String degree;
	
	@ManyToOne
	@JoinColumn(name = "userId", nullable = false)
	private User user;
	
}
