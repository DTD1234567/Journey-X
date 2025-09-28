package com.travel.service;

import com.travel.dao.PaymentDAO;
import com.travel.model.Payment;
import java.util.List;

public class PaymentService {
    private PaymentDAO paymentDAO;

    public PaymentService() {
        paymentDAO = new PaymentDAO();
    }

    public boolean processPayment(Payment payment) {
        if (payment.getCustomerId() <= 0 || payment.getBookingId() <= 0 || payment.getPackageId() <= 0) {
            return false;
        }
        if (payment.getAmount() <= 0 || payment.getMethod() == null || payment.getMethod().isEmpty()) {
            return false;
        }
        if (payment.getPaymentDate() == null || payment.getStatus() == null || payment.getStatus().isEmpty()) {
            return false;
        }
        if (payment.getCardNumber() == null || payment.getCardNumber().length() != 16 ||
                payment.getExpiryDate() == null || payment.getCvv() == null || payment.getCvv().length() != 3) {
            return false;
        }
        return paymentDAO.addPayment(payment);
    }

    public Payment getPaymentById(int paymentId) {
        return paymentDAO.getPaymentById(paymentId);
    }

    public boolean updatePayment(Payment payment) {
        if (payment.getPaymentId() <= 0 || payment.getCustomerId() <= 0 || payment.getBookingId() <= 0 || payment.getPackageId() <= 0) {
            return false;
        }
        if (payment.getAmount() <= 0 || payment.getMethod() == null || payment.getMethod().isEmpty()) {
            return false;
        }
        if (payment.getPaymentDate() == null || payment.getStatus() == null || payment.getStatus().isEmpty()) {
            return false;
        }
        if (payment.getCardNumber() == null || payment.getCardNumber().length() != 16 ||
                payment.getExpiryDate() == null || payment.getCvv() == null || payment.getCvv().length() != 3) {
            return false;
        }
        return paymentDAO.updatePayment(payment);
    }

    public boolean completePayment(int paymentId) {
        return paymentDAO.updatePaymentStatus(paymentId, "Completed");
    }

    public boolean failPayment(int paymentId) {
        return paymentDAO.updatePaymentStatus(paymentId, "Failed");
    }

    public List<Payment> getAllPayments() {
        return paymentDAO.getAllPayments();
    }

    public List<Payment> getPaymentsByCustomerId(int customerId) {
        return paymentDAO.getPaymentsByCustomerId(customerId);
    }

    public double getTotalRevenue() {
        return paymentDAO.getTotalRevenue();
    }
}