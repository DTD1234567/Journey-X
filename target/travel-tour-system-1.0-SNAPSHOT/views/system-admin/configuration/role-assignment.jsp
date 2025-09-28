<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.travel.model.SystemAdmin" %>
<%@ page import="com.travel.model.Customer" %>
<%@ page import="com.travel.model.PackageManager" %>
<%@ page import="com.travel.model.BookingManager" %>
<%@ page import="com.travel.model.PaymentManager" %>
<%@ page import="com.travel.model.CustomerSupportOfficer" %>
<%@ page import="java.util.List" %>
<%
    HttpSession sess = request.getSession(false);
    SystemAdmin admin = (SystemAdmin) sess.getAttribute("user");
    if (admin == null || !"systemAdmin".equals(sess.getAttribute("userType"))) {
        response.sendRedirect(request.getContextPath() + "/tour-login");
        return;
    }

    List<Customer> customers = (List<Customer>) request.getAttribute("customers");
    List<PackageManager> packageManagers = (List<PackageManager>) request.getAttribute("packageManagers");
    List<BookingManager> bookingManagers = (List<BookingManager>) request.getAttribute("bookingManagers");
    List<PaymentManager> paymentManagers = (List<PaymentManager>) request.getAttribute("paymentManagers");
    List<CustomerSupportOfficer> supportOfficers = (List<CustomerSupportOfficer>) request.getAttribute("supportOfficers");
%>
<jsp:include page="../../common/header-system-admin.jsp" />
<h2>Role Assignment</h2>

<%-- Display success or error messages --%>
<% if (request.getParameter("success") != null) { %>
<div class="success"><%= request.getParameter("success") %></div>
<% } %>
<% if (request.getParameter("error") != null) { %>
<div class="error"><%= request.getParameter("error") %></div>
<% } %>

<h3>Add New Package Manager</h3>
<form action="<%= request.getContextPath() %>/tour-system-admin-configuration" method="post">
    <input type="hidden" name="action" value="add-package-manager">
    <table>
        <tr><td>Name:</td><td><input type="text" name="name" required></td></tr>
        <tr><td>Job Title:</td><td><input type="text" name="jobTitle" required></td></tr>
        <tr><td>Department:</td><td><input type="text" name="department" required></td></tr>
        <tr><td>Contact Number:</td><td><input type="text" name="contactNumber" required></td></tr>
        <tr><td>Email:</td><td><input type="email" name="email" required></td></tr>
        <tr><td>Password:</td><td><input type="password" name="password" required></td></tr>
        <tr><td colspan="2" style="text-align: center;"><input type="submit" value="Add Package Manager"></td></tr>
    </table>
</form>

<h3>Add New Booking Manager</h3>
<form action="<%= request.getContextPath() %>/tour-system-admin-configuration" method="post">
    <input type="hidden" name="action" value="add-booking-manager">
    <table>
        <tr><td>Name:</td><td><input type="text" name="name" required></td></tr>
        <tr><td>Job Title:</td><td><input type="text" name="jobTitle" required></td></tr>
        <tr><td>Department:</td><td><input type="text" name="department" required></td></tr>
        <tr><td>Contact Number:</td><td><input type="text" name="contactNumber" required></td></tr>
        <tr><td>Email:</td><td><input type="email" name="email" required></td></tr>
        <tr><td>Password:</td><td><input type="password" name="password" required></td></tr>
        <tr><td colspan="2" style="text-align: center;"><input type="submit" value="Add Booking Manager"></td></tr>
    </table>
</form>

<h3>Add New Payment Manager</h3>
<form action="<%= request.getContextPath() %>/tour-system-admin-configuration" method="post">
    <input type="hidden" name="action" value="add-payment-manager">
    <table>
        <tr><td>Name:</td><td><input type="text" name="name" required></td></tr>
        <tr><td>Job Title:</td><td><input type="text" name="jobTitle" required></td></tr>
        <tr><td>Department:</td><td><input type="text" name="department" required></td></tr>
        <tr><td>Contact Number:</td><td><input type="text" name="contactNumber" required></td></tr>
        <tr><td>Email:</td><td><input type="email" name="email" required></td></tr>
        <tr><td>Password:</td><td><input type="password" name="password" required></td></tr>
        <tr><td colspan="2" style="text-align: center;"><input type="submit" value="Add Payment Manager"></td></tr>
    </table>
</form>

