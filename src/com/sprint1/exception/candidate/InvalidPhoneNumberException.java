package com.sprint1.exception.candidate;

public class InvalidPhoneNumberException extends Exception {
  public InvalidPhoneNumberException(String phone) {
    super("Invalid phone number: " + phone);
  }
}
