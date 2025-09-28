// File: src/main/java/com/travel/controller/managers/PackageManagerDashboardServlet.java
package com.travel.controller.managers;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import com.travel.model.PackageManager;

public class PackageManagerDashboardServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        // Check if user is logged in as package manager
        if (session == null || session.getAttribute("user") == null ||
                !"packageManager".equals(session.getAttribute("userType"))) {
            response.sendRedirect(request.getContextPath() + "/tour-login");
            return;
        }

        // Get package manager from session
        PackageManager manager = (PackageManager) session.getAttribute("user");

        // Set manager as request attribute
        request.setAttribute("manager", manager);

        // Forward to dashboard page
        request.getRequestDispatcher("/views/package-manager/dashboard.jsp").forward(request, response);
    }
}