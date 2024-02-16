package com.example.airport.airport;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/airport")
public class AirportController {

  private final AirportService airportService;

  @GetMapping
  ResponseEntity<List<SimpleAirportResponce>> getAllAirports() {
    return ResponseEntity.ok(airportService.getAllAirports());
  }

  @GetMapping("/{id}")
  ResponseEntity<AirportResponce> getAirportsById(@PathVariable String id) {
    return ResponseEntity.ok(airportService.getAirportById(id));
  }

  @GetMapping("/country")
  ResponseEntity<List<SimpleAirportResponce>> getAirportsByCountry(
      @RequestParam(value = "country") String country) {
    return ResponseEntity.ok(airportService.getAirportsByCountry(country));
  }

  @GetMapping("/city")
  ResponseEntity<List<SimpleAirportResponce>> getAirportByCity(
      @RequestParam(value = "city") String city) {
    return ResponseEntity.ok(airportService.getAirportsByCity(city));
  }

  @PostMapping
  ResponseEntity<SimpleAirportResponce> postAirport(@RequestBody AirportRequest request) {
    return ResponseEntity.ok(airportService.addNewAirport(request));
  }

  @PutMapping("/{id}")
  ResponseEntity<AirportResponce> putAirport(@PathVariable String id,
      @RequestBody AirportRequest airport) {
    return ResponseEntity.ok(airportService.updateAirport(id, airport));
  }

  @DeleteMapping("/{id}")
  void deleteAirport(@PathVariable String id) {
    airportService.deleteAirport(id);
  }

}
