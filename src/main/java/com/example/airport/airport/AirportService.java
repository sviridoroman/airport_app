package com.example.airport.airport;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.airport.exception.ApiRequestException;
import com.example.airport.flight.FlightRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AirportService implements IAirportService {

  private final AirportRepository airportRepository;
  private final FlightRepository flightRepository;

  @Override
  public List<SimpleAirportResponce> getAllAirports() {
    List<SimpleAirportResponce> responce = new ArrayList<>();
    airportRepository.findAll()
        .forEach(airport -> responce.add(new SimpleAirportResponce(airport)));
    return responce;
  }

  @Override
  public AirportResponce getAirportById(String id) {
    Optional<Airport> airport = airportRepository.findById(id);
    if (airport.isPresent()) {
      AirportResponce responce = new AirportResponce(airport.get());
      return responce;
    }
    throw new ApiRequestException("Airport Not Found " + id);
  }

  @Override
  public SimpleAirportResponce addNewAirport(AirportRequest request) {
    var airport = Airport.builder().name(request.getName()).city(request.getCity())
        .country(request.getCountry()).build();
    airportRepository.save(airport);
    SimpleAirportResponce responce = new SimpleAirportResponce(airport);
    return responce;
  }

  @Override
  public AirportResponce updateAirport(String id, AirportRequest request) {
    Optional<Airport> optionalAirport = airportRepository.findById(id);
    if (optionalAirport.isPresent()) {
      Airport airport = optionalAirport.get();
      if (request.getName() != null)
        airport.setName(request.getName());
      if (request.getCity() != null)
        airport.setCity(request.getCity());
      if (request.getCountry() != null)
        airport.setCountry(request.getCountry());
      airportRepository.save(airport);
      AirportResponce responce = new AirportResponce(airport);
      return responce;
    }
    throw new ApiRequestException("Airport Not Found " + id);
  }

  @Override
  public void deleteAirport(String id) {
    Optional<Airport> airport = airportRepository.findById(id);
    if (airport.isPresent()) {
      flightRepository.findAll()
          .removeIf(flight -> flight.getAirport().getId() == airport.get().getId());
      airportRepository.deleteById(id);
    }
    throw new ApiRequestException("Airport Not Found " + id);
  }

  @Override
  public List<SimpleAirportResponce> getAirportsByCity(String city) {
    List<SimpleAirportResponce> responce = new ArrayList<>();
    airportRepository.findAllByCity(city)
        .forEach(airport -> responce.add(new SimpleAirportResponce(airport)));
    return responce;
  }

  @Override
  public List<SimpleAirportResponce> getAirportsByCountry(String country) {
    List<SimpleAirportResponce> responce = new ArrayList<>();
    airportRepository.findAllByCountry(country)
        .forEach(airport -> responce.add(new SimpleAirportResponce(airport)));
    return responce;
  }

}
