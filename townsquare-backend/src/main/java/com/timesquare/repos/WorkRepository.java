package com.timesquare.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.timesquare.models.Work;

@Repository
public interface WorkRepository extends JpaRepository<Work, Long> {

}
