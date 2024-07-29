package com.example.airport.airport;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties.Jwt;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import com.example.airport.flight.Flight;
import com.example.airport.flight.FlightRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@AutoConfigureMockMvc(addFilters = false)
@SpringBootTest

class AirportControllerTest {

  @Autowired
  MockMvc mockMvc;

  @MockBean
  private AirportService airportService;

  @BeforeEach
  void setUp() {
    
  }

  @Test
  void testGetAllAirports() throws Exception {

    String airportId = UUID.randomUUID().toString();
    Airport aiport = Airport.builder()
    .id(airportId)
    .name("name")
    .city("city")
    .country("country")
    .flights(new ArrayList<Flight>())
    .build();

    Mockito.when(airportService.getAllAirports()).thenReturn(Arrays.asList(new SimpleAirportResponce(aiport)));
  
    mockMvc
      .perform(get("http://localhost:8080/airport")
      ).andExpect(MockMvcResultMatchers.status().isOk())
      .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", Matchers.is("name")));
  }

  @Test
  void testGetAirportsById() throws Exception {
    String airportId = UUID.randomUUID().toString();
    Airport aiport = Airport.builder()
    .id(airportId)
    .name("name")
    .city("city")
    .country("country")
    .flights(new ArrayList<Flight>())
    .build();
    AirportRequest request = new AirportRequest("name", "city", "country");
    Mockito.when(airportService.getAirportById(airportId)).thenReturn(new AirportResponce(aiport));
    ResultActions response = mockMvc.perform(get("http://localhost:8080/airport/{id}",airportId));

    response.andExpect(MockMvcResultMatchers.status().isOk());
  }

  @Test
  void testPostAirport() throws Exception {
    String airportId = UUID.randomUUID().toString();
    Airport aiport = Airport.builder()
    .id(airportId)
    .name("name")
    .city("city")
    .country("country")
    .flights(new ArrayList<Flight>())
    .build();

    mockMvc.perform(
      post("http://localhost:8080/airport")
        .content(new ObjectMapper().writeValueAsString(aiport))
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
      .andExpect(MockMvcResultMatchers.status().isCreated());
  }

  @Test
  void testPutAirport() {
    fail("Not yet implemented");
  }

  @Test
  void testDeleteAirport() {
    fail("Not yet implemented");
  }

  @Test
  void testAirportController() {
    fail("Not yet implemented");
  }

}
