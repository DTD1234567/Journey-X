<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.travel.model.Customer" %>

<jsp:include page="../../common/header-customer.jsp" />

<h2>Report an Issue</h2>

<%-- Display error or success message --%>
<% if (request.getAttribute("errorMessage") != null) { %>
<p style="color: red;"><%= request.getAttribute("errorMessage") %></p>
<% } %>
<% if (request.getAttribute("successMessage") != null) { %>
<p style="color: green;"><%= request.getAttribute("successMessage") %></p>
<% } %>

<form action="<%= request.getContextPath() %>/tour-customer-report-issue" method="post">
    <input type="hidden" name="action" value="report-issue">
    <table border="1">
        <tr>
            <td>Issue Details:</td>
            <td><textarea name="issueDetails" rows="5" cols="30" required placeholder="Describe the issue you encountered..."></textarea></td>
        </tr>
        <tr>
            <td colspan="2" style="text-align: center;">
                <input type="submit" value="Report Issue">
            </td>
        </tr>
    </table>
</form>

<p><a href="<%= request.getContextPath() %>/tour-customer-dashboard">Back to Dashboard</a></p>

<jsp:include page="../../common/footer.jsp" />