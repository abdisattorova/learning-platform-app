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
</div>
<div class="container d-flex justify-content-around" style="padding-top: 1%;">
    <div class="row">
        <a href='/courses/form' class="btn btn-primary" style=
                "margin-left: 16px; float: left; margin-bottom: 2rem"
           onMouseOver="this.style.color='#0F0'"
           onMouseOut="this.style.color='#00F'">
            <i class="fas fa-plus"></i> Add new course </a>


        <div class="col-md-12">

<%--            ---------------------------------------------------------------------------------------------------------------%>

<%--            <table class="table table-bordered">--%>
<%--                <thead>--%>
<%--                <tr style="text-align: center">--%>
<%--                    <th scope="col">#</th>--%>
<%--                    <th scope="col">Course</th>--%>
<%--                    <th scope="col">Authors</th>--%>
<%--                    <th scope="col">Status</th>--%>
<%--                    &lt;%&ndash;                    <c:choose>&ndash;%&gt;--%>
<%--                    &lt;%&ndash;                        <c:when test="${user.role.equals('ADMIN') or user.role.equals('SUPER_ADMIN')}">&ndash;%&gt;--%>
<%--                    <th scope="col" colspan="2">Settings</th>--%>
<%--                </tr>--%>
<%--                </thead>--%>
<%--                <tbody>--%>
<%--                <c:forEach items="${courseList}" var="course" varStatus="loop">--%>
<%--                    <tr scope="row" style="text-align: center">--%>
<%--                        <td>${loop.count}</td>--%>
<%--                        <td><a href="/courses/info/${course.id}">${course.name}--%>
<%--                        </a></td>--%>
<%--                        <td>--%>
<%--                            <c:forEach items="${course.authorDtoList}" var="author">--%>
<%--                                <a href="/users/info/${author.id}"> ${author.fullName}</a><br>--%>
<%--                            </c:forEach>--%>
<%--                        </td>--%>
<%--                        <td>${course.active}</td>--%>
<%--                            &lt;%&ndash;  <c:forEach items="${course.authorDtoList}" var="author">--%>
<%--                                  <c:if test="${author.id==user.id}">--%>
<%--                                      <c:set var="result" value="true"></c:set>--%>
<%--                                  </c:if>--%>
<%--                              </c:forEach>&ndash;%&gt;--%>
<%--                            &lt;%&ndash;                        <c:choose>&ndash;%&gt;--%>
<%--                            &lt;%&ndash;                            <c:when test="${user.role.equals('ADMIN') or user.role.equals('SUPER_ADMIN')}">&ndash;%&gt;--%>
<%--                        <td><a class="btn btn-info" href='/courses/form?id=${course.id}'><i--%>
<%--                                class="fas fa-edit"></i>--%>
<%--                        </a></td>--%>
<%--                        <td><a class="btn btn-danger" href="/courses/delete/${course.id}"><i--%>
<%--                                class="fas fa-trash"></i> </a></td>--%>
<%--                            &lt;%&ndash;                            </c:when>&ndash;%&gt;--%>
<%--                            &lt;%&ndash;                        </c:choose>&ndash;%&gt;--%>
<%--                    </tr>--%>
<%--                </c:forEach>--%>
<%--                </tbody>--%>
<%--                <tbody></tbody>--%>
<%--                <tbody></tbody>--%>
<%--            </table>--%>


<%--            ---------------------------------------------------------------------------------------------------------%>



            <div class="d-flex justify-content-around">
                <div class="row">
                    <c:forEach items="${courseList}" var="course" varStatus="loop">
                        <div class="col-md-3 mt-3">

                            <div class="card">
                                <div class="card-body" style="text-align: center">
                                    <img src="data:image/png;base64, ${course.imageUrl}" style="width: 72px; height: 72px" alt="Here should be image">
                                    <a href="/courses/info/${course.id}">
                                        <h5 class="card-title">${course.name}</h5>
                                    </a>

<%--                                    <c:forEach items="${course.authorDtoList}" var="author">--%>
<%--                                        <a href="/users/info/${author.id}"> ${author.fullName}</a><br>--%>
<%--                                    </c:forEach>--%>

                                        <%--                            <h5 class="card-title">${course.name}</h5>--%>
                                    <a class="btn btn-info" href='/courses/form?id=${course.id}'><i
                                            class="fas fa-edit"></i>
                                    </a>
                                    <a class="btn btn-danger" href="/courses/delete/${course.id}"><i
                                            class="fas fa-trash"></i> </a>

                                        <%-- <a href="<c:url value="/editTask?id=${course.id}"/>" class="btn btn-primary">Edit</a>--%>
                                        <%-- <a href="<c:url value="/deleteTask?id=${course.id}"/>" class="btn btn-danger">Delete</a>--%>
                                </div>
                            </div>

                        </div>
                        <%--            <c:set var="id" value="${task.userId}"/>--%>
                    </c:forEach>

                </div>
            </div>



<%--            ---------------------------------------------------------------------------------------------------------%>



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
