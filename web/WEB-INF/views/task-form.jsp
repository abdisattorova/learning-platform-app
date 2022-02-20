<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 02/19/2022
  Time: 5:59 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Task-form</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link href="https://cdn.rawgit.com/harvesthq/chosen/gh-pages/chosen.min.css" rel="stylesheet"/>
    </head>
<body>
<div style="padding: 20px">
    <form action="/tasks" method="post">
        <textarea rows="7" class="form-control"
                  placeholder="Enter question here"
                  name="body" required>${task.body}</textarea>
        <input hidden name="lessonId" type="number"
               class="form-control" value="${task.lessonId}">
        <input  name="title" type="text"
               class="form-control my-3" placeholder="Enter task title here" value="${task.title}">
        <input hidden name="id" type="number"
               class="form-control" value="${task.id}">
        <h5 class="my-3">Enter options here </h5>

        <c:forEach var="num" begin="1" end="4" >
        <input type="radio" name="correct_answer_flag"
         value="${num}">
        <input type="text" placeholder="Enter option here "
               name="answers" value="${task.options.get(num-1).answer}" required/>
        <br/>
        <br/>
        </c:forEach>
        <input type="submit" class="btn btn-primary" value="Save task"
               name="question_submit"/>
    </form>
</div>
</body>
</html>
