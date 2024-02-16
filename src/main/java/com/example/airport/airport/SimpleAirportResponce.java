package com.example.airport.airport;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SimpleAirportResponce {

  private String id;
  private String name;
  private String city;
  private String country;

  public SimpleAirportResponce(Airport airport) {
    this.id = airport.getId();
    this.name = airport.getName();
    this.city = airport.getCity();
    this.country = airport.getCountry();
  }
}
