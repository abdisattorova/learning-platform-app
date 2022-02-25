<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Abror
  Date: 09-Feb-22
  Time: 11:31 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@include file="header.jsp" %>--%>
<html>
<head>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <title>Courses</title>
    <%--  <style><%@include file="/WEB-INF/views/bar.css"%></style>--%>
</head>
<body class="container">
<h1>${message}</h1>
<form action="/courses" method="get">
    <div class="form-group">
        <label for="exampleInputEmail1">Search course</label>
        <input type="text" class="form-control" id="exampleInputEmail1" name="search" placeholder="Enter course name">
    </div>
    <br>
</form>
<div class="container" style="padding-top: 2rem">

<%--<div class="container d-flex justify-content-around" style="padding-top: 1%;">--%>
    <div class="row">
        <c:choose>
            <c:when test="${user.role.name().equals('ADMIN')||user.role.name().equals('MENTOR')}">
                <a href='/courses/form' class="btn btn-primary" style=
                        "margin-left: 16px; float: left; margin-bottom: 2rem"
                   onMouseOver="this.style.color='#0F0'"
                   onMouseOut="this.style.color='#00F'">
                    <i class="fas fa-plus"></i> Add new course </a>
                <br>
            </c:when>
        </c:choose>

        <div class="col-md-12">
            <%--            ---------------------------------------------------------------------------------------------------------%>
            <div class="row">
                <c:forEach items="${courseList}" var="course" varStatus="loop">

                    <div class="col-md-3 mt-3">

                        <div class="card">
                            <div class="card-body" style="text-align: center">
                                <img src="data:image/png;base64, ${course.imageUrl}"
                                     style="width: 72px; height: 72px" alt="Here should be image">
                                <br>
                                <a href="/courses/info/${course.id}">
                                    <h5 class="card-title">${course.name}</h5>
                                </a>
                                <c:forEach items="${course.authorDtoList}" var="author">
                                    <a href="/users/info/${author.id}"> ${author.fullName}</a><br>
                                </c:forEach>
                                <c:choose>
                                    <c:when test="${user.role.name().equals('ADMIN')||user.role.name().equals('MENTOR')}">
                                        <a class="btn btn-info" href='/courses/form?id=${course.id}'><i
                                                class="fas fa-edit"></i>
                                        </a>
                                        <a class="btn btn-danger" href="/courses/delete/${course.id}"><i
                                                class="fas fa-trash"></i> </a>
                                    </c:when>
                                </c:choose>
                            </div>
                        </div>

                    </div>
                    <%--            <c:set var="id" value="${task.userId}"/>--%>
                </c:forEach>
            </div>
            <div style="padding-left:40%;padding-right:40%;padding-top:1rem">
                <nav aria-label="Page navigation example">
                    <ul class="pagination">
                        <c:forEach var="page" begin="1" end="${pages}">
                            <a href='courses?page=${page-1}'>
                                <button>${page}</button>
                            </a>
                        </c:forEach>
                    </ul>
                </nav>
            </div>
        </div>
    </div>
</div>
</body>
</html>
