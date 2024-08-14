<%--
  Created by IntelliJ IDEA.
  User: kgn47
  Date: 24. 8. 14.
  Time: 오전 9:29
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-5">
    <h2 class="mb-4">Login</h2>
    <form action="m_loginOK.do" method="get">
        <div class="form-group">
            <label for="member_id">ID:</label>
            <input type="text" id="member_id" name="member_id" class="form-control" required>
        </div>
        <div class="form-group">
            <label for="pw">Password:</label>
            <input type="password" id="pw" name="pw" class="form-control" required>
        </div>
        <button type="submit" class="btn btn-primary">Login</button>
    </form>
    <a href="m_head.do" class="btn btn-secondary mt-3">Back to Product List</a>

</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
