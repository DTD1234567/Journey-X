<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.travel.model.Customer" %>
<%@ page import="com.travel.model.Booking" %>
<%@ page import="java.util.List" %>

<%
    // Check if customer is logged in
    Customer customer = (Customer) session.getAttribute("user");
    if (customer == null) {
        response.sendRedirect(request.getContextPath() + "/tour-login");
        return;
    }

    // Get bookings from request
    List<Booking> bookings = (List<Booking>) request.getAttribute("bookings");
    if (bookings == null) {
        response.sendRedirect(request.getContextPath() + "/tour-customer-dashboard?error=No booking history found");
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Booking History - Customer</title>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/style.css">
</head>
<body>
<jsp:include page="/views/common/header-customer.jsp"/>

<h2>My Booking History</h2>

<p><a href="<%= request.getContextPath() %>/tour-customer-dashboard">Back to Dashboard</a></p>

<%-- Display success or error messages --%>
<% if (request.getParameter("success") != null) { %>
<div class="success"><%= request.getParameter("success") %></div>
<% } %>
<% if (request.getAttribute("successMessage") != null) { %>
<div class="success"><%= request.getAttribute("successMessage") %></div>
<% } %>
<% if (request.getParameter("error") != null) { %>
<div class="error"><%= request.getParameter("error") %></div>
<% } %>
<% if (request.getAttribute("errorMessage") != null) { %>
<div class="error"><%= request.getAttribute("errorMessage") %></div>
<% } %>

<h3>Booking Summary</h3>
<table>
    <tr>
        <th>Total Bookings</th>
        <th>Active Bookings</th>
        <th>Completed Bookings</th>
        <th>Cancelled Bookings</th>
    </tr>
    <tr>
        <td><%= bookings.size() %></td>
        <td>
            <%
                int activeCount = 0;
                for (Booking booking : bookings) {
                    if ("Confirmed".equals(booking.getConfirmationStatus())) {
                        activeCount++;
                    }
                }
            %>
            <%= activeCount %>
        </td>
        <td>
            <%
                int completedCount = 0;
                for (Booking booking : bookings) {
                    if ("Confirmed".equals(booking.getConfirmationStatus())) {
                        // Consider bookings with past dates as completed
                        java.util.Date bookingDate = booking.getBookingDate();
                        java.util.Date currentDate = new java.util.Date();
                        if (bookingDate.before(currentDate)) {
                            completedCount++;
                        }
                    }
                }
            %>
            <%= completedCount %>
        </td>
        <td>
            <%
                int cancelledCount = 0;
                for (Booking booking : bookings) {
                    if ("Cancelled".equals(booking.getConfirmationStatus())) {
                        cancelledCount++;
                    }
                }
            %>
            <%= cancelledCount %>
        </td>
    </tr>
</table>

<h3>Booking History</h3>
<% if (bookings != null && !bookings.isEmpty()) { %>
<table border="1">
    <tr>
        <th>Booking ID</th>
        <th>Package ID</th>
        <th>Booking Date</th>
        <th>Number of Travelers</th>
        <th>Status</th>
        <th>Actions</th>
    </tr>
    <% for (Booking booking : bookings) { %>
    <tr>
        <td><%= booking.getBookingId() %></td>
        <td><%= booking.getPackageId() %></td>
        <td><%= booking.getBookingDate() %></td>
        <td><%= booking.getNumTravelers() %></td>
        <td>
            <% if ("Pending".equals(booking.getConfirmationStatus())) { %>
            <span style="color: orange;"><%= booking.getConfirmationStatus() %></span>
            <% } else if ("Confirmed".equals(booking.getConfirmationStatus())) { %>
            <span style="color: green;"><%= booking.getConfirmationStatus() %></span>
            <% } else if ("Cancelled".equals(booking.getConfirmationStatus())) { %>
            <span style="color: red;"><%= booking.getConfirmationStatus() %></span>
            <% } else { %>
            <%= booking.getConfirmationStatus() %>
            <% } %>
        </td>
        <td>
            <a href="<%= request.getContextPath() %>/customer/bookings/status">View Status</a>
            <% if (!"Cancelled".equals(booking.getConfirmationStatus())) { %>
            | <a href="<%= request.getContextPath() %>/customer/bookings/cancel">Cancel Booking</a>
            <% } %>
        </td>
    </tr>
    <% } %>
</table>

<p>Total Bookings: <%= bookings.size() %></p>

<% } else { %>
<p>No booking history found.</p>
<% } %>

<h3>Quick Actions</h3>
<table border="1">
    <tr>
        <td>
            <a href="<%= request.getContextPath() %>/customer/bookings/status">Check Booking Status</a>
            <p>View the current status of your bookings</p>
        </td>
    </tr>
    <tr>
        <td>
            <a href="<%= request.getContextPath() %>/customer/packages/list">Book New Package</a>
            <p>Browse and book new travel packages</p>
        </td>
    </tr>
    <tr>
        <td>
            <a href="<%= request.getContextPath() %>/customer/payments/history">View Payment History</a>
            <p>Check your payment records and receipts</p>
        </td>
    </tr>
    <tr>
        <td>
            <a href="<%= request.getContextPath() %>/tour-customer-support?action=create">Contact Support</a>
            <p>Get help with your bookings</p>
        </td>
    </tr>
</table>

<div class="info">
    <h4>Booking Status Guide:</h4>
    <ul>
        <li><span style="color: orange;">Pending</span> - Your booking is being processed and awaiting confirmation</li>
        <li><span style="color: green;">Confirmed</span> - Your booking has been confirmed and is active</li>
        <li><span style="color: red;">Cancelled</span> - Your booking has been cancelled</li>
    </ul>
</div>

<div class="warning">
    <h4>Important Information:</h4>
    <ul>
        <li>You can cancel your booking up to 48 hours before the travel date.</li>
        <li>Keep your booking ID safe for future reference and support inquiries.</li>
        <li>Booking confirmations are sent to your registered email address.</li>
        <li>Contact support if you don't receive a confirmation within 24 hours.</li>
    </ul>
</div>

<jsp:include page="/views/common/footer.jsp"/>
</body>
</html>