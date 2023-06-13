package com.example.springappprofessional.models;

public record CustomerUpdate(
        String name,
        String email,
        Integer age
) {
}
