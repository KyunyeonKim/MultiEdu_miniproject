<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Insert Product</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-5">
    <h1 class="mb-4">Insert New Product</h1>

    <form action="p_insertOK.do" method="post" enctype="multipart/form-data">
        <div class="form-group">
            <label for="product_id">Product ID:</label>
            <input type="text" id="product_id" name="product_id" class="form-control" required />
        </div>

        <div class="form-group">
            <label for="name">Name:</label>
            <input type="text" id="name" name="name" class="form-control" required />
        </div>

        <div class="form-group">
            <label for="content">Content:</label>
            <input type="text" id="content" name="content" class="form-control" required />
        </div>

        <div class="form-group">
            <label for="price">Price:</label>
            <input type="text" id="price" name="price" class="form-control" required />
        </div>

        <div class="form-group">
            <label for="company">Company:</label>
            <input type="text" id="company" name="company" class="form-control" required />
        </div>

        <div class="form-group">
            <label for="img">Image File:</label>
            <input type="file" id="img" name="img" class="form-control-file" />
        </div>

        <button type="submit" class="btn btn-primary">Add Product</button>
    </form>

    <a href="p_selectAll.do" class="btn btn-secondary mt-3">Back to Product List</a>
</div>


<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
