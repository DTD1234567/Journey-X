<%-- File: src/main/webapp/views/system-admin/overview/pending-actions.jsp --%>
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

<h2>View Pending Actions</h2>

<h3>Pending Actions Overview</h3>
<table>
    <tr>
        <th>Action Type</th>
        <th>Count</th>
    </tr>
    <tr>
        <td>Pending Bookings</td>
        <td>234</td>
    </tr>
    <tr>
        <td>Pending Payments</td>
        <td>156</td>
    </tr>
    <tr>
        <td>Pending Refunds</td>
        <td>45</td>
    </tr>
    <tr>
        <td>Unresolved Issues</td>
        <td>78</td>
    </tr>
</table>

<h3>Recent Pending Actions</h3>
<table>
    <tr>
        <th>Action ID</th>
        <th>Type</th>
        <th>Details</th>
        <th>Date</th>
        <th>Assigned To</th>
    </tr>
    <tr>
        <td>A12345</td>
        <td>Booking</td>
        <td>Confirm booking for John Doe</td>
        <td>2023-11-15</td>
        <td>Booking Manager</td>
    </tr>
    <tr>
        <td>A12346</td>
        <td>Payment</td>
        <td>Process payment for Jane Smith</td>
        <td>2023-11-14</td>
        <td>Payment Manager</td>
    </tr>
    <tr>
        <td>A12347</td>
        <td>Refund</td>
        <td>Process refund for Robert Johnson</td>
        <td>2023-11-13</td>
        <td>Payment Manager</td>
    </tr>
    <tr>
        <td>A12348</td>
        <td>Issue</td>
        <td>Resolve issue reported by Emily Davis</td>
        <td>2023-11-12</td>
        <td>Support Officer</td>
    </tr>
</table>

<%@ include file="../../common/footer.jsp" %>