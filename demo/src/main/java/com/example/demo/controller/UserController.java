package com.example.demo.controller;

import com.example.demo.dto.UserCreationRequestDTO;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

  @Autowired
  private UserService userService;

  @PostMapping
  public ResponseEntity<UserCreationRequestDTO> createUser(
   @RequestBody UserCreationRequestDTO user) {
    UserCreationRequestDTO createdUser = userService.createUser(user);
    return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
  }

  @GetMapping("/{userId}")
  public ResponseEntity<UserCreationRequestDTO> getUser(@PathVariable Long userId) {
    UserCreationRequestDTO user = userService.getUserById(userId);
    return new ResponseEntity<>(user, HttpStatus.OK);
  }
}
