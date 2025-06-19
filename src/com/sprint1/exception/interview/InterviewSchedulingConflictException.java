package com.sprint1.exception.interview;

public class InterviewSchedulingConflictException extends Exception {
  public InterviewSchedulingConflictException(String details) {
    super("Interview scheduling conflict: " + details);
  }
}
