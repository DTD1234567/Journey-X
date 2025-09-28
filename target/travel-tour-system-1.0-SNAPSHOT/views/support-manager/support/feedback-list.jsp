<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.travel.model.Feedback" %>
<%@ page import="java.util.List" %>
<%@ include file="../../common/header-support-officer.jsp" %>

<h2>All Customer Feedback</h2>

<% if (request.getParameter("success") != null) { %>
<div class="success"><%= request.getParameter("success") %></div>
<% } %>

<% if (request.getParameter("error") != null) { %>
<div class="error"><%= request.getParameter("error") %></div>
<% } %>

<%
    List<Feedback> feedbacks = (List<Feedback>) request.getAttribute("feedbacks");
    if (feedbacks != null && !feedbacks.isEmpty()) {
%>
<table border="1" width="100%">
    <tr>
        <th>Feedback ID</th>
        <th>Customer ID</th>
        <th>Description</th>
    </tr>
    <%
        for (Feedback feedback : feedbacks) {
    %>
    <tr>
        <td><%= feedback.getFeedbackId() %></td>
        <td><%= feedback.getCustomerId() %></td>
        <td><%= feedback.getDescription() %></td>
    </tr>
    <%
        }
    %>
</table>
<%
} else {
%>
<p>No feedback found.</p>
<%
    }
%>

<p><a href="<%= request.getContextPath() %>/tour-support-dashboard">Back to Dashboard</a></p>

