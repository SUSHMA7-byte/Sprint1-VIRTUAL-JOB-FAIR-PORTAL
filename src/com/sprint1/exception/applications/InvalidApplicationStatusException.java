package com.sprint1.exception.applications;

public class InvalidApplicationStatusException extends Exception {
  public InvalidApplicationStatusException(String status) {
    super("Invalid application status: " + status);
  }
}
