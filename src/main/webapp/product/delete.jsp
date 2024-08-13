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
    <title>Delete Product</title>
</head>
<body>
<h1>Delete Product</h1>
<form action="p_deleteOK.do" method="post">
    <label for="product_id">Product ID:</label>
    <input type="text" id="product_id" name="product_id" required /><br />

    <input type="submit" value="Delete Product" />
</form>
<a href="p_selectAll.do">Back to Product List</a>
</body>
</html>
