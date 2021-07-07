package com.udacity.jdnd.course3.critter.controller;

import com.udacity.jdnd.course3.critter.entity.Schedule;
import com.udacity.jdnd.course3.critter.schedule.ScheduleDTO;
import com.udacity.jdnd.course3.critter.service.ScheduleService;
import com.udacity.jdnd.course3.critter.util.ControllerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    ScheduleService scheduleService;

    @Autowired
    ControllerUtil controllerUtil;

    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        return (controllerUtil.convertScheduleEntityToDTO(scheduleService.addSchedule(scheduleDTO)));
    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
        List<Schedule> scheduleList = scheduleService.getAllSchedules();
        List<ScheduleDTO> scheduleDTOList = new ArrayList<>();
        for (Schedule schedule : scheduleList) {
            scheduleDTOList.add(controllerUtil.convertScheduleEntityToDTO(schedule));
        }
        System.out.println("schedule dto in controller is" + scheduleDTOList.toString());
        return scheduleDTOList;
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
        List<Schedule> scheduleList = scheduleService.getScheduleforPet(petId);
        List<ScheduleDTO> scheduleDTOList = new ArrayList<>();
        for (Schedule s : scheduleList) {
            scheduleDTOList.add(controllerUtil.convertScheduleEntityToDTO(s));
        }
        return scheduleDTOList;
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        List<Schedule> scheduleList = scheduleService.getScheduleforEmployee(employeeId);
        List<ScheduleDTO> scheduleDTOList = new ArrayList<>();
        for (Schedule s : scheduleList) {
            scheduleDTOList.add(controllerUtil.convertScheduleEntityToDTO(s));
        }
        return scheduleDTOList;
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        List<Schedule> scheduleList = scheduleService.getScheduleforCustomer(customerId);
        List<ScheduleDTO> scheduleDTOList = new ArrayList<>();
        for (Schedule s : scheduleList) {
            scheduleDTOList.add(controllerUtil.convertScheduleEntityToDTO(s));
        }
        return scheduleDTOList;
    }

}
