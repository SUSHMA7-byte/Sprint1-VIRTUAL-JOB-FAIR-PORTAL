package com.sprint1.exception.employee;

public class InvalidContactNumberException extends Exception {
  public InvalidContactNumberException(String contact) {
    super("Invalid contact number: " + contact);
  }
}
