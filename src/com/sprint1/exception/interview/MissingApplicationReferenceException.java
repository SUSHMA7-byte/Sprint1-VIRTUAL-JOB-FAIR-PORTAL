package com.sprint1.exception.interview;

public class MissingApplicationReferenceException extends Exception {
  public MissingApplicationReferenceException(int applicationId) {
    super("Missing application reference for ID: " + applicationId);
  }
}
