package com.example.airport.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiErrorResponse {

  private final String message;
  private final HttpStatus httpStatus;
  private final LocalDateTime time;
}
