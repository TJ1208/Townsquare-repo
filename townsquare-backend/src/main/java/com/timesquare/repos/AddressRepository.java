package com.timesquare.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.timesquare.models.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

}
