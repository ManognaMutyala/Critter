package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Pet;

import java.util.List;

public interface PetService {
    public Pet savePet(Pet pet, Long ownerId);

    Pet findById(Long id);

    public List<Pet> getAllPets();

    List<Pet> findByOwner(Long id);


    Customer getPetByCustomer(Long petId);
}
