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
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link href="https://cdn.rawgit.com/harvesthq/chosen/gh-pages/chosen.min.css" rel="stylesheet"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.rawgit.com/harvesthq/chosen/gh-pages/chosen.jquery.min.js"></script>


    <title>Users</title>
</head>
<body class="container">
<c:set var="msg" value="${param.message}"></c:set>
<h1>${msg}</h1>
<a class="m-4 btn btn-primary" href="/users/form">+ Add new user</a>
<div>

    <table class="table">
        <thead class="thead-light">
        <tr>
            <%--        <th scope="col">#</th>--%>
            <th scope="col">Profile photo</th>
            <th scope="col">Full name</th>
            <th scope="col">Username</th>
            <th scope="col">Password</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="user" items="${userList}">
            <tr>
                    <%--           <th scope="row">1</th>--%>

                <td><img src="data:image/png;base64, ${user.imageUrl}"
                         style="width: 40px;height: 40px;border-radius: 50%"
                         alt="Profile photo"></td>
                <td>${user.fullName}</td>
                <td>${user.username}</td>
                <td>${user.password}</td>
                <td>
                    <a class="btn btn-warning" href='/users/form?id=${user.id}'>Edit</a>
                    <a class="btn btn-danger" href="/users/delete/${user.id}">Delete</a>
                </td>
            </tr>
        </c:forEach>
        <br>
        <br>
        <c:forEach begin="1" var="page" end="${pages}">
            <a href="/users?page=${page}">
                <button>${page}</button>
            </a>
        </c:forEach>

        </tbody>
    </table>
</div>

</body>
</html>
