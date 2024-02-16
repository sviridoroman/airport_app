package com.example.airport.user;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/users")
public class UserController {

  private final UserRepository userRepository;

  public UserController(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @GetMapping
  Iterable<User> getUsers() {
    return userRepository.findAll();
  }

  @GetMapping("/{id}")
  Optional<User> getUserById(@PathVariable String id) {
    return userRepository.findById(id);
  }

  @PostMapping
  User postUser(@RequestBody User user) {
    return userRepository.save(user);
  }

  @PutMapping("/{id}")
  ResponseEntity<User> putUser(@PathVariable String id, @RequestBody User user) {
    return (!userRepository.existsById(id))
        ? new ResponseEntity<>(userRepository.save(user), HttpStatus.CREATED)
        : new ResponseEntity<>(userRepository.save(user), HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  void deleteUser(@PathVariable String id) {
    this.userRepository.deleteById(id);
  }
}
