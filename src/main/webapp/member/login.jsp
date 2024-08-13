<%--
  Created by IntelliJ IDEA.
  User: kgn47
  Date: 24. 8. 13.
  Time: 오후 3:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
</head>
<body>
<h2>Login</h2>
<form action="m_loginOK.do" method="get">
    <label for="member_id">ID:</label>
    <input type="text" id="member_id" name="member_id" required>
    <br>
    <label for="pw">Password:</label>
    <input type="password" id="pw" name="pw" required>
    <br>
    <input type="submit" value="Login">
</form>
<% if (request.getParameter("error") != null) { %>
<p style="color: red;">Login failed. Please check your ID and Password.</p>
<% } %>
</body>
</html>
