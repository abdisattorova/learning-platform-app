<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 02/12/2022
  Time: 2:30 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Main page</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
          crossorigin="anonymous">

</head>
<body>
<div style="justify-content: center">
    <div style="margin-left: 450px ; margin-top: 50px">
        <a href="/login" class="btn btn-primary"
           style="padding: 20px 60px"
           onMouseOver="this.style.color='#0F0'"
           onMouseOut="this.style.color='#00F'">
            <ion-icon name="people-outline"></ion-icon>
            Login</a>
        <a href="/register" class="btn btn-primary mx-100"
           style="padding: 20px 60px"
           onMouseOver="this.style.color='#0F0'"
           onMouseOut="this.style.color='#00F'">
            <ion-icon name="book-outline"></ion-icon>
            Register</a>
        <a href="/courses" class="btn btn-primary"
           style="padding: 20px 60px"
           onMouseOver="this.style.color='#0F0'"
           onMouseOut="this.style.color='#00F'">
            <ion-icon name="people-outline"></ion-icon>
            View site</a>
    </div>
    <div>
        <img style=" display: block;
         margin-top: 20px;
         margin-left: 400px;
        margin-right: auto;
        width: 50%;" src="https://remakelearning.org/wp-content/uploads/2020/01/122.gif">
    </div>
</div>
</body>
</html>
