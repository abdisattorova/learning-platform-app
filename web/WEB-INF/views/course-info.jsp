<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 02/12/2022
  Time: 3:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Course info</title>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <style>
        <%@include file="/WEB-INF/views/navbar.css" %>
    </style>
</head>
<body>
<c:set var="authors" value="${course.authorDtoList}"></c:set>
<div class="card-header text-muted">
    <h5 class="card-title"> All about ${course.name}</h5>
    <p class="card-text">${course.description}</p>
    <h6>Authors: </h6>
    <ul>
        <c:forEach items="${authors}" var="author">
            <a href="/users/info/${author.id}"> ${author.fullName}</a>
        </c:forEach>
    </ul>
</div>
<div style="top: 44px;">
    <div id="leftmenuinner" style="padding-top: 44px;">
        <div id="leftmenuinnerinner">
            <!--  <a href='javascript:void(0)' onclick='close_menu()' class='w3-button w3-hide-
            large w3-large w3-display-topright' style='right:16px;padding:3px 12px;font-weight:bold;'>&times;</a>-->
            <c:forEach items="${course.moduleDtoList}" var="module">
                <h5 class="left mx-3"><span class="left_h2">
                        </span> ${module.name}</h5>
                <c:forEach items="${module.lessons}" var="lesson">
                    <a target="_top" href="default.asp" class="active mx-3">${lesson.name}</a><br>
                </c:forEach>
            </c:forEach>
            <br><br>
        </div>
        <a href="/courses" class="btn btn-primary mx-3">
            <i class="bi bi-back"></i>
            Back</a>
        <br>
        <br>
    </div>
</div>
<div class="card-footer text-muted">
    Learning is fun with us
</div>
</div>
</body>
</html>
