
package com.sprint1.exception.job;

public class JobNotFoundException extends Exception {
  public JobNotFoundException(int jobId) {
    super("Job not found with ID: " + jobId);
  }
}
