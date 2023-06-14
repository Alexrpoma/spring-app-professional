package com.example.springappprofessional;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.jdbc.core.JdbcTemplate;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import javax.sql.DataSource;

@Testcontainers
public abstract class AbstractTestContainers {

  @Container
  protected static PostgreSQLContainer<?> postgreSQLContainer =
      new PostgreSQLContainer<>("postgres:15.3")
          .withDatabaseName("spring_professional_data")
          .withUsername("alex")
          .withPassword("alex");

  private static DataSource getDataSource() {
    return DataSourceBuilder.create()
        .driverClassName(postgreSQLContainer.getDriverClassName())
        .url(postgreSQLContainer.getJdbcUrl())
        .username(postgreSQLContainer.getUsername())
        .password(postgreSQLContainer.getPassword())
        .build();
  }

  protected static JdbcTemplate getJdbcTemplate() {
    return new JdbcTemplate(getDataSource());
  }
}
