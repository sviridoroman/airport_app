package com.example.airport.airport;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.UuidGenerator;

import com.example.airport.flight.Flight;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Airport {

  @Id
  @GeneratedValue
  @UuidGenerator
  private String id;
  @Column(nullable = false)
  private String name;
  @Column(nullable = false)
  private String city;
  @Column(nullable = false)
  private String country;

  @OneToMany(mappedBy = "airport", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private List<Flight> flights = new ArrayList<>();

}
