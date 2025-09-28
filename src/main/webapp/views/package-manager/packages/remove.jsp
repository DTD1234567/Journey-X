<%-- File: src/main/webapp/views/package-manager/packages/remove.jsp --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.travel.model.PackageManager" %>
<%@ page import="com.travel.model.Package" %>
<%
    // Check if package manager is logged in
    PackageManager manager = (PackageManager) session.getAttribute("user");
    if (manager == null) {
        response.sendRedirect(request.getContextPath() + "/tour-login");
        return;
    }

    // Get package to remove
    Package pkg = (Package) request.getAttribute("package");
%>
<%@ include file="../../common/header-packagemanager.jsp" %>

<h2>Remove Package</h2>

<p>Are you sure you want to remove the following package?</p>

<table>
    <tr>
        <td>Package ID:</td>
        <td><%= pkg.getPackageId() %></td>
    </tr>
    <tr>
        <td>Package Name:</td>
        <td><%= pkg.getPackageName() %></td>
    </tr>
    <tr>
        <td>Price:</td>
        <td>$<%= pkg.getPrice() %></td>
    </tr>
    <tr>
        <td>Duration:</td>
        <td><%= pkg.getDuration() %></td>
    </tr>
</table>

<form action="<%= request.getContextPath() %>/tour-package-manager-packages" method="post">
    <input type="hidden" name="action" value="delete">
    <input type="hidden" name="packageId" value="<%= pkg.getPackageId() %>">
    <input type="submit" value="Yes, Remove Package">
    <a href="<%= request.getContextPath() %>/tour-package-manager-packages?action=list">No, Cancel</a>
</form>

<%@ include file="../../common/footer.jsp" %>