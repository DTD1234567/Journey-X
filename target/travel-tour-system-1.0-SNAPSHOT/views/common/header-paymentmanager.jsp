<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String userType = (String) session.getAttribute("userType");
    if (!"paymentManager".equals(userType)) {
        response.sendRedirect(request.getContextPath() + "/tour-login");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Tour Package Booking System</title>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/headr-payment-manager.css">
</head>
<body>
<header>
    <div class="container">
        <h1>Tour Package Booking System - Payment Manager Portal</h1>
    </div>
</header>

<nav>
    <div class="container">
        <ul>
            <li><a href="<%= request.getContextPath() %>/tour-payment-manager-dashboard">Dashboard</a></li>
            <li>
                <a href="#">Payment Management</a>
                <ul>
                    <li><a href="<%= request.getContextPath() %>/tour-payment-manager-payments?action=list">View All Payments</a></li>
                    <li><a href="<%= request.getContextPath() %>/tour-payment-manager-payments?action=refund">Process Refund</a></li>
                    <li><a href="<%= request.getContextPath() %>/tour-payment-manager-payments?action=revenue">View Revenue Reports</a></li>
                </ul>
            </li>
            <li><a href="<%= request.getContextPath() %>/tour-logout">Logout</a></li>
        </ul>
    </div>
</nav>

<div class="container">
    <div class="content">