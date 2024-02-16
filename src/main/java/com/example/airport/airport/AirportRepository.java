package com.example.airport.airport;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AirportRepository extends JpaRepository<Airport, String> {

  List<Airport> findAllByCountry(String country);

  List<Airport> findAllByCity(String city);

}
