package com.timesquare.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.timesquare.models.Friend;
import com.timesquare.models.FriendId;

@Repository
public interface FriendRepository extends JpaRepository<Friend, FriendId> {

}
