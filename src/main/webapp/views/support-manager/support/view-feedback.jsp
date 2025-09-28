<%--
  Created by IntelliJ IDEA.
  User: LENOVO
  Date: 9/24/2025
  Time: 10:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.travel.model.Feedback" %>
<%@ page import="java.util.List" %>
<%@ include file="../../common/header-support-officer.jsp" %>

<h2>My Feedback</h2>

<% if (request.getParameter("success") != null) { %>
<div class="success">Feedback submitted successfully!</div>
<% } %>

<%
    List<Feedback> feedbacks = (List<Feedback>) request.getAttribute("feedbacks");
    if (feedbacks != null && !feedbacks.isEmpty()) {
%>
<table border="1" width="100%">
    <tr>
        <th>Feedback ID</th>
        <th>Description</th>
        <th>Date Submitted</th>
    </tr>
    <%
        for (Feedback feedback : feedbacks) {
    %>
    <tr>
        <td><%= feedback.getFeedbackId() %></td>
        <td><%= feedback.getDescription() %></td>
        <td><%= new java.util.Date() %></td>
    </tr>
    <%
        }
    %>
</table>
<%
} else {
%>
<p>No feedback submitted yet.</p>
<%
    }
%>

<p>
    <a href="<%= request.getContextPath() %>/customer/support/submit-feedback">Submit New Feedback</a> |
    <a href="<%= request.getContextPath() %>/customer/dashboard">Back to Dashboard</a>
</p>

