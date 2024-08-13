<%--
  Created by IntelliJ IDEA.
  User: kgn47
  Date: 24. 8. 13.
  Time: 오후 3:33
  To change this template use File | Settings | File Templates.
--%>
<%--
  Created by IntelliJ IDEA.
  User: kgn47
  Date: 24. 8. 13.
  Time: 오전 11:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Insert Product</title>
</head>
<body>
<h1>Insert New Product</h1>
<form action="m_insertOK.do" method="post">
    <label for="member_id">Member ID:</label>
    <input type="text" id="member_id" name="member_id" ><br>

    <label for="pw">Password:</label>
    <input type="password" id="pw" name="pw" ><br>

    <label for="tel">Telephone:</label>
    <input type="text" id="tel" name="tel" ><br>

    <label for="name">Name:</label>
    <input type="text" id="name" name="name" ><br>

    <input type="submit" value="Join">
</form>
<a href="m_head.do">Back to Product List</a>
</body>
</html>
