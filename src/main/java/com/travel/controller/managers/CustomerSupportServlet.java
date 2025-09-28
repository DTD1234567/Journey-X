// File: src/main/java/com/travel/controller/customer/CustomerSupportServlet.java
package com.travel.controller.managers;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import com.travel.model.Customer;
import com.travel.model.Feedback;
import com.travel.model.ReportIssue;
import com.travel.service.FeedbackService;
import com.travel.service.ReportIssueService;

@WebServlet({
        "/customer/support/submit-feedback",
        "/customer/support/view-feedback",
        "/customer/support/report-issue",
        "/customer/support/view-issues"
})
public class CustomerSupportServlet extends HttpServlet {
    private FeedbackService feedbackService;
    private ReportIssueService reportIssueService;

    @Override
    public void init() {
        feedbackService = new FeedbackService();
        reportIssueService = new ReportIssueService();
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

        String pathInfo = request.getServletPath();

        switch (pathInfo) {
            case "/customer/support/submit-feedback":
                request.getRequestDispatcher("/views/customer/support/submit-feedback.jsp").forward(request, response);
                break;
            case "/customer/support/view-feedback":
                viewCustomerFeedback(request, response);
                break;
            case "/customer/support/report-issue":
                request.getRequestDispatcher("/views/customer/support/report-issue.jsp").forward(request, response);
                break;
            case "/customer/support/view-issues":
                viewCustomerIssues(request, response);
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

        String pathInfo = request.getServletPath();

        switch (pathInfo) {
            case "/customer/support/submit-feedback":
                submitFeedback(request, response);
                break;
            case "/customer/support/report-issue":
                reportIssue(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    private void viewCustomerFeedback(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("user");
        int customerId = customer.getCustomerId();

        List<Feedback> feedbacks = feedbackService.getFeedbacksByCustomerId(customerId);
        request.setAttribute("feedbacks", feedbacks);

        request.getRequestDispatcher("/views/customer/support/view-feedback.jsp").forward(request, response);
    }

    private void viewCustomerIssues(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("user");
        int customerId = customer.getCustomerId();

        List<ReportIssue> issues = reportIssueService.getReportIssuesByCustomerId(customerId);
        request.setAttribute("issues", issues);

        request.getRequestDispatcher("/views/customer/support/view-issues.jsp").forward(request, response);
    }

    private void submitFeedback(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("user");
        int customerId = customer.getCustomerId();

        String description = request.getParameter("description");
        int employeeId = 1; // Default support officer ID

        // Generate feedback ID
        int feedbackId = (int) (System.currentTimeMillis() % 1000000);

        // Create feedback object
        Feedback feedback = new Feedback();
        feedback.setFeedbackId(feedbackId);
        feedback.setDescription(description);
        feedback.setCustomerId(customerId);
        feedback.setCustomerId(employeeId);

        // Submit feedback
        boolean success = feedbackService.createFeedback(feedback);

        if (success) {
            response.sendRedirect(request.getContextPath() + "/customer/support/view-feedback?success=1");
        } else {
            response.sendRedirect(request.getContextPath() + "/customer/support/submit-feedback?error=1");
        }
    }

    private void reportIssue(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("user");
        int customerId = customer.getCustomerId();

        String issueDetails = request.getParameter("issueDetails");
        int employeeId = 1; // Default support officer ID

        // Generate report ID
        int reportId = (int) (System.currentTimeMillis() % 1000000);

        // Create issue report object
        ReportIssue reportIssue = new ReportIssue();
        reportIssue.setReportId(reportId);
        reportIssue.setCustomerId(customerId);
        reportIssue.setEmployeeId(employeeId);
        reportIssue.setIssueDetails(issueDetails);
        reportIssue.setReportDate(new Date());

        // Submit issue report
        boolean success = reportIssueService.createReportIssue(reportIssue);

        if (success) {
            response.sendRedirect(request.getContextPath() + "/customer/support/view-issues?success=1");
        } else {
            response.sendRedirect(request.getContextPath() + "/customer/support/report-issue?error=1");
        }
    }
}