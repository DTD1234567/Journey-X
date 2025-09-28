<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.travel.model.PaymentManager" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Payment Manager Dashboard</title>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/payment-manager-dashboard.css">

</head>
<body>
<jsp:include page="/views/common/header-paymentmanager.jsp"/>

<h2>Payment Manager Dashboard</h2>

<p>Welcome, <%= ((PaymentManager)session.getAttribute("user")).getName() %>!</p>

<table border="1" width="100%">
    <tr>
        <td>
            <h3>Quick Actions</h3>
            <ul>
                <li><a href="<%= request.getContextPath() %>/payment-manager/payments/list">View All Payments</a></li>
                <li><a href="<%= request.getContextPath() %>/payment-manager/payments/list">Process Refund</a></li>
                <li><a href="<%= request.getContextPath() %>/payment-manager/payments/revenue">View Revenue Reports</a></li>
            </ul>
        </td>
    </tr>
    <tr>
        <td>
            <h3>Account Information</h3>
            <table border="1">
                <tr>
                    <td>Employee ID:</td>
                    <td><%= ((PaymentManager)session.getAttribute("user")).getEmployeeId() %></td>
                </tr>
                <tr>
                    <td>Name:</td>
                    <td><%= ((PaymentManager)session.getAttribute("user")).getName() %></td>
                </tr>
                <tr>
                    <td>Job Title:</td>
                    <td><%= ((PaymentManager)session.getAttribute("user")).getJobTitle() %></td>
                </tr>
                <tr>
                    <td>Department:</td>
                    <td><%= ((PaymentManager)session.getAttribute("user")).getDepartment() %></td>
                </tr>
                <tr>
                    <td>Email:</td>
                    <td><%= ((PaymentManager)session.getAttribute("user")).getEmail() %></td>
                </tr>
                <tr>
                    <td>Contact Number:</td>
                    <td><%= ((PaymentManager)session.getAttribute("user")).getContactNumber() %></td>
                </tr>
            </table>
        </td>
    </tr>
</table>

<jsp:include page="/views/common/footer.jsp"/>
</body>
</html>