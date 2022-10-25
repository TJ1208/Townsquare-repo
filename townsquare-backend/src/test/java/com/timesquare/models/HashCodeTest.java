package com.timesquare.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.sql.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class HashCodeTest {
	
	private User user;
	
	@BeforeEach
	void setup() {
		user = new User(3, "Taylor", "Joostema", "TaylorJ", "12345", "http",
				"TaylorJ@example.com", "What a beautiful world!", "http",
				new Date(55), "Raleigh, NC",
				"Cary, NC", null, null, null, null, null, null, null,
				null, null);
	}

	@Test
	void testAddressHashCode() {
		Address address = new Address(2L, "Willow Springs", "NC", "1543 Middle Ridge Dr.", "27545",
				"USA", null, user);
		Address newAddress = new Address(2L, "Fuquay-Varina", "NC", "1543 Middle Ridge Dr.", "27545",
				"USA", null, user);
		assertEquals(address.hashCode(), address.hashCode());
		assertNotEquals(newAddress.hashCode(), address.hashCode());
	}
	
	@Test
	void testCommentHashCode() {
		Post post = new Post(2L, "Not such a good place...", "It just stinked all the time :(", 1L, 2L, 3L, "http", user, null);
		Comment comment = new Comment(2L, "Wish I was there!!", new Date(110), 64L, 4L, post, user);
		Comment newComment = new Comment(2L, "What a world we live in!", new Date(110), 64L, 4L, post, user);
		assertEquals(comment.hashCode(), comment.hashCode());
		assertNotEquals(newComment.hashCode(), comment.hashCode());
	}
	
	@Test
	void testContactHashCode() {
		Contact contact = new Contact(2L, "123-456-7890", "098-765-4321", "919-339-3801", user);
		Contact newContact = new Contact(3L, "123-456-7890", "098-765-4321", "919-339-3801", user);
		assertEquals(contact.hashCode(), contact.hashCode());
		assertNotEquals(newContact.hashCode(), contact.hashCode());
	}
	
	@Test
	void testEducationHashCode() {
		Education education = new Education(2L, false, true, new Date(55), new Date(65), "Graduated with a High School Diploma", 
				"Fuquay-Varina Senior High School", "High School Diploma", user);
		Education newEducation = new Education(2L, true, true, new Date(55), new Date(65), "Graduated with a High School Diploma", 
				"Fuquay-Varina Senior High School", "High School Diploma", user);
		assertEquals(education.hashCode(), education.hashCode());
		assertNotEquals(newEducation.hashCode(), education.hashCode());
	}
	
	@Test
	void testFriendHashCode() {
		Friend friend = new Friend(new FriendId(3L, 3L), user, user);
		Friend newFriend = new Friend(new FriendId(3L, 2L), user, user);
		assertEquals(friend.hashCode(), friend.hashCode());
		assertNotEquals(newFriend.hashCode(), friend.hashCode());
	}
	
	@Test
	void testImageHashCode() {
		Image image = new Image(2L, "http", new java.sql.Date(55), user);
		Image newImage = new Image(2L, "https", new java.sql.Date(55), user);
		assertEquals(image.hashCode(), image.hashCode());
		assertNotEquals(newImage.hashCode(), image.hashCode());
	}
	
	@Test
	void testPostHashCode() {
		Post post = new Post(2L, "Not such a good place...", "It just stinked all the time :(", 1L, 2L, 3L, "http", user, null);
		Post newPost = new Post(2L, "A very good place!", "It just stinked all the time :(", 1L, 2L, 3L, "http", user, null);
		assertEquals(post.hashCode(), post.hashCode());
		assertNotEquals(newPost.hashCode(), post.hashCode());
	}
	
	@Test
	void testRequestHashCode() {
		Request request = new Request(new RequestId(3, 2), user, user);
		Request newRequest = new Request(new RequestId(3, 3), user, user);
		assertEquals(request.hashCode(), request.hashCode());
		assertNotEquals(newRequest.hashCode(), request.hashCode());
	}
	
	@Test
	void testUserHashCode() {
		User newUser = new User(2L, "Janice", "Doe", "Janice", "67890", "http",
				"Janice@example.com", "Family, friends, and football!", "http",
				new Date(65), "Oxford, NC",
				"Raleigh, NC", null, null, null, null, null, null, null,
				null, null);
		assertEquals(user.hashCode(), user.hashCode());
		assertNotEquals(newUser.hashCode(), user.hashCode());
	}
	
	@Test
	void testWorkHashCode() {
		Work workplace = new Work(2L, "Foodlion", "Grocery Manager", "Fuquay-Varina", null, null, null, user);
		Work newWorkplace = new Work(2L, "Harris Teeter", "Grocery Manager", "Fuquay-Varina", null, null, null, user);
		assertEquals(workplace.hashCode(), workplace.hashCode());
		assertNotEquals(newWorkplace.hashCode(), workplace.hashCode());
	}
	
}
