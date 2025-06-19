package com.sprint1.exception.applications;

public class ApplicationNotFoundException extends Exception {
  public ApplicationNotFoundException(int applicationId) {
    super("Application not found with ID: " + applicationId);
  }
}
