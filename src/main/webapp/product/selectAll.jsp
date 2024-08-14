<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/header/head.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Product List</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <h1 class="my-4">Product List</h1>


    <form action="p_searchList.do" method="get" class="form-inline mb-4">
        <div class="form-group mx-sm-3 mb-2">
            <label for="searchKey" class="sr-only">Search Key</label>
            <select id="searchKey" name="searchKey" class="form-control">
                <option value="name">Name</option>
                <option value="company">Company</option>
            </select>
        </div>
        <div class="form-group mx-sm-3 mb-2">
            <label for="searchWord" class="sr-only">Search Word</label>
            <input type="text" id="searchWord" name="searchWord" class="form-control" placeholder="Search Word">
        </div>
        <button type="submit" class="btn btn-primary mb-2">Search</button>
    </form>


    <table class="table table-striped">
        <thead class="thead-dark">
        <tr>
            <th>Product ID</th>
            <th>Name</th>
            <th>Content</th>
            <th>Price</th>
            <th>Company</th>
            <th>Image</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="product" items="${list}">
            <tr>
                <td>${product.product_id}</td>
                <td>${product.name}</td>
                <td>${product.content}</td>
                <td>${product.price}</td>
                <td>${product.company}</td>
                <td><img src="${pageContext.request.contextPath}/upload/${product.img}" alt="Product Image" class="img-thumbnail" width="100"></td>
                <td>
                    <a href="p_selectOne.do?product_id=${product.product_id}" class="btn btn-info btn-sm">View</a>
                    <a href="p_deleteOK.do?product_id=${product.product_id}" class="btn btn-danger btn-sm">Delete</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <a href="p_insert.do" class="btn btn-success">Add New Product</a>
</div>


<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
