package com.sprint1.exception.interview;

public class InvalidResultStatusException extends Exception {
  public InvalidResultStatusException(String status) {
    super("Invalid result status: " + status);
  }
}
