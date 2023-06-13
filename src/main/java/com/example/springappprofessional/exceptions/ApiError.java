package com.example.springappprofessional.exceptions;

public record ApiError(
        String path,
        String message,
        int statusCode,
        String localDateTime
) {
}
