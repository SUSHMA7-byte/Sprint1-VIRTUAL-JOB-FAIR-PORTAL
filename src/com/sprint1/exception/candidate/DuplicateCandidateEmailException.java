package com.sprint1.exception.candidate;

public class DuplicateCandidateEmailException extends Exception {
  public DuplicateCandidateEmailException(String email) {
    super("Candidate email already exists: " + email);
  }
}
