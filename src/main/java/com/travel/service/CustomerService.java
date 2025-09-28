package com.travel.service;

import com.travel.dao.CustomerDAO;
import com.travel.model.Customer;
import java.util.List;

// Service class for Customer business logic
public class CustomerService {
    private CustomerDAO customerDAO;

    // Default constructor
    public CustomerService() {
        customerDAO = new CustomerDAO();
    }

    // Register a new customer
    public boolean registerCustomer(Customer customer) {
        // Check if customer with same email already exists
        Customer existingCustomer = customerDAO.getCustomerByEmail(customer.getEmail());
        if (existingCustomer != null) {
            return false; // Customer already exists
        }

        return customerDAO.addCustomer(customer);
    }

    // Get customer by ID
    public Customer getCustomerById(int customerId) {
        return customerDAO.getCustomerById(customerId);
    }

    // Get customer by email
    public Customer getCustomerByEmail(String email) {
        return customerDAO.getCustomerByEmail(email);
    }

    // Update customer information
    public boolean updateCustomer(Customer customer) {
        return customerDAO.updateCustomer(customer);
    }

    // Delete a customer
    public boolean deleteCustomer(int customerId) {
        return customerDAO.deleteCustomer(customerId);
    }

    // Get all customers
    public List<Customer> getAllCustomers() {
        return customerDAO.getAllCustomers();
    }

    // Customer login
    public Customer login(String email, String password) {
        return customerDAO.validateCustomer(email, password);
    }
}