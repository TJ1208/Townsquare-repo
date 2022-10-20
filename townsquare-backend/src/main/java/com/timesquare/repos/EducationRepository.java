package com.timesquare.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.timesquare.models.Education;

@Repository
public interface EducationRepository extends JpaRepository<Education, Long> {

}
