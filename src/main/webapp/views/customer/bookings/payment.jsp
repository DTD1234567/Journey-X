<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.travel.model.Customer" %>
<%@ page import="com.travel.model.Booking" %>
<%@ page import="com.travel.model.Package" %>

<%
    Customer customer = (Customer) session.getAttribute("user");
    if (customer == null) {
        response.sendRedirect(request.getContextPath() + "/tour-login");
        return;
    }

    Booking booking = (Booking) request.getAttribute("booking");
    Package pkg = (Package) request.getAttribute("package");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Payment - Customer</title>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/style.css">
</head>
<body>
<jsp:include page="/views/common/header-customer.jsp"/>

<h2>Make Payment</h2>

<% if (request.getAttribute("errorMessage") != null) { %>
<div class="error"><%= request.getAttribute("errorMessage") %></div>
<% } %>
<% if (request.getAttribute("successMessage") != null) { %>
<div class="success"><%= request.getAttribute("successMessage") %></div>
<% } %>

<p><a href="<%= request.getContextPath() %>/tour-customer-packages/list">Back to Packages</a></p>

<% if (booking != null && pkg != null) { %>
<h3>Booking Details</h3>
<table>
    <tr>
        <td>Booking ID:</td>
        <td><%= booking.getBookingId() %></td>
    </tr>
    <tr>
        <td>Package:</td>
        <td><%= pkg.getPackageName() %></td>
    </tr>
    <tr>
        <td>Price:</td>
        <td>$<%= pkg.getPrice() %></td>
    </tr>
    <tr>
        <td>Number of Travelers:</td>
        <td><%= booking.getNumTravelers() %></td>
    </tr>
    <tr>
        <td>Total Amount:</td>
        <td>$<%= pkg.getPrice() * booking.getNumTravelers() %></td>
    </tr>
</table>

<h3>Payment Information</h3>
<form action="<%= request.getContextPath() %>/customer/bookings/payment" method="post">
    <input type="hidden" name="bookingId" value="<%= booking.getBookingId() %>">
    <input type="hidden" name="packageId" value="<%= pkg.getPackageId() %>">
    <input type="hidden" name="customerId" value="<%= customer.getCustomerId() %>">
    <input type="hidden" name="amount" value="<%= pkg.getPrice() * booking.getNumTravelers() %>">

    <table>
        <tr>
            <td>Card Number:</td>
            <td><input type="text" name="cardNumber" pattern="\d{16}" required></td>
        </tr>
        <tr>
            <td>Expiry Date:</td>
            <td><input type="text" name="expiryDate" pattern="\d{2}/\d{2}" required></td>
        </tr>
        <tr>
            <td>CVV:</td>
            <td><input type="text" name="cvv" pattern="\d{3}" required></td>
        </tr>
        <tr>
            <td colspan="2" style="text-align: center;">
                <input type="submit" value="Complete Payment">
            </td>
        </tr>
    </table>
</form>

<div>
    <h4>Payment Security:</h4>
    <ul>
        <li>Your payment information is encrypted and secure</li>
        <li>We do not store your complete card details</li>
        <li>Payment confirmation will be sent to your email</li>
    </ul>
</div>
<% } else { %>
<p>Booking or package information not found.</p>
<% } %>

<jsp:include page="/views/common/footer.jsp"/>
</body>
</html>