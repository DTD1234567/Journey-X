<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Register - Tour Package Booking System</title>
    <% out.println("<!-- Context Path: " + request.getContextPath() + " -->"); %>

    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/register.css">
</head>
<body>
<h1>Customer Registration</h1>

<% if (request.getAttribute("errorMessage") != null) { %>
<p><%= request.getAttribute("errorMessage") %></p>
<% } %>

<form action="tour-register" method="post">
    <table>
        <tr>
            <td>First Name:</td>
            <td><input type="text" name="firstName" required></td>
        </tr>
        <tr>
            <td>Last Name:</td>
            <td><input type="text" name="lastName" required></td>
        </tr>
        <tr>
            <td>Customer Name:</td>
            <td><input type="text" name="customerName" required></td>
        </tr>
        <tr>
            <td>NIC Number:</td>
            <td><input type="text" name="nicNumber" required></td>
        </tr>
        <tr>
            <td>Gender:</td>
            <td>
                <select name="gender" required>
                    <option value="">-- Select Gender --</option>
                    <option value="Male">Male</option>
                    <option value="Female">Female</option>
                    <option value="Other">Other</option>
                </select>
            </td>
        </tr>
        <tr>
            <td>Password:</td>
            <td><input type="password" name="password" required></td>
        </tr>
        <tr>
            <td>Address:</td>
            <td><input type="text" name="address" required></td>
        </tr>
        <tr>
            <td>Email:</td>
            <td><input type="email" name="email" required></td>
        </tr>
        <tr>
            <td>Phone Number:</td>
            <td><input type="text" name="phoneNumber" required></td>
        </tr>
        <tr>
            <td>
                <input type="submit" value="Register">
            </td>
        </tr>
    </table>
</form>

<p>Already have an account? <a href="tour-login">Login here</a></p>
</body>
</html>