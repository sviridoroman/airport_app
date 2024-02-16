package com.example.airport.auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.airport.config.JwtService;
import com.example.airport.user.Role;
import com.example.airport.user.User;
import com.example.airport.user.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.var;

@Service
@RequiredArgsConstructor
public class AutheticationService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;

  public AuthenticationResponce register(RegisterRequest request) {
    var user = User.builder().firstName(request.getFirstName()).lastName(request.getLastName())
        .email(request.getEmail()).password(passwordEncoder.encode(request.getPassword()))
        .role(Role.USER).build();
    userRepository.save(user);
    var jwtToken = jwtService.generateToken(user);
    return AuthenticationResponce.builder().token(jwtToken).build();
  }

  public AuthenticationResponce authenticate(AuthenticationRequest request) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
    var user = userRepository.findByEmail(request.getEmail()).orElseThrow();
    var jwtToken = jwtService.generateToken(user);
    return AuthenticationResponce.builder().token(jwtToken).build();
  }
}
