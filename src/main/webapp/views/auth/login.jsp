<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login - JOURNEY X</title>
    <% out.println("<!-- Context Path: " + request.getContextPath() + " -->"); %>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/login.css">
</head>
<body>
<header>
    <div class="container">
        <h1>JOURNEY X</h1>
    </div>
</header>

<div class="container">
    <div class="content">
        <h2>Login</h2>

        <%-- Display error message if login failed --%>
        <% if (request.getAttribute("errorMessage") != null) { %>
        <div class="error"><%= request.getAttribute("errorMessage") %></div>
        <% } %>

        <%-- Display success message if registration was successful --%>
        <% if (request.getParameter("registered") != null) { %>
        <div class="success">Registration successful! Please login with your credentials.</div>
        <% } %>

        <form action="<%= request.getContextPath() %>/tour-login" method="post">
            <table>
                <tr>
                    <td>Email:</td>
                    <td><input type="email" name="email" value="<%= request.getAttribute("email") != null ? request.getAttribute("email") : "" %>" required></td>
                </tr>
                <tr>
                    <td>Password:</td>
                    <td><input type="password" name="password" required></td>
                </tr>
                <tr>
                    <td>User Type:</td>
                    <td>
                        <select name="userType" required>
                            <option value="">-- Select User Type --</option>
                            <option value="customer" <%= request.getAttribute("userType") != null && "customer".equals(request.getAttribute("userType")) ? "selected" : "" %>>Customer</option>
                            <option value="packageManager" <%= request.getAttribute("userType") != null && "packageManager".equals(request.getAttribute("userType")) ? "selected" : "" %>>Package Manager</option>
                            <option value="bookingManager" <%= request.getAttribute("userType") != null && "bookingManager".equals(request.getAttribute("userType")) ? "selected" : "" %>>Booking Manager</option>
                            <option value="paymentManager" <%= request.getAttribute("userType") != null && "paymentManager".equals(request.getAttribute("userType")) ? "selected" : "" %>>Payment Manager</option>
                            <option value="supportOfficer" <%= request.getAttribute("userType") != null && "supportOfficer".equals(request.getAttribute("userType")) ? "selected" : "" %>>Customer Support Officer</option>
                            <option value="systemAdmin" <%= request.getAttribute("userType") != null && "systemAdmin".equals(request.getAttribute("userType")) ? "selected" : "" %>>System Administrator</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td colspan="2" style="text-align: center;">
                        <input type="submit" value="Login">
                        <input type="reset" value="Reset">
                    </td>
                </tr>
            </table>
        </form>

        <p>Don't have an account? <a href="<%= request.getContextPath() %>/tour-register">Register here</a></p>
    </div>
</div>

<footer>
    <div class="container">
        <p>&copy; 2023 Tour Package Booking System. All rights reserved.</p>
    </div>
</footer>
</body>
</html>