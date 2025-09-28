<%-- File: src/main/webapp/views/common/footer.jsp --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
</div>
</div>

<footer>
    <div class="container">
        <p>&copy; 2023 Tour Package Booking System. All rights reserved.</p>
        <p>
            <a href="<%= request.getContextPath() %>/tour-login">Login</a> |
            <a href="<%= request.getContextPath() %>/tour-register">Register</a> |
            <a href="<%= request.getContextPath() %>/views/common/contact.jsp">Contact Us</a>
        </p>
    </div>
</footer>
</body>
</html>