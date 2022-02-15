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

</head>
<body>
<h3 style="color: blueviolet">${param.msg}</h3>
<c:set var="authors" value="${course.authorDtoList}"></c:set>
<div class="card-header text-muted">
    <img src="${course.imageUrl}" alt="keldi">
    <h5 class="card-title"> All about ${course.name}</h5>
    <p class="card-text">${course.description}</p>
    <h6>Authors: </h6>
    <ul>
        <c:forEach items="${authors}" var="author">
            <a href="/users/info/${author.id}"> ${author.fullName}</a>
        </c:forEach>
    </ul>

    <a href='/modules/form?courseId=${course.id}' class="btn btn-primary" style=
            "margin-left: 3px; float: left;"
       onMouseOver="this.style.color='#0F0'"
       onMouseOut="this.style.color='#00F'">
        <i class="fas fa-plus"></i> Add new module </a>
</div>
<div style="top: 44px;">
    <div id="leftmenuinner" style="padding-top: 44px;">
        <div id="leftmenuinnerinner">
            <c:forEach items="${course.moduleDtoList}" var="module">

                <h5 class="left mx-3"><span class="left_h2">
                        </span> ${module.name} </h5>

                <td><a class="btn btn-danger mx-3"
                       href="/modules/delete?id=${module.id}&courseId=${course.id}">
                    <i class="fas fa-trash"></i>
                    Delete</a>
                </td>
                <td>
                    <a class="btn btn-info "
                       href="/modules/form?id=${module.id}&courseId=${course.id}">
                        <i class="fas fa-edit"></i>
                        Edit</a>
                </td>
                <br>
                <br>
                <c:forEach items="${module.lessons}" var="lesson">
                    <a target="_top" href="/lessons/${lesson.id}" class="active mx-3">${lesson.name}</a><br>
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
</body>
</html>
