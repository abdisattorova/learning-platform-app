<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 02/20/2022
  Time: 3:28 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link href="https://cdn.rawgit.com/harvesthq/chosen/gh-pages/chosen.min.css" rel="stylesheet"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.rawgit.com/harvesthq/chosen/gh-pages/chosen.jquery.min.js"></script>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
          rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
          crossorigin="anonymous">

</head>
<body>

<div class="container mt-5 mb-5">
    <div class="row height d-flex justify-content-center align-items-center">
        <div class="col-md-7">
            <div class="card">
                <div class="p-3">
                    <h6>Messages</h6>
                </div>
                <c:forEach var="discussion" items="${discussions}">
                    <div class="mt-2">
                        <div class="d-flex flex-row p-3">
                            <img src="data:image/png;base64,${discussion.user.imageUrl}"
                                 width="40"
                                 height="40"
                                 class="rounded-circle mr-3">
                            <div class="w-100">
                                <div class="d-flex justify-content-between align-items-center">
                                    <div class="d-flex flex-row align-items-center"><span
                                            class="mr-2">${discussion.user.fullName}</span>
                                            <%--                                        <small class="c-badge">Top Comment</small>--%>
                                    </div>
                                    <small>${discussion.created_at.format(DateTimeFormatter.ofPattern("hh:mm, dd.MM.yyyy"))}</small>
                                </div>
                                <p class="text-justify comment-text mb-0">${discussion.message}</p>
                                <div class="d-flex flex-row user-feed"><span class="wish"><i
                                        class="fa fa-heartbeat mr-2"></i>24</span> <span class="ml-3"><i
                                        class="fa fa-comments-o mr-2"></i>Reply</span></div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
            <div class="mt-3 d-flex flex-row align-items-center p-3 form-color">
                <img src="data:image/png;base64,${user.imageUrl}" width="50"
                     class="rounded-circle mr-2">
                <div style="padding: 8px">
                    <form action="/discussions" method="post">
                             <textarea cols="70" rows="4" class="form-control"
                                       placeholder="Enter your message..."
                                       name="message" required></textarea>
                        <input hidden type="number" name="lessonId" value="${lessonId}">
                        <input type="submit" class="btn btn-primary my-2" style="margin-left: 450px" value="Send"
                               name="submit"/>
                    </form>
                </div>
            </div>
            <a class="btn btn-primary my-3" href="/lessons/${lessonId}">Back to lesson</a>
        </div>
    </div>
</div>

</body>
</html>
