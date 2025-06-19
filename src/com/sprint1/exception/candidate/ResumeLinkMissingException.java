package com.sprint1.exception.candidate;

public class ResumeLinkMissingException extends Exception {
  public ResumeLinkMissingException() {
    super("Resume link is required but missing.");
  }
}
