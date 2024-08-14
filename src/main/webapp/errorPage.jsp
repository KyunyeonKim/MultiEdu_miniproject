<%--
  Created by IntelliJ IDEA.
  User: kgn47
  Date: 24. 8. 14.
  Time: 오후 12:12
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<html>
<head>
    <title>Error</title>
</head>
<body>
<h1>Error</h1>
<p><c:out value="${errorMessage}"/></p>
<a href="p_selectAll.do" class="btn btn-secondary">Back to Product List</a>
</body>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</html>