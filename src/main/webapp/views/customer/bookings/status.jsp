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
        response.sendRedirect(request.getContextPath() + "/tour-customer-dashboard?error=No bookings found");
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Booking Status - Customer</title>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/style.css">
</head>
<body>
<jsp:include page="/views/common/header-customer.jsp"/>

<h2>My Booking Status</h2>

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

<%-- Display success message for new bookings --%>
<% if (request.getAttribute("successMessage") != null) { %>
<div class="success">
    <%= request.getAttribute("successMessage") %>
    <p>Thank you for booking with us. Your booking has been received and is being processed.</p>
</div>
<% } %>

<h3>Booking Status Overview</h3>
<table>
    <tr>
        <th>Total Bookings</th>
        <th>Pending</th>
        <th>Confirmed</th>
        <th>Cancelled</th>
    </tr>
    <tr>
        <td><%= bookings.size() %></td>
        <td>
            <%
                int pendingCount = 0;
                for (Booking booking : bookings) {
                    if ("Pending".equals(booking.getConfirmationStatus())) {
                        pendingCount++;
                    }
                }
            %>
            <%= pendingCount %>
        </td>
        <td>
            <%
                int confirmedCount = 0;
                for (Booking booking : bookings) {
                    if ("Confirmed".equals(booking.getConfirmationStatus())) {
                        confirmedCount++;
                    }
                }
            %>
            <%= confirmedCount %>
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

<h3>Current Booking Status</h3>
<% if (bookings != null && !bookings.isEmpty()) { %>
<table>
    <tr>
        <th>Booking ID</th>
        <th>Package ID</th>
        <th>Booking Date</th>
        <th>Number of Travelers</th>
        <th>Status</th>
        <th>Status Details</th>
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
            <%= booking.getConfirmationStatus() %>
            <% } else if ("Confirmed".equals(booking.getConfirmationStatus())) { %>
            <%= booking.getConfirmationStatus() %>
            <% } else if ("Cancelled".equals(booking.getConfirmationStatus())) { %>
            <%= booking.getConfirmationStatus() %>
            <% } else { %>
            <%= booking.getConfirmationStatus() %>
            <% } %>
        </td>
        <td>
            <% if ("Pending".equals(booking.getConfirmationStatus())) { %>
            Your booking is being processed. Please wait for confirmation.
            <% } else if ("Confirmed".equals(booking.getConfirmationStatus())) { %>
            Your booking has been confirmed. Enjoy your trip!
            <% } else if ("Cancelled".equals(booking.getConfirmationStatus())) { %>
            Your booking has been cancelled.
            <% } else { %>
            Status information not available.
            <% } %>
        </td>
        <td>
            <a href="<%= request.getContextPath() %>/customer/bookings/history">View History</a>
            <% if (!"Cancelled".equals(booking.getConfirmationStatus())) { %>
            | <a href="<%= request.getContextPath() %>/customer/bookings/cancel">Cancel Booking</a>
            <% } %>
        </td>
    </tr>
    <% } %>
</table>

<% } else { %>
<p>No bookings found.</p>
<% } %>

<h3>What You Can Do</h3>
<table>
    <tr>
        <td>
            <a href="<%= request.getContextPath() %>/customer/bookings/history">View Booking History</a>
            <p>See all your past and current bookings</p>
        </td>
    </tr>
    <tr>
        <td>
            <a href="<%= request.getContextPath() %>/customer/packages/list">Browse More Packages</a>
            <p>Book additional travel packages</p>
        </td>
    </tr>
    <tr>
        <td>
            <a href="<%= request.getContextPath() %>/customer/payments/history">View Payment History</a>
            <p>Check your payment records</p>
        </td>
    </tr>
    <tr>
        <td>
            <a href="<%= request.getContextPath() %>/tour-customer-support?action=create">Contact Support</a>
            <p>Get help with your bookings</p>
        </td>
    </tr>
</table>

<div>
    <h4>Booking Status Information:</h4>
    <ul>
        <li><strong>Pending</strong> - Your booking is being processed and awaiting confirmation</li>
        <li><strong>Confirmed</strong> - Your booking has been confirmed and is active</li>
        <li><strong>Cancelled</strong> - Your booking has been cancelled</li>
    </ul>
</div>

<div>
    <h4>Important Notes:</h4>
    <ul>
        <li>Booking confirmations are typically processed within 24 hours</li>
        <li>You will receive an email confirmation once your booking is confirmed</li>
        <li>Keep your booking ID safe for future reference</li>
        <li>Contact support if you don't receive confirmation within 48 hours</li>
    </ul>
</div>

<jsp:include page="/views/common/footer.jsp"/>
</body>
</html>