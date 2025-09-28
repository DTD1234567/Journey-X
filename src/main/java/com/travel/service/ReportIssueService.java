package com.travel.service;

import com.travel.dao.ReportIssueDAO;
import com.travel.model.ReportIssue;
import java.util.List;

public class ReportIssueService {
    private ReportIssueDAO reportIssueDAO;

    public ReportIssueService() {
        reportIssueDAO = new ReportIssueDAO();
    }

    public boolean createReportIssue(ReportIssue reportIssue) {
        if (reportIssue.getIssueDetails() == null || reportIssue.getIssueDetails().trim().isEmpty()) {
            return false;
        }
        if (reportIssue.getCustomerId() <= 0) {
            return false;
        }
        return reportIssueDAO.addReportIssue(reportIssue);
    }

    public ReportIssue getReportIssueById(int reportId) {
        if (reportId <= 0) {
            return null;
        }
        return reportIssueDAO.getReportIssueById(reportId);
    }

    public boolean updateReportIssue(ReportIssue reportIssue) {
        if (reportIssue.getIssueDetails() == null || reportIssue.getIssueDetails().trim().isEmpty()) {
            return false;
        }
        if (reportIssue.getCustomerId() <= 0 ) {
            return false;
        }
        return reportIssueDAO.updateReportIssue(reportIssue);
    }

    public List<ReportIssue> getAllReportIssues() {
        return reportIssueDAO.getAllReportIssues();
    }

    public List<ReportIssue> getReportIssuesByCustomerId(int customerId) {
        return reportIssueDAO.getReportIssuesByCustomerId(customerId);
    }
}