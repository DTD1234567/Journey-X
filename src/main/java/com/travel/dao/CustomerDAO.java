// File: src/main/java/com/travel/dao/CustomerDAO.java
package com.travel.dao;

import com.travel.model.Customer;
import com.travel.util.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {

    // Add a new customer to the database
    public boolean addCustomer(Customer customer) {
        String sql = "INSERT INTO Customer (FirstName, LastName, CustomerName, NICNumber, Gender, Password, Address, Emial) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, customer.getFirstName()); // Fixed index
            pstmt.setString(2, customer.getLastName());  // Fixed index
            pstmt.setString(3, customer.getCustomerName());
            pstmt.setString(4, customer.getNicNumber());
            pstmt.setString(5, customer.getGender());
            pstmt.setString(6, customer.getPassword());
            pstmt.setString(7, customer.getAddress());
            pstmt.setString(8, customer.getEmail()); // Using "Emial" in DB but getEmail() in model

            int rowsAffected = pstmt.executeUpdate();

            // If customer was added successfully, get the generated ID
            if (rowsAffected > 0) {
                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    customer.setCustomerId(rs.getInt(1));
                    return true;
                }
            }
            return false;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Add customer phone number
    public boolean addCustomerPhone(int customerId, String phoneNumber) {
        String sql = "INSERT INTO CustomerPhone (CustomerId, PhoneNumber) VALUES (?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, customerId);
            pstmt.setString(2, phoneNumber);

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Register customer with phone number
    public boolean registerCustomer(Customer customer, String phoneNumber) {
        // First add the customer
        boolean customerAdded = addCustomer(customer);

        // If customer was added successfully, add the phone number
        if (customerAdded) {
            return addCustomerPhone(customer.getCustomerId(), phoneNumber);
        }

        return false;
    }

    // Get customer by ID
    public Customer getCustomerById(int customerId) {
        String sql = "SELECT * FROM Customer WHERE CustomerId = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, customerId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Customer customer = new Customer();
                customer.setCustomerId(rs.getInt("CustomerId"));
                customer.setFirstName(rs.getString("FirstName"));
                customer.setLastName(rs.getString("LastName"));
                customer.setCustomerName(rs.getString("CustomerName"));
                customer.setNicNumber(rs.getString("NICNumber"));
                customer.setGender(rs.getString("Gender"));
                customer.setPassword(rs.getString("Password"));
                customer.setAddress(rs.getString("Address"));
                customer.setEmail(rs.getString("Emial")); // Fixed column name
                return customer;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Get customer by email
    public Customer getCustomerByEmail(String email) {
        String sql = "SELECT * FROM Customer WHERE Emial = ?"; // Fixed column name

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Customer customer = new Customer();
                customer.setCustomerId(rs.getInt("CustomerId"));
                customer.setFirstName(rs.getString("FirstName"));
                customer.setLastName(rs.getString("LastName"));
                customer.setCustomerName(rs.getString("CustomerName"));
                customer.setNicNumber(rs.getString("NICNumber"));
                customer.setGender(rs.getString("Gender"));
                customer.setPassword(rs.getString("Password"));
                customer.setAddress(rs.getString("Address"));
                customer.setEmail(rs.getString("Emial")); // Fixed column name
                return customer;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Update customer information
    public boolean updateCustomer(Customer customer) {
        String sql = "UPDATE Customer SET FirstName = ?, LastName = ?, CustomerName = ?, NICNumber = ?, Gender = ?, Password = ?, Address = ?, Emial = ? WHERE CustomerId = ?"; // Fixed column name

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, customer.getFirstName());
            pstmt.setString(2, customer.getLastName());
            pstmt.setString(3, customer.getCustomerName());
            pstmt.setString(4, customer.getNicNumber());
            pstmt.setString(5, customer.getGender());
            pstmt.setString(6, customer.getPassword());
            pstmt.setString(7, customer.getAddress());
            pstmt.setString(8, customer.getEmail());
            pstmt.setInt(9, customer.getCustomerId());

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Delete a customer
    public boolean deleteCustomer(int customerId) {
        String sql = "DELETE FROM Customer WHERE CustomerId = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, customerId);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Get all customers
    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT * FROM Customer";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Customer customer = new Customer();
                customer.setCustomerId(rs.getInt("CustomerId"));
                customer.setFirstName(rs.getString("FirstName"));
                customer.setLastName(rs.getString("LastName"));
                customer.setCustomerName(rs.getString("CustomerName"));
                customer.setNicNumber(rs.getString("NICNumber"));
                customer.setGender(rs.getString("Gender"));
                customer.setPassword(rs.getString("Password"));
                customer.setAddress(rs.getString("Address"));
                customer.setEmail(rs.getString("Emial")); // Fixed column name
                customers.add(customer);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    // Validate customer login
    public Customer validateCustomer(String email, String password) {
        String sql = "SELECT * FROM Customer WHERE Emial = ? AND Password = ?"; // Fixed column name

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, email);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Customer customer = new Customer();
                customer.setCustomerId(rs.getInt("CustomerId"));
                customer.setFirstName(rs.getString("FirstName"));
                customer.setLastName(rs.getString("LastName"));
                customer.setCustomerName(rs.getString("CustomerName"));
                customer.setNicNumber(rs.getString("NICNumber"));
                customer.setGender(rs.getString("Gender"));
                customer.setPassword(rs.getString("Password"));
                customer.setAddress(rs.getString("Address"));
                customer.setEmail(rs.getString("Emial")); // Fixed column name
                return customer;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}