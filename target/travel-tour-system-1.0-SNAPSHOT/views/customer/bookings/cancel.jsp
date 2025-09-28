<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.travel.model.Customer" %>
<%@ page import="com.travel.model.Booking" %>
<%@ page import="java.util.List" %>

<%
    Customer customer = (Customer) session.getAttribute("user");
    if (customer == null) {
        response.sendRedirect(request.getContextPath() + "/tour-login");
        return;
    }

    List<Booking> bookings = (List<Booking>) request.getAttribute("bookings");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Cancel Booking - Customer</title>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/style.css">
</head>
<body>
<jsp:include page="/views/common/header-customer.jsp"/>

<h2>Cancel Booking</h2>

<% if (request.getAttribute("errorMessage") != null) { %>
<div class="error"><%= request.getAttribute("errorMessage") %></div>
<% } %>
<% if (request.getAttribute("successMessage") != null) { %>
<div class="success"><%= request.getAttribute("successMessage") %></div>
<% } %>

<p><a href="<%= request.getContextPath() %>/tour-customer-dashboard">Back to Dashboard</a></p>

<h3>Your Bookings</h3>
<% if (bookings != null && !bookings.isEmpty()) { %>
<table>
    <tr>
        <th>Booking ID</th>
        <th>Booking Date</th>
        <th>Status</th>
        <th>Action</th>
    </tr>
    <% for (Booking booking : bookings) { %>
    <tr>
        <td><%= booking.getBookingId() %></td>
        <td><%= booking.getBookingDate() %></td>
        <td><%= booking.getConfirmationStatus() %></td>
        <td>
            <% if (!"Cancelled".equals(booking.getConfirmationStatus())) { %>
            <form action="<%= request.getContextPath() %>/customer/bookings/cancel" method="post" style="display: inline;">
                <input type="hidden" name="bookingId" value="<%= booking.getBookingId() %>">
                <input type="submit" value="Cancel Booking" onclick="return confirm('Are you sure you want to cancel this booking?');">
            </form>
            <% } else { %>
            Already Cancelled
            <% } %>
        </td>
    </tr>
    <% } %>
</table>
<% } else { %>
<p>No bookings found.</p>
<% } %>

<div>
    <h4>Important:</h4>
    <ul>
        <li>You can only cancel bookings that are not already cancelled</li>
        <li>Cancellation cannot be undone</li>
        <li>Refund policy applies to cancelled bookings</li>
    </ul>
</div>

<jsp:include page="/views/common/footer.jsp"/>
</body>
</html>