package com.example.airport.flight;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class FlightRequest {

  @JsonFormat(pattern = "yyyy-MM-dd")
  private LocalDate arrivalDate;
  private String airportId;
  private int price;
  private boolean completed;
}
