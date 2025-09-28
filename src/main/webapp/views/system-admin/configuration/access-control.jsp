<%-- File: src/main/webapp/views/system-admin/configuration/access-control.jsp --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.travel.model.SystemAdmin" %>
<%
    // Check if system admin is logged in
    SystemAdmin admin = (SystemAdmin) request.getAttribute("admin");
    if (admin == null) {
        response.sendRedirect(request.getContextPath() + "/tour-login");
        return;
    }
%>
<%@ include file="../../common/header-system-admin.jsp" %>

<h2>Control System Access</h2>

<h3>System Access Settings</h3>
<form action="<%= request.getContextPath() %>/tour-system-admin-configuration" method="post">
    <input type="hidden" name="action" value="update-access-control">
    <table>
        <tr>
            <td>Customer Registration:</td>
            <td>
                <select name="customerRegistration">
                    <option value="enabled" selected>Enabled</option>
                    <option value="disabled">Disabled</option>
                </select>
            </td>
        </tr>
        <tr>
            <td>Package Booking:</td>
            <td>
                <select name="packageBooking">
                    <option value="enabled" selected>Enabled</option>
                    <option value="disabled">Disabled</option>
                </select>
            </td>
        </tr>
        <tr>
            <td>Online Payment:</td>
            <td>
                <select name="onlinePayment">
                    <option value="enabled" selected>Enabled</option>
                    <option value="disabled">Disabled</option>
                </select>
            </td>
        </tr>
        <tr>
            <td>Customer Feedback:</td>
            <td>
                <select name="customerFeedback">
                    <option value="enabled" selected>Enabled</option>
                    <option value="disabled">Disabled</option>
                </select>
            </td>
        </tr>
        <tr>
            <td>Issue Reporting:</td>
            <td>
                <select name="issueReporting">
                    <option value="enabled" selected>Enabled</option>
                    <option value="disabled">Disabled</option>
                </select>
            </td>
        </tr>
        <tr>
            <td colspan="2" style="text-align: center;">
                <input type="submit" value="Update Access Control">
            </td>
        </tr>
    </table>
</form>

<%@ include file="../../common/footer.jsp" %>