package com.digitalwallet.walletservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.time.LocalDateTime;
import java.util.Collections;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusinessException(BusinessException ex) {
        ErrorResponse response = new ErrorResponse();
        response.setTimestamp(LocalDateTime.now());
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setMessage(ex.getMessage());
        response.setErrors(Collections.emptyList()); // works in Java 8
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFound(ResourceNotFoundException ex) {
        ErrorResponse response = new ErrorResponse();
        response.setTimestamp(LocalDateTime.now());
        response.setStatus(HttpStatus.NOT_FOUND.value());
        response.setMessage(ex.getMessage());
        response.setErrors(Collections.emptyList());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneralException(Exception ex) {
        ErrorResponse response = new ErrorResponse();
        response.setTimestamp(LocalDateTime.now());
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.setMessage("An unexpected error occurred");
        response.setErrors(Collections.emptyList());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
