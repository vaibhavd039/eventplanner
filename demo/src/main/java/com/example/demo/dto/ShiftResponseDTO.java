package com.example.demo.dto;

import java.sql.Timestamp;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ShiftResponseDTO {

  Timestamp startTime;
  Timestamp endTime;
  String userId;
}
