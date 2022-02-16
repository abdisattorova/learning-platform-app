<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%--<%@include file="/WEB-INF/views/header.jsp" %>--%>
<html>

<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link href="https://cdn.rawgit.com/harvesthq/chosen/gh-pages/chosen.min.css" rel="stylesheet"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.rawgit.com/harvesthq/chosen/gh-pages/chosen.jquery.min.js"></script>
    <title>Add Course</title>
</head>
<body>

<div style="padding: 20px">
    <form id="user-form" method="post" action="/users">
        <div class="form-group">
            <c:choose>
                <c:when test="${selectedUser.id!=null}">
                    <input hidden name="id" type="number" class="form-control" value="${selectedUser.id}">
                </c:when>
                <c:otherwise>
                    <input hidden name="id" type="number" class="form-control" value="${0}">
                </c:otherwise>
            </c:choose>
        </div>
        <div>
            <label class="form-check-label" for="photo">Upload your photo: </label>
            <input id="photo" type="file" name="file"/>
        </div>
        <br>
        <div class="form-group">
            <input id="fullname" required name="fullName" type="text" class="form-control"
                   placeholder="Enter full name" value="${selectedUser.fullName}">
        </div>
        <br>
        <div class="form-group">
            <input id="username" required name="username" type="text" class="form-control"
                   placeholder="Enter username" value="${selectedUser.username}">
        </div>
        <br>
        <div class="form-group">
            <input id="password" required name="password" type="text" class="form-control"
                   placeholder="Enter password" value="${selectedUser.password}">
        </div>
        <br>

        <br>
        <button type="submit" class="btn btn-primary">Save</button>
    </form>
</div>


</body>

</html>
<%--<%@include file="/view/footer.jsp" %>--%>

