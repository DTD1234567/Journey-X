// File: src/main/java/com/travel/service/CustomerSupportService.java
package com.travel.service;

import com.travel.dao.CustomerSupportOfficerDAO;
import com.travel.model.CustomerSupportOfficer;
import java.util.List;

public class CustomerSupportService {
    private CustomerSupportOfficerDAO customerSupportOfficerDAO;

    public CustomerSupportService() {
        customerSupportOfficerDAO = new CustomerSupportOfficerDAO();
    }

    // Authenticate customer support officer login
    public CustomerSupportOfficer authenticateCustomerSupportOfficer(String email, String password) {
        return customerSupportOfficerDAO.validateCustomerSupportOfficer(email, password);
    }

    // Get customer support officer by ID
    public CustomerSupportOfficer getCustomerSupportOfficerById(int employeeId) {
        return customerSupportOfficerDAO.getCustomerSupportOfficerById(employeeId);
    }

    // Get customer support officer by email
    public CustomerSupportOfficer getCustomerSupportOfficerByEmail(String email) {
        return customerSupportOfficerDAO.getCustomerSupportOfficerByEmail(email);
    }

    // Add new customer support officer
    public boolean addCustomerSupportOfficer(CustomerSupportOfficer officer) {
        // Check if email already exists
        CustomerSupportOfficer existingOfficer = customerSupportOfficerDAO.getCustomerSupportOfficerByEmail(officer.getEmail());
        if (existingOfficer != null) {
            return false; // Email already exists
        }

        return customerSupportOfficerDAO.addCustomerSupportOfficer(officer);
    }

    // Update customer support officer
    public boolean updateCustomerSupportOfficer(CustomerSupportOfficer officer) {
        return customerSupportOfficerDAO.updateCustomerSupportOfficer(officer);
    }

    // Delete customer support officer
    public boolean deleteCustomerSupportOfficer(int employeeId) {
        return customerSupportOfficerDAO.deleteCustomerSupportOfficer(employeeId);
    }

    // Get all customer support officers
    public List<CustomerSupportOfficer> getAllCustomerSupportOfficers() {
        return customerSupportOfficerDAO.getAllCustomerSupportOfficers();
    }
}