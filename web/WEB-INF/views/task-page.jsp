<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: TEXNO
  Date: 2/20/2022
  Time: 1:48 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Task page</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link href="https://cdn.rawgit.com/harvesthq/chosen/gh-pages/chosen.min.css" rel="stylesheet"/>
</head>
<body>
<div style="padding: 20px">
    <form action="/tasks/check/${task.id}" method="get">
        <input hidden name="id" type="number"
               class="form-control" value="${task.id}">
        <h4>${task.title}</h4>
        <p>${task.body}</p>
        <h5 class="my-3">Select correct option </h5>
        <c:forEach var="num" begin="1" end="4" >
            <input type="radio" name="answer"
                   value="${task.options.get(num-1).id}">
            <input readonly type="text" value="${task.options.get(num-1).answer}" />
            <br/>
            <br/>
        </c:forEach>
        <input type="submit" class="btn btn-primary" value="Submit answer"
               name="question_submit"/>
    </form>
</div>
</body>
</html>
