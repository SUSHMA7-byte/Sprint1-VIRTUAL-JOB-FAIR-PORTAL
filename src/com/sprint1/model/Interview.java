package com.sprint1.model;

import java.time.LocalDateTime;

public abstract class Interview {
    private int interviewId;
    private LocalDateTime interviewDatetime;
    private String resultStatus;
    private int candidateId;
    private int jobId;
    private String companyId;
    private String interviewType;

    public Interview(int interviewId, LocalDateTime interviewDatetime, String resultStatus, String interviewType) {
        this.interviewId = interviewId;
        this.interviewDatetime = interviewDatetime;
        this.resultStatus = resultStatus;
        this.interviewType=interviewType;
    }

    public String getInterviewType() {
        return interviewType;
    }

    public void setInterviewType(String interviewType) {
        this.interviewType = interviewType;
    }



    public int getInterviewId() {
        return interviewId;
    }

    public void setInterviewId(int interviewId) {
        this.interviewId = interviewId;
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

    public int getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(int candidateId) {
        this.candidateId = candidateId;
    }

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public abstract void conductInterview();

    @Override
    public String toString() {
        return "Interview{" +
                "interviewId=" + interviewId +
                ", interviewDatetime=" + interviewDatetime +
                ", resultStatus='" + resultStatus + '\'' +
                ", candidateId=" + candidateId +
                ", jobId=" + jobId +
                ", companyId=" + companyId +
                '}';
    }
}
