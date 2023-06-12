package com.example.springappprofessional.services;

import com.example.springappprofessional.dao.CustomerDao;
import com.example.springappprofessional.dtos.CustomerDTO;
import com.example.springappprofessional.dtos.CustomerDTOMapper;
import com.example.springappprofessional.models.Customer;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CustomerService {

  private final CustomerDao customerDao;

  //In Qualifier, we can use Jdbc or Jpa for the different request to database.
  public CustomerService(@Qualifier("Jdbc") CustomerDao customerDao) {
    this.customerDao = customerDao;
  }

  public List<CustomerDTO> getAllCustomer() {
    List<CustomerDTO> customerDTOS = new ArrayList<>();
    CustomerDTOMapper customerDTOMapper = new CustomerDTOMapper();
    customerDao.getAllCustomer().forEach(c -> customerDTOS.add(customerDTOMapper.apply(c)));
    return customerDTOS;
  }

  public CustomerDTO getCustomer(UUID uuid) {
    Optional<Customer> customer = customerDao.getCustomerById(uuid);
    if (customer.isPresent()) {
      CustomerDTOMapper customerDTOMapper = new CustomerDTOMapper();
      return customerDTOMapper.apply(customer.get());
    }
    throw new IllegalArgumentException("Oops resource not found!");
  }

  public void addCustomer(Customer customer) {
    if (customerDao.existCustomerWithEmail(customer.getEmail())) {
      throw new IllegalArgumentException("The email %s is already registered!".formatted(customer.getEmail()));
    }
    String encryptedPassword = BCrypt.hashpw(customer.getPassword(), BCrypt.gensalt());
    customer.setPassword(encryptedPassword);
    customerDao.insertCostumer(customer);
  }

  public void deleteCustomer(UUID uuid) {
    if (!customerDao.existCustomerById(uuid)) {
      throw new IllegalArgumentException("The use with id: %s doesn't exist!".formatted(uuid));
    }
    customerDao.deleteCustomerById(uuid);
  }

  @Transactional  //update customer if it is possible, assurance that the data is not corrupt
  public void updateCustomer(UUID uuid, Customer uptadeCustomer) {
    Optional<Customer> customerOptional = customerDao.getCustomerById(uuid);
    if (customerOptional.isEmpty()) {
      throw new IllegalArgumentException("The use with id: %s doesn't exist!".formatted(uuid));
    }
    if (uptadeCustomer.getEmail() != null && customerDao.existCustomerWithEmail(uptadeCustomer.getEmail())) {
      throw new IllegalArgumentException("The email %s already exist".formatted(uptadeCustomer.getEmail()));
    }
    uptadeCustomer.setId(uuid);
    customerDao.updateCustomer(uptadeCustomer);
  }
}
