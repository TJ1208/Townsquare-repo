package com.timesquare.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.timesquare.models.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

}
