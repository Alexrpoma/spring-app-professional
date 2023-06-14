package com.example.springappprofessional.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;

import java.util.UUID;

import static jakarta.persistence.GenerationType.AUTO;

@Getter
@Setter
@ToString
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
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

  @Id
  @GeneratedValue(strategy = AUTO) //We need this strategy if we work with JPA Hibernate
  @Column(columnDefinition = "uuid DEFAULT gen_random_uuid()") //We need to define random uuid if we work with JDBC
  private UUID id;
  @Column(nullable = false)
  private String name;
  @Column(nullable = false)
  private String email;
  @Min(value = 5, message = "The minimum age is 5")
  @Max(value = 70, message = "The maximum age is 70")
  @Column(nullable = false)
  private Integer age;
  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private Gender gender;
  @Column(nullable = false)
  private String password;
  @Column(name = "profile_image_id")
  private String profileImageId;

  public Customer(UUID id, String name, String email, Integer age, Gender gender, String profileImageId) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.age = age;
    this.gender = gender;
    this.profileImageId = profileImageId;
  }

}
