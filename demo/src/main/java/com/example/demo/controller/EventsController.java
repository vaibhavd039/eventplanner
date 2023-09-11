package com.example.demo.controller;

import com.example.demo.dto.EventCreationRequestDTO;
import com.example.demo.dto.EventResponseDTO;
import com.example.demo.exception.EventConflictException;
import com.example.demo.service.EventService;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
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
@RequestMapping("/api/event")
public class EventsController {

  @Autowired
  private EventService eventService;

  @PostMapping
  public ResponseEntity<EventResponseDTO> createEvent(
   @RequestBody EventCreationRequestDTO requestDTO) {
    EventResponseDTO createdEvent = null;
    try {
      createdEvent = eventService.createEvent(requestDTO);
      return new ResponseEntity<>(createdEvent, HttpStatus.CREATED);
    } catch (EventConflictException e) {
      return new ResponseEntity<>(createdEvent, HttpStatus.CONFLICT);
    }
  }

  @GetMapping("/{eventId}")
  public ResponseEntity<EventResponseDTO> getEvent(@PathVariable Long eventId) {
    EventResponseDTO event = eventService.getEventById(eventId);
    try {
      event = eventService.getEventById(eventId);
      return new ResponseEntity<>(event, HttpStatus.OK);
    } catch (EntityNotFoundException e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @GetMapping("/user/{userId}")
  public ResponseEntity<List<EventResponseDTO>> getEventsForUser(@PathVariable Long userId) {
    List<EventResponseDTO> events = eventService.getEventsForUser(userId);
    return new ResponseEntity<>(events, HttpStatus.OK);
  }
}
