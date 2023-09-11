package com.example.demo.dto;

import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FavorableShiftDTO {

  Timestamp startTime;
  Timestamp endTime;
}
