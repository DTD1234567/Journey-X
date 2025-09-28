<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.travel.model.SystemAdmin" %>
<%@ page import="com.travel.model.PaymentManager" %>
<%@ page import="java.util.List" %>
<%
  // Check if system admin is logged in
  SystemAdmin admin = (SystemAdmin) request.getAttribute("admin");
  if (admin == null) {
    response.sendRedirect(request.getContextPath() + "/tour-login");
    return;
  }

  // Get payment managers list
  List<PaymentManager> paymentManagers = (List<PaymentManager>) request.getAttribute("paymentManagers");
%>
<%@ include file="../../common/header-system-admin.jsp" %>

<h2>Payment Managers</h2>

<%-- Display success or error messages --%>
<% if (request.getParameter("success") != null) { %>
<div class="success">Payment manager added successfully!</div>
<% } %>
<% if (request.getParameter("error") != null) { %>
<div class="error"><%= request.getParameter("error") %></div>
<% } %>

<h3>Add New Payment Manager</h3>
<form action="<%= request.getContextPath() %>/tour-system-admin-users" method="post">
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

<h3>Current Payment Managers</h3>
<table>
  <tr>
    <th>Employee ID</th>
    <th>Name</th>
    <th>Email</th>
    <th>Job Title</th>
    <th>Department</th>
    <th>Contact Number</th>
    <th>Actions</th>
  </tr>
  <% if (paymentManagers != null && !paymentManagers.isEmpty()) {
    for (PaymentManager manager : paymentManagers) { %>
  <tr>
    <td><%= manager.getEmployeeId() %></td>
    <td><%= manager.getName() %></td>
    <td><%= manager.getEmail() %></td>
    <td><%= manager.getJobTitle() %></td>
    <td><%= manager.getDepartment() %></td>
    <td><%= manager.getContactNumber() %></td>
    <td>
      <form action="<%= request.getContextPath() %>/tour-system-admin-users" method="post" style="display: inline;">
        <input type="hidden" name="action" value="delete-payment-manager">
        <input type="hidden" name="id" value="<%= manager.getEmployeeId() %>">
        <input type="submit" value="Delete" onclick="return confirm('Are you sure you want to delete this payment manager?');">
      </form>
    </td>
  </tr>
  <% }
  } else { %>
  <tr>
    <td colspan="7" style="text-align: center;">No payment managers found</td>
  </tr>
  <% } %>
</table>

<%@ include file="../../common/footer.jsp" %>