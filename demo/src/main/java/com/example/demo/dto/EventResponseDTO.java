package com.example.demo.dto;

import java.sql.Timestamp;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EventResponseDTO {

  Timestamp startTime;
  Timestamp endTime;
  List<String> attendees;
  String organizer;
}
