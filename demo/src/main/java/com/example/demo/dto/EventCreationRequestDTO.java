package com.example.demo.dto;

import java.sql.Timestamp;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EventCreationRequestDTO {

  Timestamp startTime;
  Timestamp endTime;
  Long organizerId;
  List<Long> attendees;
}
