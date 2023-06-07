package com.example.springappprofessional.controllers;

import com.example.springappprofessional.models.Customer;
import com.example.springappprofessional.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/customer")
public class CustomerController {

  private final CustomerService customerService;

  @GetMapping
  public List<Customer> getAllCustomer() {
    return customerService.getAllCustomer();
  }

  @GetMapping(path = "/{uuid}")
  public Customer getCustomer(@PathVariable(name = "uuid") UUID uuid) {
    return customerService.getCustomer(uuid);
  }

  @PostMapping
  public void registerCustomer(@RequestBody Customer customer) {
    customerService.addCustomer(customer);
  }

  @DeleteMapping(path = "/{uuid}")
  public void deleteCustomer(@PathVariable(name = "uuid") UUID uuid) {
    customerService.deleteCustomer(uuid);
  }
}
