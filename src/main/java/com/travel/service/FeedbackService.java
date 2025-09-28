package com.travel.service;

import com.travel.dao.FeedbackDAO;
import com.travel.model.Feedback;
import java.util.List;

public class FeedbackService {
    private FeedbackDAO feedbackDAO;

    public FeedbackService() {
        feedbackDAO = new FeedbackDAO();
    }

    public boolean createFeedback(Feedback feedback) {
        if (feedback.getDescription() == null || feedback.getDescription().trim().isEmpty()) {
            return false;
        }
        if (feedback.getCustomerId() <= 0) {
            return false;
        }
        return feedbackDAO.addFeedback(feedback);
    }

    public List<Feedback> getAllFeedbacks() {
        return feedbackDAO.getAllFeedbacks();
    }

    public List<Feedback> getFeedbacksByCustomerId(int customerId) {
        return feedbackDAO.getFeedbacksByCustomerId(customerId);
    }
}