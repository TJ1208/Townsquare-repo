package com.timesquare.controllers;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.timesquare.dto.CommentDTO;
import com.timesquare.models.Comment;
import com.timesquare.services.CommentService;

@RestController
@RequestMapping("/api/comment")
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping()
	public List<Comment> getAllComments() {
		return commentService.getAllComments();
	}
	
	@GetMapping("/{userId}")
	public List<Comment> getAllUserComments(@PathVariable Long userId) {
		return commentService.getAllUserComments(userId);
	}
	
	@GetMapping("/id/{commentId}")
	public Comment getCommentById(@PathVariable Long commentId) throws Exception {
		return commentService.getCommentById(commentId);
	}
	
	@PostMapping("/add")
	public String addComment(@RequestBody CommentDTO commentDTO) {
		Comment comment = dtoToEntity(commentDTO);
		return commentService.addComment(comment);
	}
	
	@PutMapping("/update")
	public String updateComment(@RequestBody CommentDTO commentDTO) {
		Comment comment =dtoToEntity(commentDTO);
		return commentService.updateComment(comment);
	}
	
	@DeleteMapping("/delete/{commentId}")
	public String deleteComment(@PathVariable Long commentId) {
		return commentService.deleteComment(commentId);
	}

	private Comment dtoToEntity(CommentDTO commentDTO) {
		Comment comment = modelMapper.map(commentDTO, Comment.class);
		return comment;
	}
}
