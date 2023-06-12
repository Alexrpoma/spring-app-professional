package com.example.springappprofessional.models;

public record CustomerRegistration(
    String name,
    String email,
    String password,
    Integer age,
    Gender gender
) { }
