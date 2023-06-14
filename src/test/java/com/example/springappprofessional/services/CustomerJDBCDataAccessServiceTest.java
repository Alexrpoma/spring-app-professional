package com.example.springappprofessional.services;

import com.example.springappprofessional.AbstractTestContainers;
import com.example.springappprofessional.mappers.CustomerRowMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class CustomerJDBCDataAccessServiceTest extends AbstractTestContainers {

  private CustomerJDBCDataAccessService customerJDBCDataAccessServiceTest;
  private final CustomerRowMapper customerRowMapper = new CustomerRowMapper();

  @BeforeEach
  void setUp() {
    customerJDBCDataAccessServiceTest = new CustomerJDBCDataAccessService(
        getJdbcTemplate(), customerRowMapper
    );
  }

  @Test
  void getAllCustomer() {

  }

  @Test
  void getCustomerById() {
  }

  @Test
  void insertCostumer() {
  }

  @Test
  void existCustomerWithEmail() {
  }

  @Test
  void existCustomerById() {
  }

  @Test
  void deleteCustomerById() {
  }

  @Test
  void updateCustomer() {
  }

  @Test
  void getCustomerByEmail() {
  }
}