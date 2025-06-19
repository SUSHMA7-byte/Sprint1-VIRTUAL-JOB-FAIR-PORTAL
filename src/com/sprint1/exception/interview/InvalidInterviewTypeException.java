package com.sprint1.exception.interview;

public class InvalidInterviewTypeException extends Exception {
  public InvalidInterviewTypeException(String type) {
    super("Invalid interview type: " + type);
  }
}
