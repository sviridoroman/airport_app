package com.example.airport.booking;

import org.hibernate.annotations.UuidGenerator;

import com.example.airport.flight.Flight;
import com.example.airport.user.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Booking {

  @Id
  @GeneratedValue
  @UuidGenerator
  private String id;

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @ManyToOne
  @JoinColumn(name = "flight_id", nullable = false)
  private Flight flight;

  @Column(name = "count", nullable = false)
  private int count;

  @Column(name = "total", nullable = false)
  private int total;
}
