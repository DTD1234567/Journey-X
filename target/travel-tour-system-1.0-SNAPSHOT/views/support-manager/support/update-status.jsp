<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.travel.model.ReportIssue" %>
<%@ include file="../../common/header-support-officer.jsp" %>

<h2>Update Issue Status</h2>

<%
    ReportIssue issue = (ReportIssue) request.getAttribute("issue");
    if (issue != null) {
%>
<form action="<%= request.getContextPath() %>/tour-support-officer-management" method="post">
    <input type="hidden" name="action" value="update-status">
    <input type="hidden" name="issueId" value="<%= issue.getReportId() %>">

    <table border="1">
        <tr>
            <td>Report ID:</td>
            <td><%= issue.getReportId() %></td>
        </tr>
        <tr>
            <td>Customer ID:</td>
            <td><%= issue.getCustomerId() %></td>
        </tr>
        <tr>
            <td>Issue Details:</td>
            <td><%= issue.getIssueDetails() %></td>
        </tr>
        <tr>
            <td>Report Date:</td>
            <td><%= issue.getReportDate() %></td>
        </tr>
        <tr>
            <td>Status:</td>
            <td>
                <select name="status" required>
                    <option value="">-- Select Status --</option>
                    <option value="Open">Open</option>
                    <option value="In Progress">In Progress</option>
                    <option value="Resolved">Resolved</option>
                    <option value="Closed">Closed</option>
                </select>
            </td>
        </tr>
        <tr>
            <td colspan="2" style="text-align: center;">
                <input type="submit" value="Update Status">
                <a href="<%= request.getContextPath() %>/tour-support-officer-management?action=issues-list">Cancel</a>
            </td>
        </tr>
    </table>
</form>
<%
} else {
%>
<p>Issue not found.</p>
<%
    }
%>

<p><a href="<%= request.getContextPath() %>/tour-support-officer-management?action=issues-list">Back to Issues List</a></p>

