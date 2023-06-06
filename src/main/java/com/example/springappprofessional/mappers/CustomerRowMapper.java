package com.example.springappprofessional.mappers;

import com.example.springappprofessional.models.Customer;
import com.example.springappprofessional.models.Gender;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

@Component
public class CustomerRowMapper implements RowMapper<Customer> {
  @Override
  public Customer mapRow(ResultSet resultSet, int rowNum) throws SQLException {
    return new Customer(
      UUID.fromString(resultSet.getString("id")),
      resultSet.getString("name"),
      resultSet.getString("email"),
      resultSet.getInt("age"),
      Gender.valueOf(resultSet.getString("gender")),
      resultSet.getString("profile_image_id"));
  }
}
