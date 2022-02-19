<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: TEXNO
  Date: 2/18/2022
  Time: 3:59 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Lesson-form</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link href="https://cdn.rawgit.com/harvesthq/chosen/gh-pages/chosen.min.css" rel="stylesheet"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.rawgit.com/harvesthq/chosen/gh-pages/chosen.jquery.min.js"></script>

</head>
<body>
<div style="padding: 20px">
    <form id="user-form" method="post" action="/lessons">
        <div class="form-group">
            <input hidden name="moduleId" type="number" class="form-control" value="${param.moduleId}">
            <c:choose>
                <c:when test="${lesson.id!=null}">
                    <input hidden name="id" type="number" class="form-control" value="${lesson.id}">
                </c:when>
<%--                <c:otherwise>--%>
<%--                    <input hidden name="id" type="number" class="form-control" value="${null}">--%>
<%--                </c:otherwise>--%>
            </c:choose>
        </div>
        <div class="form-group">
            <input id="name" required name="name" type="text" class="form-control"
                   placeholder="Enter lesson name " value="${lesson.name}">
        </div>
        <br>
        <div class="form-group">
            <textarea name="body"
                      placeholder="Enter lesson body"
                      class="form-control"
                      rows="5"  >${lesson.body}</textarea>
        </div>
        <br>
        <div class="form-group">
            <input id="video_link" required name="video_link" type="text" class="form-control"
                   placeholder="Enter lesson video link " value="${lesson.video_link}">
        </div>
        <button type="submit" class="btn btn-primary my-3">Save</button>
    </form>
</div>


</body>
</html>

