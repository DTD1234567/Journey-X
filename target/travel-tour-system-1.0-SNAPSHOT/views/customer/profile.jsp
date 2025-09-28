<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.travel.model.Customer" %>

<%
    Customer customer = (Customer) session.getAttribute("user");
    if (customer == null) {
        response.sendRedirect(request.getContextPath() + "/tour-login");
        return;
    }

    String successMessage = (String) request.getAttribute("successMessage");
    String errorMessage = (String) request.getAttribute("errorMessage");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>My Profile - Customer</title>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/style.css">
</head>
<body>
<jsp:include page="/views/common/header-customer.jsp"/>

<h2>My Profile</h2>

<% if (successMessage != null) { %>
<div class="success"><%= successMessage %></div>
<% } %>
<% if (errorMessage != null) { %>
<div class="error"><%= errorMessage %></div>
<% } %>

<h3>Personal Information</h3>
<form action="<%= request.getContextPath() %>/tour-customer-profile" method="post">
    <table>
        <tr>
            <td>Customer ID:</td>
            <td><%= customer.getCustomerId() %></td>
        </tr>
        <tr>
            <td>First Name:</td>
            <td><input type="text" name="firstName" value="<%= customer.getFirstName() %>" required></td>
        </tr>
        <tr>
            <td>Last Name:</td>
            <td><input type="text" name="lastName" value="<%= customer.getLastName() %>" required></td>
        </tr>
        <tr>
            <td>Full Name:</td>
            <td><%= customer.getCustomerName() %></td>
        </tr>
        <tr>
            <td>NIC Number:</td>
            <td><input type="text" name="nicNumber" value="<%= customer.getNicNumber() %>" required></td>
        </tr>
        <tr>
            <td>Gender:</td>
            <td>
                <select name="gender" required>
                    <option value="Male" <%= "Male".equals(customer.getGender()) ? "selected" : "" %>>Male</option>
                    <option value="Female" <%= "Female".equals(customer.getGender()) ? "selected" : "" %>>Female</option>
                    <option value="Other" <%= "Other".equals(customer.getGender()) ? "selected" : "" %>>Other</option>
                </select>
            </td>
        </tr>
        <tr>
            <td>Address:</td>
            <td><input type="text" name="address" value="<%= customer.getAddress() %>" required></td>
        </tr>
        <tr>
            <td>Email:</td>
            <td><input type="email" name="email" value="<%= customer.getEmail() %>" required></td>
        </tr>
        <tr>
            <td>Phone Number:</td>
            <td><input type="text" name="phoneNumber" value="<%= customer.getContactNumber() %>" required></td>
        </tr>
        <tr>
            <td colspan="2" style="text-align: center;">
                <input type="submit" name="action" value="Update Profile">
            </td>
        </tr>
    </table>
</form>

<h3>Change Password</h3>
<form action="<%= request.getContextPath() %>/tour-customer-profile" method="post">
    <table>
        <tr>
            <td>Current Password:</td>
            <td><input type="password" name="currentPassword" required></td>
        </tr>
        <tr>
            <td>New Password:</td>
            <td><input type="password" name="newPassword" required></td>
        </tr>
        <tr>
            <td>Confirm New Password:</td>
            <td><input type="password" name="confirmPassword" required></td>
        </tr>
        <tr>
            <td colspan="2" style="text-align: center;">
                <input type="submit" name="action" value="Change Password">
            </td>
        </tr>
    </table>
</form>

<p><a href="<%= request.getContextPath() %>/tour-customer-dashboard">Back to Dashboard</a></p>

<jsp:include page="/views/common/footer.jsp"/>
</body>
</html>