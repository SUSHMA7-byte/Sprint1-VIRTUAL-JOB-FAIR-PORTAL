package com.sprint1.service.user;

public class Employee extends User {
    private int employeeId;
    private String role;

    public Employee(int employeeId, String fullName, String email, String phoneNumber, String role) {
        super(fullName, email, phoneNumber);
        this.employeeId = employeeId;
        this.role = role;
    }

    @Override
    public String getRoleDescription() {
        return "Employee Role: " + role;
    }

    @Override
    public String toString() {
        return super.toString() + ", Employee ID: " + employeeId + ", Role: " + role;
    }
}
