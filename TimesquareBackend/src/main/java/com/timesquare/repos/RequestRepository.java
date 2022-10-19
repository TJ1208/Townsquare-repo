package com.timesquare.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.timesquare.models.Request;
import com.timesquare.models.RequestId;

@Repository
public interface RequestRepository extends JpaRepository<Request, RequestId> {

}
