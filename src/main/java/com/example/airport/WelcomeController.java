package com.example.airport;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.airport.config.JwtAuthentificationFilter;
import com.example.airport.user.User;
import com.example.airport.user.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/demo")
public class WelcomeController {

  private final JwtAuthentificationFilter jwtAuthentificationFilter;
  private final UserRepository userRepository;

  @GetMapping
  public ResponseEntity<String> welcome() {

    Optional<User> user = userRepository.findByEmail(jwtAuthentificationFilter.getUserEmail());
    String userId = user.get().getId();

    return ResponseEntity.ok("Welcome " + jwtAuthentificationFilter.getUserEmail() + " " + userId);
  }

}
