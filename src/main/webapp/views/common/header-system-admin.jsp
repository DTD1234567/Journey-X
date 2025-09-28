<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    // Check if system admin is logged in
    String userType = (String) session.getAttribute("userType");
    if (userType == null || !"systemAdmin".equals(userType)) {
        response.sendRedirect(request.getContextPath() + "/tour-login");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Tour Package Booking System</title>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/header-system-admin.css">
</head>
<body>
<header>
    <div class="container">
        <h1>Tour Package Booking System - System Admin Portal</h1>
    </div>
</header>

<nav>
    <div class="container">
        <ul>
            <li><a href="<%= request.getContextPath() %>/tour-system-admin-dashboard">Dashboard</a></li>
            <li>
                <a href="#">System Overview</a>
                <ul>
                    <li><a href="<%= request.getContextPath() %>/tour-booking-manager-bookings?action=list">View Bookings</a></li>
                    <li><a href="<%= request.getContextPath() %>/tour-system-admin-overview?action=revenue">View Revenue</a></li>
                    <li><a href="<%= request.getContextPath() %>/tour-system-admin-overview?action=feedback">View Feedback</a></li>
                    <li><a href="<%= request.getContextPath() %>/tour-system-admin-overview?action=pending-actions">View Pending Actions</a></li>
                </ul>
            </li>
            <li>
                <a href="#">User Management</a>
                <ul>
                    <li><a href="<%= request.getContextPath() %>/tour-system-admin-users?action=customers">Manage Customers</a></li>
                    <li><a href="<%= request.getContextPath() %>/tour-system-admin-users?action=package-managers">Manage Package Managers</a></li>
                    <li><a href="<%= request.getContextPath() %>/tour-system-admin-users?action=booking-managers">Manage Booking Managers</a></li>
                    <li><a href="<%= request.getContextPath() %>/tour-system-admin-users?action=payment-managers">Manage Payment Managers</a></li>
                    <li><a href="<%= request.getContextPath() %>/tour-system-admin-users?action=support-officers">Manage Support Officers</a></li>
                    <li><a href="<%= request.getContextPath() %>/tour-system-admin-users?action=system-admins">Manage System Admins</a></li>
                </ul>
            </li>
            <li>
                <a href="#">System Configuration</a>
                <ul>
                    <li><a href="<%= request.getContextPath() %>/tour-system-admin-configuration?action=system-messages">Edit System Messages</a></li>
                    <li><a href="<%= request.getContextPath() %>/tour-system-admin-configuration?action=access-control">Control System Access</a></li>
                    <li><a href="<%= request.getContextPath() %>/tour-system-admin-configuration?action=role-assignment">Assign Roles</a></li>
                </ul>
            </li>
            <li><a href="<%= request.getContextPath() %>/tour-logout">Logout</a></li>
        </ul>
    </div>
</nav>

<div class="container">
    <div class="content">