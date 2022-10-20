package com.timesquare.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.timesquare.models.Comment;
import com.timesquare.repos.CommentRepository;

@Service
public class CommentService {
	
	@Autowired
	private CommentRepository commentRepo;
	
	public List<Comment> getAllComments() {
		return commentRepo.findAll();
	}
	
	public List<Comment> getAllUserComments(Long userId) {
		return commentRepo.findAll().stream()
					.filter((comment) -> comment.getUser().getUserId() == userId)
					.collect(Collectors.toList());
	}
	
	public Comment getCommentById(Long commentId) throws Exception {
		return commentRepo.findById(commentId).orElseThrow(
				() -> new Exception("Comment not found with id " + commentId));
	}
	
	public String addComment(Comment comment) {
		commentRepo.save(comment);
		return comment.getUser().getFirstName() + " " + comment.getUser().getLastName()
				+ " commented on your post!";
	}
	
	public String updateComment(Comment comment) {
		if (commentRepo.findById(comment.getCommentId()).isPresent()) {
			commentRepo.save(comment);
			return comment.getUser().getFirstName() + " " + comment.getUser().getLastName()
					+ "'s comment was edited.";
		}
		return "No comment found with the id " + comment.getCommentId();
	}
	
	public String deleteComment(Long commentId) {
		if (commentRepo.findById(commentId).isPresent()) {
			commentRepo.deleteById(commentId);
			return "Comment deleted with id " + commentId;
		}
		return "No comment found with id " + commentId;
	}

}
