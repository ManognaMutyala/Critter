package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


// declaring customer repo to initialize and retrieve tables from customer
@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long> {


}
