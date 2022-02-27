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
    <link href="//netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css"
          rel="stylesheet" id="bootstrap-css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link href="https://cdn.rawgit.com/harvesthq/chosen/gh-pages/chosen.min.css" rel="stylesheet"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.rawgit.com/harvesthq/chosen/gh-pages/chosen.jquery.min.js"></script>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
          rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
          crossorigin="anonymous">
</head>
<body>

<div class="container mt-5">
    <div class="row d-flex justify-content-center">
        <div class="col-md-7">
            <div class="card p-3 py-4">
                <div class="text-center"><img src="data:image/png;base64,${user.imageUrl}" width="100"
                                              class="rounded-circle">
                </div>
                <div class="text-center mt-3"><span class="bg-secondary p-1 px-4 rounded text-white">Pro</span>
                    <h5 class="mt-2 mb-0">${user.fullName}</h5> <span> ${user.role.name()} </span>
                    <div class="px-4 mt-1">
                        <h6>@${user.username}</h6>
                        <p class="fonts">
                            <c:forEach items="${courses}" var="course">
                                <a href="/courses/info/${course.id}">${course.name}</a><br>
                            </c:forEach>
                        </p>

                    </div>

                    <div class="buttons">
                        <a href="/messages/with/${user.id}" class="btn btn-outline-primary px-4">Message</a>
                        <c:choose>
                            <c:when test="${admin.id!=null&&user.is_blocked==false}">
                                <a href="/users/block/${user.id}" class="btn btn-primary px-4 ms-3">Block</a>
                            </c:when>
                            <c:when test="${admin.id!=null&&user.is_blocked==true}">
                                <a href="/users/block/${user.id}" class="btn btn-primary px-4 ms-3">Unblock</a>
                            </c:when>
                        </c:choose>
                        <c:choose>
                            <c:when test="${admin.id!=null && user.role.name().equals('MENTOR')}">
                                <a href="/users/role/${user.id}" class="btn btn-primary px-4 ms-3">USER</a>
                            </c:when>
                            <c:when test="${admin.id!=null && user.role.name().equals('USER')}">
                                <a href="/users/role/${user.id}" class="btn btn-primary px-4 ms-3">Mentor</a>
                            </c:when>
                        </c:choose>

                        <%--                        <a class="btn btn-primary px-4 ms-3">Contact</a>--%>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
