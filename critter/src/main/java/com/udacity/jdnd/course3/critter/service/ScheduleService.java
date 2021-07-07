package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.Schedule;
import com.udacity.jdnd.course3.critter.schedule.ScheduleDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ScheduleService {
    Schedule addSchedule(ScheduleDTO scheduleDTO);

    List<Schedule> getAllSchedules();

    List<Schedule> getScheduleforPet(Long id);

    List<Schedule> getScheduleforEmployee(Long id);

    List<Schedule> getScheduleforCustomer(Long id);
}
