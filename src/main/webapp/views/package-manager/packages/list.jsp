<%-- File: src/main/webapp/views/package-manager/packages/list.jsp --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.travel.model.PackageManager" %>
<%@ page import="com.travel.model.Package" %>
<%@ page import="java.util.List" %>
<%
  // Check if package manager is logged in
  PackageManager manager = (PackageManager) session.getAttribute("user");
  if (manager == null) {
    response.sendRedirect(request.getContextPath() + "/tour-login");
    return;
  }

  // Get packages list
  List<Package> packages = (List<Package>) request.getAttribute("packages");
%>
<%@ include file="../../common/header-packagemanager.jsp" %>

<h2>Package List</h2>

<%-- Display success or error messages --%>
<% if (request.getParameter("success") != null) { %>
<% if ("1".equals(request.getParameter("success"))) { %>
<div class="success">Package added successfully!</div>
<% } else if ("2".equals(request.getParameter("success"))) { %>
<div class="success">Package updated successfully!</div>
<% } else if ("3".equals(request.getParameter("success"))) { %>
<div class="success">Package deleted successfully!</div>
<% } %>
<% } %>

<% if (request.getParameter("error") != null) { %>
<div class="error">Operation failed. Please try again.</div>
<% } %>

<p><a href="<%= request.getContextPath() %>/tour-package-manager-packages?action=add">Add New Package</a></p>

<table>
  <tr>
    <th>Package ID</th>
    <th>Package Name</th>
    <th>Price</th>
    <th>Start Date</th>
    <th>End Date</th>
    <th>Duration</th>
    <th>Actions</th>
  </tr>
  <% if (packages != null && !packages.isEmpty()) {
    for (Package pkg : packages) { %>
  <tr>
    <td><%= pkg.getPackageId() %></td>
    <td><%= pkg.getPackageName() %></td>
    <td>$<%= pkg.getPrice() %></td>
    <td><%= pkg.getStartDate() %></td>
    <td><%= pkg.getEndDate() %></td>
    <td><%= pkg.getDuration() %></td>
    <td>
      <a href="<%= request.getContextPath() %>/tour-package-manager-packages?action=edit&id=<%= pkg.getPackageId() %>">Edit</a> |
      <a href="<%= request.getContextPath() %>/tour-package-manager-packages?action=remove&id=<%= pkg.getPackageId() %>">Remove</a>
    </td>
  </tr>
  <% }
  } else { %>
  <tr>
    <td colspan="7" style="text-align: center;">No packages found</td>
  </tr>
  <% } %>
</table>

<%@ include file="../../common/footer.jsp" %>