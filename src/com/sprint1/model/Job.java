package com.sprint1.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Job {
    private int jobId;
    private String jobTitle;
    private String jobDescription;
    private double salaryPackage;
    private int totalOpenings;
    private LocalDateTime applicationStartDate;
    private LocalDateTime applicationEndDate;
    private String jobLocation;
    private String jobType;
    private int companyId;

    public Job() {}

    public Job(int jobId, String jobTitle, String jobDescription, double salaryPackage, int totalOpenings,
               LocalDateTime applicationStartDate, LocalDateTime applicationEndDate,
               String jobLocation, String jobType) {
        this.jobId = jobId;
        this.jobTitle = jobTitle;
        this.jobDescription = jobDescription;
        this.salaryPackage = salaryPackage;
        this.totalOpenings = totalOpenings;
        this.applicationStartDate = applicationStartDate;
        this.applicationEndDate = applicationEndDate;
        this.jobLocation = jobLocation;
        this.jobType = jobType;
    }



    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public double getSalaryPackage() {
        return salaryPackage;
    }

    public void setSalaryPackage(double salaryPackage) {
        this.salaryPackage = salaryPackage;
    }

    public int getTotalOpenings() {
        return totalOpenings;
    }

    public void setTotalOpenings(int totalOpenings) {
        this.totalOpenings = totalOpenings;
    }

    public LocalDateTime getApplicationStartDate() {
        return applicationStartDate;
    }

    public void setApplicationStartDate(LocalDateTime applicationStartDate) {
        this.applicationStartDate = applicationStartDate;
    }

    public LocalDateTime getApplicationEndDate() {
        return applicationEndDate;
    }

    public void setApplicationEndDate(LocalDateTime applicationEndDate) {
        this.applicationEndDate = applicationEndDate;
    }

    public String getJobLocation() {
        return jobLocation;
    }

    public void setJobLocation(String jobLocation) {
        this.jobLocation = jobLocation;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm");

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

        String startDateStr = (applicationStartDate != null) ? applicationStartDate.format(formatter) : "N/A";
        String endDateStr = (applicationEndDate != null) ? applicationEndDate.format(formatter) : "N/A";

        return "Job Details:\n" +
                "Job ID            : " + jobId + "\n" +
                "Title             : " + jobTitle + "\n" +
                "Description       : " + jobDescription + "\n" +
                "Salary Package    : ₹" + salaryPackage + "\n" +
                "Total Openings    : " + totalOpenings + "\n" +
                "Start Date        : " + startDateStr + "\n" +
                "End Date          : " + endDateStr + "\n" +
                "Location          : " + jobLocation + "\n" +
                "Job Type          : " + jobType + "\n" +
                "Company ID        : " + companyId + "\n";
    }

}
