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
<form action="m_insertOK.do" method="post" enctype="multipart/form-data">
    <label for="product_id">Product ID:</label>
    <input type="text" id="product_id" name="product_id" required /><br />

    <label for="name">Name:</label>
    <input type="text" id="name" name="name" required /><br />

    <label for="content">Content:</label>
    <input type="text" id="content" name="content" required /><br />

    <label for="price">Price:</label>
    <input type="text" id="price" name="price" required /><br />

    <label for="company">Company:</label>
    <input type="text" id="company" name="company" required /><br />

    <label for="img">Image File:</label>
    <input type="file" id="img" name="img" /><br />

    <input type="submit" value="Add Product" />
</form>
<a href="p_selectAll.do">Back to Product List</a>
</body>
</html>
