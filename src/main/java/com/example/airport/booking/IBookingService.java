package com.example.airport.booking;

import java.util.List;

import org.springframework.http.ResponseEntity;

public interface IBookingService {

  ResponseEntity<BookingResponce> getBookingById(String id);

  List<BookingResponce> getAllBookings();

  List<Booking> getAllBookingsByFlightId(String id);

  // List<Booking> getAllBookingsByUserId(String id);

  BookingResponce bookFlight(BookingRequest request);

  void deleteBooking(String id);
}
