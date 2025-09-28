<%-- File: src/main/webapp/views/system-admin/overview/revenue.jsp --%>
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

<h2>View Revenue</h2>

<h3>Revenue Statistics</h3>
<table>
    <tr>
        <th>Metric</th>
        <th>Value</th>
    </tr>
    <tr>
        <td>Total Revenue</td>
        <td>$456,789</td>
    </tr>
    <tr>
        <td>This Month Revenue</td>
        <td>$45,678</td>
    </tr>
    <tr>
        <td>Last Month Revenue</td>
        <td>$42,345</td>
    </tr>
    <tr>
        <td>Average Booking Value</td>
        <td>$128</td>
    </tr>
</table>

<h3>Revenue by Package</h3>
<table>
    <tr>
        <th>Package</th>
        <th>Bookings</th>
        <th>Revenue</th>
    </tr>
    <tr>
        <td>Summer Beach Package</td>
        <td>1,234</td>
        <td>$185,100</td>
    </tr>
    <tr>
        <td>Mountain Adventure</td>
        <td>987</td>
        <td>$148,050</td>
    </tr>
    <tr>
        <td>City Tour</td>
        <td>765</td>
        <td>$76,500</td>
    </tr>
    <tr>
        <td>Historical Sites</td>
        <td>581</td>
        <td>$47,139</td>
    </tr>
</table>

<%@ include file="../../common/footer.jsp" %>