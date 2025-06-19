package com.sprint1.exception.employee;

public class EmployeeNotFoundException extends Exception {
  public EmployeeNotFoundException(int id) {
    super("Employee not found with ID: " + id);
  }
}
