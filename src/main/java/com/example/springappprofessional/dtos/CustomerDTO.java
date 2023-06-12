package com.example.springappprofessional.dtos;

import com.example.springappprofessional.models.Gender;

import java.util.UUID;

public record CustomerDTO(
        UUID id,
        String name,
        String email,
        Gender gender,
        Integer age,
        String profileImageId
) {
}