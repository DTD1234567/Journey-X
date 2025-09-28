<%-- File: src/main/webapp/views/package-manager/packages/edit.jsp --%>
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

  // Get package to edit
  Package pkg = (Package) request.getAttribute("package");
%>
<%@ include file="../../common/header-packagemanager.jsp" %>



<form action="<%= request.getContextPath() %>/tour-package-manager-packages" method="post">
  <input type="hidden" name="action" value="update">
  <input type="hidden" name="packageId" value="<%= pkg.getPackageId() %>">
  <table>
    <tr>
      <td>Package Name:</td>
      <td><input type="text" name="packageName" value="<%= pkg.getPackageName() %>" required></td>
    </tr>
    <tr>
      <td>Price:</td>
      <td><input type="number" name="price" step="0.01" value="<%= pkg.getPrice() %>" required></td>
    </tr>
    <tr>
      <td>Start Date:</td>
      <td><input type="date" name="startDate" value="<%= pkg.getStartDate() %>" required></td>
    </tr>
    <tr>
      <td>End Date:</td>
      <td><input type="date" name="endDate" value="<%= pkg.getEndDate() %>" required></td>
    </tr>
    <tr>
      <td>Duration:</td>
      <td><input type="text" name="duration" value="<%= pkg.getDuration() %>" required></td>
    </tr>
    <tr>
      <td>Destinations:</td>
      <td><textarea name="destinations" rows="3" cols="30" required><%= pkg.getDestinations() %></textarea></td>
    </tr>
    <tr>
      <td>Description:</td>
      <td><textarea name="description" rows="5" cols="30" required><%= pkg.getDescription() %></textarea></td>
    </tr>
    <tr>
      <td colspan="2" style="text-align: center;">
        <input type="submit" value="Update Package">
        <input type="reset" value="Reset">
      </td>
    </tr>
  </table>
</form>

<p><a href="<%= request.getContextPath() %>/tour-package-manager-packages?action=list">Back to Package List</a></p>

<%@ include file="../../common/footer.jsp" %>