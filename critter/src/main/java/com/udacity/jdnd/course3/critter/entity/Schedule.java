package com.udacity.jdnd.course3.critter.entity;

import com.udacity.jdnd.course3.critter.user.EmployeeSkill;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
public class Schedule {

    @Id
    @GeneratedValue
    Long id;

    @ManyToMany
    List<Employees> employeesList;

    @ElementCollection
    Set<EmployeeSkill> activities;

    @ManyToMany
    List<Pet> petList;

    LocalDate datelocal;

    public LocalDate getDatelocal() {
        return datelocal;
    }

    public void setDatelocal(LocalDate datelocal) {
        this.datelocal = datelocal;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Employees> getEmployeesList() {
        return employeesList;
    }

    public void setEmployeesList(List<Employees> employeesList) {
        this.employeesList = employeesList;
    }

    public List<Pet> getPetList() {
        return petList;
    }

    public void setPetList(List<Pet> petList) {
        this.petList = petList;
    }


    public Set<EmployeeSkill> getActivities() {
        return activities;
    }

    public void setActivities(Set<EmployeeSkill> activities) {
        this.activities = activities;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "id=" + id +
                ", employeesList=" + employeesList +
                ", activities=" + activities +
                ", petList=" + petList +
                ", datelocal=" + datelocal +
                '}';
    }

}
