<%@ page import="com.travel.model.Customer" %>
<%@ page import="com.travel.model.Booking" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
    Customer customer = (Customer) session.getAttribute("user");
    if (customer == null) {
        response.sendRedirect(request.getContextPath() + "/tour-login");
        return;
    }

    Booking booking = (Booking) request.getAttribute("booking");
    if (booking == null) {
        response.sendRedirect(request.getContextPath() + "/customer/payments/make?error=Booking not found");
        return;
    }

    double amount = booking.getTotalPrice();
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Make Payment</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/customer-payment-make.css">
</head>
<body>
<jsp:include page="/views/common/header-customer.jsp"/>

<h2>Make a Payment</h2>

<%-- Messages --%>
<% if (request.getAttribute("errorMessage") != null) { %>
<p class="error"><%= request.getAttribute("errorMessage") %></p>
<% } %>
<% if (request.getAttribute("successMessage") != null) { %>
<p class="success"><%= request.getAttribute("successMessage") %></p>
<% } %>

<form action="<%= request.getContextPath() %>/customer/payments/make" method="post">
    <input type="hidden" name="bookingId" value="<%= booking.getBookingId() %>">

    <table>
        <tr>
            <td>Amount ($):</td>
            <td><input type="number" name="amount" value="<%= booking.getTotalPrice() %>" readonly required></td>
        </tr>
        <tr>
            <td>Payment Method:</td>
            <td>
                <select name="method" required>
                    <option value="">-- Select Payment Method --</option>
                    <option value="Credit Card">Credit Card</option>
                    <option value="Debit Card">Debit Card</option>
                    <option value="Bank Transfer">Bank Transfer</option>
                    <option value="Cash">Cash</option>
                    <option value="Check">Check</option>
                </select>
            </td>
        </tr>

        <%-- Card Fields --%>
        <tr>
            <td>Card Number:</td>
            <td><input type="text" name="cardNumber" required></td>
        </tr>
        <tr>
            <td>Expiry Date:</td>
            <td><input type="month" name="expiryDate" required></td>
        </tr>
        <tr>
            <td>CVV:</td>
            <td><input type="text" name="cvv" maxlength="3" pattern="\d{3}" required></td>
        </tr>

        <tr>
            <td colspan="2" style="text-align:center;">
                <input type="submit" value="Submit Payment">
                <input type="reset" value="Reset">
            </td>
        </tr>
    </table>
</form>

<p><a href="<%= request.getContextPath() %>/customer/payments/history">View Payment History</a></p>
<p><a href="<%= request.getContextPath() %>/customer/dashboard">Back to Dashboard</a></p>

<jsp:include page="/views/common/footer.jsp"/>
</body>
</html>
