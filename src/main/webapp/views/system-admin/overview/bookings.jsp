<%-- File: src/main/webapp/views/system-admin/overview/bookings.jsp --%>
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

<h2>View Bookings</h2>

<h3>Booking Statistics</h3>
<table>
    <tr>
        <th>Metric</th>
        <th>Value</th>
    </tr>
    <tr>
        <td>Total Bookings</td>
        <td>3,567</td>
    </tr>
    <tr>
        <td>Confirmed Bookings</td>
        <td>3,123</td>
    </tr>
    <tr>
        <td>Pending Bookings</td>
        <td>234</td>
    </tr>
    <tr>
        <td>Cancelled Bookings</td>
        <td>210</td>
    </tr>
</table>

<h3>Recent Bookings</h3>
<table>
    <tr>
        <th>Booking ID</th>
        <th>Customer</th>
        <th>Package</th>
        <th>Booking Date</th>
        <th>Status</th>
    </tr>
    <tr>
        <td>B12345</td>
        <td>John Doe</td>
        <td>Summer Beach Package</td>
        <td>2023-11-15</td>
        <td>Confirmed</td>
    </tr>
    <tr>
        <td>B12346</td>
        <td>Jane Smith</td>
        <td>Mountain Adventure</td>
        <td>2023-11-14</td>
        <td>Pending</td>
    </tr>
    <tr>
        <td>B12347</td>
        <td>Robert Johnson</td>
        <td>City Tour</td>
        <td>2023-11-13</td>
        <td>Confirmed</td>
    </tr>
    <tr>
        <td>B12348</td>
        <td>Emily Davis</td>
        <td>Historical Sites</td>
        <td>2023-11-12</td>
        <td>Cancelled</td>
    </tr>
</table>

<%@ include file="../../common/footer.jsp" %>