package com.timesquare.repos;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import com.timesquare.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	@Query("SELECT u FROM User u WHERE CONCAT_WS(' ', firstName, lastName) LIKE %:username%")
	public Optional<List<User>> getUsersByName(@PathVariable String username);
}
