package com.example.demo.service;

import com.example.demo.builder.EventBuilder;
import com.example.demo.domain.Event;
import com.example.demo.domain.UserEntity;
import com.example.demo.dto.EventCreationRequestDTO;
import com.example.demo.dto.EventResponseDTO;
import com.example.demo.exception.EventConflictException;
import com.example.demo.repository.EventRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventService {

  @Autowired
  private EventRepository eventRepository;

  @Autowired
  private UserService userService;

  public EventResponseDTO createEvent(EventCreationRequestDTO requestDTO) {
    if (hasConflicts(requestDTO)) {
      throw new EventConflictException("Event conflicts with existing events");
    }
    Event event = EventBuilder.buildEventFromRequest(requestDTO);
    UserEntity organizer = userService.getUserEntityById(requestDTO.getOrganizerId());
    Set<UserEntity> attendees = userService.getUserEntityByIdsIN(requestDTO.getAttendees());
    event.setAttendees(attendees);
    event.setOrganizer(organizer);
    eventRepository.save(event);
    return EventBuilder.buildResponseFromEntity(event);
  }


  public EventResponseDTO getEventById(Long eventId) {
    Event event = eventRepository.findById(eventId).orElseThrow(EntityNotFoundException::new);
    return EventBuilder.buildResponseFromEntity(event);
  }

  public List<EventResponseDTO> getEventsForUser(Long userId) {
    List<Event> eventList = eventRepository.findEventByOrganizerId(userId);
    return eventList.stream().map(event -> {
      return EventBuilder.buildResponseFromEntity(event);
    }).collect(
     Collectors.toList());
  }

  private boolean hasConflicts(EventCreationRequestDTO requestDTO) {
    List<Event> existingEvents = eventRepository.findConflictingEvents(
     requestDTO.getStartTime(), requestDTO.getEndTime(), requestDTO.getAttendees());
    return !existingEvents.isEmpty();
  }
}
