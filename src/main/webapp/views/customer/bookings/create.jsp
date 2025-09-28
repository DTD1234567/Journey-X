<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.travel.model.Package" %>
<%@ page import="com.travel.model.Customer" %>

<%
    Customer customer = (Customer) session.getAttribute("user");
    if (customer == null) {
        response.sendRedirect(request.getContextPath() + "/tour-login");
        return;
    }

    Package tourPackage = (Package) request.getAttribute("package");
    if (tourPackage == null) {
        response.sendRedirect(request.getContextPath() + "/tour-customer-packages/list?error=Package not found");
        return;
    }

    // Initial total price for display (will be recalculated on submission)
    double initialTotalPrice = (request.getAttribute("initialTotalPrice") != null)
            ? (double) request.getAttribute("initialTotalPrice")
            : tourPackage.getPrice();
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Create Booking</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/customer-booking-create.css">
</head>
<body>
<jsp:include page="/views/common/header-customer.jsp"/>

<h2>Create a Booking</h2>
<p><a href="<%= request.getContextPath() %>/tour-customer-packages/list">Back to Packages</a></p>

<% if (request.getAttribute("errorMessage") != null) { %>
<div class="error"><%= request.getAttribute("errorMessage") %></div>
<% } %>
<% if (request.getAttribute("successMessage") != null) { %>
<div class="success"><%= request.getAttribute("successMessage") %></div>
<% } %>

<h3>Package Details</h3>
<table>
    <tr><td>Name:</td><td><%= tourPackage.getPackageName() %></td></tr>
    <tr><td>Price per Traveler:</td><td>$<%= tourPackage.getPrice() %></td></tr>
    <tr><td>Duration:</td><td><%= tourPackage.getDuration() %></td></tr>
    <tr><td>Start Date:</td><td><%= tourPackage.getStartDate() %></td></tr>
    <tr><td>End Date:</td><td><%= tourPackage.getEndDate() %></td></tr>
    <tr><td>Destinations:</td><td><%= tourPackage.getDestinations() %></td></tr>
</table>

<h3>Booking Form</h3>
<form action="<%= request.getContextPath() %>/customer/bookings/create" method="post">
    <input type="hidden" name="packageId" value="<%= tourPackage.getPackageId() %>">

    <table>
        <tr>
            <td>Customer:</td>
            <td><%= customer.getCustomerName() %> (ID: <%= customer.getCustomerId() %>)</td>
        </tr>
        <tr>
            <td>Preferred Date:</td>
            <td><input type="date" name="bookingDate" required min="<%= tourPackage.getStartDate() %>" max="<%= tourPackage.getEndDate() %>"></td>
        </tr>
        <tr>
            <td>Number of Travelers:</td>
            <td><input type="number" name="numTravelers" min="1" max="20" value="1" required></td>
        </tr>
        <tr>
            <td>Total Price ($):</td>
            <td><input type="number" name="totalPrice" value="<%= initialTotalPrice %>" readonly required></td>
        </tr>
        <tr>
            <td><input type="submit" value="Book Package"></td>
            <td><input type="reset" value="Reset"></td>
        </tr>
    </table>
</form>

<p><a href="<%= request.getContextPath() %>/customer/dashboard">Back to Dashboard</a></p>

<jsp:include page="/views/common/footer.jsp"/>
</body>
</html>