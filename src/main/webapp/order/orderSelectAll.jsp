<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>
<%@ page import="orders.OrdersSeachVO" %>

<html>
<head>
    <title>Order List</title>
</head>
<body>
<h1>Order List</h1>
<table border="1">
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
    </tr>
    </thead>
    <tbody>
    <!-- JSTL을 사용하여 ordersList 반복 처리 -->
    <c:forEach var="order" items="${ordersList}">
        <tr>
            <td><c:out value="${order.order_id}"/></td>
            <td><c:out value="${order.product_id}"/></td>
            <td><c:out value="${order.name}"/></td>
            <td><c:out value="${order.content}"/></td>
            <td><c:out value="${order.price}"/></td>
            <td><c:out value="${order.company}"/></td>
            <td><c:out value="${order.img}"/></td>
            <td><c:out value="${order.order_date}"/></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<a href="/m_head.do">Back to Product List</a>
</body>
</html>