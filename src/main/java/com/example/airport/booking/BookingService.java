package com.example.airport.booking;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.airport.config.JwtAuthentificationFilter;
import com.example.airport.exception.ApiRequestException;
import com.example.airport.flight.Flight;
import com.example.airport.flight.FlightRepository;
import com.example.airport.user.User;
import com.example.airport.user.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BookingService implements IBookingService {

  private final BookingRepository bookedFlightRepository;
  private final FlightRepository flightRepository;
  private final JwtAuthentificationFilter jwtAuthentificationFilter;
  private final UserRepository userRepository;

  @Override
  public ResponseEntity<BookingResponce> getBookingById(String id) {
    Optional<Booking> booking = bookedFlightRepository.findById(id);
    if (booking.isPresent()) {
      BookingResponce responce = new BookingResponce(booking.get());
      return ResponseEntity.ok(responce);
    }
    throw new ApiRequestException("Booking not found " + id);
  }

  @Override
  public List<BookingResponce> getAllBookings() {
    List<BookingResponce> bookings = new ArrayList<>();
    for (Booking booking : bookedFlightRepository.findAll()) {
      bookings.add(new BookingResponce(booking));
    }
    return bookings;
  }

  @Override
  public List<Booking> getAllBookingsByFlightId(String id) {
    return bookedFlightRepository.findByFlightId(id);
  }

  // @Override
  // public List<Booking> getAllBookingsByUserId(String id) {
  // return bookedFlightRepository.findByUserId(id);
  // }

  @Override
  public BookingResponce bookFlight(BookingRequest request) {
    Optional<Flight> optionalFlight = flightRepository.findById(request.getFlightId());
    if (optionalFlight.isPresent()) {
      Flight flight = optionalFlight.get();
      User user = userRepository.findByEmail(jwtAuthentificationFilter.getUserEmail()).get();
      var booking = Booking.builder().flight(flight).user(user).count(request.getCount())
          .total(flight.getPrice() * request.getCount()).build();
      bookedFlightRepository.save(booking);
      return new BookingResponce(booking);
    }
    throw new ApiRequestException("Flight not found " + request.getFlightId());
  }

  @Override
  public void deleteBooking(String id) {
    Optional<Booking> booking = bookedFlightRepository.findById(id);
    if (booking.isPresent()) {
      bookedFlightRepository.deleteById(id);
    }
    throw new ApiRequestException("Booking not found " + id);
  }

}
