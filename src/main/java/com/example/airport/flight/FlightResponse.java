package com.example.airport.flight;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FlightResponse {

  private String id;
  private Date arrivalDate;
  private String airportId;
  private int price;
  private boolean completed;

  public FlightResponse(Flight flight) {
    this.id = flight.getId();
    this.arrivalDate = flight.getArrivalDate();
    this.airportId = flight.getAirport().getId();
    this.price = flight.getPrice();
    this.completed = flight.isCompleted();
  }
}
