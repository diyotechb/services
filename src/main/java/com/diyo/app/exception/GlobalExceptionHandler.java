package com.diyo.app.exception;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException ex) {
        StringBuilder errorMessage = new StringBuilder("Validation error(s): ");

        ex.getConstraintViolations().forEach(violation -> {
            errorMessage.append(violation.getMessage()).append("; ");
        });

        return ResponseEntity.badRequest().body(errorMessage.toString());
    }

    @ExceptionHandler(InvalidResumeFileException.class)
    public ResponseEntity<String> handleInvalidResumeFileException(InvalidResumeFileException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(InvalidUserIdException.class)
    public ResponseEntity<String> handleInvalidUserException(InvalidUserIdException invalid) {
        return ResponseEntity.badRequest().body(invalid.getMessage());
    }
}

