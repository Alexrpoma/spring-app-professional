package com.example.springappprofessional.repositories;

import com.example.springappprofessional.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {

  boolean existsCustomerByEmail(String email);
  boolean existsCustomerById(UUID uuid);
  Optional<Customer> getCustomersByEmail(String email);
  @Modifying(clearAutomatically = true)
  @Query("UPDATE Customer c SET c.profileImageId = ?1 WHERE c.id = ?2")
  int updateProfileImageById(String profileImageId, UUID uuid);
}
