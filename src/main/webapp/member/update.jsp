<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Update Member</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-5">
    <h1 class="mb-4">Update Member</h1>
    <form action="m_updateOK.do" method="post">
        <input type="hidden" id="member_id" name="member_id" value="${vo2.member_id}" />

        <div class="form-group">
            <label for="pw">Password:</label>
            <input type="text" id="pw" name="pw" class="form-control" value="${vo2.pw}" required>
        </div>
        <div class="form-group">
            <label for="tel">Telephone:</label>
            <input type="text" id="tel" name="tel" class="form-control" value="${vo2.tel}" required>
        </div>
        <div class="form-group">
            <label for="name">Name:</label>
            <input type="text" id="name" name="name" class="form-control" value="${vo2.name}" required>
        </div>
        <button type="submit" class="btn btn-primary">Update Member</button>
    </form>
    <a href="m_head.do" class="btn btn-secondary mt-3">Back to Product List</a>
</div>

<!-- Bootstrap JS and dependencies -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
