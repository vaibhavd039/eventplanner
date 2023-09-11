package com.example.demo.dto;

import java.sql.Timestamp;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ShiftCreationRequestDTO {

  Timestamp startTime;
  Timestamp endTime;
  Long userId;
}
