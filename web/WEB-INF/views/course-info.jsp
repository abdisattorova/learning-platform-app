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
    <%--    <jsp:include page="header.jsp" />--%>
    <jsp:include page="courses-header.jsp"/>
</head>
<body>
<c:set var="authors" value="${course.authorDtoList}"></c:set>
<div class="container mt-5">
    <div class="row d-flex justify-content-center">
        <div class="col-md-9">
            <div class="card p-3 py-4">
                <div class="text-center"><img src="data:image/png;base64,${course.imageUrl}" width="500">
                </div>
                <div class="text-center mt-3">
                    <h5 class="mt-2 mb-0">${course.name}</h5>
                    <p class="card-text">${course.description}</p>
                    <br>
                    <h5> Mentors: </h5>
                    <div>
                        <c:forEach items="${authors}" var="author">
                            <a href="/users/info/${author.id}"> ${author.fullName}</a>
                        </c:forEach>
                    </div>
                    <br>
                    <h5>Modules</h5>
                    <c:forEach items="${course.moduleDtoList}" var="module">
                        <details>
                            <summary>${module.name}
                                <c:choose>
                                    <c:when test="${user.role.name().equals('ADMIN')||isAuthor}">
                                        <a href="/modules/delete?id=${module.id}&courseId=${course.id}">
                                            <i class="fas fa-trash" style="color: red"></i></a>
                                        <a href="/modules/form?id=${module.id}&courseId=${course.id}">
                                            <i class="fas fa-edit" style="color: green"></i>
                                        </a>
                                    </c:when>
                                </c:choose>
                            </summary>
                            <p>
                                <c:forEach items="${module.lessons}" var="lesson">
                                    <a target="_top" href="/lessons/${lesson.id}"
                                       class="active mx-3">${lesson.name}</a><br>
                                </c:forEach>
                            </p>
                        </details>

                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
<div class="card-footer text-muted">
    <p style="color: blue;justify-content: center;text-align: center">LEARNING IS FUN WITH US </p>
</div>
</body>
</html>
