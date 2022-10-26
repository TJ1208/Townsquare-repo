package com.timesquare.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.sql.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.timesquare.models.FriendId;
import com.timesquare.models.Post;
import com.timesquare.models.RequestId;
import com.timesquare.models.User;


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
	void testAddressDTOHashCode() {
		AddressDTO address = new AddressDTO(2L, "Willow Springs", "NC", "1543 Middle Ridge Dr.", "27545",
				"USA", null, user);
		AddressDTO newAddressDTO = new AddressDTO(2L, "Fuquay-Varina", "NC", "1543 Middle Ridge Dr.", "27545",
				"USA", null, user);
		assertEquals(address.hashCode(), address.hashCode());
		assertNotEquals(newAddressDTO.hashCode(), address.hashCode());
	}
	
	@Test
	void testCommentDTOHashCode() {
		Post post = new Post(2L, "Not such a good place...", "It just stinked all the time :(", 1L, 2L, 3L, "http", user, null);
		CommentDTO comment = new CommentDTO(2L, "Wish I was there!!", new Date(110), 64L, 4L, post, user);
		CommentDTO newCommentDTO = new CommentDTO(2L, "What a world we live in!", new Date(110), 64L, 4L, post, user);
		assertEquals(comment.hashCode(), comment.hashCode());
		assertNotEquals(newCommentDTO.hashCode(), comment.hashCode());
	}
	
	@Test
	void testContactHashCode() {
		ContactDTO contact = new ContactDTO(2L, "123-456-7890", "098-765-4321", "919-339-3801", user);
		ContactDTO  newContact = new ContactDTO(3L, "123-456-7890", "098-765-4321", "919-339-3801", user);
		assertEquals(contact.hashCode(), contact.hashCode());
		assertNotEquals(newContact.hashCode(), contact.hashCode());
	}
	
	@Test
	void testEducationHashCode() {
		EducationDTO  education = new EducationDTO(2L, false, true, new Date(55), new Date(65), "Graduated with a High School Diploma", 
				"Fuquay-Varina Senior High School", "High School Diploma", user);
		EducationDTO  newEducation = new EducationDTO(2L, true, true, new Date(55), new Date(65), "Graduated with a High School Diploma", 
				"Fuquay-Varina Senior High School", "High School Diploma", user);
		assertEquals(education.hashCode(), education.hashCode());
		assertNotEquals(newEducation.hashCode(), education.hashCode());
	}
	
	@Test
	void testFriendHashCode() {
		FriendDTO friend = new FriendDTO(new FriendId(3L, 3L), user, user);
		FriendDTO newFriend = new FriendDTO(new FriendId(3L, 2L), user, user);
		assertEquals(friend.hashCode(), friend.hashCode());
		assertNotEquals(newFriend.hashCode(), friend.hashCode());
	}
	
	@Test
	void testImageHashCode() {
		ImageDTO image = new ImageDTO(2L, "http", new java.sql.Date(55), user);
		ImageDTO newImage = new ImageDTO(2L, "https", new java.sql.Date(55), user);
		assertEquals(image.hashCode(), image.hashCode());
		assertNotEquals(newImage.hashCode(), image.hashCode());
	}
	
	@Test
	void testPostDTOHashCode() {
		PostDTO post = new PostDTO(2L, "Not such a good place...", "It just stinked all the time :(", 1L, 2L, 3L, "http", user, null);
		PostDTO newPostDTO = new PostDTO(2L, "A very good place!", "It just stinked all the time :(", 1L, 2L, 3L, "http", user, null);
		assertEquals(post.hashCode(), post.hashCode());
		assertNotEquals(newPostDTO.hashCode(), post.hashCode());
	}
	
	@Test
	void testRequestHashCode() {
		RequestDTO request = new RequestDTO(new RequestId(3, 2), user, user);
		RequestDTO newRequest = new RequestDTO(new RequestId(3, 3), user, user);
		assertEquals(request.hashCode(), request.hashCode());
		assertNotEquals(newRequest.hashCode(), request.hashCode());
	}
	
	@Test
	void testUserHashCode() {
		UserDTO userDTO = new UserDTO(3L, "John", "Doe", "Janice", "67890", "http",
				"Janice@example.com", "Family, friends, and football!", "http",
				new Date(65), "Oxford, NC",
				"Raleigh, NC", null);
		UserDTO newUserDTO = new UserDTO(2L, "Janice", "Doe", "Janice", "67890", "http",
				"Janice@example.com", "Family, friends, and football!", "http",
				new Date(65), "Oxford, NC",
				"Raleigh, NC", null);
		assertEquals(userDTO.hashCode(), userDTO.hashCode());
		assertNotEquals(newUserDTO.hashCode(), userDTO.hashCode());
	}
	
	@Test
	void testWorkHashCode() {
		WorkDTO workplace = new WorkDTO(2L, "Foodlion", "Grocery Manager", "Fuquay-Varina", null, null, null, user);
		WorkDTO newWorkplace = new WorkDTO(2L, "Harris Teeter", "Grocery Manager", "Fuquay-Varina", null, null, null, user);
		assertEquals(workplace.hashCode(), workplace.hashCode());
		assertNotEquals(newWorkplace.hashCode(), workplace.hashCode());
	}
	
}
