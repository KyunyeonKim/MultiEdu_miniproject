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
    <form action="m_insertOK.do" method="post">
        <div class="form-group">
            <label for="member_id">Member ID:</label>
            <input type="text" id="member_id" name="member_id" class="form-control" required>
        </div>
        <div class="form-group">
            <label for="pw">Password:</label>
            <input type="password" id="pw" name="pw" class="form-control" required>
        </div>
        <div class="form-group">
            <label for="tel">Telephone:</label>
            <input type="text" id="tel" name="tel" class="form-control">
        </div>
        <div class="form-group">
            <label for="name">Name:</label>
            <input type="text" id="name" name="name" class="form-control">
        </div>
        <button type="submit" class="btn btn-primary">Join</button>
    </form>
    <a href="m_head.do" class="btn btn-secondary mt-3">Back to Product List</a>
</div>


<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
