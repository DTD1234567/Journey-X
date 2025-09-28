<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.travel.model.*" %>
<%@ page import="com.travel.service.*" %> <%-- Import your service classes --%>

<%
    // Check if system admin is logged in
    Object admin = session.getAttribute("admin");
    if (admin == null) {
        response.sendRedirect(request.getContextPath() + "/tour-login");
        return;
    }

    String managerType = request.getParameter("managerType");
    int employeeId = Integer.parseInt(request.getParameter("employeeId"));

    String name = "", jobTitle = "", department = "", contactNumber = "", email = "";

    // Create service instances
    PackageManagerService pmService = new PackageManagerService();
    BookingManagerService bmService = new BookingManagerService();
    PaymentManagerService payService = new PaymentManagerService();
    CustomerSupportService soService = new CustomerSupportService();

    switch(managerType) {
        case "package":
            PackageManager pm = pmService.getPackageManagerById(employeeId);
            name = pm.getName(); jobTitle = pm.getJobTitle(); department = pm.getDepartment();
            contactNumber = pm.getContactNumber(); email = pm.getEmail();
            break;
        case "booking":
            BookingManager bm = bmService.getBookingManagerById(employeeId);
            name = bm.getName(); jobTitle = bm.getJobTitle(); department = bm.getDepartment();
            contactNumber = bm.getContactNumber(); email = bm.getEmail();
            break;
        case "payment":
            PaymentManager paym = payService.getPaymentManagerById(employeeId);
            name = paym.getName(); jobTitle = paym.getJobTitle(); department = paym.getDepartment();
            contactNumber = paym.getContactNumber(); email = paym.getEmail();
            break;
        case "support":
            CustomerSupportOfficer so = soService.getCustomerSupportOfficerById(employeeId);
            name = so.getName(); jobTitle = so.getJobTitle(); department = so.getDepartment();
            contactNumber = so.getContactNumber(); email = so.getEmail();
            break;
    }
%>

<%@ include file="../../common/header-system-admin.jsp" %>

<h2>Edit <%= managerType.substring(0,1).toUpperCase() + managerType.substring(1) %> Manager</h2>

<form action="<%= request.getContextPath() %>/tour-system-admin-users" method="post">
    <input type="hidden" name="action" value="update-<%= managerType %>-manager">
    <input type="hidden" name="employeeId" value="<%= employeeId %>">

    <table>
        <tr><td>Name:</td><td><input type="text" name="name" value="<%= name %>" required></td></tr>
        <tr><td>Job Title:</td><td><input type="text" name="jobTitle" value="<%= jobTitle %>" required></td></tr>
        <tr><td>Department:</td><td><input type="text" name="department" value="<%= department %>" required></td></tr>
        <tr><td>Contact Number:</td><td><input type="text" name="contactNumber" value="<%= contactNumber %>" required></td></tr>
        <tr><td>Email:</td><td><input type="email" name="email" value="<%= email %>" required></td></tr>
        <tr><td>Password:</td><td><input type="password" name="password"></td></tr>
        <tr><td colspan="2" style="text-align:center;">
            <input type="submit" value="Update Manager">
            <input type="reset" value="Reset">
        </td></tr>
    </table>
</form>

<p><a href="<%= request.getContextPath() %>/tour-system-admin-configuration">Back to User List</a></p>

<%@ include file="../../common/footer.jsp" %>
