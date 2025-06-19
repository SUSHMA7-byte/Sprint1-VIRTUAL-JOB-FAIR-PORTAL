package com.sprint1.exception.interview;

public class RecruiterNotFoundException extends Exception {
  public RecruiterNotFoundException(int recruiterId) {
    super("Recruiter not found with ID: " + recruiterId);
  }
}
