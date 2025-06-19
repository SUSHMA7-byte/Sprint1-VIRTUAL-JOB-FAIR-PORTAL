
package com.sprint1.exception.job;

public class InvalidApplicationDatesException extends Exception {
  public InvalidApplicationDatesException() {
    super("Application end date must be after start date.");
  }
}
