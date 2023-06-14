package com.example.springappprofessional.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiError> handleException(
            ResourceNotFoundException resourceNotFoundException,
            HttpServletRequest httpRequest
    ) {
        ApiError apiError = new ApiError(
                httpRequest.getRequestURI(),
                resourceNotFoundException.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                getDateTime()
        );
        return ResponseEntity.badRequest().body(apiError);
    }


    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<ApiError> handleException(
            DuplicateResourceException duplicateResourceException,
            HttpServletRequest httpServletRequest
    ) {
        ApiError apiError = new ApiError(
                httpServletRequest.getRequestURI(),
                duplicateResourceException.getMessage(),
                HttpStatus.CONFLICT.value(),
                getDateTime()
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiError);
    }

    @ExceptionHandler(RequestValidationException.class)
    public ResponseEntity<ApiError> handleException(
            RequestValidationException requestValidationException,
            HttpServletRequest httpServletRequest
    ) {
        ApiError apiError = new ApiError(
                httpServletRequest.getRequestURI(),
                requestValidationException.getMessage(),
                HttpStatus.BAD_REQUEST.value(),
                getDateTime()
        );
        return ResponseEntity.badRequest().body(apiError);
    }

    private static String getDateTime() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
