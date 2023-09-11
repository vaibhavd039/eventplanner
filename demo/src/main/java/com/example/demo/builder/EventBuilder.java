package com.example.demo.builder;

import com.example.demo.domain.Event;
import com.example.demo.domain.UserEntity;
import com.example.demo.dto.EventCreationRequestDTO;
import com.example.demo.dto.EventResponseDTO;
import java.util.stream.Collectors;

public final class EventBuilder {

  private EventBuilder() {
  }

  public static Event buildEventFromRequest(EventCreationRequestDTO requestDTO) {
    Event event = new Event();
    event.setEndTime(requestDTO.getEndTime());
    event.setStartTime(requestDTO.getStartTime());
    return event;
  }

  public static EventResponseDTO buildResponseFromEntity(Event entity) {
    return EventResponseDTO.builder().startTime(entity.getStartTime()).endTime(entity.getEndTime())
     .organizer(entity.getOrganizer().getUsername())
     .attendees(
      entity.getAttendees().stream().map(UserEntity::getUsername).collect(Collectors.toList()))
     .build();
  }

}
