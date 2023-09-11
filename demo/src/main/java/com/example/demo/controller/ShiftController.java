package com.example.demo.controller;

import com.example.demo.dto.ShiftCreationRequestDTO;
import com.example.demo.dto.ShiftResponseDTO;
import com.example.demo.service.ShiftService;
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
@RequestMapping("/api/shift")
public class ShiftController {

  @Autowired
  private ShiftService shiftService;

  @PostMapping
  public ResponseEntity<ShiftResponseDTO> createShift(@RequestBody ShiftCreationRequestDTO shift) {
    ShiftResponseDTO createdShift = shiftService.createShift(shift);
    return new ResponseEntity<>(createdShift, HttpStatus.CREATED);
  }

  @GetMapping("/{shiftId}")
  public ResponseEntity<ShiftResponseDTO> getShift(@PathVariable Long shiftId) {
    try {
      return new ResponseEntity<>(shiftService.getShiftById(shiftId), HttpStatus.OK);
    } catch (EntityNotFoundException ex) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @GetMapping("/user/{userId}")
  public ResponseEntity<List<ShiftResponseDTO>> getShiftsForUser(@PathVariable Long userId) {
    List<ShiftResponseDTO> shifts = shiftService.getShiftsForUser(userId);
    return new ResponseEntity<>(shifts, HttpStatus.OK);
  }
}
