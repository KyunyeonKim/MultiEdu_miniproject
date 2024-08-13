<%--
  Created by IntelliJ IDEA.
  User: kgn47
  Date: 24. 8. 13.
  Time: 오전 11:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Product Details</title>
</head>
<body>
<h1>Product Details</h1>
<table border="1">
    <tr>
        <th>Product ID</th>
        <td>${vo2.product_id}</td>
    </tr>
    <tr>
        <th>Name</th>
        <td>${vo2.name}</td>
    </tr>
    <tr>
        <th>Content</th>
        <td>${vo2.content}</td>
    </tr>
    <tr>
        <th>Price</th>
        <td>${vo2.price}</td>
    </tr>
    <tr>
        <th>Company</th>
        <td>${vo2.company}</td>
    </tr>
    <tr>
        <th>Image</th>
        <td><img src="upload/${vo2.img}" alt="Product Image" width="100" /></td>
    </tr>
</table>
<a href="p_selectAll.do">Back to Product List</a>
<a href="p_update.do?product_id=${vo2.product_id}">Edit Product</a>
<a href="p_delete.do?product_id=${vo2.product_id}">Delete Product</a>
</body>
</html>
