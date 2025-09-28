// File: src/main/java/com/travel/controller/auth/LoginServlet.java
package com.travel.controller.auth;

import com.travel.dao.*;
import com.travel.model.*;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet {
    private CustomerDAO customerDAO;
    private PackageManagerDAO packageManagerDAO;
    private BookingManagerDAO bookingManagerDAO;
    private PaymentManagerDAO paymentManagerDAO;
    private CustomerSupportOfficerDAO customerSupportOfficerDAO;
    private SystemAdminDAO systemAdminDAO;

    @Override
    public void init() {
        customerDAO = new CustomerDAO();
        packageManagerDAO = new PackageManagerDAO();
        bookingManagerDAO = new BookingManagerDAO();
        paymentManagerDAO = new PaymentManagerDAO();
        customerSupportOfficerDAO = new CustomerSupportOfficerDAO();
        systemAdminDAO = new SystemAdminDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/views/auth/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String userType = request.getParameter("userType");

        if (userType == null || userType.isEmpty()) {
            setLoginError(request, response, "Please select a user type", email, userType);
            return;
        }

        Object user = authenticateUser(email, password, userType);

        if (user != null) {
            createSession(request, user, userType);
            response.sendRedirect(request.getContextPath() + getDashboardUrl(userType));
        } else {
            String errorMsg = emailExistsInAnyTable(email) ?
                    "Email exists but not for the selected user type" :
                    "Invalid email or password for the selected user type";
            setLoginError(request, response, errorMsg, email, userType);
        }
    }

    private Object authenticateUser(String email, String password, String userType) {
        switch (userType) {
            case "customer": return customerDAO.validateCustomer(email, password);
            case "packageManager": return packageManagerDAO.validatePackageManager(email, password);
            case "bookingManager": return bookingManagerDAO.validateBookingManager(email, password);
            case "paymentManager": return paymentManagerDAO.validatePaymentManager(email, password);
            case "supportOfficer": return customerSupportOfficerDAO.validateCustomerSupportOfficer(email, password);
            case "systemAdmin": return systemAdminDAO.validateSystemAdmin(email, password);
            default: return null;
        }
    }

    private boolean emailExistsInAnyTable(String email) {
        return customerDAO.getCustomerByEmail(email) != null ||
                packageManagerDAO.getPackageManagerByEmail(email) != null ||
                bookingManagerDAO.getBookingManagerByEmail(email) != null ||
                paymentManagerDAO.getPaymentManagerByEmail(email) != null ||
                customerSupportOfficerDAO.getCustomerSupportOfficerByEmail(email) != null ||
                systemAdminDAO.getSystemAdminByEmail(email) != null;
    }

    private void createSession(HttpServletRequest request, Object user, String userType) {
        HttpSession session = request.getSession();
        session.setAttribute("user", user);
        session.setAttribute("userType", userType);
    }

    private String getDashboardUrl(String userType) {
        return switch (userType) {
            case "customer" -> "/tour-customer-dashboard";
            case "packageManager" -> "/tour-package-manager-dashboard";
            case "bookingManager" -> "/tour-booking-manager-dashboard";
            case "paymentManager" -> "/tour-payment-manager-dashboard";
            case "supportOfficer" -> "/tour-support-dashboard";
            case "systemAdmin" -> "/tour-system-admin-dashboard";
            default -> "/tour-login";
        };
    }

    private void setLoginError(HttpServletRequest request, HttpServletResponse response, String errorMsg, String email, String userType) throws ServletException, IOException {
        request.setAttribute("errorMessage", errorMsg);
        request.setAttribute("email", email);
        request.setAttribute("userType", userType);
        request.getRequestDispatcher("/views/auth/login.jsp").forward(request, response);
    }
}