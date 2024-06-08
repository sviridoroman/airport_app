package com.example.airport.flight;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.UuidGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import com.example.airport.airport.Airport;
import com.example.airport.booking.Booking;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class Flight {

  @Id
  @GeneratedValue
  @UuidGenerator
  private String id;

  @Column(name = "arrivalDate", nullable = false)
  private LocalDate arrivalDate;

  @ManyToOne
  @JoinColumn(name = "airport_id", nullable = false)
  private Airport airport;

  @Column(name = "price", nullable = false)
  private int price;

  @Column(name = "completed", nullable = false)
  private boolean completed;

  @OneToMany(mappedBy = "flight", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private List<Booking> bookings = new ArrayList<>();
}
