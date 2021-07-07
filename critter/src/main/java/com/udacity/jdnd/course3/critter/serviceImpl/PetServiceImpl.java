package com.udacity.jdnd.course3.critter.serviceImpl;

import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.repository.PetRepo;
import com.udacity.jdnd.course3.critter.service.CustomerService;
import com.udacity.jdnd.course3.critter.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional

public class PetServiceImpl implements PetService {

    @Autowired
    PetRepo petRepo;

    @Autowired
    CustomerService customerService;

    @Override
    public Pet savePet(Pet pet, Long ownerId) {
        System.out.println("pet object is " + pet.toString());
        Customer customer = customerService.findById(ownerId);
        System.out.println("customer is " + customer);
        if (customer != null) {
            pet.setCustomer(customer);
        }

        Pet newPet = petRepo.save(pet);
        System.out.println("saved pet is" + newPet);
        return newPet;

    }

    @Override
    public Pet findById(Long id) {
        Optional<Pet> employeeOptional = petRepo.findById(id);
        return employeeOptional.orElse(null);
    }

    @Override
    public List<Pet> getAllPets() {
        //  List<Pet> pets = (List<Pet>) petRepo.findAll();
        return petRepo.findAll();
    }

    @Override
    public List<Pet> findByOwner(Long ownerId) {
        List<Pet> petlist = petRepo.findPetsByCustomerId(ownerId);
        return petlist;
    }

    @Override
    public Customer getPetByCustomer(Long petId) {
        if (findById(petId) != null) {
            Customer customer = findById(petId).getCustomer();
            return customer;
        }
        return null;
    }
}
