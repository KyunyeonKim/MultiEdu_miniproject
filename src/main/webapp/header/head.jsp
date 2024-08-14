<%--
  Created by IntelliJ IDEA.
  User: kgn47
  Date: 24. 8. 14.
  Time: 오전 9:29
  To change this template use File | Settings | File Templates.
--%>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>My Website</title>
</head>
<body>
<header style="background-color: green; padding: 20px; text-align: center; color: white;">
    <div>
        <h1>쇼핑몰</h1>
        <nav>
            <ul style="list-style-type: none; padding: 0; margin: 0;">
                <c:choose>
                    <c:when test="${sessionScope.loggedInUser != null}">
                        <li style="display: inline; margin-right: 15px;">
                            <a href="${pageContext.request.contextPath}/m_update.do" style="color: white; text-decoration: none;">사용자 정보 변경</a>
                        </li>
                        <li style="display: inline; margin-right: 15px;">
                            <a href="${pageContext.request.contextPath}/m_logout.do" style="color: white; text-decoration: none;">로그아웃</a>
                        </li>
                        <li style="display: inline; color: white;">회원 ID: <c:out value="${sessionScope.loggedInUser}"/></li>
                    </c:when>
                    <c:otherwise>
                        <li style="display: inline; margin-right: 15px;">
                            <a href="${pageContext.request.contextPath}/m_insert.do" style="color: white; text-decoration: none;">회원가입</a>
                        </li>
                        <li style="display: inline; margin-right: 15px;">
                            <a href="${pageContext.request.contextPath}/m_login.do" style="color: white; text-decoration: none;">로그인</a>
                        </li>
                    </c:otherwise>
                </c:choose>
                <li style="display: inline; margin-left: 15px;">
                    <a href="${pageContext.request.contextPath}/o_pselectAll.do" style="color: white; text-decoration: none;">구매내역</a>
                </li>
            </ul>
        </nav>
    </div>
</header>
<hr style="border: 1px solid #4CAF50;">
</body>
</html>
