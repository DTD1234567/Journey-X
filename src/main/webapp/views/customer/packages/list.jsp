<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.travel.model.Package" %>
<%@ page import="java.util.List" %>

<%
    List<Package> packages = (List<Package>) request.getAttribute("packages");
    String error = request.getParameter("error");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Available Packages - Customer</title>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/style.css">
</head>
<body>
<jsp:include page="/views/common/header-customer.jsp"/>

<h2>Available Tour Packages</h2>

<% if (error != null) { %>
<div class="error"><%= error %></div>
<% } %>

<% if (packages != null && !packages.isEmpty()) { %>
<table>
    <tr>
        <th>Package Name</th>
        <th>Price</th>
        <th>Duration</th>
        <th>Destinations</th>
        <th>Actions</th>
    </tr>
    <% for (Package pkg : packages) { %>
    <tr>
        <td><%= pkg.getPackageName() %></td>
        <td>$<%= pkg.getPrice() %></td>
        <td><%= pkg.getDuration() %></td>
        <td><%= pkg.getDestinations() %></td>
        <td>
            <a href="<%= request.getContextPath() %>/tour-customer-packages/details?id=<%= pkg.getPackageId() %>">View Details</a>
            <a href="<%= request.getContextPath() %>/customer/bookings/create?packageId=<%= pkg.getPackageId() %>">Book Now</a>
        </td>
    </tr>
    <% } %>
</table>
<% } else { %>
<p>No packages available at the moment.</p>
<% } %>

<p><a href="<%= request.getContextPath() %>/tour-customer-dashboard">Back to Dashboard</a></p>

<jsp:include page="/views/common/footer.jsp"/>
</body>
</html>