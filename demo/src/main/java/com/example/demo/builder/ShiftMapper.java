package com.example.demo.builder;

import com.example.demo.domain.Shift;
import com.example.demo.dto.ShiftCreationRequestDTO;
import com.example.demo.dto.ShiftResponseDTO;

public final class ShiftMapper {

  private ShiftMapper() {
  }

  public static Shift buildShiftFromRequestDTO(ShiftCreationRequestDTO shiftCreationRequestDTO) {
    Shift shift = new Shift();
    shift.setStartTime(shiftCreationRequestDTO.getStartTime());
    shift.setEndTime(shiftCreationRequestDTO.getEndTime());
    return shift;
  }

  public static ShiftResponseDTO buildShiftCreationResponseDTO(Shift shift) {
    return ShiftResponseDTO.builder().endTime(shift.getEndTime()).startTime(shift.getStartTime())
     .userId(shift.getUserEntity().getUsername()).build();
  }
}
