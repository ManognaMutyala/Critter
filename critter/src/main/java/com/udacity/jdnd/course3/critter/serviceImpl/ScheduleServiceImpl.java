package com.udacity.jdnd.course3.critter.serviceImpl;

import com.udacity.jdnd.course3.critter.entity.Employees;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.entity.Schedule;
import com.udacity.jdnd.course3.critter.repository.ScheduleRepo;
import com.udacity.jdnd.course3.critter.schedule.ScheduleDTO;
import com.udacity.jdnd.course3.critter.service.EmployeeService;
import com.udacity.jdnd.course3.critter.service.PetService;
import com.udacity.jdnd.course3.critter.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional

public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    ScheduleRepo scheduleRepo;

    @Autowired
    PetService petService;

    @Autowired
    EmployeeService employeeService;


    @Override
    public Schedule addSchedule(ScheduleDTO scheduleDTO) {


        List<Pet> petsList = new ArrayList<>();
        List<Employees> employeeList = new ArrayList<>();
        List<Pet> pets = petService.getAllPets();
        Schedule schedule = new Schedule();
        List<Employees> allEmployees = employeeService.getAllEmployees();
        for (Pet pet : pets) {
            if (scheduleDTO.getPetIds().contains(pet.getId()))
                petsList.add(pet);

        }

        for (Employees emp : allEmployees) {
            if (scheduleDTO.getEmployeeIds().contains(emp.getId()))
                employeeList.add(emp);
        }

        schedule.setEmployeesList(employeeList);
        schedule.setPetList(petsList);
        schedule.setActivities(scheduleDTO.getActivities());
        schedule.setDatelocal(scheduleDTO.getDate());
        schedule.setId(scheduleDTO.getId());
        return scheduleRepo.save(schedule);

    }

    @Override
    public List<Schedule> getAllSchedules() {
        return (scheduleRepo.findAll());
    }

    @Override
    public List<Schedule> getScheduleforPet(Long id) {
        List<Schedule> scheduleList = getAllSchedules();
        List<Schedule> petScheduleList = new ArrayList<>();
        Pet pet = petService.findById(id);
        for (Schedule s : scheduleList) {
            if (s.getPetList().contains(pet))
                petScheduleList.add(s);
        }
        return petScheduleList;
    }

    @Override
    public List<Schedule> getScheduleforEmployee(Long id) {
        List<Schedule> scheduleList = getAllSchedules();
        List<Schedule> employeeScheduleList = new ArrayList<>();
        Employees employee = employeeService.findById(id);
        for (Schedule s : scheduleList) {
            if (s.getEmployeesList().contains(employee))
                employeeScheduleList.add(s);
        }
        return employeeScheduleList;
    }

    @Override
    public List<Schedule> getScheduleforCustomer(Long id) {
        List<Schedule> customerScheduleList = new ArrayList<>();
        List<Pet> petList = petService.findByOwner(id);
        for (Pet p : petList) {
            customerScheduleList.addAll(getScheduleforPet(p.getId()));
        }
        return customerScheduleList;
    }

}
