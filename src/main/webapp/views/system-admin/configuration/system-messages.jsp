<%-- File: src/main/webapp/views/system-admin/configuration/system-messages.jsp --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.travel.model.SystemAdmin" %>
<%
    // Check if system admin is logged in
    SystemAdmin admin = (SystemAdmin) request.getAttribute("admin");
    if (admin == null) {
        response.sendRedirect(request.getContextPath() + "/tour-login");
        return;
    }
%>
<%@ include file="../../common/header-system-admin.jsp" %>

<h2>Edit System Messages</h2>

<h3>Welcome Message</h3>
<form action="<%= request.getContextPath() %>/tour-system-admin-configuration" method="post">
    <input type="hidden" name="action" value="update-welcome-message">
    <table>
        <tr>
            <td>Message:</td>
            <td><textarea name="welcomeMessage" rows="4" cols="50">Welcome to Tour Package Booking System! We offer the best travel packages for your dream vacation.</textarea></td>
        </tr>
        <tr>
            <td colspan="2" style="text-align: center;">
                <input type="submit" value="Update Welcome Message">
            </td>
        </tr>
    </table>
</form>

<h3>Registration Confirmation Message</h3>
<form action="<%= request.getContextPath() %>/tour-system-admin-configuration" method="post">
    <input type="hidden" name="action" value="update-registration-message">
    <table>
        <tr>
            <td>Message:</td>
            <td><textarea name="registrationMessage" rows="4" cols="50">Thank you for registering with Tour Package Booking System. Your account has been created successfully.</textarea></td>
        </tr>
        <tr>
            <td colspan="2" style="text-align: center;">
                <input type="submit" value="Update Registration Message">
            </td>
        </tr>
    </table>
</form>

<h3>Booking Confirmation Message</h3>
<form action="<%= request.getContextPath() %>/tour-system-admin-configuration" method="post">
    <input type="hidden" name="action" value="update-booking-message">
    <table>
        <tr>
            <td>Message:</td>
            <td><textarea name="bookingMessage" rows="4" cols="50">Your booking has been confirmed. Thank you for choosing Tour Package Booking System.</textarea></td>
        </tr>
        <tr>
            <td colspan="2" style="text-align: center;">
                <input type="submit" value="Update Booking Message">
            </td>
        </tr>
    </table>
</form>

<%@ include file="../../common/footer.jsp" %>