package com.sprint1.exception.company;

public class CompanyNotFoundException extends Exception {
  public CompanyNotFoundException(int companyId) {
    super("Company not found with ID: " + companyId);
  }
}
