<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.travel.model.Package" %>

<%
    Package pkg = (Package) request.getAttribute("package");
    String error = request.getParameter("error");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Package Details - Customer</title>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/style.css">
</head>
<body>
<jsp:include page="/views/common/header-customer.jsp"/>

<h2>Package Details</h2>

<% if (error != null) { %>
<div class="error"><%= error %></div>
<% } %>

<% if (pkg != null) { %>
<h3><%= pkg.getPackageName() %></h3>

<table>
    <tr>
        <td>Price:</td>
        <td>$<%= pkg.getPrice() %></td>
    </tr>
    <tr>
        <td>Duration:</td>
        <td><%= pkg.getDuration() %></td>
    </tr>
    <tr>
        <td>Start Date:</td>
        <td><%= pkg.getStartDate() %></td>
    </tr>
    <tr>
        <td>End Date:</td>
        <td><%= pkg.getEndDate() %></td>
    </tr>
    <tr>
        <td>Destinations:</td>
        <td><%= pkg.getDestinations() %></td>
    </tr>
    <tr>
        <td>Description:</td>
        <td><%= pkg.getDescription() != null ? pkg.getDescription() : "No description available" %></td>
    </tr>
</table>

<table>
    <tr>
        <td>
            <a href="<%= request.getContextPath() %>/customer/bookings/create?packageId=<%= pkg.getPackageId() %>">Book This Package</a>
        </td>
    </tr>
    <tr>
        <td>
            <a href="<%= request.getContextPath() %>/tour-customer-packages/list">Back to Packages</a>
        </td>
    </tr>
</table>

<% } else { %>
<p>Package not found.</p>
<p><a href="<%= request.getContextPath() %>/tour-customer-packages/list">Back to Packages</a></p>
<% } %>

<jsp:include page="/views/common/footer.jsp"/>
</body>
</html>