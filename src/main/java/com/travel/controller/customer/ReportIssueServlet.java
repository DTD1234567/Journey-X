package com.travel.controller.customer;

import com.travel.dao.ReportIssueDAO;
import com.travel.model.ReportIssue;
import com.travel.model.Customer;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class ReportIssueServlet extends HttpServlet {
    private ReportIssueDAO reportIssueDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        reportIssueDAO = new ReportIssueDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null ||
                !"customer".equals(session.getAttribute("userType"))) {
            response.sendRedirect(request.getContextPath() + "/tour-login");
            return;
        }

        String action = request.getParameter("action");

        switch (action) {
            case "report-issue":
                showReportIssueForm(request, response);
                break;
            case "issue-list":
                viewIssueList(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null ||
                !"customer".equals(session.getAttribute("userType"))) {
            response.sendRedirect(request.getContextPath() + "/tour-login");
            return;
        }

        String action = request.getParameter("action");

        switch (action) {
            case "report-issue":
                reportIssue(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    private void showReportIssueForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/views/customer/support/report-issue.jsp").forward(request, response);
    }

    private void reportIssue(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("user");
        int customerId = customer.getCustomerId();

        int reportId = (int) (System.currentTimeMillis() % 1000000);
        String issueDetails = request.getParameter("issueDetails");

        ReportIssue reportIssue = new ReportIssue();
        reportIssue.setReportId(reportId);
        reportIssue.setCustomerId(customerId);
        reportIssue.setIssueDetails(issueDetails);
        reportIssue.setReportDate(new Date());

        boolean success = reportIssueDAO.addReportIssue(reportIssue);

        if (success) {
            request.setAttribute("successMessage", "Issue reported successfully. ID: " + reportId);
        } else {
            request.setAttribute("errorMessage", "Failed to report issue");
        }
        showReportIssueForm(request, response);
    }

    private void viewIssueList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("user");
        int customerId = customer.getCustomerId();

        List<ReportIssue> issues = reportIssueDAO.getReportIssuesByCustomerId(customerId);
        request.setAttribute("issues", issues);
        request.getRequestDispatcher("/views/customer/support/issue-list.jsp").forward(request, response);
    }
}