<%-- File: src/main/webapp/views/package-manager/packages/add.jsp --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.travel.model.PackageManager" %>
<%
    // Check if package manager is logged in
    PackageManager manager = (PackageManager) session.getAttribute("user");
    if (manager == null) {
        response.sendRedirect(request.getContextPath() + "/tour-login");
        return;
    }
%>
<%@ include file="../../common/header-packagemanager.jsp" %>

<h2>Add New Package</h2>

<%-- Display error message if needed --%>
<% if (request.getParameter("error") != null) { %>
<div class="error">Failed to add package. Please try again.</div>
<% } %>

<form action="<%= request.getContextPath() %>/tour-package-manager-packages" method="post">
    <input type="hidden" name="action" value="add">
    <table>
        <tr>
            <td>Package Name:</td>
            <td><input type="text" name="packageName" required></td>
        </tr>
        <tr>
            <td>Price:</td>
            <td><input type="number" name="price" step="0.01" required></td>
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
            <td><input type="text" name="duration" required></td>
        </tr>
        <tr>
            <td>Destinations:</td>
            <td><textarea name="destinations" rows="3" cols="30" required></textarea></td>
        </tr>
        <tr>
            <td>Description:</td>
            <td><textarea name="description" rows="5" cols="30" required></textarea></td>
        </tr>
        <tr>
            <td colspan="2" style="text-align: center;">
                <input type="submit" value="Add Package">
                <input type="reset" value="Reset">
            </td>
        </tr>
    </table>
</form>

<p><a href="<%= request.getContextPath() %>/tour-package-manager-packages?action=list">Back to Package List</a></p>

<%@ include file="../../common/footer.jsp" %>