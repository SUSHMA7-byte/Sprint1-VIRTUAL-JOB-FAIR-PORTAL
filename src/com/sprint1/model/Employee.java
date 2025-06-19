package com.sprint1.model;

public class Employee {
    private int employeeId;
    private String fullName;
    private String role;
    private String contactNumber;
    private String email;

    public Employee(int employeeId, String fullName, String role, String contactNumber, String email) {
        this.employeeId = employeeId;
        this.fullName = fullName;
        this.role = role;
        this.contactNumber = contactNumber;
        this.email = email;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
