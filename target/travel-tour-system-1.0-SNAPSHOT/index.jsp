<%-- File: src/main/webapp/index.jsp --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
  // Check if user is logged in
  String userType = (String) session.getAttribute("userType");

  if (userType != null) {
    // Redirect to appropriate dashboard based on user type
    if ("customer".equals(userType)) {
      response.sendRedirect("tour-customer-dashboard");
    } else if ("packageManager".equals(userType)) {
      response.sendRedirect("tour-package-manager-dashboard");
    } else if ("bookingManager".equals(userType)) {
      response.sendRedirect("tour-booking-manager-dashboard");
    } else if ("paymentManager".equals(userType)) {
      response.sendRedirect("tour-payment-manager-dashboard");
    } else if ("supportOfficer".equals(userType)) {
      response.sendRedirect("tour-support-dashboard");
    } else if ("systemAdmin".equals(userType)) {
      response.sendRedirect("tour-system-admin-dashboard");
    }
  } else {
    // Redirect to login page if not logged in
    response.sendRedirect("tour-login");
  }
%>