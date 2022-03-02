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
    <form id="jsp/course-form" method="post" action="/courses" enctype="multipart/form-data">
        <div class="form-group">
            <c:choose>
                <c:when test="${selectedCourse.id!=null}">
                    <input hidden name="id" type="number" class="form-control" value="${selectedCourse.id}">
                    <input hidden name="imageUrl" type="text" class="form-control" value="${selectedCourse.imageUrl}">
                </c:when>
                <c:otherwise>
                    <input hidden name="id" type="number" class="form-control" value="${0}">
                </c:otherwise>
            </c:choose>
        </div>
        <div class="form-group">
            <input id="name" required name="name" type="text" class="form-control"
                   placeholder="Enter course name" value="${selectedCourse.name}">
        </div>
        <br>
        <div class="form-group">
            <textarea id="description" required name="description" class="form-control" rows="3"
                      placeholder="Enter course description">${selectedCourse.description}</textarea>
        </div>
        <br>

        <div class="form-check">
            <input name="isActive" checked type="checkbox" class="form-check-input" id="exampleCheck1">
            <label class="form-check-label" for="exampleCheck1">Is Active</label>
        </div>

        <div>
            <label class="form-check-label" for="photo">Upload photo: </label>
            <input id="photo" type="file" name="file"/>
        </div>
        <br>

        <div class="form-group">
            <label for="authors" id="authors"> Choose authors:</label>
            <select multiple name='authorsIds'>
                <c:forEach var="author" items="${authors}">
                    <option value="${author.id}">${author.fullName}</option>
                </c:forEach>
            </select>
        </div>
        <br>

        <button type="submit" class="btn btn-primary">Save</button>
    </form>
</div>
</body>

</html>
<%--<%@include file="/view/footer.jsp" %>--%>

