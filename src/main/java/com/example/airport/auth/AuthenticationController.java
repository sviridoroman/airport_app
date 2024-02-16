package com.example.airport.auth;

import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;



@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

  private final AutheticationService autheticationService;

  @PostMapping("/register")
  public ResponseEntity<AuthenticationResponce> register(@RequestBody RegisterRequest request) {
    return ResponseEntity.ok(autheticationService.register(request));
  }

  @PostMapping("/authenticate")
  public ResponseEntity<AuthenticationResponce> authenticate(
      @RequestBody AuthenticationRequest request) {
    return ResponseEntity.ok(autheticationService.authenticate(request));
  }

}
