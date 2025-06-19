
package com.sprint1.exception.job;

public class NegativeSalaryException extends Exception {
  public NegativeSalaryException(double salary) {
    super("Salary package cannot be negative: " + salary);
  }
}
