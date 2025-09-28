<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    // Check if package manager is logged in
    String userType = (String) session.getAttribute("userType");
    if (userType == null || !"packageManager".equals(userType)) {
        response.sendRedirect(request.getContextPath() + "/tour-login");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Tour Package Booking System</title>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/style.css">
</head>
<body>
<header>
    <div class="container">
        <h1>Tour Package Booking System - Package Manager Portal</h1>
    </div>
</header>

<nav>
    <div class="container">
        <ul>
            <li><a href="<%= request.getContextPath() %>/tour-package-manager-dashboard">Dashboard</a></li>
            <li>
                <a href="#">Package Management</a>
                <ul>
                    <li><a href="<%= request.getContextPath() %>/tour-package-manager-packages?action=add">Add New Package</a></li>
                    <li><a href="<%= request.getContextPath() %>/tour-package-manager-packages?action=list">View All Packages</a></li>
                </ul>
            </li>
            <li><a href="<%= request.getContextPath() %>/tour-logout">Logout</a></li>
        </ul>
    </div>
</nav>

<div class="container">
    <div class="content">