<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.travel.model.Customer" %>

<%
    Customer customer = (Customer) session.getAttribute("user");
    if (customer == null) {
        response.sendRedirect(request.getContextPath() + "/tour-login");
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Customer Dashboard</title>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/customer-dashboard.css">
</head>
<body>
<jsp:include page="/views/common/header-customer.jsp"/>

<h2>Welcome, <%= customer.getCustomerName() %>!</h2>

<h3>Quick Actions</h3>
<table>
    <tr>
        <td><a href="<%= request.getContextPath() %>/tour-customer-packages/list">Browse Packages</a></td>
    </tr>
    <tr>
        <td><a href="<%= request.getContextPath() %>/customer/bookings/create">Book a Package</a></td>
    </tr>
    <tr>
        <td><a href="<%= request.getContextPath() %>/customer/bookings/history">View Booking History</a></td>
    </tr>
    <tr>
        <td><a href="<%= request.getContextPath() %>/customer/payments/history">View Payment History</a></td>
    </tr>
    <tr>
        <td><a href="<%= request.getContextPath() %>/tour-customer-support?action=create">Submit Feedback</a></td>
    </tr>
</table>

<h3>Account Information</h3>
<table>
    <tr>
        <td>Customer ID:</td>
        <td><%= customer.getCustomerId() %></td>
    </tr>
    <tr>
        <td>Email:</td>
        <td><%= customer.getEmail() %></td>
    </tr>
    <tr>
        <td>Phone:</td>
        <td><%= customer.getContactNumber() %></td>
    </tr>
</table>

<jsp:include page="/views/common/footer.jsp"/>
</body>
</html>