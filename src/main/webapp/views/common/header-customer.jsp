<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String userType = (String) session.getAttribute("userType");
    if (userType == null || !"customer".equals(userType)) {
        response.sendRedirect(request.getContextPath() + "/tour-login");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Tour Package Booking System</title>
    <% out.println("<!-- Context Path: " + request.getContextPath() + " -->"); %>

    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/header-customer.css">
</head>
<body>
<header>
    <div class="container">
        <h1>Tour Package Booking System - Customer Portal</h1>
    </div>
</header>

<nav>
    <div class="container">
        <ul>
            <li><a href="<%= request.getContextPath() %>/tour-customer-dashboard">Dashboard</a></li>
            <li><a href="<%= request.getContextPath() %>/tour-customer-profile">Profile</a></li>
            <li>
                <a href="#">Packages</a>
                <ul>
                    <li><a href="<%= request.getContextPath() %>/tour-customer-packages?action=list">View Packages</a></li>
                </ul>
            </li>
            <li><a href="<%= request.getContextPath() %>/tour-logout">Logout</a></li>

        </ul>
    </div>
</nav>

<div class="container">
    <div class="content">