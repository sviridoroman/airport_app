package com.example.airport.flight;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.airport.airport.Airport;
import com.example.airport.airport.AirportRepository;
import com.example.airport.exception.ApiRequestException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class FlightService implements IFlightService {

  private final FlightRepository flightRepository;
  private final AirportRepository airportRepository;

  @Override
  public List<FlightResponse> getAllFlights() {
    List<FlightResponse> response = new ArrayList<>();
    flightRepository.findAll().forEach((flight) -> response.add(new FlightResponse(flight)));
    return response;
  }

  @Override
  public FlightResponse getFlightById(String id) {
    Optional<Flight> flight = flightRepository.findById(id);
    if (flight.isPresent()) {
      FlightResponse response = new FlightResponse(flight.get());
      return response;
    }
    throw new ApiRequestException("Flight Not Found " + id);
  }

  @Override
  public FlightResponse addFlight(FlightRequest request) {
    Optional<Airport> airport = airportRepository.findById(request.getAirportId());
    if (airport.isPresent()) {
      var flight = Flight
        .builder()
        .arrivalDate(request.getArrivalDate())
        .airport(airport.get())
        .price(request.getPrice())
        .completed(request.isCompleted())
        .build();
      flightRepository.save(flight);
      FlightResponse response = new FlightResponse(flight);
      return response;
    }
    throw new ApiRequestException("Airport Not Found " + request.getAirportId());
  }

  @Override
  public FlightResponse updateFlight(String id, FlightRequest request) {
    Flight flight = flightRepository.findById(id).get();
    if (request.getArrivalDate() != null)
      flight.setArrivalDate(request.getArrivalDate());
    if (request.getAirportId() != null)
      flight.setAirport(airportRepository.findById(request.getAirportId()).get());
    flight.setPrice(request.getPrice());
    flight.setCompleted(request.isCompleted());
    flightRepository.save(flight);
    FlightResponse response = new FlightResponse(flight);
    return response;
  }

  @Override
  public void deleteAirport(String id) {
    Optional<Flight> flight = flightRepository.findById(id);
    if (!flight.isPresent()) {
      throw new ApiRequestException("Flight Not Found " + id);
    }
    flightRepository.deleteById(id);
  }

  @Override
  public List<FlightResponse> getAllFlightsByAirportId(String airportId) {
    List<FlightResponse> response = new ArrayList<>();
    flightRepository.findAll().stream().filter(flight -> flight.getAirport().getId() == airportId)
        .forEach((flight) -> response.add(new FlightResponse(flight)));
    return response;
  }

  @Override
  public List<FlightResponse> getAllFlightsByAirportName(String airportName) {
    List<FlightResponse> response = new ArrayList<>();
    flightRepository.findAll().stream()
        .filter(flight -> flight.getAirport().getName() == airportName)
        .forEach((flight) -> response.add(new FlightResponse(flight)));
    return response;
  }

}
