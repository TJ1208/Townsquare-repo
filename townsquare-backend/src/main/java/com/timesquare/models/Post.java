package com.timesquare.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "post")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "postId")
	private long postId;
	
	@Column(name = "title", nullable = false)
	private String title;
	
	@Column(name = "description")
	@Lob
	private String description;
	
	@Column(name = "likes", nullable = false)
	private long likes;
	
	@Column(name = "dislikes", nullable = false)
	private long dislikes;
	
	@Column(name = "shares", nullable = false)
	private long shares;
	
	@Column(name = "imageUrl")
	@Lob
	private String imageUrl;
	
	@Column(name = "date")
	private java.sql.Date date;
	
	@ManyToOne
	@JoinColumn(name = "userId", nullable = false)
	private User user;
	
	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Comment> comments;

}
