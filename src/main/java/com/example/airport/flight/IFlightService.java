package com.example.airport.flight;

import java.util.List;

public interface IFlightService {

  List<FlightResponse> getAllFlights();

  FlightResponse getFlightById(String id);

  FlightResponse addFlight(FlightRequest request);

  FlightResponse updateFlight(String id, FlightRequest request);

  void deleteAirport(String id);

  List<FlightResponse> getAllFlightsByAirportId(String airportId);

  List<FlightResponse> getAllFlightsByAirportName(String airportName);
}
