package com.example.airport.auth;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties.Jwt;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.airport.airport.Airport;
import com.example.airport.airport.AirportService;
import com.example.airport.airport.SimpleAirportResponce;
import com.example.airport.flight.Flight;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthenticationControllerTest {

  @Autowired
  MockMvc mockMvc; 

  @MockBean
  private AirportService airportService;

  @BeforeEach
  void setUp() {
    
  }

  @Test
  void userCanGetToken() throws Exception{
    String email = "admin";
    String password = "admin";
    String body = "{\"email\":\"" + email + "\", \"password\":\""
                  + password + "\"}";
    mockMvc.perform(post("http://localhost:8080/api/v1/auth/authenticate")
      .content(body))
      .andExpect(MockMvcResultMatchers.status().is(415)).andReturn();
  }
  
  @Test
  void testauthenticate() throws Exception {

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
      .perform(post("http://localhost:8080/api/v1/auth/authenticate").with(SecurityMockMvcRequestPostProcessors.user("admin").password("admin").roles("ADMIN"))
      // .with(SecurityMockMvcRequestPostProcessors.jwt().authorities(new SimpleGrantedAuthority("admin"))))
      ).andExpect(MockMvcResultMatchers.status().isOk())
      .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", Matchers.is("name")));
  }

  // @Test
  // void testGetAllAirports() throws Exception {

  //   String airportId = UUID.randomUUID().toString();
  //   Airport aiport = Airport.builder()
  //   .id(airportId)
  //   .name("name")
  //   .city("city")
  //   .country("country")
  //   .flights(new ArrayList<Flight>())
  //   .build();

     

  //   Mockito.when(airportService.getAllAirports()).thenReturn(Arrays.asList(new SimpleAirportResponce(aiport)));
    
  //   mockMvc
  //     .perform(get("http://localhost:8080/airport")
  //     .with(SecurityMockMvcRequestPostProcessors
  //       .jwt().jwt(jwt -> jwt.header("kid", "one").claim("iss", "https://idp.example.org"))))
  //     .andExpect(MockMvcResultMatchers.status().isOk())
  //     .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", Matchers.is("name")));
  // }
}
