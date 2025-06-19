package com.sprint1.model;

import java.time.LocalDateTime;

public abstract class Interview {
    private int interviewId;
    private int applicationId;
    private int jobId;
    private int companyId;
    private int recruiterId;
    private int candidateId;

    private LocalDateTime interviewDatetime;
    private String resultStatus;
    private String interviewType;

    public Interview() {
        // Default constructor
    }

    public Interview(int interviewId, LocalDateTime interviewDatetime, String resultStatus, String interviewType) {
        this.interviewId = interviewId;
        this.interviewDatetime = interviewDatetime;
        this.resultStatus = resultStatus;
        this.interviewType = interviewType;
    }

    // Getters and Setters
    public int getInterviewId() {
        return interviewId;
    }

    public void setInterviewId(int interviewId) {
        this.interviewId = interviewId;
    }

    public int getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(int applicationId) {
        this.applicationId = applicationId;
    }

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public int getRecruiterId() {
        return recruiterId;
    }

    public void setRecruiterId(int recruiterId) {
        this.recruiterId = recruiterId;
    }

    public int getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(int candidateId) {
        this.candidateId = candidateId;
    }

    public LocalDateTime getInterviewDatetime() {
        return interviewDatetime;
    }

    public void setInterviewDatetime(LocalDateTime interviewDatetime) {
        this.interviewDatetime = interviewDatetime;
    }

    public String getResultStatus() {
        return resultStatus;
    }

    public void setResultStatus(String resultStatus) {
        this.resultStatus = resultStatus;
    }

    public String getInterviewType() {
        return interviewType;
    }

    public void setInterviewType(String interviewType) {
        this.interviewType = interviewType;
    }

    //Abstract method to be implemented by HR as wel as Technical Interview subclasses
    public abstract void conductInterview();

    @Override
    public String toString() {
        return "Interview{" +
                "interviewId=" + interviewId +
                ", applicationId=" + applicationId +
                ", jobId=" + jobId +
                ", companyId=" + companyId +
                ", recruiterId=" + recruiterId +
                ", candidateId=" + candidateId +
                ", interviewDatetime=" + interviewDatetime +
                ", resultStatus='" + resultStatus + '\'' +
                ", interviewType='" + interviewType + '\'' +
                '}';
    }
}
