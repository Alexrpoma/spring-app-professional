package com.example.springappprofessional.dao;

import com.example.springappprofessional.models.Customer;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CustomerDao {
  List<Customer> getAllCustomer();
  Optional<Customer> getCustomerById(UUID uuid);
  void insertCostumer(Customer customer);
  boolean existCustomerWithEmail(String email);
  boolean existCustomerById(UUID uuid);
  void deleteCustomerById(UUID uuid);
  void updateCustomer(Customer customer);
  Optional<Customer> getCustomerByEmail(String email);
  void updateCustomerProfileImageId(String profileImageId, UUID uuid);
}
