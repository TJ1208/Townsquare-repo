package com.timesquare.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.sql.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.timesquare.models.FriendId;
import com.timesquare.models.Post;
import com.timesquare.models.RequestId;
import com.timesquare.models.User;

public class EqualsTest {
	
	private User user;

	@BeforeEach
	void setup() {
		user = new User(3, "Taylor", "Joostema", "TaylorJ", "12345", "http",
				"TaylorJ@example.com", "What a beautiful world!", "http",
				new Date(55), "Raleigh, NC",
				"Cary, NC", null, null, null, null, null, null, null,
				null, null, null);
	}

	@Test
	void testAddressEquals() {
		AddressDTO address = new AddressDTO(2L, "Willow Springs", "NC", "1543 Middle Ridge Dr.", "27545",
				"USA", null, user);
		AddressDTO newAddress = new AddressDTO(2L, "Willow Springs", "NC", "1543 Middle Ridge Dr.", "27545",
				"USA", null, user);
		boolean equals = address.equals(newAddress);
		assertEquals(true, equals);
		assertNotEquals(false, equals);
		assertNotSame(address, newAddress);
		address = newAddress;
		assertSame(address, newAddress);
		assertNotEquals(address, address.toString());

	}
	
	@Test
	void testCommentEquals() {
		Post post = new Post(2L, "Not such a good place...", "It just stinked all the time :(", 1L, 2L, 3L, "http", null, user, null);
		CommentDTO comment = new CommentDTO(2L, "Wish I was there!!", new Date(110), 64L, 4L, post, user);
		CommentDTO newComment = new CommentDTO(2L, "Wish I was there!!", new Date(110), 64L, 4L, post, user);
		boolean equals = comment.equals(newComment);
		assertEquals(true, equals);
		assertNotEquals(false, equals);
		assertNotSame(comment, newComment);
		comment = newComment;
		assertSame(comment, newComment);
		assertNotEquals(comment, comment.toString());

	}
	
	@Test
	void testContactEquals() {
		ContactDTO contact = new ContactDTO(2L, "123-456-7890", "098-765-4321", "919-339-3801", user);
		ContactDTO newContact = new ContactDTO(2L, "123-456-7890", "098-765-4321", "919-339-3801", user);
		boolean equals = contact.equals(newContact);
		assertEquals(true, equals);
		assertNotEquals(false, equals);
		assertNotSame(contact, newContact);
		contact = newContact;
		assertSame(contact, newContact);
		assertNotEquals(contact, contact.toString());

	}
	
	@Test
	void testEducationEquals() {
		EducationDTO education = new EducationDTO(2L, true, true, new Date(55), new Date(65), "Graduated with a High School Diploma", 
				"Fuquay-Varina Senior High School", "High School Diploma", user);
		EducationDTO newEducation = new EducationDTO(2L, true, true, new Date(55), new Date(65), "Graduated with a High School Diploma", 
				"Fuquay-Varina Senior High School", "High School Diploma", user);
		boolean equals = education.equals(newEducation);
		assertEquals(true, equals);
		assertNotEquals(false, equals);
		assertNotSame(education, newEducation);
		education = newEducation;
		assertSame(education, newEducation);
		assertNotEquals(education, education.toString());

	}
	
	@Test
	void testFriendEquals() {
		FriendDTO friend = new FriendDTO(new FriendId(3L, 3L), user, user);
		FriendDTO newFriend = new FriendDTO(new FriendId(3L, 3L), user, user);
		boolean equals = friend.equals(newFriend);
		assertEquals(true, equals);
		assertNotEquals(false, equals);
		assertNotSame(friend, newFriend);
		friend = newFriend;
		assertSame(friend, newFriend);
		assertNotEquals(friend, friend.toString());

	}
	
	@Test
	void testImageEquals() {
		ImageDTO image = new ImageDTO(2L, "http", new java.sql.Date(55), user);
		ImageDTO newImage = new ImageDTO(2L, "http", new java.sql.Date(55), user);
		boolean equals = image.equals(newImage);
		assertEquals(true, equals);
		assertNotEquals(false, equals);
		assertNotSame(image, newImage);
		image = newImage;
		assertSame(image, newImage);
		assertNotEquals(image, image.toString());

	}
	
	@Test
	void testPostEquals() {
		PostDTO post = new PostDTO(2L, "Not such a good place...", "It just stinked all the time :(", 1L, 2L, 3L, "http", null, user, null);
		PostDTO newPost = new PostDTO(2L, "Not such a good place...", "It just stinked all the time :(", 1L, 2L, 3L, "http", null, user, null);
		boolean equals = post.equals(newPost);
		assertEquals(true, equals);
		assertNotEquals(false, equals);
		assertNotSame(post, newPost);
		post = newPost;
		assertSame(post, newPost);
		assertNotEquals(post, post.toString());

	}
	
	@Test
	void testRequestEquals() {
		RequestDTO request = new RequestDTO(new RequestId(3, 2), user, user);
		RequestDTO newRequest = new RequestDTO(new RequestId(3, 2), user, user);
		boolean equals = request.equals(newRequest);
		assertEquals(true, equals);
		assertNotEquals(false, equals);
		assertNotSame(request, newRequest);
		request = newRequest;
		assertSame(request, newRequest);
		assertNotEquals(request, request.toString());

	}
	
	@Test
	void testUserEquals() {
		UserDTO user = new UserDTO(3, "Taylor", "Joostema", "TaylorJ", "12345", "http",
				"TaylorJ@example.com", "What a beautiful world!", "http",
				new Date(55), "Raleigh, NC",
				"Cary, NC", null);
		UserDTO newUser = new UserDTO(3, "Taylor", "Joostema", "TaylorJ", "12345", "http",
				"TaylorJ@example.com", "What a beautiful world!", "http",
				new Date(55), "Raleigh, NC",
				"Cary, NC", null);
		boolean equals = user.equals(newUser);
		assertEquals(true, equals);
		assertNotEquals(false, equals);
		assertNotSame(user, newUser);
		user = newUser;
		assertSame(user, newUser);
		assertNotEquals(user, user.toString());

	}
	
	@Test
	void testWorkEquals() {
		WorkDTO workplace = new WorkDTO(2L, "Foodlion", "Grocery Manager", "Fuquay-Varina", null, null, null, user);
		WorkDTO newWorkplace = new WorkDTO(2L, "Foodlion", "Grocery Manager", "Fuquay-Varina", null, null, null, user);
		boolean equals = workplace.equals(newWorkplace);
		assertEquals(true, equals);
		assertNotEquals(false, equals);
		assertNotSame(workplace, newWorkplace);
		workplace = newWorkplace;
		assertSame(workplace, newWorkplace);
		assertNotEquals(workplace, workplace.toString());
	}
	
}