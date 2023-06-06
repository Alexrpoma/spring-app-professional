package com.example.springappprofessional.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

import static jakarta.persistence.GenerationType.AUTO;

@Getter
@Setter
@Entity
@Table(
  name = "customer",
  uniqueConstraints ={
    @UniqueConstraint(
      name = "customer_email_unique",
      columnNames = "email"
    ),
    @UniqueConstraint(
      name = "customer_profileId_unique",
      columnNames = "profile_image_id"
    )
  }
)
public class Customer {

  @Id
  @GeneratedValue(strategy = AUTO)
  private UUID id;
  @Column(nullable = false)
  private String name;
  @Column(nullable = false)
  private String email;
  @Column(nullable = false)
  private Integer age;
  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private Gender gender;
  @Column(nullable = false)
  private String password;
  @Column(name = "profile_image_id")
  private String profileImageId;
}
