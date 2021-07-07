package com.udacity.jdnd.course3.critter.util;

import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Employees;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.entity.Schedule;
import com.udacity.jdnd.course3.critter.pet.PetDTO;
import com.udacity.jdnd.course3.critter.schedule.ScheduleDTO;
import com.udacity.jdnd.course3.critter.service.CustomerService;
import com.udacity.jdnd.course3.critter.service.PetService;
import com.udacity.jdnd.course3.critter.user.CustomerDTO;
import com.udacity.jdnd.course3.critter.user.EmployeeDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ControllerUtil {

    @Autowired
    CustomerService customerService;

    @Autowired
    PetService petService;


    public CustomerDTO convertCustomerEntitytoCustomerDTO(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        BeanUtils.copyProperties(customer, customerDTO);
        List<Pet> petsList = petService.findByOwner(customer.getId());
        if (petsList != null) {
            List<Long> petIds = new ArrayList<>();
            for (Pet pet : petsList) {
                petIds.add(pet.getId());
            }
            customerDTO.setPetIds(petIds);
        }
        return customerDTO;

    }

    public Customer convertCustomerDTOToCustomerEntity(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDTO, customer);
        return customer;
    }

    public EmployeeDTO convertEmployeeEntitytoDTO(Employees employees) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        BeanUtils.copyProperties(employees, employeeDTO);
        return employeeDTO;
    }

    public Employees convertEmployeeDTOtoEntity(EmployeeDTO employeeDTO) {
        Employees employees = new Employees();
        BeanUtils.copyProperties(employeeDTO, employees);
        return employees;
    }

    public PetDTO convertPetEntitytoPetDTO(Pet pet) {
        PetDTO petDTO = new PetDTO();
        BeanUtils.copyProperties(pet, petDTO);
        Customer customer = customerService.findById(pet.getCustomer().getId());

        if (customer != null) {
            petDTO.setOwnerId(customer.getId());
        }
        return petDTO;
    }

    public Pet convertPetDTOtoPetEntity(PetDTO petDTO) {
        Pet pet = new Pet();
        BeanUtils.copyProperties(petDTO, pet);
        return pet;
    }

    public ScheduleDTO convertScheduleEntityToDTO(Schedule schedule) {
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        BeanUtils.copyProperties(schedule, scheduleDTO);

        List<Long> employeeIds = new ArrayList<>();
        List<Long> petIds = new ArrayList<>();

        for (Employees employee : schedule.getEmployeesList()) {
            employeeIds.add(employee.getId());
        }

        for (Pet pet : schedule.getPetList()) {
            petIds.add(pet.getId());
        }

        scheduleDTO.setEmployeeIds(employeeIds);
        scheduleDTO.setPetIds(petIds);
        scheduleDTO.setDate(schedule.getDatelocal());
        scheduleDTO.setActivities(schedule.getActivities());
        scheduleDTO.setId(schedule.getId());
        return scheduleDTO;
    }


}
