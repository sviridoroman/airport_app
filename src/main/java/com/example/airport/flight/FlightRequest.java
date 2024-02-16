package com.example.airport.flight;

import java.util.Date;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class FlightRequest {

  private Date arrivalDate;
  private String airportId;
  private int price;
  private boolean completed;
}
