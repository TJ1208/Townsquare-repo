package com.timesquare.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.sql.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
		Address address = new Address(2L, "Willow Springs", "NC", "1543 Middle Ridge Dr.", "27545",
				"USA", null, user);
		Address newAddress = new Address(2L, "Willow Springs", "NC", "1543 Middle Ridge Dr.", "27545",
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
		Comment comment = new Comment(2L, "Wish I was there!!", new Date(110), 64L, 4L, post, user);
		Comment newComment = new Comment(2L, "Wish I was there!!", new Date(110), 64L, 4L, post, user);
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
		Contact contact = new Contact(2L, "123-456-7890", "098-765-4321", "919-339-3801", user);
		Contact newContact = new Contact(2L, "123-456-7890", "098-765-4321", "919-339-3801", user);
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
		Education education = new Education(2L, true, true, new Date(55), new Date(65), "Graduated with a High School Diploma", 
				"Fuquay-Varina Senior High School", "High School Diploma", user);
		Education newEducation = new Education(2L, true, true, new Date(55), new Date(65), "Graduated with a High School Diploma", 
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
		Friend friend = new Friend(new FriendId(3L, 3L), user, user, false);
		Friend newFriend = new Friend(new FriendId(3L, 3L), user, user, false);
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
		Image image = new Image(2L, "http", new java.sql.Date(55), user);
		Image newImage = new Image(2L, "http", new java.sql.Date(55), user);
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
		Post post = new Post(2L, "Not such a good place...", "It just stinked all the time :(", 1L, 2L, 3L, "http", null, user, null);
		Post newPost = new Post(2L, "Not such a good place...", "It just stinked all the time :(", 1L, 2L, 3L, "http", null, user, null);
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
		Request request = new Request(new RequestId(3, 2), user, user);
		Request newRequest = new Request(new RequestId(3, 2), user, user);
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
		User newUser = new User(3, "Taylor", "Joostema", "TaylorJ", "12345", "http",
				"TaylorJ@example.com", "What a beautiful world!", "http",
				new Date(55), "Raleigh, NC",
				"Cary, NC", null, null, null, null, null, null, null,
				null, null, null);
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
		Work workplace = new Work(2L, "Foodlion", "Grocery Manager", "Fuquay-Varina", null, null, null, user);
		Work newWorkplace = new Work(2L, "Foodlion", "Grocery Manager", "Fuquay-Varina", null, null, null, user);
		boolean equals = workplace.equals(newWorkplace);
		assertEquals(true, equals);
		assertNotEquals(false, equals);
		assertNotSame(workplace, newWorkplace);
		workplace = newWorkplace;
		assertSame(workplace, newWorkplace);
		assertNotEquals(workplace, workplace.toString());
	}
	
}
