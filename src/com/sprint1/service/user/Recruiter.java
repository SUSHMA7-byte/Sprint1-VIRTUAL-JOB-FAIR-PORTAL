package com.sprint1.service.user;

public class Recruiter extends Employee {
    private String companyName;

    public Recruiter(int employeeId, String fullName, String email, String phoneNumber, String role, String companyName) {
        super(employeeId, fullName, email, phoneNumber, role);
        this.companyName = companyName;
    }

    @Override
    public String getRoleDescription() {
        return "Recruiter from " + companyName;
    }

    @Override
    public String toString() {
        return super.toString() + ", Company: " + companyName;
    }
}
