package com.travel.controller.auth;

import com.travel.dao.CustomerDAO;
import com.travel.model.Customer;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// Servlet for handling customer registration
public class RegisterServlet extends HttpServlet {
    private CustomerDAO customerDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        customerDAO = new CustomerDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Forward to registration page
        request.getRequestDispatcher("/views/auth/register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get registration parameters
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String nicNumber = request.getParameter("nicNumber");
        String gender = request.getParameter("gender");
        String password = request.getParameter("password");
        String address = request.getParameter("address");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phoneNumber");

        // Basic validation
        if (firstName == null || firstName.isEmpty() ||
                lastName == null || lastName.isEmpty() ||
                nicNumber == null || nicNumber.isEmpty() ||
                gender == null || gender.isEmpty() ||
                password == null || password.isEmpty() ||
                address == null || address.isEmpty() ||
                email == null || email.isEmpty() ||
                phoneNumber == null || phoneNumber.isEmpty()) {

            request.setAttribute("errorMessage", "All fields are required");
            request.getRequestDispatcher("/views/auth/register.jsp").forward(request, response);
            return;
        }

        // Check if email already exists
        Customer existingCustomer = customerDAO.getCustomerByEmail(email);
        if (existingCustomer != null) {
            request.setAttribute("errorMessage", "Email already registered");
            request.getRequestDispatcher("/views/auth/register.jsp").forward(request, response);
            return;
        }

        // Create customer object
        Customer customer = new Customer();
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setCustomerName(firstName + " " + lastName);
        customer.setNicNumber(nicNumber);
        customer.setGender(gender);
        customer.setPassword(password);
        customer.setAddress(address);
        customer.setEmail(email);

        // Register customer with phone number
        boolean success = customerDAO.registerCustomer(customer, phoneNumber);

        if (success) {
            request.setAttribute("successMessage", "Registration successful. Please login.");
            request.getRequestDispatcher("/views/auth/login.jsp").forward(request, response);
        } else {
            request.setAttribute("errorMessage", "Registration failed. Please try again.");
            request.getRequestDispatcher("/views/auth/register.jsp").forward(request, response);
        }
    }
}