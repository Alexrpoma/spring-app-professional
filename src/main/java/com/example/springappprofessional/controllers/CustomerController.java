package com.example.springappprofessional.controllers;

import com.example.springappprofessional.dao.CustomerDao;
import com.example.springappprofessional.models.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/customer")
public class CustomerController {

  private final CustomerDao customerDao;

  @Autowired
  public CustomerController(@Qualifier("Jdbc") CustomerDao customerDao) {
    this.customerDao = customerDao;
  }

  @GetMapping
  public List<Customer> getAllCustomers() {
    return customerDao.getAllCustomer();
  }
}
