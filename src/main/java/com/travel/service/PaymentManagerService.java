// File: src/main/java/com/travel/service/PaymentManagerService.java
package com.travel.service;

import com.travel.dao.PaymentManagerDAO;
import com.travel.model.PackageManager;
import com.travel.model.PaymentManager;
import java.util.List;

public class PaymentManagerService {
    private PaymentManagerDAO paymentManagerDAO;

    public PaymentManagerService() {
        paymentManagerDAO = new PaymentManagerDAO();
    }

    // Authenticate payment manager login
    public PaymentManager authenticatePaymentManager(String email, String password) {
        return paymentManagerDAO.validatePaymentManager(email, password);
    }

    // Get payment manager by ID
    public PaymentManager getPaymentManagerById(int employeeId) {
        return paymentManagerDAO.getPaymentManagerById(employeeId);
    }

    // Get payment manager by email
    public PaymentManager getPaymentManagerByEmail(String email) {
        return paymentManagerDAO.getPaymentManagerByEmail(email);
    }

    // Add new payment manager
    public boolean addPaymentManager(PaymentManager manager) {
        // Check if email already exists
        PaymentManager existingManager = paymentManagerDAO.getPaymentManagerByEmail(manager.getEmail());
        if (existingManager != null) {
            return false; // Email already exists
        }

        return paymentManagerDAO.addPaymentManager(manager);
    }
    // Update payment manager
    public boolean updatePaymentManager(PaymentManager manager) {
        return paymentManagerDAO.updatePaymentManager(manager);
    }

    // Delete payment manager
    public boolean deletePaymentManager(int employeeId) {
        return paymentManagerDAO.deletePaymentManager(employeeId);
    }

    // Get all payment managers
    public List<PaymentManager> getAllPaymentManagers() {
        return paymentManagerDAO.getAllPaymentManagers();
    }
}