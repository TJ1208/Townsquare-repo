package com.timesquare.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.timesquare.models.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

}
