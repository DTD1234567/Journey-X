package com.travel.dao;

import com.travel.model.Payment;
import com.travel.util.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaymentDAO {

    public boolean addPayment(Payment payment) {
        String sql = "INSERT INTO Payment (PaymentId, PaymentDate, Amount, Method, Status, CustomerId, BookingId, PackageId, CardNumber, ExpiryDate, CVV) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, payment.getPaymentId());
            pstmt.setDate(2, new java.sql.Date(payment.getPaymentDate().getTime()));
            pstmt.setDouble(3, payment.getAmount());
            pstmt.setString(4, payment.getMethod());
            pstmt.setString(5, payment.getStatus());
            pstmt.setInt(6, payment.getCustomerId());
            pstmt.setInt(7, payment.getBookingId());
            pstmt.setInt(8, payment.getPackageId());
            pstmt.setString(9, payment.getCardNumber());
            pstmt.setString(10, payment.getExpiryDate());
            pstmt.setString(11, payment.getCvv());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Payment getPaymentById(int paymentId) {
        String sql = "SELECT * FROM Payment WHERE PaymentId = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, paymentId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Payment payment = new Payment();
                payment.setPaymentId(rs.getInt("PaymentId"));
                payment.setPaymentDate(rs.getDate("PaymentDate"));
                payment.setAmount(rs.getDouble("Amount"));
                payment.setMethod(rs.getString("Method"));
                payment.setStatus(rs.getString("Status"));
                payment.setCustomerId(rs.getInt("CustomerId"));
                payment.setBookingId(rs.getInt("BookingId"));
                payment.setPackageId(rs.getInt("PackageId"));
                payment.setCardNumber(rs.getString("CardNumber"));
                payment.setExpiryDate(rs.getString("ExpiryDate"));
                payment.setCvv(rs.getString("CVV"));
                return payment;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updatePayment(Payment payment) {
        String sql = "UPDATE Payment SET PaymentDate = ?, Amount = ?, Method = ?, Status = ?, CustomerId = ?, BookingId = ?, PackageId = ?, CardNumber = ?, ExpiryDate = ?, CVV = ? WHERE PaymentId = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDate(1, new java.sql.Date(payment.getPaymentDate().getTime()));
            pstmt.setDouble(2, payment.getAmount());
            pstmt.setString(3, payment.getMethod());
            pstmt.setString(4, payment.getStatus());
            pstmt.setInt(5, payment.getCustomerId());
            pstmt.setInt(6, payment.getBookingId());
            pstmt.setInt(7, payment.getPackageId());
            pstmt.setString(8, payment.getCardNumber());
            pstmt.setString(9, payment.getExpiryDate());
            pstmt.setString(10, payment.getCvv());
            pstmt.setInt(11, payment.getPaymentId());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deletePayment(int paymentId) {
        String sql = "DELETE FROM Payment WHERE PaymentId = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, paymentId);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Payment> getAllPayments() {
        List<Payment> payments = new ArrayList<>();
        String sql = "SELECT * FROM Payment";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Payment payment = new Payment();
                payment.setPaymentId(rs.getInt("PaymentId"));
                payment.setPaymentDate(rs.getDate("PaymentDate"));
                payment.setAmount(rs.getDouble("Amount"));
                payment.setMethod(rs.getString("Method"));
                payment.setStatus(rs.getString("Status"));
                payment.setCustomerId(rs.getInt("CustomerId"));
                payment.setBookingId(rs.getInt("BookingId"));
                payment.setPackageId(rs.getInt("PackageId"));
                payment.setCardNumber(rs.getString("CardNumber"));
                payment.setExpiryDate(rs.getString("ExpiryDate"));
                payment.setCvv(rs.getString("CVV"));
                payments.add(payment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return payments;
    }

    public List<Payment> getPaymentsByCustomerId(int customerId) {
        List<Payment> payments = new ArrayList<>();
        String sql = "SELECT * FROM Payment WHERE CustomerId = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, customerId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Payment payment = new Payment();
                payment.setPaymentId(rs.getInt("PaymentId"));
                payment.setPaymentDate(rs.getDate("PaymentDate"));
                payment.setAmount(rs.getDouble("Amount"));
                payment.setMethod(rs.getString("Method"));
                payment.setStatus(rs.getString("Status"));
                payment.setCustomerId(rs.getInt("CustomerId"));
                payment.setBookingId(rs.getInt("BookingId"));
                payment.setPackageId(rs.getInt("PackageId"));
                payment.setCardNumber(rs.getString("CardNumber"));
                payment.setExpiryDate(rs.getString("ExpiryDate"));
                payment.setCvv(rs.getString("CVV"));
                payments.add(payment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return payments;
    }

    public boolean updatePaymentStatus(int paymentId, String status) {
        String sql = "UPDATE Payment SET Status = ? WHERE PaymentId = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, status);
            pstmt.setInt(2, paymentId);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public double getTotalRevenue() {
        String sql = "SELECT SUM(Amount) FROM Payment WHERE Status = 'Completed'";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getDouble(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0;
    }
}