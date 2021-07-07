package com.udacity.jdnd.course3.critter.controller;

import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.pet.PetDTO;
import com.udacity.jdnd.course3.critter.service.PetService;
import com.udacity.jdnd.course3.critter.util.ControllerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {

    @Autowired
    PetService petService;

    @Autowired
    ControllerUtil controllerUtil;


    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {
        PetDTO savedPetDTO = controllerUtil.convertPetEntitytoPetDTO(petService.savePet(controllerUtil.convertPetDTOtoPetEntity(petDTO), petDTO.getOwnerId()));
        savedPetDTO.setOwnerId(petDTO.getOwnerId());
        return savedPetDTO;
    }

    //Additional method to save pet by provided ownerid in the request uri ,postman collection 2nd request
    @PostMapping("/{ownerId}")
    public PetDTO savePetById(@RequestBody PetDTO petDTO, @PathVariable long ownerId) {
        petDTO.setOwnerId(ownerId);
        PetDTO savedPetDTO = controllerUtil.convertPetEntitytoPetDTO(petService.savePet(controllerUtil.convertPetDTOtoPetEntity(petDTO), petDTO.getOwnerId()));
        return savedPetDTO;
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {
        return (controllerUtil.convertPetEntitytoPetDTO(petService.findById(petId)));
    }

    @GetMapping
    public List<PetDTO> getPets() {
        List<PetDTO> PetDTOList = new ArrayList<>();
        List<Pet> petList = petService.getAllPets();
        for (Pet pet : petList) {
            PetDTOList.add(controllerUtil.convertPetEntitytoPetDTO(pet));
        }
        return PetDTOList;
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
        List<PetDTO> PetDTOList = new ArrayList<>();
        List<Pet> petList = petService.findByOwner(ownerId);
        for (Pet pet : petList) {
            PetDTOList.add(controllerUtil.convertPetEntitytoPetDTO(pet));
        }
        return PetDTOList;
    }


}
