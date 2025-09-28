// File: src/main/java/com/travel/controller/managers/PaymentManagerDashboardServlet.java
package com.travel.controller.managers;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import com.travel.model.PaymentManager;

public class PaymentManagerDashboardServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        // Check if user is logged in as payment manager
        if (session == null || session.getAttribute("user") == null ||
                !"paymentManager".equals(session.getAttribute("userType"))) {
            response.sendRedirect(request.getContextPath() + "/tour-login");
            return;
        }

        // Get payment manager from session
        PaymentManager manager = (PaymentManager) session.getAttribute("user");

        // Set manager as request attribute
        request.setAttribute("manager", manager);

        // Forward to dashboard page
        request.getRequestDispatcher("/views/payment-manager/dashboard.jsp").forward(request, response);
    }
}