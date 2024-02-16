package com.example.airport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.airport.user.Role;
import com.example.airport.user.User;
import com.example.airport.user.UserRepository;

@SpringBootApplication
public class AirportApplication implements CommandLineRunner {

  @Autowired
  private UserRepository userRepository;

  public static void main(String[] args) {
    SpringApplication.run(AirportApplication.class, args);
  }

  public void run(String... args) {
    var user = User.builder().email("admin").firstName("admin").lastName("admin").role(Role.ADMIN)
        .password(new BCryptPasswordEncoder().encode("admin")).build();
    userRepository.save(user);
  }
}
