<%-- File: src/main/webapp/views/system-admin/overview/feedback.jsp --%>
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

<h2>View Feedback</h2>

<h3>Feedback Statistics</h3>
<table>
    <tr>
        <th>Metric</th>
        <th>Value</th>
    </tr>
    <tr>
        <td>Total Feedback</td>
        <td>2,345</td>
    </tr>
    <tr>
        <td>Average Rating</td>
        <td>4.2/5</td>
    </tr>
    <tr>
        <td>Positive Feedback</td>
        <td>1,876 (80%)</td>
    </tr>
    <tr>
        <td>Negative Feedback</td>
        <td>469 (20%)</td>
    </tr>
</table>

<h3>Recent Feedback</h3>
<table>
    <tr>
        <th>Feedback ID</th>
        <th>Customer</th>
        <th>Package</th>
        <th>Rating</th>
        <th>Date</th>
    </tr>
    <tr>
        <td>F12345</td>
        <td>John Doe</td>
        <td>Summer Beach Package</td>
        <td>5/5</td>
        <td>2023-11-15</td>
    </tr>
    <tr>
        <td>F12346</td>
        <td>Jane Smith</td>
        <td>Mountain Adventure</td>
        <td>4/5</td>
        <td>2023-11-14</td>
    </tr>
    <tr>
        <td>F12347</td>
        <td>Robert Johnson</td>
        <td>City Tour</td>
        <td>3/5</td>
        <td>2023-11-13</td>
    </tr>
    <tr>
        <td>F12348</td>
        <td>Emily Davis</td>
        <td>Historical Sites</td>
        <td>5/5</td>
        <td>2023-11-12</td>
    </tr>
</table>

<%@ include file="../../common/footer.jsp" %>