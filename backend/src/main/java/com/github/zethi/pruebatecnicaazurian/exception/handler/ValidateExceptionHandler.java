package com.github.zethi.pruebatecnicaazurian.exception.handler;

import com.fasterxml.jackson.databind.JsonMappingException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class ValidateExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String, Object>> handleValidationExceptions(MethodArgumentNotValidException exception) {
        Map<String, Object> response = new HashMap<>();

        List<String> fieldErrorsDefaultMessage = new ArrayList<>();
        exception.getBindingResult().getAllErrors().forEach((error) -> {
            final String errorMessage = error.getDefaultMessage();
            fieldErrorsDefaultMessage.add(errorMessage);
        });

        response.put("status", HttpStatus.BAD_REQUEST.value());
        response.put("errors", fieldErrorsDefaultMessage);
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String, Object>> handleConstraintViolationExceptions(ConstraintViolationException exception) {
        Map<String, Object> response = new HashMap<>();

        List<String> fieldErrorsDefaultMessage = new ArrayList<>();
        exception.getConstraintViolations().forEach((error) -> {
            final String errorMessage = error.getMessage();
            fieldErrorsDefaultMessage.add(errorMessage);
        });

        response.put("status", HttpStatus.BAD_REQUEST.value());
        response.put("errors", fieldErrorsDefaultMessage);
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String, Object>> handleHttpMessageNotReadableException(HttpMessageNotReadableException exception) {
        Map<String, Object> response = new HashMap<>();
        List<String> errors = new ArrayList<>();

        Throwable rootCause = exception.getRootCause();
        if (rootCause != null) {
            if (rootCause instanceof JsonMappingException jsonMappingException) {
                List<JsonMappingException.Reference> path = jsonMappingException.getPath();
                System.out.println(path);
                if (path != null && !path.isEmpty()) {
                    path.forEach(reference -> {
                        String originalMessage = jsonMappingException.getOriginalMessage();
                        int enumClassIndex = originalMessage.indexOf("Enum class");
                        final String enumValidValues = originalMessage.substring(enumClassIndex).replace("Enum class: ", "");
                        errors.add("Field '" + reference.getFieldName() + "' can only have this values: " + enumValidValues);
                    });
                }
            }
        }

        response.put("status", HttpStatus.BAD_REQUEST.value());
        response.put("errors", errors);
        return ResponseEntity.badRequest().body(response);
    }
}