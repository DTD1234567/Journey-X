<%--
  Created by IntelliJ IDEA.
  User: LENOVO
  Date: 9/24/2025
  Time: 10:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/header-support-officer.jsp" %>

<h2>Report an Issue</h2>

<% if (request.getParameter("error") != null) { %>
<div class="error">Failed to report issue. Please try again.</div>
<% } %>

<form action="<%= request.getContextPath() %>/customer/support/report-issue" method="post">
    <table border="1">
        <tr>
            <td>Issue Details:</td>
            <td><textarea name="issueDetails" rows="5" cols="50" required></textarea></td>
        </tr>
        <tr>
            <td colspan="2" style="text-align: center;">
                <input type="submit" value="Report Issue">
                <input type="reset" value="Reset">
            </td>
        </tr>
    </table>
</form>

<p><a href="<%= request.getContextPath() %>/customer/dashboard">Back to Dashboard</a></p>

