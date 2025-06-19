
package com.sprint1.exception.job;

public class InvalidOpeningsCountException extends Exception {
  public InvalidOpeningsCountException(int count) {
    super("Total openings must be greater than 0: " + count);
  }
}
