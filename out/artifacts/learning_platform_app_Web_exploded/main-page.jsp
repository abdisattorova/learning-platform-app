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
<div class="m-20">
    <a href="/login" class="btn btn-primary" style=
            "margin-left: 16px; float: left; margin-bottom: 2rem"
       onMouseOver="this.style.color='#0F0'"
       onMouseOut="this.style.color='#00F'">
        <ion-icon name="people-outline"></ion-icon>
        Login</a>
    <a href="/register" class="btn btn-primary" style=
            "margin-left: 16px; float: left; margin-bottom: 2rem"
       onMouseOver="this.style.color='#0F0'"
       onMouseOut="this.style.color='#00F'">
        <ion-icon name="book-outline"></ion-icon>
        Register</a>
</div>
</body>
</html>
