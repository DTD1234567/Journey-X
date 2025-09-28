package com.travel.controller.customer;

import com.travel.dao.FeedbackDAO;
import com.travel.model.Feedback;
import com.travel.model.Customer;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/tour-customer-support")
public class FeedbackServlet extends HttpServlet {
    private FeedbackDAO feedbackDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        feedbackDAO = new FeedbackDAO();
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
            case "create":
                showCreateFeedbackForm(request, response);
                break;
            case "feedback-list":
                viewFeedbackList(request, response);
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
            case "create":
                createFeedback(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    private void showCreateFeedbackForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/views/customer/support/create-feedback.jsp").forward(request, response);
    }

    private void createFeedback(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("user");
        int customerId = customer.getCustomerId();

        int feedbackId = (int) (System.currentTimeMillis() % 1000000);
        String description = request.getParameter("description");

        Feedback feedback = new Feedback();
        feedback.setFeedbackId(feedbackId);
        feedback.setCustomerId(customerId);
        feedback.setDescription(description);

        boolean success = feedbackDAO.addFeedback(feedback);

        if (success) {
            request.setAttribute("successMessage", "Feedback submitted successfully. ID: " + feedbackId);
        } else {
            request.setAttribute("errorMessage", "Failed to submit feedback");
        }
        showCreateFeedbackForm(request, response);
    }

    private void viewFeedbackList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("user");
        int customerId = customer.getCustomerId();

        List<Feedback> feedbacks = feedbackDAO.getFeedbacksByCustomerId(customerId);
        request.setAttribute("feedbacks", feedbacks);
        request.getRequestDispatcher("/views/customer/support/feedback-list.jsp").forward(request, response);
    }
}