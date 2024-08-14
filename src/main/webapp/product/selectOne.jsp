<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Product Details</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-5">
    <h1 class="mb-4">Product Details</h1>


    <table class="table table-bordered">
        <tbody>
        <tr>
            <th scope="row">Product ID</th>
            <td>${vo2.product_id}</td>
        </tr>
        <tr>
            <th scope="row">Name</th>
            <td>${vo2.name}</td>
        </tr>
        <tr>
            <th scope="row">Content</th>
            <td>${vo2.content}</td>
        </tr>
        <tr>
            <th scope="row">Price</th>
            <td>${vo2.price}</td>
        </tr>
        <tr>
            <th scope="row">Company</th>
            <td>${vo2.company}</td>
        </tr>
        <tr>
            <th scope="row">Image</th>
            <td><img src="${pageContext.request.contextPath}/upload/${vo2.img}" alt="Product Image" class="img-fluid" style="max-width: 200px;" /></td>
        </tr>
        </tbody>
    </table>


    <h2 class="mt-4">Likes: ${likeCount}</h2>
    <h2 class="mt-4">Dislikes: ${dislikeCount}</h2>


    <form action="o_insertOK.do" method="post" class="mt-4">
        <input type="hidden" name="product_id" value="${vo2.product_id}" />
        <input type="hidden" name="member_id" value="${sessionScope.loggedInUser}" />
        <button type="submit" class="btn btn-primary">Buy Now</button>
    </form>


    <h2 class="mt-5">Leave a Comment</h2>
    <form action="c_insertOK.do" method="post">
        <input type="hidden" name="product_id" value="${vo2.product_id}" />
        <input type="hidden" name="member_id" value="${sessionScope.loggedInUser}" />
        <div class="form-group">
            <label for="title">Title:</label>
            <input type="text" id="title" name="title" class="form-control" required />
        </div>
        <div class="form-group">
            <label for="content">Content:</label>
            <textarea id="content" name="content" class="form-control" rows="4" required></textarea>
        </div>
        <button type="submit" class="btn btn-primary">Submit Comment</button>
    </form>


    <div class="mt-3">
        <a href="p_selectAll.do" class="btn btn-secondary">Back to Product List</a>
    </div>


    <h2 class="mt-5">Comments</h2>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>Comment ID</th>
            <th>Member ID</th>
            <th>Product ID</th>
            <th>Title</th>
            <th>Content</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="comment" items="${list}">
            <tr>
                <td>${comment.id}</td>
                <td>${comment.member_id}</td>
                <td>${comment.product_id}</td>
                <td>${comment.title}</td>
                <td>${comment.content}</td>
                <td>
                    <a href="c_deleteOK.do?comment_id=${comment.id}&product_id=${vo2.product_id}" class="btn btn-danger btn-sm">Delete</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>


<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
