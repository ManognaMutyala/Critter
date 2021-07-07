package com.udacity.jdnd.course3.critter.serviceImpl;

import com.udacity.jdnd.course3.critter.entity.Employees;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepo;
import com.udacity.jdnd.course3.critter.service.EmployeeService;
import com.udacity.jdnd.course3.critter.user.EmployeeRequestDTO;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import com.udacity.jdnd.course3.critter.util.ControllerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional

public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepo employeeRepo;

    @Autowired
    ControllerUtil controllerUtil;

    @Override
    public Employees saveEmployee(Employees employee) {
        return employeeRepo.save(employee);
    }

    //add exception later
    @Override
    public Employees findById(Long id) {
        Optional<Employees> employeeOptional = employeeRepo.findById(id);
        return employeeOptional.orElse(null);
    }

    @Override
    public List<Employees> getAllEmployees() {
        return employeeRepo.findAll();
    }

    @Override
    public void updateEmployee(Long employeeId, Set<DayOfWeek> daysAvailable) {
        Employees employee = findById(employeeId);
        if (employee != null) {
            employee.setDaysAvailable(daysAvailable);
            saveEmployee(employee);
        }

    }

    // https://knowledge.udacity.com/questions/536405 -- to understand the expectations

    @Override
    public List<Employees> findEmployeesForService(EmployeeRequestDTO employeeDTO) {
        Set<EmployeeSkill> skills = employeeDTO.getSkills();
        DayOfWeek day = employeeDTO.getDate().getDayOfWeek();

        List<Employees> employeesList = getAllEmployees();
        List<Employees> skilledEmployees = new ArrayList<>();

        for (Employees e : employeesList) {
            if (e.getSkills().containsAll(skills) && e.getDaysAvailable().contains(day))
                skilledEmployees.add(e);
        }
        return skilledEmployees;
    }
}
