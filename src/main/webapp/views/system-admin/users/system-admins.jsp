<%-- File: src/main/webapp/views/system-admin/users/system-admins.jsp --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.travel.model.SystemAdmin" %>
<%@ page import="java.util.List" %>
<%
    SystemAdmin admin = (SystemAdmin) request.getAttribute("admin");
    if (admin == null) response.sendRedirect(request.getContextPath() + "/tour-login");

    List<SystemAdmin> admins = (List<SystemAdmin>) request.getAttribute("admins");
%>
<%@ include file="../../common/header-system-admin.jsp" %>

<h2>Manage System Admins</h2>

<% if (request.getParameter("success") != null) { %>
<div class="success">System admin added successfully!</div>
<% } %>
<% if (request.getParameter("error") != null) { %>
<div class="error"><%= request.getParameter("error") %></div>
<% } %>

<table>
    <tr>
        <th>Employee ID</th><th>Name</th><th>Job Title</th><th>Department</th>
        <th>Email</th><th>Contact Number</th><th>Actions</th>
    </tr>
    <% if (admins != null && !admins.isEmpty()) {
        for (SystemAdmin systemAdmin : admins) { %>
    <tr>
        <td><%= systemAdmin.getEmployeeId() %></td>
        <td><%= systemAdmin.getName() %></td>
        <td><%= systemAdmin.getJobTitle() %></td>
        <td><%= systemAdmin.getDepartment() %></td>
        <td><%= systemAdmin.getEmail() %></td>
        <td><%= systemAdmin.getContactNumber() %></td>
        <td>
            <a href="<%= request.getContextPath() %>/tour-system-admin-users?action=edit-system-admin&id=<%= systemAdmin.getEmployeeId() %>">Edit</a> |
            <a href="<%= request.getContextPath() %>/tour-system-admin-users?action=delete-system-admin&id=<%= systemAdmin.getEmployeeId() %>" onclick="return confirm('Are you sure you want to delete this system admin?')">Delete</a>
        </td>
    </tr>
    <% }
    } else { %>
    <tr><td colspan="7" style="text-align: center;">No system admins found</td></tr>
    <% } %>
</table>

<%@ include file="../../common/footer.jsp" %>