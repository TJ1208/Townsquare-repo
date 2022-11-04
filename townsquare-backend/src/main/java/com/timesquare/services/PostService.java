package com.timesquare.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.timesquare.models.Post;
import com.timesquare.repos.PostRepository;

@Service
public class PostService {
	
	@Autowired
	private PostRepository postRepo;
	
	public List<Post> getAllPosts() {
		return postRepo.findAll();
	}
	
	public List<Post> getAllUserPosts(Long userId) {
		return postRepo.findAll().stream()
					.filter((post) -> post.getUser().getUserId() == userId)
					.collect(Collectors.toList());
	}
	
	public Post getPostById(Long postId) throws Exception {
		return postRepo.findById(postId).orElseThrow(
				() -> new Exception("Post not found with id " + postId));
	}
	
	public void addPost(Post post) {
		postRepo.save(post);
	}
	
	public String updatePost(Post post) {
		if (postRepo.findById(post.getPostId()).isPresent()) {
			postRepo.save(post);
			return post.getUser().getFirstName() + " " + post.getUser().getLastName()
					+ "'s post has been updated.";
		}
		return "No Post found with the id " + post.getPostId();
	}
	
	public String deletePost(Long postId) {
		if (postRepo.findById(postId).isPresent()) {
			postRepo.deleteById(postId);
			return "Post deleted with id " + postId;
		}
		return "No Post found with id " + postId;
	}

}
