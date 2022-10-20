package com.timesquare.dto;

import com.timesquare.models.Post;
import com.timesquare.models.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {
	
	private long commentId;
	private String comment;
	private java.sql.Date commentDate;
	private long likes;
	private long dislikes;
	private Post post;
	private User user;

}
