package com.example.airport.airport;

import java.util.ArrayList;
import java.util.UUID;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.example.airport.flight.Flight;

@DataJpaTest
public class AirportRepositoryTest {
  
  @Autowired
  private AirportRepository airportRepository;

  @Autowired
  private TestEntityManager entityManager;

  String airportId = UUID.randomUUID().toString();

  @BeforeEach
  public void setUp() {
    var aiport = Airport.builder()
      .id(airportId)
      .name("name")
      .city("city")
      .country("country")
      .flights(new ArrayList<Flight>())
      .build();
    entityManager.persist(aiport);
 }

 @Test
  public void whenFindById_thenTeturnAirport() {
    String name = "name";
    String city = "city";
    String country = "country";

    Airport airportFound = airportRepository.findById(airportId).get();

    Assertions.assertThat(airportFound.getName()).isEqualTo(name);
    Assertions.assertThat(airportFound.getCity()).isEqualTo(city);
    Assertions.assertThat(airportFound.getCountry()).isEqualTo(country);
  };
}
