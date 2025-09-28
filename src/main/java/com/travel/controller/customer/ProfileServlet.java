package com.travel.controller.customer;

import com.travel.dao.CustomerDAO;
import com.travel.model.Customer;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

// Servlet for handling customer profile
@WebServlet("/customer/profile")
public class ProfileServlet extends HttpServlet {
    private CustomerDAO customerDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        customerDAO = new CustomerDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Check if user is logged in and is a customer
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null ||
                !"customer".equals(session.getAttribute("userType"))) {
            response.sendRedirect(request.getContextPath() + "/tour-login");
            return;
        }

        // Forward to profile page
        request.getRequestDispatcher("/views/customer/profile.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Check if user is logged in and is a customer
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null ||
                !"customer".equals(session.getAttribute("userType"))) {
            response.sendRedirect(request.getContextPath() + "/tour-login");
            return;
        }

        // Get form parameters
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String customerName = request.getParameter("customerName");
        String nicNumber = request.getParameter("nicNumber");
        String gender = request.getParameter("gender");
        String address = request.getParameter("address");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phoneNumber");

        // Get current customer from session
        Customer customer = (Customer) session.getAttribute("user");

        // Update customer object
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setCustomerName(customerName);
        customer.setNicNumber(nicNumber);
        customer.setGender(gender);
        customer.setAddress(address);
        customer.setEmail(email);
        customer.setContactNumber(phoneNumber);

        // Update customer in database
        boolean success = customerDAO.updateCustomer(customer);

        if (success) {
            // Update session with new customer data
            session.setAttribute("user", customer);
            request.setAttribute("successMessage", "Profile updated successfully");
        } else {
            request.setAttribute("errorMessage", "Failed to update profile");
        }

        // Forward back to profile page
        request.getRequestDispatcher("/views/customer/profile.jsp").forward(request, response);
    }
}