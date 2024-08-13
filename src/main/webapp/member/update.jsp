<%--
  Created by IntelliJ IDEA.
  User: kgn47
  Date: 24. 8. 13.
  Time: 오후 3:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update Member</title>
</head>
<body>
<h1>Update Member</h1>
<form action="m_updateOK.do" method="post">
    <input type="hidden" id="member_id" name="member_id" value="${vo2.member_id}" />

    <label for="pw">Password:</label>
    <input type="password" id="pw" name="pw" value="${vo2.pw}" required /><br />

    <label for="tel">Telephone:</label>
    <input type="text" id="tel" name="tel" value="${vo2.tel}" required /><br />

    <label for="name">Name:</label>
    <input type="text" id="name" name="name" value="${vo2.name}" required /><br />

    <input type="submit" value="Update Member" />
</form>
<a href="m_head.do">Back to Product List</a>

</body>
</html>

