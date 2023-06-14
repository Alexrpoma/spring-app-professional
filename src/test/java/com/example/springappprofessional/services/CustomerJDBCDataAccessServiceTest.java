package com.example.springappprofessional.services;

import com.example.springappprofessional.dtos.CustomerDTOMapper;
import com.example.springappprofessional.mappers.CustomerRowMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.jdbc.core.JdbcTemplate;
import org.testcontainers.containers.PostgreSQLContainer;

import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.*;

class CustomerJDBCDataAccessServiceTest {

  private CustomerJDBCDataAccessService customerJDBCDataAccessServiceTest;

  private CustomerRowMapper customerRowMapper = new CustomerRowMapper();

  private final PostgreSQLContainer<?> postgreSQLContainer =
      new PostgreSQLContainer<>("PostgresSpringProfessional")
          .withDatabaseName("spring_professional_data")
          .withUsername("alex")
          .withPassword("alex");

  private DataSource getDataSource() {
    return DataSourceBuilder.create()
        .driverClassName(postgreSQLContainer.getDriverClassName())
        .url(postgreSQLContainer.getJdbcUrl())
        .username(postgreSQLContainer.getUsername())
        .password(postgreSQLContainer.getPassword())
        .build();
  }

  private JdbcTemplate getJdbcTemplate() {
    return new JdbcTemplate(getDataSource());
  }

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