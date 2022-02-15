<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: TEXNO
  Date: 2/15/2022
  Time: 10:56 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Module</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link href="https://cdn.rawgit.com/harvesthq/chosen/gh-pages/chosen.min.css" rel="stylesheet"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.rawgit.com/harvesthq/chosen/gh-pages/chosen.jquery.min.js"></script>
    <title>Add Course</title>
</head>
<body>

<div style="padding: 20px">
    <form id="user-form" method="post" action="/">
        <div class="form-group">
            <c:choose>
               <c:when test="${id!=null}">
                    <input hidden name="id" type="number" class="form-control" value="${id}">
                </c:when>
                <c:otherwise>
                    <input hidden name="id" type="number" class="form-control" value="${0}">
                </c:otherwise>
            </c:choose>
        </div>
        <div class="form-group">
            <input id="name" required name="name" type="text" class="form-control"
                   placeholder="Enter full name" value="${name}">
        </div>
        <button type="submit" class="btn btn-primary">Save</button>
    </form>
</div>
<%--changed this page--%>

</body>
</html>
