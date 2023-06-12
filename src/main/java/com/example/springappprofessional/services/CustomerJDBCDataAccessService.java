package com.example.springappprofessional.services;

import com.example.springappprofessional.dao.CustomerDao;
import com.example.springappprofessional.mappers.CustomerRowMapper;
import com.example.springappprofessional.models.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("Jdbc")
@RequiredArgsConstructor
public class CustomerJDBCDataAccessService implements CustomerDao {

  private final JdbcTemplate jdbcTemplate;

  private final CustomerRowMapper customerRowMapper;

  @Override
  public List<Customer> getAllCustomer() {
    var sql = """
        SELECT age, id, email, gender, name, profile_image_id
        FROM customer
        LIMIT 100
      """;
    return jdbcTemplate.query(sql, customerRowMapper);
  }

  @Override
  public Optional<Customer> getCustomerById(UUID uuid) {
    String sql = """
      SELECT id, name, email, gender, age, profile_image_id
      FROM customer
      WHERE id = ?
    """;
    return jdbcTemplate.query(sql, customerRowMapper, uuid).stream().findFirst();
  }

  @Override
  public void insertCostumer(Customer customer) {
    var sql = """
        INSERT INTO customer (name, email, password, gender, age)
        VALUES (?, ?, ?, ?, ?)
      """;
    int result = jdbcTemplate.update(sql,
      customer.getName(),
      customer.getEmail(),
      customer.getPassword(),
      customer.getGender().name(),
      customer.getAge());

    System.out.println(result);
  }

  @Override
  public boolean existCustomerWithEmail(String email) {
    var sql = """
        SELECT * FROM customer
        WHERE email = ?
      """;
    return jdbcTemplate.query(sql, customerRowMapper, email).size() > 0;
  }

  @Override
  public boolean existCustomerById(UUID uuid) {
    var sql = """
        SELECT * FROM customer
        WHERE id = ?
      """;
    return jdbcTemplate.query(sql, customerRowMapper, uuid).size() > 0;
  }

  @Override
  public void deleteCustomerById(UUID uuid) {
    var sql = """
        DELETE FROM customer
        WHERE id = ?
      """;
    int result = jdbcTemplate.update(sql, uuid);
    System.out.println("deleteCustomerById result: " + result);
  }

  @Override
  public void updateCustomer(Customer customer) {
    if (customer.getName() != null) {
      var sql = """
          UPDATE customer SET name = ?
          WHERE id = ?
        """;
      int result = jdbcTemplate.update(sql, customer.getName(), customer.getId());
      System.out.println("update customer name result: " + result);
    }
    if (customer.getEmail() != null) {
      var sql = """
          UPDATE customer SET email = ?
          WHERE id = ?
        """;
      int result = jdbcTemplate.update(sql, customer.getEmail(), customer.getId());
      System.out.println("update customer email result: " + result);
    }
    if (customer.getAge() != null) {
      var sql = """
          UPDATE customer SET age = ?
          WHERE id = ?
        """;
      int result = jdbcTemplate.update(sql, customer.getAge(), customer.getId());
      System.out.println("update customer age result: " + result);
    }
  }

  @Override
  public Optional<Customer> getCustomerByEmail(String email) {
    var sql = """
        SELECT age, id, email, gender, name, profile_image_id FROM customer
        WHERE email = ?
      """;
    return jdbcTemplate.query(sql, customerRowMapper, email).stream().findFirst();
  }

  @Override
  public void updateCustomerProfileImageId(String profileImageId, UUID uuid) {
    var sql = """
        UPDATE customer SET profile_image_id = ?
        WHERE id = ?
      """;
    int result = jdbcTemplate.update(sql, profileImageId, uuid);
    System.out.println("update customer profile result: " + result);
  }
}
