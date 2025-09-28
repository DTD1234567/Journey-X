<%--
  Created by IntelliJ IDEA.
  User: LENOVO
  Date: 9/20/2025
  Time: 12:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%-- File: src/main/webapp/views/system-admin/users/package-managers.jsp --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.travel.model.SystemAdmin" %>
<%@ page import="com.travel.model.PackageManager" %>
<%@ page import="java.util.List" %>
<%
    // Check if system admin is logged in
    SystemAdmin admin = (SystemAdmin) request.getAttribute("admin");
    if (admin == null) {
        response.sendRedirect(request.getContextPath() + "/tour-login");
        return;
    }

    // Get list of package managers
    List<PackageManager> packageManagers = (List<PackageManager>) request.getAttribute("packageManagers");
%>
<%@ include file="../../common/header-system-admin.jsp" %>

<h2>Manage Package Managers</h2>

<%-- Display success or error messages --%>
<% if (request.getParameter("success") != null) { %>
<div class="success">Package manager added successfully!</div>
<% } %>
<% if (request.getParameter("error") != null) { %>
<div class="error">Failed to add package manager. Please try again.</div>
<% } %>

<h3>Add New Package Manager</h3>
<form action="<%= request.getContextPath() %>/tour-system-admin-users" method="post">
    <input type="hidden" name="action" value="add-package-manager">
    <table>
        <tr>
            <td>Name:</td>
            <td><input type="text" name="name" required></td>
        </tr>
        <tr>
            <td>Job Title:</td>
            <td><input type="text" name="jobTitle" required></td>
        </tr>
        <tr>
            <td>Department:</td>
            <td><input type="text" name="department" required></td>
        </tr>
        <tr>
            <td>Contact Number:</td>
            <td><input type="text" name="contactNumber" required></td>
        </tr>
        <tr>
            <td>Email:</td>
            <td><input type="email" name="email" required></td>
        </tr>
        <tr>
            <td>Password:</td>
            <td><input type="password" name="password" required></td>
        </tr>
        <tr>
            <td colspan="2" style="text-align: center;">
                <input type="submit" value="Add Package Manager">
            </td>
        </tr>
    </table>
</form>

<h3>Existing Package Managers</h3>
<table>
    <tr>
        <th>Employee ID</th>
        <th>Name</th>
        <th>Job Title</th>
        <th>Department</th>
        <th>Email</th>
        <th>Contact Number</th>
        <th>Actions</th>
    </tr>
    <% if (packageManagers != null && !packageManagers.isEmpty()) { %>
    <% for (PackageManager manager : packageManagers) { %>
    <tr>
        <td><%= manager.getEmployeeId() %></td>
        <td><%= manager.getName() %></td>
        <td><%= manager.getJobTitle() %></td>
        <td><%= manager.getDepartment() %></td>
        <td><%= manager.getEmail() %></td>
        <td><%= manager.getContactNumber() %></td>
        <td>
            <a href="<%= request.getContextPath() %>/tour-system-admin-users?action=edit-package-manager&id=<%= manager.getEmployeeId() %>">Edit</a> |
            <a href="<%= request.getContextPath() %>/tour-system-admin-users?action=delete-package-manager&id=<%= manager.getEmployeeId() %>" onclick="return confirm('Are you sure you want to delete this package manager?')">Delete</a>
        </td>
    </tr>
    <% } %>
    <% } else { %>
    <tr>
        <td colspan="7" style="text-align: center;">No package managers found</td>
    </tr>
    <% } %>
</table>

<%@ include file="../../common/footer.jsp" %>