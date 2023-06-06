package com.example.springappprofessional.services;

import com.example.springappprofessional.dao.CustomerDao;
import com.example.springappprofessional.models.Customer;
import com.example.springappprofessional.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("Jpa")
@RequiredArgsConstructor
public class CustomerJPADataAccessService implements CustomerDao {

  private final CustomerRepository customerRepository;

  @Override
  public List<Customer> getAllCustomer() {
    return customerRepository.findAll();
  }

  @Override
  public Optional<Customer> getCustomerById(UUID uuid) {
    return customerRepository.findById(uuid);
  }

  @Override
  public void insertCostumer(Customer customer) {
    Optional<Customer> customerOptional = customerRepository.getCustomersByEmail(customer.getEmail());
    if (customerOptional.isPresent()) {
      throw new IllegalArgumentException("Oops the email is taken!");
    }
    customerRepository.save(customer);
  }

  @Override
  public boolean existCustomerWithEmail(String email) {
    return customerRepository.existsCustomerByEmail(email);
  }

  @Override
  public boolean existCustomerById(UUID uuid) {
    return customerRepository.existsCustomerById(uuid);
  }

  @Override
  public void deleteCustomerById(UUID uuid) {
    customerRepository.deleteById(uuid);
  }

  @Override
  public void updateCustomer(Customer customer) {
    customerRepository.save(customer);
  }

  @Override
  public Optional<Customer> getCustomerByEmail(String email) {
    return customerRepository.getCustomersByEmail(email);
  }

  @Override
  public void updateCustomerProfileImageId(String profileImageId, UUID uuid) {
    customerRepository.updateProfileImageById(profileImageId, uuid);
  }
}
