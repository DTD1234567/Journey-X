<%--
  Created by IntelliJ IDEA.
  User: LENOVO
  Date: 9/20/2025
  Time: 3:09 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.travel.model.Package" %>
<%@ page import="java.text.SimpleDateFormat" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Package</title>
</head>
<body>
<jsp:include page="/views/common/header-customer.jsp"/>

<h2>Edit Package</h2>

<%-- Display error message if exists --%>
<% if (request.getAttribute("errorMessage") != null) { %>
<p><%= request.getAttribute("errorMessage") %></p>
<% } %>

<%
    Package pkg = (Package) request.getAttribute("package");
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    if (pkg != null) {
%>

<form action="<%= request.getContextPath() %>/customer/packages/edit" method="post">
    <table border="1">
        <tr>
            <td>Package ID:</td>
            <td><%= pkg.getPackageId() %></td>
        </tr>
        <tr>
            <td>Package Name:</td>
            <td><input type="text" name="packageName" value="<%= pkg.getPackageName() %>" required></td>
        </tr>
        <tr>
            <td>Price:</td>
            <td><input type="number" name="price" value="<%= pkg.getPrice() %>" required></td>
        </tr>
        <tr>
            <td>Start Date:</td>
            <td><input type="date" name="startDate" value="<%= dateFormat.format(pkg.getStartDate()) %>" required></td>
        </tr>
        <tr>
            <td>End Date:</td>
            <td><input type="date" name="endDate" value="<%= dateFormat.format(pkg.getEndDate()) %>" required></td>
        </tr>
        <tr>
            <td>Duration:</td>
            <td><input type="text" name="duration" value="<%= pkg.getDuration() %>" required></td>
        </tr>
        <tr>
            <td>Destinations:</td>
            <td><input type="text" name="destinations" value="<%= pkg.getDestinations() %>" required></td>
        </tr>
        <tr>
            <td>Description:</td>
            <td><textarea name="description" rows="5" cols="30" required><%= pkg.getDescription() %></textarea></td>
        </tr>
        <tr>
            <td colspan="2" align="center">
                <input type="hidden" name="packageId" value="<%= pkg.getPackageId() %>">
                <input type="submit" value="Update Package">
                <input type="reset" value="Reset">
            </td>
        </tr>
    </table>
</form>

<% } else { %>
<p>Package not found.</p>
<% } %>

<p><a href="<%= request.getContextPath() %>/customer/packages/list">Back to Package List</a></p>

<jsp:include page="/views/common/footer.jsp"/>
</body>
</html>
