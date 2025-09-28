<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.travel.model.ReportIssue" %>
<%@ include file="../common/header-support-officer.jsp" %>

<h2>Reply to Issue</h2>

<%
    ReportIssue issue = (ReportIssue) request.getAttribute("issue");
    if (issue != null) {
%>
<form action="<%= request.getContextPath() %>/tour-support-officer-management" method="post">
    <input type="hidden" name="action" value="reply-issue">
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
            <td>Your Reply:</td>
            <td><textarea name="reply" rows="5" cols="50" required></textarea></td>
        </tr>
        <tr>
            <td colspan="2" style="text-align: center;">
                <input type="submit" value="Send Reply">
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

