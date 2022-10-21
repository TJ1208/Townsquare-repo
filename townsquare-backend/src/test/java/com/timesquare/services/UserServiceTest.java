package com.timesquare.services;

import static org.mockito.Mockito.verify;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.timesquare.models.Address;
import com.timesquare.models.Comment;
import com.timesquare.models.Contact;
import com.timesquare.models.Education;
import com.timesquare.models.Friend;
import com.timesquare.models.Image;
import com.timesquare.models.Post;
import com.timesquare.models.User;
import com.timesquare.models.Work;
import com.timesquare.repos.UserRepository;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
	
	@Mock
	private UserRepository userRepo;
	
	private UserService userService;
	
	private User user;
	
	@BeforeEach
	void setUp() {
		userService = new UserService(userRepo);
		List<Address> addresses = new ArrayList<>();
		List<Friend> friends = new ArrayList<>();
		List<Friend> users = new ArrayList<>();
		List<Image> images = new ArrayList<>();
		List<Contact> contacts = new ArrayList<>();
		List<Work> workplaces = new ArrayList<>();
		List<Education> educations = new ArrayList<>();
		List<Post> posts = new ArrayList<>();
		List<Comment> comments = new ArrayList<>();
		user = new User(1L, "John", "Doe", "JohnnyD", "12345", "http",
				"JohnDoe@example.com", "Living my best life!", "http",
				new Date(55), "Raleigh, NC",
				"Oxford, NC", addresses, users, friends, images, contacts,
				workplaces, educations, posts, comments);
	}
	
	@Test
	void getAllUsers() {
		userService.getAllUsers();
		verify(userRepo).findAll();
	}

}
