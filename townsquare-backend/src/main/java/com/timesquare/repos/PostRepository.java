package com.timesquare.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.timesquare.models.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

}
