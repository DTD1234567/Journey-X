<%--
  Created by IntelliJ IDEA.
  User: LENOVO
  Date: 9/20/2025
  Time: 3:08 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Create Package</title>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/customer-booking-create.css">

</head>
<body>
<jsp:include page="/views/common/header-customer.jsp"/>

<h2>Create New Package</h2>

<%-- Display error message if exists --%>
<% if (request.getAttribute("errorMessage") != null) { %>
<p><%= request.getAttribute("errorMessage") %></p>
<% } %>

<form action="<%= request.getContextPath() %>/customer/packages/create" method="post">
    <table>
        <tr>
            <td>Package Name:</td>
            <td><input type="text" name="packageName" required></td>
        </tr>
        <tr>
            <td>Price:</td>
            <td><input type="number" name="price" required></td>
        </tr>
        <tr>
            <td>Start Date:</td>
            <td><input type="date" name="startDate" required></td>
        </tr>
        <tr>
            <td>End Date:</td>
            <td><input type="date" name="endDate" required></td>
        </tr>
        <tr>
            <td>Duration:</td>
            <td><input type="text" name="duration" placeholder="e.g., 7 days 6 nights" required></td>
        </tr>
        <tr>
            <td>Destinations:</td>
            <td><input type="text" name="destinations" placeholder="e.g., Paris, London, Rome" required></td>
        </tr>
        <tr>
            <td>Description:</td>
            <td><textarea name="description" rows="5" cols="30" required></textarea></td>
        </tr>
        <tr>
            <td>
                <input type="submit" value="Create Package">
                <input type="reset" value="Reset">
            </td>
        </tr>
    </table>
</form>

<p><a href="<%= request.getContextPath() %>/customer/packages/list">Back to Package List</a></p>

<jsp:include page="/views/common/footer.jsp"/>
</body>
</html>
