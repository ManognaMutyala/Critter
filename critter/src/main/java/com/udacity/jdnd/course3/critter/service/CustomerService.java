package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.Customer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerService {
    public Customer saveCustomer(Customer customer);

    public List<Customer> getAllCustomers();

    public Customer findById(Long id);


}
