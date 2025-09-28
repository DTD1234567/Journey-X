<%-- File: src/main/webapp/views/system-admin/users/customers.jsp --%>
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

<h2>Manage Customers</h2>

<h3>Customer Management</h3>
<p>This section allows you to manage customer accounts. You can view, edit, or delete customer information as needed.</p>
<h3>Recent Customers</h3>
<table>
    <tr>
        <th>Customer ID</th>
        <th>Name</th>
        <th>Email</th>
        <th>Registration Date</th>
        <th>Status</th>
        <th>Actions</th>
    </tr>
    <tr>
        <td>
            <a href="<%= request.getContextPath() %>/tour-system-admin-users?action=edit-customer&id=C12345">Edit</a> |
            <a href="<%= request.getContextPath() %>/tour-system-admin-users?action=delete-customer&id=C12345" onclick="return confirm('Are you sure you want to delete this customer?')">Delete</a>
        </td>
    </tr>
    <tr>
        <td>
            <a href="<%= request.getContextPath() %>/tour-system-admin-users?action=edit-customer&id=C12346">Edit</a> |
            <a href="<%= request.getContextPath() %>/tour-system-admin-users?action=delete-customer&id=C12346" onclick="return confirm('Are you sure you want to delete this customer?')">Delete</a>
        </td>
    </tr>
    <tr>
        <td>
            <a href="<%= request.getContextPath() %>/tour-system-admin-users?action=edit-customer&id=C12347">Edit</a> |
            <a href="<%= request.getContextPath() %>/tour-system-admin-users?action=delete-customer&id=C12347" onclick="return confirm('Are you sure you want to delete this customer?')">Delete</a>
        </td>
    </tr>
    <tr>
        <td>
            <a href="<%= request.getContextPath() %>/tour-system-admin-users?action=edit-customer&id=C12348">Edit</a> |
            <a href="<%= request.getContextPath() %>/tour-system-admin-users?action=delete-customer&id=C12348" onclick="return confirm('Are you sure you want to delete this customer?')">Delete</a>
        </td>
    </tr>
</table>

<%@ include file="../../common/footer.jsp" %>