package com.example.demo.exception;

public class EventConflictException extends RuntimeException {

  String message;

  public EventConflictException(String message) {
    super(message);
    this.message = message;
  }
}
