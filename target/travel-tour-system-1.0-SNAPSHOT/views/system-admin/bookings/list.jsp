<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.travel.model.SystemAdmin" %>
<%@ page import="com.travel.model.Booking" %>
<%@ page import="com.travel.service.BookingService" %>
<%@ page import="java.util.List" %>

<%
    // Check if system admin is logged in
    SystemAdmin admin = (SystemAdmin) session.getAttribute("admin");
    if (admin == null) {
        response.sendRedirect(request.getContextPath() + "/tour-login");
        return;
    }

    // Get bookings list and statistics
    BookingService bookingService = new BookingService();
    List<Booking> bookings = (List<Booking>) request.getAttribute("bookings");

    // If bookings not set in request, get all bookings
    if (bookings == null) {
        bookings = bookingService.getAllBookings();
    }

    // Get statistics
    int totalBookings = bookingService.getTotalBookings();
    int pendingBookings = bookingService.getPendingBookingsCount();
    int confirmedBookings = bookingService.getConfirmedBookingsCount();
    int cancelledBookings = bookingService.getCancelledBookingsCount();
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>All Bookings - System Admin</title>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/style.css">
</head>
<body>
<jsp:include page="/views/common/header-system-admin.jsp"/>

<h2>All Bookings</h2>

<%-- Display success or error messages --%>
<% if (request.getParameter("success") != null) { %>
<div class="success"><%= request.getParameter("success") %></div>
<% } %>
<% if (request.getParameter("error") != null) { %>
<div class="error"><%= request.getParameter("error") %></div>
<% } %>

<p><a href="<%= request.getContextPath() %>/tour-system-admin-dashboard">Back to Dashboard</a></p>

<h3>Booking Statistics</h3>
<table border="1">
    <tr>
        <th>Total Bookings</th>
        <th>Pending Bookings</th>
        <th>Confirmed Bookings</th>
        <th>Cancelled Bookings</th>
    </tr>
    <tr>
        <td><%= totalBookings %></td>
        <td><%= pendingBookings %></td>
        <td><%= confirmedBookings %></td>
        <td><%= cancelledBookings %></td>
    </tr>
</table>

<h3>Filter Options</h3>
<table border="1">
    <tr>
        <td>
            <a href="<%= request.getContextPath() %>/tour-system-admin-bookings?action=bookings">All Bookings</a> |
            <a href="<%= request.getContextPath() %>/tour-system-admin-bookings?action=view-pending">Pending Only</a> |
            <a href="<%= request.getContextPath() %>/tour-system-admin-bookings?action=view-confirmed">Confirmed Only</a> |
            <a href="<%= request.getContextPath() %>/tour-system-admin-bookings?action=view-cancelled">Cancelled Only</a> |
            <a href="<%= request.getContextPath() %>/tour-system-admin-bookings?action=booking-stats">View Statistics</a>
        </td>
    </tr>
</table>

<h3>Booking List</h3>
<% if (bookings != null && !bookings.isEmpty()) { %>
<table border="1">
    <tr>
        <th>Booking ID</th>
        <th>Customer ID</th>
        <th>Package ID</th>
        <th>Booking Date</th>
        <th>Number of Travelers</th>
        <th>Status</th>
        <th>Actions</th>
    </tr>
    <% for (Booking booking : bookings) { %>
    <tr>
        <td><%= booking.getBookingId() %></td>
        <td><%= booking.getCustomerId() %></td>
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
            <a href="<%= request.getContextPath() %>/tour-system-admin-bookings?action=booking-details&id=<%= booking.getBookingId() %>">View Details</a>
            <% if ("Pending".equals(booking.getConfirmationStatus())) { %>
            | <form action="<%= request.getContextPath() %>/tour-system-admin-bookings" method="post" style="display: inline;">
            <input type="hidden" name="action" value="confirm-booking">
            <input type="hidden" name="bookingId" value="<%= booking.getBookingId() %>">
            <input type="submit" value="Confirm" onclick="return confirm('Are you sure you want to confirm this booking?');">
        </form>
            <% } %>
            <% if (!"Cancelled".equals(booking.getConfirmationStatus())) { %>
            | <form action="<%= request.getContextPath() %>/tour-system-admin-bookings" method="post" style="display: inline;">
            <input type="hidden" name="action" value="cancel-booking">
            <input type="hidden" name="bookingId" value="<%= booking.getBookingId() %>">
            <input type="submit" value="Cancel" onclick="return confirm('Are you sure you want to cancel this booking?');">
        </form>
            <% } %>
        </td>
    </tr>
    <% } %>
</table>

<p>Total Bookings Displayed: <%= bookings.size() %></p>

<% } else { %>
<p>No bookings found.</p>
<% } %>

<h3>Administrative Actions</h3>
<table border="1">
    <tr>
        <td>
            <a href="<%= request.getContextPath() %>/tour-system-admin-bookings?action=booking-stats">View Detailed Statistics</a>
            <p>View comprehensive booking statistics and reports</p>
        </td>
    </tr>
    <tr>
        <td>
            <a href="<%= request.getContextPath() %>/tour-system-admin-bookings?action=view-pending">Manage Pending Bookings</a>
            <p>Review and process bookings awaiting confirmation</p>
        </td>
    </tr>
    <tr>
        <td>
            <a href="<%= request.getContextPath() %>/tour-system-admin-bookings?action=managers">Manage Booking Managers</a>
            <p>Add, edit, or remove booking managers</p>
        </td>
    </tr>
</table>

<div class="info">
    <h4>System Information:</h4>
    <ul>
        <li>System Administrators have full control over all bookings in the system.</li>
        <li>You can confirm, cancel, or view details of any booking.</li>
        <li>Booking statistics are updated in real-time.</li>
        <li>All booking actions are logged for audit purposes.</li>
    </ul>
</div>

<jsp:include page="/views/common/footer.jsp"/>
</body>
</html>