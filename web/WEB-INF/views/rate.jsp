<%--
  Created by IntelliJ IDEA.
  User: TEXNO
  Date: 2/27/2022
  Time: 3:03 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Rate</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ" crossorigin="anonymous">
    <style>
        <%@include file="/WEB-INF/views/star.css" %>
    </style>
</head>
<body>

<h1 class="text-center" alt="Simple">${course.name} </h1>

<div class="container">
    <form method="post" action="/courses/rate-course/${course.id}">
        <div class="starrating risingstar d-flex justify-content-center flex-row-reverse">
            <input type="radio" id="star5" name="rate" value="5"/><label for="star5" title="5 star">5</label>
            <input type="radio" id="star4" name="rate" value="4"/><label for="star4" title="4 star">4</label>
            <input type="radio" id="star3" name="rate" value="3"/><label for="star3" title="3 star">3</label>
            <input type="radio" id="star2" name="rate" value="2"/><label for="star2" title="2 star">2</label>
            <input type="radio" id="star1" name="rate" value="1"/><label for="star1" title="1 star">1</label>
       <button type="submit" >Submit</button>
        </div>
    </form>

</div>


</body>
</html>
