package com.sprint1.exception.employee;

public class DuplicateEmployeeEmailException extends Exception {
  public DuplicateEmployeeEmailException(String email) {
    super("Employee email already exists: " + email);
  }
}
