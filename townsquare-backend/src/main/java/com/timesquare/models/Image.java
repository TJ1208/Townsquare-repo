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
@Table(name = "image")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Image {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "imageId")
	private long imageId;
	
	@Column(name = "imageUrl", nullable = false)
	@Lob
	private String imageUrl;
	
	@Column(name = "imageDate", nullable = false)
	private java.sql.Date imageDate;
	
	@ManyToOne
	@JoinColumn(name = "userId")
	private User user;
}
