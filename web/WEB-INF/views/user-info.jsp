<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 02/12/2022
  Time: 5:36 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User info</title>
</head>
<body>
    <h3>${user.fullName}</h3>
    <h3>@${user.username}</h3>
    <h3>Courses of author</h3>
    <c:forEach items="${courses}" var="course">
      <a href="/courses/info/${course.id}">${course.name}</a><br>
    </c:forEach>
</body>
</html>
