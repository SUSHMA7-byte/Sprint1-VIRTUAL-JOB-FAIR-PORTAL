package com.sprint1.exception.applications;

public class DuplicateApplicationException extends Exception {
  public DuplicateApplicationException() {
    super("Candidate has already applied to this job.");
  }
}
