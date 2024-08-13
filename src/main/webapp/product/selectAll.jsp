<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/header/head.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Product List</title>
</head>
<body>
<h1>Product List</h1>
<form action="p_searchList.do" method="get">
    <label for="searchKey">Search Key:</label>
    <select id="searchKey" name="searchKey">
        <option value="name">Name</option>
        <option value="company">Company</option>
    </select>
    <label for="searchWord">Search Word:</label>
    <input type="text" id="searchWord" name="searchWord" />
    <input type="submit" value="Search" />
</form>

<table border="1">
    <tr>
        <th>Product ID</th>
        <th>Name</th>
        <th>Content</th>
        <th>Price</th>
        <th>Company</th>
        <th>Image</th>
        <th>Actions</th>
    </tr>
    <c:forEach var="product" items="${list}">
        <tr>
            <td>${product.product_id}</td>
            <td>${product.name}</td>
            <td>${product.content}</td>
            <td>${product.price}</td>
            <td>${product.company}</td>
            <td><img src="${pageContext.request.contextPath}/upload/${product.img}" alt="Product Image" width="100" /></td>
            <td>
                <a href="p_selectOne.do?product_id=${product.product_id}">View</a>
                <a href="p_update.do?product_id=${product.product_id}">Edit</a>
                <a href="p_delete.do?product_id=${product.product_id}">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
<a href="p_insert.do">Add New Product</a>
</body>
</html>
