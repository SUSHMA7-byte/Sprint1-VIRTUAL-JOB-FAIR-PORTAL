package com.sprint1.main;

import com.sprint1.dao.CandidateDAO;
import com.sprint1.exception.EmptyFieldException;
import com.sprint1.exception.InvalidEmailException;
import com.sprint1.exception.ValidationException;
import com.sprint1.model.Candidate;
import com.sprint1.model.Company;
import com.sprint1.service.candidate.CandidateServiceImpl;
import com.sprint1.service.company.CompanyServiceImpl;
import com.sprint1.testing.TestMain;
import com.sprint1.util.ValidationUtil;
import com.sprint1.validators.*;

import java.util.Scanner;

public class VirtualJobFairApp {
    public static void main(String[] args) throws ValidationException, InvalidEmailException, EmptyFieldException {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== VIRTUAL JOB FAIR PORTAL =====");
            System.out.println("1. Candidate");
            System.out.println("2. Company");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int mainChoice = sc.nextInt();
            sc.nextLine();

            switch (mainChoice) {
                case 0:
                    TestMain tester = new TestMain(sc);
                    tester.testHandler();
                    break;

                case 1:
                    System.out.println("\n===== CANDIDATE PORTAL =====");
                    System.out.println("1. Register");
                    System.out.println("2. Login");
                    System.out.print("Choose an option: ");
                    int candidateChoice = sc.nextInt();
                    sc.nextLine();

                    CandidateServiceImpl candidateService = new CandidateServiceImpl();
                    CandidateDAO candidateDAO = new CandidateDAO();
                    Candidate currentCandidate = null;

                    if (candidateChoice == 1) {
                        try {
                            System.out.print("Enter Full Name: ");
                            String fullName = sc.nextLine();
                            System.out.print("Enter Email: ");
                            String email = sc.nextLine();
                            System.out.print("Enter Phone Number: ");
                            String phone = sc.nextLine();
                            System.out.print("Enter Resume Link: ");
                            String resume = sc.nextLine();
                            System.out.print("Enter College Name: ");
                            String college = sc.nextLine();
                            System.out.print("Enter Country: ");
                            String country = sc.nextLine();

                            CandidateValidator.validateCandidateFields(fullName, email, phone, resume, college, country);

                            Candidate newCandidate = new Candidate(fullName, email, phone, resume, college, country);
                            // newCandidate.setFullName(fullName);
                            // newCandidate.setEmail(email);
                            // newCandidate.setPhoneNumber(phone);
                            // newCandidate.setResumeLink(resume);
                            // newCandidate.setCollege(college);
                            // newCandidate.setCountry(country);

                            int generatedId = candidateDAO.registerCandidate(newCandidate);
                            newCandidate.setCandidateId(generatedId);
                            currentCandidate = newCandidate;
                            System.out.println("Registration successful. Your Candidate ID is: " + currentCandidate.getCandidateId());

                        } catch (ValidationException ve) {
                            System.out.println("Validation Errors:");
                            for (String error : ve.getErrors()) {
                                System.out.println("- " + error);
                            }
                        } catch (Exception e) {
                            System.out.println("Unexpected Error: " + e.getMessage());
                        }
                    } else if (candidateChoice == 2) {
                        try {
                            System.out.print("Enter Registered Email: ");
                            String email = ValidationUtil.validateEmail(sc.nextLine());
                            Candidate loggedInCandidate = candidateDAO.getCandidateByEmail(email);
                            if (loggedInCandidate != null) {
                                currentCandidate = loggedInCandidate;
                                System.out.println("Login successful.");
                            } else {
                                System.out.println("Login failed. Email not found.");
                                break;
                            }
                        } catch (InvalidEmailException e) {
                            System.out.println(e.getMessage());
                        }
                    } else {
                        System.out.println("Invalid option.");
                        break;
                    }

                    if (currentCandidate != null) {
                        candidateService.candidateDashboard(sc, currentCandidate);
                    }
                    break;

                case 2:
                    System.out.println("\n===== COMPANY PORTAL =====");
                    System.out.println("1. Register");
                    System.out.println("2. Login");
                    System.out.print("Choose an option: ");
                    int companyChoice = sc.nextInt();
                    sc.nextLine();

                    CompanyServiceImpl companyService = new CompanyServiceImpl();
                    int companyId = -1;

                    if (companyChoice == 1) {
                        try {
                            System.out.print("Enter Company Name: ");
                            String name = sc.nextLine();

                            System.out.print("Enter Industry Type: ");
                            String industry = sc.nextLine();

                            System.out.print("Enter Contact Email: ");
                            String email = sc.nextLine();

                            System.out.print("Enter Contact Phone Number: ");
                            String phone = sc.nextLine();

                            System.out.print("Enter Office Address: ");
                            String address = sc.nextLine();

                            CompanyValidator.validateCompanyFields(name, industry, email, phone, address);
                            Company newCompany = new Company(name, industry, email, phone, address);
                            // newCompany.setCompanyName(name);
                            // newCompany.setIndustryType(industry);
                            // newCompany.setContactEmail(email);
                            // newCompany.setContactPhone(phone);
                            // newCompany.setOfficeAddress(address);

                            companyService.registerCompany(newCompany);
                            System.out.println("Registration successful. Please log in to continue.");
                        } catch (ValidationException ve) {
                            System.out.println("Validation Errors:");
                            for (String error : ve.getErrors()) {
                                System.out.println("- " + error);
                            }
                        } catch (Exception e) {
                            System.out.println("Unexpected Error: " + e.getMessage());
                        }
                    } else if (companyChoice == 2) {
                        try {
                            System.out.print("Enter Registered Email: ");
                            String email = ValidationUtil.validateEmail(sc.nextLine());

                            Company loggedInCompany = companyService.loginCompany(email); // fetch by email
                            if (loggedInCompany != null) {
                                companyId = loggedInCompany.getCompanyId();
                                System.out.println("Login successful. Welcome, " + loggedInCompany.getCompanyName());
                            } else {
                                System.out.println("Login failed. Email not found.");
                                break;
                            }
                        } catch (InvalidEmailException e) {
                            System.out.println(e.getMessage());
                        }
                    } else {
                        System.out.println("Invalid option.");
                        break;
                    }

                    if (companyId != -1) {
                        companyService.companyDashboard(sc, companyId);
                    }
                    break;

                case 3:
                    System.out.println("Thank you for using the Virtual Job Fair Portal!");
                    sc.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
