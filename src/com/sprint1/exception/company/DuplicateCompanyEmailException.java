package com.sprint1.exception.company;

public class DuplicateCompanyEmailException extends Exception {
  public DuplicateCompanyEmailException(String email) {
    super("Company email already exists: " + email);
  }
}
