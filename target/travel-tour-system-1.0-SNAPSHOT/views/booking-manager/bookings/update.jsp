<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.travel.model.BookingManager" %>
<%@ page import="com.travel.model.Booking" %>
<%@ page import="com.travel.service.BookingService" %>
<%@ page import="java.text.SimpleDateFormat" %>

<%
    BookingManager manager = (BookingManager) session.getAttribute("user");
    if (manager == null) {
        response.sendRedirect(request.getContextPath() + "/tour-login");
        return;
    }

    Booking booking = (Booking) request.getAttribute("booking");
    if (booking == null) {
        response.sendRedirect(request.getContextPath() + "/tour-booking-manager-bookings?action=list&error=Booking not found");
        return;
    }

    BookingService bookingService = new BookingService();
    boolean canModify = bookingService.canModifyBooking(booking.getBookingId());

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String formattedDate = sdf.format(booking.getBookingDate());
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Update Booking - Booking Manager</title>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/style.css">
</head>
<body>
<jsp:include page="/views/common/header-bookingmanager.jsp"/>

<h2>Update Booking Details</h2>

<p><a href="<%= request.getContextPath() %>/tour-booking-manager-bookings?action=list">Back to Bookings List</a></p>

<%-- Display error message if exists --%>
<% if (request.getParameter("error") != null) { %>
<div class="error"><%= request.getParameter("error") %></div>
<% } %>

<% if (canModify) { %>
<h3>Booking Information</h3>
<form action="<%= request.getContextPath() %>/tour-booking-manager-bookings" method="post">
    <input type="hidden" name="action" value="update">
    <input type="hidden" name="bookingId" value="<%= booking.getBookingId() %>">

    <table>
        <tr>
            <td>Booking ID:</td>
            <td><%= booking.getBookingId() %></td>
        </tr>
        <tr>
            <td>Customer ID:</td>
            <td><%= booking.getCustomerId() %></td>
        </tr>
        <tr>
            <td>Package ID:</td>
            <td><%= booking.getPackageId() %></td>
        </tr>
        <tr>
            <td>Current Status:</td>
            <td>
                <% if ("Pending".equals(booking.getConfirmationStatus())) { %>
               <%= booking.getConfirmationStatus() %>
                <% } else if ("Confirmed".equals(booking.getConfirmationStatus())) { %>
               <%= booking.getConfirmationStatus() %>
                <% } else if ("Cancelled".equals(booking.getConfirmationStatus())) { %>
                <%= booking.getConfirmationStatus() %>
                <% } else { %>
                <%= booking.getConfirmationStatus() %>
                <% } %>
            </td>
        </tr>
        <tr>
            <td>Booking Date:</td>
            <td>
                <input type="date" name="bookingDate" value="<%= formattedDate %>" required>
            </td>
        </tr>
        <tr>
            <td>Number of Travelers:</td>
            <td>
                <input type="number" name="numTravelers" value="<%= booking.getNumTravelers() %>" min="1" max="20" required>
            </td>
        </tr>
        <tr>
            <td colspan="2" style="text-align: center;">
                <input type="submit" value="Update Booking">
                <input type="reset" value="Reset">
            </td>
        </tr>
    </table>
</form>

<h3>Additional Actions</h3>
<table>
    <tr>
        <td>
            <% if ("Pending".equals(booking.getConfirmationStatus())) { %>
            <form action="<%= request.getContextPath() %>/tour-booking-manager-bookings" method="post" style="display: inline;">
                <input type="hidden" name="action" value="confirm">
                <input type="hidden" name="bookingId" value="<%= booking.getBookingId() %>">
                <input type="submit" value="Confirm Booking" onclick="return confirm('Are you sure you want to confirm this booking?');">
            </form>
            <% } %>

            <% if (!"Cancelled".equals(booking.getConfirmationStatus())) { %>
            <form action="<%= request.getContextPath() %>/tour-booking-manager-bookings" method="post" style="display: inline;">
                <input type="hidden" name="action" value="cancel">
                <input type="hidden" name="bookingId" value="<%= booking.getBookingId() %>">
                <input type="submit" value="Cancel Booking" onclick="return confirm('Are you sure you want to cancel this booking?');">
            </form>
            <% } %>
        </td>
    </tr>
</table>

<% } else { %>
<div class="error">
    <p>This booking cannot be modified because it has already been confirmed or cancelled.</p>
    <p>Only pending bookings can be modified.</p>
</div>

<h3>Booking Details</h3>
<table>
    <tr>
        <td>Booking ID:</td>
        <td><%= booking.getBookingId() %></td>
    </tr>
    <tr>
        <td>Customer ID:</td>
        <td><%= booking.getCustomerId() %></td>
    </tr>
    <tr>
        <td>Package ID:</td>
        <td><%= booking.getPackageId() %></td>
    </tr>
    <tr>
        <td>Booking Date:</td>
        <td><%= booking.getBookingDate() %></td>
    </tr>
    <tr>
        <td>Number of Travelers:</td>
        <td><%= booking.getNumTravelers() %></td>
    </tr>
    <tr>
        <td>Status:</td>
        <td>
            <% if ("Pending".equals(booking.getConfirmationStatus())) { %>
            <%= booking.getConfirmationStatus() %>
            <% } else if ("Confirmed".equals(booking.getConfirmationStatus())) { %>
            <%= booking.getConfirmationStatus() %>
            <% } else if ("Cancelled".equals(booking.getConfirmationStatus())) { %>
           <%= booking.getConfirmationStatus() %>
            <% } else { %>
            <%= booking.getConfirmationStatus() %>
            <% } %>
        </td>
    </tr>
</table>
<% } %>

<jsp:include page="/views/common/footer.jsp"/>
</body>
</html>