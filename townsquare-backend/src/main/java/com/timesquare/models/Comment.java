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
@Table(name = "comment")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "commentId")
	private long commentId;
	
	@Column(name = "comment", nullable = false)
	@Lob
	private String comment;
	
	@Column(name = "commentDate", nullable = false)
	private java.sql.Date commentDate;
	
	@Column(name = "likes")
	private long likes;
	
	@Column(name = "dislikes")
	private long dislikes;
	
	@ManyToOne
	@JoinColumn(name = "postId", nullable = false)
	private Post post;
	
	@ManyToOne
	@JoinColumn(name = "userId", nullable = false)
	private User user;

}
