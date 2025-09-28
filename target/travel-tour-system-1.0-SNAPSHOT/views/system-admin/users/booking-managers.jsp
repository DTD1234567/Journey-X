<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.travel.model.SystemAdmin" %>
<%@ page import="com.travel.model.BookingManager" %>
<%@ page import="com.travel.service.BookingManagerService" %>
<%@ page import="java.util.List" %>

<%
    // Check if system admin is logged in
    SystemAdmin admin = (SystemAdmin) session.getAttribute("admin");
    if (admin == null) {
        response.sendRedirect(request.getContextPath() + "/tour-login");
        return;
    }

    // Get booking managers list
    BookingManagerService bookingManagerService = new BookingManagerService();
    List<BookingManager> bookingManagers = (List<BookingManager>) request.getAttribute("managers");

    // If managers not set in request, get all managers
    if (bookingManagers == null) {
        bookingManagers = bookingManagerService.getAllBookingManagers();
    }
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Manage Booking Managers - System Admin</title>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/style.css">
</head>
<body>
<jsp:include page="/views/common/header-system-admin.jsp"/>

<h2>Manage Booking Managers</h2>

<%-- Display success or error messages --%>
<% if (request.getParameter("success") != null) { %>
<div class="success"><%= request.getParameter("success") %></div>
<% } %>
<% if (request.getParameter("error") != null) { %>
<div class="error"><%= request.getParameter("error") %></div>
<% } %>

<p><a href="<%= request.getContextPath() %>/tour-system-admin-dashboard">Back to Dashboard</a></p>



<h3>Quick Actions</h3>
<table border="1">
    <tr>
        <td>
            <a href="<%= request.getContextPath() %>/tour-system-admin-bookings?action=add-manager">Add New Booking Manager</a>
            <p>Create a new booking manager account</p>
        </td>
    </tr>
    <tr>
        <td>
            <a href="<%= request.getContextPath() %>/tour-system-admin-bookings?action=managers">View All Booking Managers</a>
            <p>View and manage all booking manager accounts</p>
        </td>
    </tr>
</table>

<h3>Existing Booking Managers</h3>
<% if (bookingManagers != null && !bookingManagers.isEmpty()) { %>
<table border="1">
    <tr>
        <th>Employee ID</th>
        <th>Name</th>
        <th>Job Title</th>
        <th>Department</th>
        <th>Email</th>
        <th>Contact Number</th>
        <th>Actions</th>
    </tr>
    <% for (BookingManager manager : bookingManagers) { %>
    <tr>
        <td><%= manager.getEmployeeId() %></td>
        <td><%= manager.getName() %></td>
        <td><%= manager.getJobTitle() %></td>
        <td><%= manager.getDepartment() %></td>
        <td><%= manager.getEmail() %></td>
        <td><%= manager.getContactNumber() %></td>
        <td>
            <a href="<%= request.getContextPath() %>/tour-system-admin-bookings?action=edit-manager&id=<%= manager.getEmployeeId() %>">Edit</a>
            <form action="<%= request.getContextPath() %>/tour-system-admin-bookings" method="post" style="display: inline;">
                <input type="hidden" name="action" value="delete-manager">
                <input type="hidden" name="employeeId" value="<%= manager.getEmployeeId() %>">
                <input type="submit" value="Delete" onclick="return confirm('Are you sure you want to delete this booking manager? This action cannot be undone.');">
            </form>
        </td>
    </tr>
    <% } %>
</table>

<p>Total Booking Managers: <%= bookingManagers.size() %></p>

<% } else { %>
<p>No booking managers found.</p>
<% } %>

<h3>Add New Booking Manager</h3>
<form action="<%= request.getContextPath() %>/tour-system-admin-bookings" method="post">
    <input type="hidden" name="action" value="add-manager">
    <table>
        <tr>
            <td>Name:</td>
            <td><input type="text" name="name" required></td>
        </tr>
        <tr>
            <td>Job Title:</td>
            <td><input type="text" name="jobTitle" required></td>
        </tr>
        <tr>
            <td>Department:</td>
            <td><input type="text" name="department" required></td>
        </tr>
        <tr>
            <td>Contact Number:</td>
            <td><input type="text" name="contactNumber" required></td>
        </tr>
        <tr>
            <td>Email:</td>
            <td><input type="email" name="email" required></td>
        </tr>
        <tr>
            <td>Password:</td>
            <td><input type="password" name="password" required></td>
        </tr>
        <tr>
            <td colspan="2" style="text-align: center;">
                <input type="submit" value="Add Booking Manager">
                <input type="reset" value="Reset">
            </td>
        </tr>
    </table>
</form>

<div class="info">
    <h4>Important Notes:</h4>
    <ul>
        <li>Only System Administrators can add, edit, or delete booking managers.</li>
        <li>Booking managers can manage bookings but cannot modify their own account details beyond basic profile information.</li>
        <li>Each booking manager must have a unique email address.</li>
        <li>Deleting a booking manager will permanently remove their account and all associated data.</li>
    </ul>
</div>

<jsp:include page="/views/common/footer.jsp"/>
</body>
</html>