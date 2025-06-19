
package com.sprint1.exception.job;

public class InvalidJobTypeException extends Exception {
  public InvalidJobTypeException(String type) {
    super("Invalid job type: " + type);
  }
}
