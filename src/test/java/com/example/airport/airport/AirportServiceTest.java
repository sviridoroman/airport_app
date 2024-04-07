package com.example.airport.airport;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.assertj.core.api.Assertions;
import org.hibernate.id.UUIDGenerator;
import org.hibernate.id.uuid.UuidGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.airport.flight.Flight;
import com.example.airport.flight.FlightRepository;

@ExtendWith(MockitoExtension.class)
class AirportServiceTest {

  @Mock
  private AirportRepository airportRepository;

  @Mock
  private FlightRepository flightRepository;
  
  private AirportService service;

  @BeforeEach
  void setUp() {
    service = new AirportService(airportRepository, flightRepository);
  }

  @Test
  void canGetAllAirports(){
    service.getAllAirports();
    verify(airportRepository).findAll();
  }

  @Test
  void getAllAirports_ReturnsResponse() {
    List<Airport> repoAirports = new ArrayList<>();

    String airportId = UUID.randomUUID().toString();
    var aiport = Airport.builder()
        .id(airportId)
        .name("name")
        .city("city")
        .country("country")
        .flights(new ArrayList<Flight>())
        .build();

    repoAirports.add(aiport);

    when(airportRepository.findAll()).thenReturn(repoAirports);

    List<SimpleAirportResponce> aiports = service.getAllAirports();

    Assertions.assertThat(aiports).isNotNull();

    assertEquals(repoAirports.get(0).getName(), aiports.get(0).getName());
    assertEquals(repoAirports.get(0).getCity(), aiports.get(0).getCity());
    assertEquals(repoAirports.get(0).getCountry(), aiports.get(0).getCountry());
  }

  @Test
  void getAirportById_WhenAirportExists() {
    String airportId = UUID.randomUUID().toString();
    var aiport = Airport.builder()
        .id(airportId)
        .name("name")
        .city("city")
        .country("country")
        .flights(new ArrayList<Flight>())
        .build();

    when(airportRepository.findById(airportId)).thenReturn(Optional.of(aiport));

    AirportResponce responce = service.getAirportById(airportId);

    Assertions.assertThat(responce).isNotNull();
    assertEquals(aiport.getName(), responce.getName());
    assertEquals(aiport.getCity(), responce.getCity());
    assertEquals(aiport.getCountry(), responce.getCountry());
  }

  @Test
  void getAirportById_WhenAirportNotExists() {
    String airportId = UUID.randomUUID().toString();

    when(airportRepository.findById(airportId)).thenReturn(Optional.empty());

    try {
      AirportResponce responce = service.getAirportById(airportId);
      Assertions.assertThat(responce).isNull();
    } catch(Exception e) {
      System.out.println(e);
    }
  }

  @Test
  void canAddAirport () {
    AirportRequest request = new AirportRequest("name", "city", "country");

    var airport = Airport.builder()
       .name("name")
       .city("city")
       .country("country")
       .build();

   SimpleAirportResponce responce = service.addNewAirport(request);
   
   assertEquals(request.getCity(), responce.getCity());
   assertEquals(request.getCountry(), responce.getCountry());
   assertEquals(request.getName(), responce.getName());
   assertEquals(airport.getCity(), responce.getCity());
   assertEquals(airport.getCountry(), responce.getCountry());
   assertEquals(airport.getName(), responce.getName());
  }

  @Test
  void canSaveAirport() {
    AirportRequest request = new AirportRequest("name", "city", "country");

    SimpleAirportResponce responce = service.addNewAirport(request);
   
    assertEquals(request.getCity(), responce.getCity());
    assertEquals(request.getCountry(), responce.getCountry());
    assertEquals(request.getName(), responce.getName());
  }

  @Test
  void UpdateAirport_returnsAirportresponce() {
    AirportRequest request = new AirportRequest("name", "city", "country");
    SimpleAirportResponce airport = service.addNewAirport(request);

    String airportId = UUID.randomUUID().toString();
    var savedAirport = Airport.builder()
        .id(airportId)
        .name("name")
        .city("city")
        .country("country")
        .flights(new ArrayList<Flight>())
        .build();
    
    when(airportRepository.findById(airport.getId())).thenReturn(Optional.ofNullable(savedAirport));
    when(airportRepository.save(savedAirport)).thenReturn(savedAirport);

    AirportResponce responce = service.updateAirport(airport.getId(), request);

    assertEquals(request.getCity(), responce.getCity());
    assertEquals(request.getCountry(), responce.getCountry());
    assertEquals(request.getName(), responce.getName());
  }

  @Test
  void DeleteAirport_ReturnsVoid() {
    
    String airportId = UUID.randomUUID().toString();
    var airport = Airport.builder()
        .id(airportId)
        .name("name")
        .city("city")
        .country("country")
        .build();
    
    when(airportRepository.findById(airportId))
      .thenReturn(Optional.ofNullable(airport));

    doNothing().when(airportRepository).deleteById(airportId);

    service.deleteAirport(airportId);

    assertAll(() -> service.deleteAirport(airportId));
  }

}