<h3>Add New Support Officer</h3>
<form action="<%= request.getContextPath() %>/tour-system-admin-configuration" method="post">
    <input type="hidden" name="action" value="add-support-officer">
    <table>
        <tr><td>Name:</td><td><input type="text" name="name" required></td></tr>
        <tr><td>Job Title:</td><td><input type="text" name="jobTitle" required></td></tr>
        <tr><td>Department:</td><td><input type="text" name="department" required></td></tr>
        <tr><td>Contact Number:</td><td><input type="text" name="contactNumber" required></td></tr>
        <tr><td>Email:</td><td><input type="email" name="email" required></td></tr>
        <tr><td>Password:</td><td><input type="password" name="password" required></td></tr>
        <tr><td colspan="2" style="text-align: center;"><input type="submit" value="Add Support Officer"></td></tr>
    </table>
</form>

<h3>Current Users</h3>
<table border="1">
    <tr>
        <th>User</th>
        <th>Email</th>
        <th>Role</th>
        <th>Actions</th>
    </tr>

    <%-- Customers --%>
    <% if (customers != null && !customers.isEmpty()) { for (Customer c : customers) { %>
    <tr>
        <td><%= c.getCustomerName() %></td>
        <td><%= c.getEmail() %></td>
        <td>Customer</td>
        <td>
            <form action="<%= request.getContextPath() %>/tour-system-admin-configuration" method="post" style="display:inline;">
                <input type="hidden" name="action" value="delete-customer">
                <input type="hidden" name="customerId" value="<%= c.getCustomerId() %>">
                <input type="submit" value="Delete" onclick="return confirm('Delete?');">
            </form>
        </td>
    </tr>
    <% } } %>

    <%-- Package Managers --%>
    <% if (packageManagers != null && !packageManagers.isEmpty()) { for (PackageManager pm : packageManagers) { %>
    <tr>
        <td><%= pm.getName() %></td>
        <td><%= pm.getEmail() %></td>
        <td>Package Manager</td>
        <td>
            <form action="<%= request.getContextPath() %>/tour-system-admin-configuration" method="post" style="display:inline;">
                <input type="hidden" name="action" value="delete-package-manager">
                <input type="hidden" name="employeeId" value="<%= pm.getEmployeeId() %>">
                <input type="submit" value="Delete" onclick="return confirm('Delete?');">
            </form>
        </td>
    </tr>
    <% } } %>

    <%-- Booking Managers --%>
    <% if (bookingManagers != null && !bookingManagers.isEmpty()) { for (BookingManager bm : bookingManagers) { %>
    <tr>
        <td><%= bm.getName() %></td>
        <td><%= bm.getEmail() %></td>
        <td>Booking Manager</td>
        <td>
            <form action="<%= request.getContextPath() %>/tour-system-admin-configuration" method="post" style="display:inline;">
                <input type="hidden" name="action" value="delete-booking-manager">
                <input type="hidden" name="employeeId" value="<%= bm.getEmployeeId() %>">
                <input type="submit" value="Delete" onclick="return confirm('Delete?');">
            </form>
        </td>
    </tr>
    <% } } %>

    <%-- Payment Managers --%>
    <% if (paymentManagers != null && !paymentManagers.isEmpty()) { for (PaymentManager pym : paymentManagers) { %>
    <tr>
        <td><%= pym.getName() %></td>
        <td><%= pym.getEmail() %></td>
        <td>Payment Manager</td>
        <td>
            <form action="<%= request.getContextPath() %>/tour-system-admin-configuration" method="post" style="display:inline;">
                <input type="hidden" name="action" value="delete-payment-manager">
                <input type="hidden" name="employeeId" value="<%= pym.getEmployeeId() %>">
                <input type="submit" value="Delete" onclick="return confirm('Delete?');">
            </form>
        </td>
    </tr>
    <% } } %>

    <%-- Support Officers --%>
    <% if (supportOfficers != null && !supportOfficers.isEmpty()) { for (CustomerSupportOfficer cso : supportOfficers) { %>
    <tr>
        <td><%= cso.getName() %></td>
        <td><%= cso.getEmail() %></td>
        <td>Support Officer</td>
        <td>
            <form action="<%= request.getContextPath() %>/tour-system-admin-configuration" method="post" style="display:inline;">
                <input type="hidden" name="action" value="delete-support-officer">
                <input type="hidden" name="employeeId" value="<%= cso.getEmployeeId() %>">
                <input type="submit" value="Delete" onclick="return confirm('Delete?');">
            </form>
        </td>
    </tr>
    <% } } %>

    <% if ((customers == null || customers.isEmpty()) && (packageManagers == null || packageManagers.isEmpty()) && (bookingManagers == null || bookingManagers.isEmpty()) && (paymentManagers == null || paymentManagers.isEmpty()) && (supportOfficers == null || supportOfficers.isEmpty())) { %>
    <tr><td colspan="4">No users found</td></tr>
    <% } %>
</table>
<jsp:include page="../../common/footer.jsp" />