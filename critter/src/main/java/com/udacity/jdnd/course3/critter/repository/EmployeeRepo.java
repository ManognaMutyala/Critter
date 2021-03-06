package com.udacity.jdnd.course3.critter.repository;


import com.udacity.jdnd.course3.critter.entity.Employees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepo extends JpaRepository<Employees, Long> {
}
