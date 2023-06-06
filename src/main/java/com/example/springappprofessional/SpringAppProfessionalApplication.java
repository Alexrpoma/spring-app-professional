package com.example.springappprofessional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class SpringAppProfessionalApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringAppProfessionalApplication.class, args);
  }

}
