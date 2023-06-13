package com.example.springappprofessional.services;

import com.example.springappprofessional.dao.CustomerDao;
import com.example.springappprofessional.dtos.CustomerDTO;
import com.example.springappprofessional.dtos.CustomerDTOMapper;
import com.example.springappprofessional.models.Customer;
import com.example.springappprofessional.models.CustomerRegistration;
import com.example.springappprofessional.models.CustomerUpdate;
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

  public void addCustomer(CustomerRegistration customerRegistration) {
    if (customerDao.existCustomerWithEmail(customerRegistration.email())) {
      throw new IllegalArgumentException(
              "The email %s is already registered!"
                      .formatted(customerRegistration.email()));
    }
    String encryptedPassword = BCrypt.hashpw(customerRegistration.password(), BCrypt.gensalt());
    Customer customer = new Customer();
    customer.setName(customerRegistration.name());
    customer.setEmail(customerRegistration.email());
    customer.setPassword(encryptedPassword);
    customer.setAge(customerRegistration.age());
    customer.setGender(customerRegistration.gender());
    customerDao.insertCostumer(customer);
  }

  public void deleteCustomer(UUID uuid) {
    if (!customerDao.existCustomerById(uuid)) {
      throw new IllegalArgumentException("The use with id: %s doesn't exist!".formatted(uuid));
    }
    customerDao.deleteCustomerById(uuid);
  }

  @Transactional  //update customer if it is possible, assurance that the data is not corrupt
  public void updateCustomer(UUID uuid, CustomerUpdate customerUpdate) {
    Optional<Customer> customerOptional = customerDao.getCustomerById(uuid);
    customerOptional.ifPresentOrElse(customer -> {
      if (customerUpdate.email() != null && !customerUpdate.email().equals(customer.getEmail())) {
        if (customerDao.existCustomerWithEmail(customerUpdate.email())) {
          throw new IllegalArgumentException("The email %s already exist".formatted(customerUpdate.email()));
        }
        customer.setEmail(customerUpdate.email());
      }
      if (customerUpdate.name() != null) {
        customer.setName(customerUpdate.name());
      }
      if (customerUpdate.age() != null) {
        customer.setAge(customerUpdate.age());
      }
      customerDao.updateCustomer(customer);
    }, () -> customerOptional.orElseThrow(
            () -> new IllegalArgumentException("The use with id: %s doesn't exist!".formatted(uuid))
    ));
  }
}
