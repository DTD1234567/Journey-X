<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.travel.model.Customer" %>

<%
    Customer customer = (Customer) session.getAttribute("user");
    if (customer == null) {
        response.sendRedirect(request.getContextPath() + "/tour-login");
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Submit Feedback - Customer</title>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/style.css">
</head>
<body>
<jsp:include page="/views/common/header-customer.jsp"/>

<h2>Submit Feedback</h2>

<% if (request.getAttribute("errorMessage") != null) { %>
<div class="error"><%= request.getAttribute("errorMessage") %></div>
<% } %>
<% if (request.getAttribute("successMessage") != null) { %>
<div class="success"><%= request.getAttribute("successMessage") %></div>
<% } %>

<form action="<%= request.getContextPath() %>/tour-customer-support" method="post">
    <input type="hidden" name="action" value="create">

    <table>
        <tr>
            <td>Your Feedback:</td>
            <td><textarea name="description" rows="5" cols="30" required placeholder="Please share your feedback about our service..."></textarea></td>
        </tr>
        <tr>
            <td colspan="2" style="text-align: center;">
                <input type="submit" value="Submit Feedback">
            </td>
        </tr>
    </table>
</form>

<table>
    <tr>
        <td><a href="<%= request.getContextPath() %>/tour-customer-support?action=feedback-list">View My Feedback</a></td>
    </tr>
    <tr>
        <td><a href="<%= request.getContextPath() %>/tour-customer-dashboard">Back to Dashboard</a></td>
    </tr>
</table>

<jsp:include page="/views/common/footer.jsp"/>
</body>
</html>