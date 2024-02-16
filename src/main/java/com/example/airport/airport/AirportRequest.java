package com.example.airport.airport;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AirportRequest {

  private String name;
  private String city;
  private String country;
}
