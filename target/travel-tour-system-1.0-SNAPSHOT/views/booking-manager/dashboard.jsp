<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.travel.model.BookingManager" %>
<%@ page import="com.travel.service.BookingService" %>
<%@ page import="com.travel.model.Booking" %>
<%@ page import="java.util.List" %>

<%
    // Check if booking manager is logged in
    BookingManager manager = (BookingManager) session.getAttribute("user");
    if (manager == null) {
        response.sendRedirect(request.getContextPath() + "/tour-login");
        return;
    }

    // Get booking statistics
    BookingService bookingService = new BookingService();
    int totalBookings = bookingService.getTotalBookings();
    int pendingBookings = bookingService.getPendingBookingsCount();
    int confirmedBookings = bookingService.getConfirmedBookingsCount();
    int cancelledBookings = bookingService.getCancelledBookingsCount();
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Booking Manager Dashboard</title>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/style.css">
</head>
<body>
<jsp:include page="/views/common/header-bookingmanager.jsp"/>

<h2>Booking Manager Dashboard</h2>

<p>Welcome, <%= manager.getName() %>!</p>

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

<h3>Quick Actions</h3>
<table>
    <tr>
        <td>
            <h4><a href="<%= request.getContextPath() %>/tour-booking-manager-bookings?action=list">View All Bookings</a></h4>
            <p>View and manage all customer bookings</p>
        </td>
    </tr>
    <tr>
        <td>
            <h4><a href="<%= request.getContextPath() %>/tour-booking-manager-bookings?action=view-pending">View Pending Bookings</a></h4>
            <p>Review and confirm pending bookings</p>
        </td>
    </tr>
    <tr>
        <td>
            <h4><a href="<%= request.getContextPath() %>/tour-booking-manager-bookings?action=view-confirmed">View Confirmed Bookings</a></h4>
            <p>View all confirmed bookings</p>
        </td>
    </tr>
    <tr>
        <td>
            <h4><a href="<%= request.getContextPath() %>/tour-booking-manager-bookings?action=view-cancelled">View Cancelled Bookings</a></h4>
            <p>View all cancelled bookings</p>
        </td>
    </tr>
</table>

<h3>Recent Bookings</h3>
<table>
    <tr>
        <th>Booking ID</th>
        <th>Customer ID</th>
        <th>Package ID</th>
        <th>Booking Date</th>
        <th>Status</th>
        <th>Actions</th>
    </tr>
    <%
        List<Booking> recentBookings = bookingService.getAllBookings();
        if (recentBookings != null && !recentBookings.isEmpty()) {
            // Show 5 most recent bookings
            int maxBookings = Math.min(recentBookings.size(), 5);
            for (int i = 0; i < maxBookings; i++) {
                Booking booking = recentBookings.get(i);
    %>
    <tr>
        <td><%= booking.getBookingId() %></td>
        <td><%= booking.getCustomerId() %></td>
        <td><%= booking.getPackageId() %></td>
        <td><%= booking.getBookingDate() %></td>
        <td><%= booking.getConfirmationStatus() %></td>
        <td>
            <a href="<%= request.getContextPath() %>/tour-booking-manager-bookings?action=update&id=<%= booking.getBookingId() %>">Update</a>
            <% if ("Pending".equals(booking.getConfirmationStatus())) { %>
            | <a href="<%= request.getContextPath() %>/tour-booking-manager-bookings?action=confirm&id=<%= booking.getBookingId() %>">Confirm</a>
            <% } %>
            <% if (!"Cancelled".equals(booking.getConfirmationStatus())) { %>
            | <a href="<%= request.getContextPath() %>/tour-booking-manager-bookings?action=cancel&id=<%= booking.getBookingId() %>">Cancel</a>
            <% } %>
        </td>
    </tr>
    <%
        }
    } else {
    %>
    <tr>
        <td colspan="6" style="text-align: center;">No bookings found</td>
    </tr>
    <%
        }
    %>
</table>

<% if (recentBookings != null && recentBookings.size() > 5) { %>
<p><a href="<%= request.getContextPath() %>/tour-booking-manager-bookings?action=list">View All Bookings</a></p>
<% } %>

<h3>Account Information</h3>
<table>
    <tr>
        <td>Employee ID:</td>
        <td><%= manager.getEmployeeId() %></td>
    </tr>
    <tr>
        <td>Name:</td>
        <td><%= manager.getName() %></td>
    </tr>
    <tr>
        <td>Job Title:</td>
        <td><%= manager.getJobTitle() %></td>
    </tr>
    <tr>
        <td>Department:</td>
        <td><%= manager.getDepartment() %></td>
    </tr>
    <tr>
        <td>Email:</td>
        <td><%= manager.getEmail() %></td>
    </tr>
    <tr>
        <td>Contact Number:</td>
        <td><%= manager.getContactNumber() %></td>
    </tr>
</table>

<jsp:include page="/views/common/footer.jsp"/>
</body>
</html>