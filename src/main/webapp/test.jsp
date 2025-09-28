<%--
  Created by IntelliJ IDEA.
  User: LENOVO
  Date: 9/20/2025
  Time: 7:16 PM
  To change this template use File | Settings | File Templates.
--%>
<%-- File: src/main/webapp/test.jsp --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Test Page</title>
</head>
<body>
<h1>Test Page</h1>
<p>If you can see this, your application is deployed correctly.</p>
<p>Context Path: <%= request.getContextPath() %></p>
<p><a href="<%= request.getContextPath() %>/tour-login">Go to Login</a></p>
</body>
</html>