package com.example.demo.repository;

import com.example.demo.domain.Event;
import java.sql.Timestamp;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

  @Query("SELECT e FROM Event e " +
   "JOIN e.attendees a " +
   "WHERE (e.startTime BETWEEN :startTime AND :endTime OR e.endTime BETWEEN :startTime AND :endTime) "
   +
   "AND a.id IN :attendees")
  List<Event> findConflictingEvents(
   @Param("startTime") Timestamp startTime,
   @Param("endTime") Timestamp endTime,
   @Param("attendees") List<Long> attendees);

  List<Event> findEventByOrganizerId(Long userId);
}
