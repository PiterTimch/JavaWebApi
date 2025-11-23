package org.example.services;

import org.example.data.common.ValidationException;
import org.example.data.dto.validation.FieldError;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class ValidationService {
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<Map<String, Object>> handleValidation(ValidationException ex) {
        Map<String, Object> errors = new HashMap<>();
        for (FieldError error : ex.getErrors()) {
            errors.computeIfAbsent(error.getField(), k -> new java.util.ArrayList<String>());
            ((List<String>) errors.get(error.getField())).add(error.getMessage());
        }

        Map<String, Object> response = new HashMap<>();
        response.put("status", 400);
        response.put("isValid", false);
        response.put("errors", errors);

        return ResponseEntity.badRequest().body(response);
    }
}
