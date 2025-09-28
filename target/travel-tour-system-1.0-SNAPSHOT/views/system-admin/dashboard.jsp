<%-- File: src/main/webapp/views/system-admin/dashboard.jsp --%>
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
<%@ include file="../common/header-system-admin.jsp" %>
<!-- Add CSS for dashboard styling -->
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/system-admin-dashboard.css">

<h2>Welcome, <%= admin.getName() %>!</h2>

<h3>System Overview</h3>
<div class="quick-actions">
    <div class="quick-action">
        <h3><a href="<%= request.getContextPath() %>/tour-system-admin-overview?action=bookings">View Bookings</a></h3>
        <p>Check all booking information and statistics</p>
    </div>
    <div class="quick-action">
        <h3><a href="<%= request.getContextPath() %>/tour-system-admin-overview?action=revenue">View Revenue</a></h3>
        <p>Monitor system revenue and financial reports</p>
    </div>
    <div class="quick-action">
        <h3><a href="<%= request.getContextPath() %>/tour-system-admin-overview?action=feedback">View Feedback</a></h3>
        <p>Review customer feedback and ratings</p>
    </div>
    <div class="quick-action">
        <h3><a href="<%= request.getContextPath() %>/tour-system-admin-overview?action=pending-actions">View Pending Actions</a></h3>
        <p>Check pending tasks and actions</p>
    </div>
</div>

<h3>User Management</h3>
<div class="quick-actions">
    <div class="quick-action">
        <h3><a href="<%= request.getContextPath() %>/tour-system-admin-users?action=customers">Manage Customers</a></h3>
        <p>Add, edit, or remove customer accounts</p>
    </div>
    <div class="quick-action">
        <h3><a href="<%= request.getContextPath() %>/tour-system-admin-users?action=package-managers">Manage Package Managers</a></h3>
        <p>Manage package manager accounts</p>
    </div>
    <div class="quick-action">
        <h3><a href="<%= request.getContextPath() %>/tour-system-admin-users?action=booking-managers">Manage Booking Managers</a></h3>
        <p>Manage booking manager accounts</p>
    </div>
    <div class="quick-action">
        <h3><a href="<%= request.getContextPath() %>/tour-system-admin-users?action=payment-managers">Manage Payment Managers</a></h3>
        <p>Manage payment manager accounts</p>
    </div>
    <div class="quick-action">
        <h3><a href="<%= request.getContextPath() %>/tour-system-admin-users?action=support-officers">Manage Support Officers</a></h3>
        <p>Manage customer support officer accounts</p>
    </div>
    <div class="quick-action">
        <h3><a href="<%= request.getContextPath() %>/tour-system-admin-users?action=system-admins">Manage System Admins</a></h3>
        <p>Manage system administrator accounts</p>
    </div>
</div>

<h3>System Configuration</h3>
<div class="quick-actions">
    <div class="quick-action">
        <h3><a href="<%= request.getContextPath() %>/tour-system-admin-configuration?action=system-messages">Edit System Messages</a></h3>
        <p>Update system notifications and messages</p>
    </div>
    <div class="quick-action">
        <h3><a href="<%= request.getContextPath() %>/tour-system-admin-configuration?action=access-control">Control System Access</a></h3>
        <p>Manage system access permissions</p>
    </div>
    <div class="quick-action">
        <h3><a href="<%= request.getContextPath() %>/tour-system-admin-configuration?action=role-assignment">Assign Roles</a></h3>
        <p>Assign roles and permissions to users</p>
    </div>
</div>


<%@ include file="../common/footer.jsp" %>