package com.example.springappprofessional.services;

import com.example.springappprofessional.dao.CustomerDao;
import com.example.springappprofessional.dtos.CustomerDTO;
import com.example.springappprofessional.dtos.CustomerDTOMapper;
import com.example.springappprofessional.models.Customer;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CustomerService {

  private final CustomerDao customerDao;
  private final CustomerDTOMapper customerDTOMapper;

  //In Qualifier, we can use Jdbc or Jpa for the different request to database.
  public CustomerService(@Qualifier("Jdbc") CustomerDao customerDao, CustomerDTOMapper customerDTOMapper) {
    this.customerDao = customerDao;
    this.customerDTOMapper = customerDTOMapper;
  }

  public List<CustomerDTO> getAllCustomer() {
    return customerDao.getAllCustomer()
            .stream().map(customerDTOMapper)
            .collect(Collectors.toList());
  }

  public CustomerDTO getCustomer(UUID uuid) {
    return customerDTOMapper.apply(customerDao.getCustomerById(uuid).orElseThrow(
            () -> new IllegalArgumentException("Oops resource not found!")
    ));
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
