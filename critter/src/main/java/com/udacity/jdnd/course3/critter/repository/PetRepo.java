package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// declaring pet repo to initialize and retrieve tables from customer

@Repository
public interface PetRepo extends JpaRepository<Pet, Long> {

    List<Pet> findPetsByCustomerId(Long ownerId);
}
