package com.udacity.jdnd.course3.critter.service;


import com.udacity.jdnd.course3.critter.entity.Employees;
import com.udacity.jdnd.course3.critter.user.EmployeeRequestDTO;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;

@Repository
public interface EmployeeService {

    Employees saveEmployee(Employees employee);

    Employees findById(Long id);

    List<Employees> getAllEmployees();


    void updateEmployee(Long employeeId, Set<DayOfWeek> daysAvailable);

    List<Employees> findEmployeesForService(EmployeeRequestDTO employeeRequestDTO);
}
