package com.sprint1.exception.interview;

public class InterviewNotFoundException extends Exception {
  public InterviewNotFoundException(int interviewId) {
    super("Interview not found with ID: " + interviewId);
  }
}
