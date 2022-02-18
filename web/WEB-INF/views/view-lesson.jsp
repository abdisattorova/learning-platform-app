<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 02/18/2022
  Time: 3:38 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Lesson</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link href="https://cdn.rawgit.com/harvesthq/chosen/gh-pages/chosen.min.css" rel="stylesheet"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.rawgit.com/harvesthq/chosen/gh-pages/chosen.jquery.min.js"></script>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

</head>
<body>
<td><a style="margin-left: 1000px;margin-top: 10px" class="btn btn-info" href='/lessons/form?id=${lesson.id}'><i
        class="fas fa-edit"> Edit</i>
</a></td>
<td><a style="margin-left: 1000px;margin-top: 10px" class="btn btn-danger" href="/lessons/delete/${lesson.id}"><i
        class="fas fa-trash"> Delete</i> </a></td>
<div style="margin-left: 250px;margin-top: 50px"><h2>${lesson.name}</h2></div>
<br>
<iframe style="margin-left: 250px" width="800" height="500"
        src="${lesson.video_link}"
        title="YouTube video player"
        allow="accelerometer; autoplay; clipboard-write;
        encrypted-media; gyroscope; picture-in-picture"
        allowfullscreen>
</iframe>
<br>
<div style="margin-left: 250px;margin-top: 50px; margin-right:
100px;font-family:sans-serif"><p>${lesson.body}</p></div>
<a href="/courses/info/${lesson.module.course.id}" class="btn btn-primary" style=
        "margin-left: 250px; float: left; margin-bottom: 2rem"
   onMouseOver="this.style.color='#0F0'"
   onMouseOut="this.style.color='#00F'">
    <ion-icon class="bi bi-youtube"></ion-icon>
    Back</a>
</body>
</html>
