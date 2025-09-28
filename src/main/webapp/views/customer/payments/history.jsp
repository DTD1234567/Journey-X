<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.travel.model.Customer" %>
<%@ page import="com.travel.model.Payment" %>
<%@ page import="java.util.List" %>

<%
    Customer customer = (Customer) session.getAttribute("user");
    if (customer == null) {
        response.sendRedirect(request.getContextPath() + "/tour-login");
        return;
    }

    List<Payment> payments = (List<Payment>) request.getAttribute("payments");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Payment History - Customer</title>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/style.css">
</head>
<body>
<jsp:include page="/views/common/header-customer.jsp"/>

<h2>Payment History</h2>

<% if (payments != null && !payments.isEmpty()) { %>
<table>
    <tr>
        <th>Payment ID</th>
        <th>Booking ID</th>
        <th>Package ID</th>
        <th>Amount</th>
        <th>Method</th>
        <th>Status</th>
        <th>Payment Date</th>
    </tr>
    <% for (Payment payment : payments) { %>
    <tr>
        <td><%= payment.getPaymentId() %></td>
        <td><%= payment.getBookingId() %></td>
        <td><%= payment.getPackageId() %></td>
        <td>$<%= payment.getAmount() %></td>
        <td><%= payment.getMethod() %></td>
        <td><%= payment.getStatus() %></td>
        <td><%= payment.getPaymentDate() %></td>
    </tr>
    <% } %>
</table>
<% } else { %>
<p>No payment history available.</p>
<% } %>

<table>
    <tr>
        <td><a href="<%= request.getContextPath() %>/tour-customer-dashboard">Back to Dashboard</a></td>
    </tr>
    <tr>
        <td><a href="<%= request.getContextPath() %>/tour-customer-packages/list">Browse Packages</a></td>
    </tr>
</table>

<jsp:include page="/views/common/footer.jsp"/>
</body>
</html>