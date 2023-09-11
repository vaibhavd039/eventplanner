package com.example.demo.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import java.sql.Timestamp;
import java.util.Set;

@Entity
public class Event {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Timestamp startTime;
  private Timestamp endTime;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private UserEntity organizer;

  @ManyToMany
  @JoinColumn(name = "user_id")
  private Set<UserEntity> attendees;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Timestamp getStartTime() {
    return startTime;
  }

  public void setStartTime(Timestamp startTime) {
    this.startTime = startTime;
  }

  public Timestamp getEndTime() {
    return endTime;
  }

  public void setEndTime(Timestamp endTime) {
    this.endTime = endTime;
  }

  public UserEntity getOrganizer() {
    return organizer;
  }

  public void setOrganizer(UserEntity organizer) {
    this.organizer = organizer;
  }

  public Set<UserEntity> getAttendees() {
    return attendees;
  }

  public void setAttendees(Set<UserEntity> attendees) {
    this.attendees = attendees;
  }
}
