package com.travel.dao;

import com.travel.model.Feedback;
import com.travel.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FeedbackDAO {
    public boolean addFeedback(Feedback feedback) {
        String sql = "INSERT INTO Feedback (FeedbackId, CustomerId, Description) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, feedback.getFeedbackId());
            pstmt.setInt(2, feedback.getCustomerId());
            pstmt.setString(3, feedback.getDescription());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("SQL Error in addFeedback: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public List<Feedback> getAllFeedbacks() {
        List<Feedback> feedbacks = new ArrayList<>();
        String sql = "SELECT * FROM Feedback";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Feedback feedback = new Feedback();
                feedback.setFeedbackId(rs.getInt("FeedbackId"));
                feedback.setCustomerId(rs.getInt("CustomerId"));
                feedback.setDescription(rs.getString("Description"));
                feedbacks.add(feedback);
            }
        } catch (SQLException e) {
            System.err.println("SQL Error in getAllFeedbacks: " + e.getMessage());
            e.printStackTrace();
        }
        return feedbacks;
    }

    public List<Feedback> getFeedbacksByCustomerId(int customerId) {
        List<Feedback> feedbacks = new ArrayList<>();
        String sql = "SELECT * FROM Feedback WHERE CustomerId = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, customerId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Feedback feedback = new Feedback();
                    feedback.setFeedbackId(rs.getInt("FeedbackId"));
                    feedback.setCustomerId(rs.getInt("CustomerId"));
                    feedback.setDescription(rs.getString("Description"));
                    feedbacks.add(feedback);
                }
            }
        } catch (SQLException e) {
            System.err.println("SQL Error in getFeedbacksByCustomerId: " + e.getMessage());
            e.printStackTrace();
        }
        return feedbacks;
    }
}