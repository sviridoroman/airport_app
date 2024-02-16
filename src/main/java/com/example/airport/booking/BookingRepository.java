package com.example.airport.booking;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, String> {

  List<Booking> findByFlightId(String id);

  // List<Booking> findByUserId(String id);
}
