package com.travel.model;

import java.util.Date;

// Model class for ReportIssue entity
public class ReportIssue {
    private int reportId;
    private int customerId;
    private int employeeId;
    private String issueDetails;
    private Date reportDate;

    // Default constructor
    public ReportIssue() {
    }

    // Parameterized constructor
    public ReportIssue(int reportId, int customerId, int employeeId,
                       String issueDetails, Date reportDate) {
        this.reportId = reportId;
        this.customerId = customerId;
        this.employeeId = employeeId;
        this.issueDetails = issueDetails;
        this.reportDate = reportDate;
    }

    // Getters and Setters
    public int getReportId() {
        return reportId;
    }

    public void setReportId(int reportId) {
        this.reportId = reportId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getIssueDetails() {
        return issueDetails;
    }

    public void setIssueDetails(String issueDetails) {
        this.issueDetails = issueDetails;
    }

    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }
}