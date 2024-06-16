package com.example.airport.flight;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/flight")
@RequiredArgsConstructor
public class FlightController {

  private final FlightService flightService;

  @GetMapping
  ResponseEntity<List<FlightResponse>> getAllFlights() {
    return ResponseEntity.ok(flightService.getAllFlights());
  }

  @GetMapping("/{id}")
  ResponseEntity<FlightResponse> getFlightById(@PathVariable String id) {
    return ResponseEntity.ok(flightService.getFlightById(id));
  }

  @PostMapping
  ResponseEntity<FlightResponse> postFlight(@RequestBody FlightRequest request) {
    return ResponseEntity.ok(flightService.addFlight(request));
  }

  @CrossOrigin
  @PutMapping("/{id}")
  ResponseEntity<FlightResponse> putFlight(@PathVariable String id,
      @RequestBody FlightRequest request) {
    return ResponseEntity.ok(flightService.updateFlight(id, request));
  }
  
  @CrossOrigin
  @DeleteMapping("/{id}")
  public void deleteById(@PathVariable String id) {
    flightService.deleteAirport(id);
  }
}
