<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.travel.model.ReportIssue" %>
<%@ page import="java.util.List" %>
<%@ include file="../../common/header-support-officer.jsp" %>

<h2>All Reported Issues</h2>

<% if (request.getParameter("success") != null) { %>
<div class="success"><%= request.getParameter("success") %></div>
<% } %>

<% if (request.getParameter("error") != null) { %>
<div class="error"><%= request.getParameter("error") %></div>
<% } %>

<%
    List<ReportIssue> issues = (List<ReportIssue>) request.getAttribute("issues");
    if (issues != null && !issues.isEmpty()) {
%>
<table border="1" width="100%">
    <tr>
        <th>Report ID</th>
        <th>Customer ID</th>
        <th>Issue Details</th>
        <th>Report Date</th>
        <th>Actions</th>
    </tr>
    <%
        for (ReportIssue issue : issues) {
    %>
    <tr>
        <td><%= issue.getReportId() %></td>
        <td><%= issue.getCustomerId() %></td>
        <td><%= issue.getIssueDetails() %></td>
        <td><%= issue.getReportDate() %></td>
        <td>
            <a href="<%= request.getContextPath() %>/tour-support-officer-panel?action=reply-issue&id=<%= issue.getReportId() %>">Reply</a> |
            <a href="<%= request.getContextPath() %>/tour-support-officer-panel?action=update-status&id=<%= issue.getReportId() %>">Update Status</a>
        </td>
    </tr>
    <%
        }
    %>
</table>
<%
} else {
%>
<p>No issues found.</p>
<%
    }
%>

<p><a href="<%= request.getContextPath() %>/tour-support-dashboard">Back to Dashboard</a></p>

