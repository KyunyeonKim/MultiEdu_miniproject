<%--
  Created by IntelliJ IDEA.
  User: kgn47
  Date: 24. 8. 13.
  Time: 오후 3:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>My Website</title>
    <style>
        /* 스타일 추가 (필요에 따라) */
        header {
            background-color: #f8f9fa;
            padding: 10px;
            text-align: center;
        }
        nav ul {
            list-style-type: none;
            padding: 0;
        }
        nav ul li {
            display: inline;
            margin-right: 15px;
        }
    </style>
</head>
<body>
<header>
    <h1>My Website</h1>
    <nav>
        <ul>
            <li><a href="${pageContext.request.contextPath}/m_insert.do">회원가입</a></li>
            <li><a href="${pageContext.request.contextPath}/m_login.do">로그인</a></li>
            <li><a href="${pageContext.request.contextPath}/m_update.do">사용자 정보 변경</a></li>
            <li><a href="${pageContext.request.contextPath}/m_logout.do">로그아웃</a></li>
            <li><a href="${pageContext.request.contextPath}/o_pselectAll.do">구매내역</a></li>
        </ul>
    </nav>
    <div>
    </div>
</header>
<hr>
</body>
</html>
