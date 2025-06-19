package com.sprint1.model;

public class Company {
    private int companyId;
    private String companyName;
    private String industryType;
    private String contactEmail;
    private String contactPhone;
    private String officeAddress;

    public Company() {
    }

    public Company(int companyId, String companyName, String industryType, String contactEmail, String contactPhone, String officeAddress) {
        this.companyId = companyId;
        this.companyName = companyName;
        this.industryType = industryType;
        this.contactEmail = contactEmail;
        this.contactPhone = contactPhone;
        this.officeAddress = officeAddress;
    }

    public Company( String companyName, String industryType, String contactEmail, String contactPhone, String officeAddress) {
        //this.companyId = companyId;
        this.companyName = companyName;
        this.industryType = industryType;
        this.contactEmail = contactEmail;
        this.contactPhone = contactPhone;
        this.officeAddress = officeAddress;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getIndustryType() {
        return industryType;
    }

    public void setIndustryType(String industryType) {
        this.industryType = industryType;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getOfficeAddress() {
        return officeAddress;
    }

    public void setOfficeAddress(String officeAddress) {
        this.officeAddress = officeAddress;
    }
}
