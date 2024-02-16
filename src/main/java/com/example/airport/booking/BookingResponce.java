package com.example.airport.booking;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookingResponce {

  private String id;
  private String flightId;
  private String userId;
  private int count;
  private int total;

  public BookingResponce(Booking booking) {
    this.id = booking.getId();
    this.flightId = booking.getFlight().getId();
    this.userId = booking.getUser().getId();
    this.count = booking.getCount();
    this.total = booking.getTotal();
  }
}
