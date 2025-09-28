<%-- File: src/main/webapp/views/package-manager/dashboard.jsp --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.travel.model.PackageManager" %>
<%@ page import="com.travel.model.Package" %>
<%@ page import="java.util.List" %>
<%@ page import="com.travel.service.PackageService" %>
<%
    // Check if package manager is logged in
    PackageManager manager = (PackageManager) session.getAttribute("user");
    if (manager == null) {
        response.sendRedirect(request.getContextPath() + "/tour-login");
        return;
    }

    // Create package service and get all packages
    PackageService packageService = new PackageService();
    List<Package> packages = packageService.getAllPackages();
%>
<%@ include file="../common/header-packagemanager.jsp" %>

<h2>Welcome, <%= manager.getName() %>!</h2>

<%-- Display success or error messages --%>
<% if (request.getParameter("success") != null) { %>
<div class="success">Package <%= request.getParameter("success").equals("1") ? "added" : "updated" %> successfully!</div>
<% } %>

<% if (request.getParameter("error") != null) { %>
<div class="error">Operation failed. Please try again.</div>
<% } %>

<h3>Quick Actions</h3>
<div class="quick-actions">
    <div class="quick-action">
        <h3><a href="<%= request.getContextPath() %>/tour-package-manager-packages?action=add">Add New Package</a></h3>
        <p>Create a new tour package with destinations, pricing, and details</p>
    </div>
    <div class="quick-action">
        <h3><a href="<%= request.getContextPath() %>/tour-package-manager-packages?action=list">View All Packages</a></h3>
        <p>View and manage all available tour packages</p>
    </div>
    <%-- Replace the Edit Package quick action with this --%>
    <div class="quick-action">
        <h3><a href="<%= request.getContextPath() %>/tour-package-manager-packages?action=list">Edit Package</a></h3>
        <p>Select a package from the list to update its details</p>
    </div>
    <%-- Replace the Remove Package quick action with this --%>
    <div class="quick-action">
        <h3><a href="<%= request.getContextPath() %>/tour-package-manager-packages?action=list">Remove Package</a></h3>
        <p>Select a package from the list to remove it</p>
    </div>
</div>

<h3>Package Statistics</h3>
<table>
    <tr>
        <th>Metric</th>
        <th>Value</th>
    </tr>
    <tr>
        <td>Total Packages</td>
        <td><%= packages != null ? packages.size() : 0 %></td>
    </tr>
    <tr>
        <td>Active Packages</td>
        <td><%= packages != null ? packages.size() : 0 %></td>
    </tr>
    <tr>
        <td>Most Popular Package</td>
        <td><%= packages != null && !packages.isEmpty() ? packages.get(0).getPackageName() : "None" %></td>
    </tr>
</table>

<h3>Recent Packages</h3>
<table>
    <tr>
        <th>Package ID</th>
        <th>Package Name</th>
        <th>Price</th>
        <th>Duration</th>
        <th>Actions</th>
    </tr>
    <% if (packages != null && !packages.isEmpty()) {
        // Show up to 5 most recent packages
        int maxPackages = Math.min(packages.size(), 5);
        for (int i = 0; i < maxPackages; i++) {
            Package pkg = packages.get(i); %>
    <tr>
        <td><%= pkg.getPackageId() %></td>
        <td><%= pkg.getPackageName() %></td>
        <td>$<%= pkg.getPrice() %></td>
        <td><%= pkg.getDuration() %></td>
        <td>
            <a href="<%= request.getContextPath() %>/tour-package-manager-packages?action=edit&id=<%= pkg.getPackageId() %>">Edit</a> |
            <a href="<%= request.getContextPath() %>/tour-package-manager-packages?action=remove&id=<%= pkg.getPackageId() %>">Remove</a>
        </td>
    </tr>
    <% }
    } else { %>
    <tr>
        <td colspan="5" style="text-align: center;">No packages found</td>
    </tr>
    <% } %>
</table>

<% if (packages != null && packages.size() > 5) { %>
<p><a href="<%= request.getContextPath() %>/tour-package-manager-packages?action=list">View All Packages</a></p>
<% } %>

<%@ include file="../common/footer.jsp" %>