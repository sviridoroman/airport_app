package com.example.airport.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

  @ExceptionHandler(ApiRequestException.class)
  public ResponseEntity<ApiErrorResponse> handleBookingNotFoundexception(ApiRequestException ex) {
    ApiErrorResponse apiException =
        new ApiErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST, LocalDateTime.now());
    return new ResponseEntity<>(apiException, HttpStatus.BAD_REQUEST);
  }
}
