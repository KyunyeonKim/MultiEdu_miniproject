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
    <title>Update Product</title>
</head>
<body>
<h1>Update Product</h1>
<form action="p_updateOK.do" method="post" enctype="multipart/form-data">
    <input type="hidden" id="product_id" name="product_id" value="${vo2.product_id}" />

    <label for="name">Name:</label>
    <input type="text" id="name" name="name" value="${vo2.name}" required /><br />

    <label for="content">Content:</label>
    <input type="text" id="content" name="content" value="${vo2.content}" required /><br />

    <label for="price">Price:</label>
    <input type="text" id="price" name="price" value="${vo2.price}" required /><br />

    <label for="company">Company:</label>
    <input type="text" id="company" name="company" value="${vo2.company}" required /><br />

    <label for="img">Image URL:</label>
    <input type="text" id="img" name="img" value="${vo2.img}" /><br />

    <input type="submit" value="Update Product" />
</form>
<a href="p_selectAll.do">Back to Product List</a>
</body>
</html>
