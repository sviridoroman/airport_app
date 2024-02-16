package com.example.airport.airport;

import java.util.List;

public interface IAirportService {

  List<SimpleAirportResponce> getAllAirports();

  AirportResponce getAirportById(String id);

  SimpleAirportResponce addNewAirport(AirportRequest request);

  AirportResponce updateAirport(String id, AirportRequest request);

  void deleteAirport(String id);

  List<SimpleAirportResponce> getAirportsByCity(String city);

  List<SimpleAirportResponce> getAirportsByCountry(String country);

}
