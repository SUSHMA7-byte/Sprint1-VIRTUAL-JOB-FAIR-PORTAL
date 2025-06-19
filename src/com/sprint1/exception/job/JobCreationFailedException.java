
package com.sprint1.exception.job;

public class JobCreationFailedException extends Exception {
  public JobCreationFailedException(String reason) {
    super("Job creation failed: " + reason);
  }
}
