<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String userType = (String) session.getAttribute("userType");
    if (userType == null || !"supportOfficer".equals(userType)) {
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
        <h1>Tour Package Booking System - Customer Support Portal</h1>
    </div>
</header>

<nav>
    <div class="container">
        <ul>
            <li><a href="<%= request.getContextPath() %>/tour-support-dashboard">Dashboard</a></li>
            <li>
                <a href="#">Customer Support</a>
                <ul>
                    <li><a href="<%= request.getContextPath() %>/tour-support-officer-panel?action=feedback-list">View All Feedback</a></li>
                    <li><a href="<%= request.getContextPath() %>/tour-support-officer-panel?action=issues-list">View All Issues</a></li>
                    <li><a href="<%= request.getContextPath() %>/tour-support-officer-panel?action=reply-issue">Reply to Issues</a></li>
                    <li><a href="<%= request.getContextPath() %>/tour-support-officer-panel?action=update-status">Mark Issues as Resolved</a></li>
                </ul>
            </li>
            <li><a href="<%= request.getContextPath() %>/tour-logout">Logout</a></li>
        </ul>
    </div>
</nav>

<div class="container">
    <div class="content">