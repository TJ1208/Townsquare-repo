package com.timesquare.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.timesquare.models.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

}
