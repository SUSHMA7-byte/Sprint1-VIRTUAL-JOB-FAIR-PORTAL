package com.sprint1.service.user;

public class CandidateUser extends User {
    private String resumeLink;
    private String collegeName;

    public CandidateUser(String fullName, String email, String phoneNumber, String resumeLink, String collegeName) {
        super(fullName, email, phoneNumber);
        this.resumeLink = resumeLink;
        this.collegeName = collegeName;
    }

    @Override
    public String getRoleDescription() {
        return "Candidate from " + collegeName;
    }

    @Override
    public String toString() {
        return super.toString() + ", College: " + collegeName + ", Resume: " + resumeLink;
    }
}
