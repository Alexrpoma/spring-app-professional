package com.example.springappprofessional.controllers;

import com.example.springappprofessional.dtos.CustomerDTO;
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
  public List<CustomerDTO> getAllCustomer() {
    return customerService.getAllCustomer();
  }

  @GetMapping(path = "/{uuid}")
  public CustomerDTO getCustomer(@PathVariable(name = "uuid") UUID uuid) {
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

  @PutMapping(path = "/{uuid}")
  public void updateCustomer(@PathVariable(name = "uuid") UUID uuid, @RequestBody Customer customer) {
    customerService.updateCustomer(uuid, customer);
  }
}
