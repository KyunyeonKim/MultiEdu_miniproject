<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>
<%@ page import="orders.OrdersSeachVO" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Order List</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-5">
    <h1 class="mb-4">Order List</h1>
    <table class="table table-striped table-bordered">
        <thead>
        <tr>
            <th>Order ID</th>
            <th>Product ID</th>
            <th>Name</th>
            <th>Content</th>
            <th>Price</th>
            <th>Company</th>
            <th>Image</th>
            <th>Order Date</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>

        <c:forEach var="order" items="${ordersList}">
            <tr>
                <td><c:out value="${order.order_id}"/></td>
                <td><c:out value="${order.product_id}"/></td>
                <td><c:out value="${order.name}"/></td>
                <td><c:out value="${order.content}"/></td>
                <td><c:out value="${order.price}"/></td>
                <td><c:out value="${order.company}"/></td>
                <td><img src="upload/${order.img}" alt="Product Image" class="img-fluid" style="max-width: 100px;" /></td>
                <td><c:out value="${order.order_date}"/></td>
                <td>

                    <form action="like_add.do" method="post" class="d-inline">
                        <input type="hidden" name="product_id" value="${order.product_id}"/>
                        <input type="hidden" name="order_id" value="${order.order_id}"/>
                        <input type="hidden" name="member_id" value="${sessionScope.loggedInUser}"/>
                        <button type="submit" class="btn btn-success btn-sm">Like</button>
                    </form>

                    <form action="dislike_add.do" method="post" class="d-inline">
                        <input type="hidden" name="product_id" value="${order.product_id}"/>
                        <input type="hidden" name="order_id" value="${order.order_id}"/>
                        <input type="hidden" name="member_id" value="${sessionScope.loggedInUser}"/>
                        <button type="submit" class="btn btn-danger btn-sm">Dislike</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <a href="p_selectAll.do" class="btn btn-secondary">Back to Product List</a>
</div>


<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
