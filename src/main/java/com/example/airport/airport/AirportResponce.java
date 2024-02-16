package com.example.airport.airport;

import java.util.ArrayList;
import java.util.List;

import com.example.airport.flight.Flight;
import com.example.airport.flight.FlightResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AirportResponce {

  private String id;
  private String name;
  private String city;
  private String country;
  private List<FlightResponse> flights;

  public AirportResponce(Airport airport) {
    this.id = airport.getId();
    this.name = airport.getName();
    this.city = airport.getCity();
    this.country = airport.getCountry();
    this.flights = convertToResponse(airport.getFlights());
  }

  private List<FlightResponse> convertToResponse(List<Flight> flights) {
    List<FlightResponse> flightResponse = new ArrayList<>();
    for (Flight flight : flights) {
      flightResponse.add(new FlightResponse(flight));
    }
    return flightResponse;
  }
}
