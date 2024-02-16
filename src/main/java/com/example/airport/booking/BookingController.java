package com.example.airport.booking;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RequiredArgsConstructor
@RestController
@RequestMapping("/booking")
public class BookingController {

  private final BookingService bookingService;

  @GetMapping
  public ResponseEntity<List<BookingResponce>> getBookedFlights() {
    return ResponseEntity.ok(bookingService.getAllBookings());
  }

  @GetMapping("/{id}")
  public ResponseEntity<BookingResponce> getMethodName(@PathVariable String id) {
    return bookingService.getBookingById(id);
  }

  @PostMapping()
  public ResponseEntity<BookingResponce> bookFlight(@RequestBody BookingRequest request) {
    return ResponseEntity.ok(bookingService.bookFlight(request));
  }

  @DeleteMapping("/{id}")
  public void deleteBooking(@PathVariable String id) {
    bookingService.deleteBooking(id);
  }
}
