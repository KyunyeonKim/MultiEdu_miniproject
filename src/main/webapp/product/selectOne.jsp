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

<!-- 구매하기 버튼 추가 -->
<form action="o_insertOK.do" method="post">
    <input type="hidden" name="product_id" value="${vo2.product_id}" />
    <input type="hidden" name="member_id" value="${sessionScope.loggedInUser}" /> <!-- 세션에서 member_id 가져옴 -->
    <input type="submit" value="Buy Now" />
</form>

<a href="p_selectAll.do">Back to Product List</a>
<a href="p_update.do?product_id=${vo2.product_id}">Edit Product</a>
<a href="p_delete.do?product_id=${vo2.product_id}">Delete Product</a>
</body>
</html>