<%--
  Created by IntelliJ IDEA.
  User: hp
  Date: 2/23/2022
  Time: 11:59 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin page</title>

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
            integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.7/dist/umd/popper.min.js"
            integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js"
            integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
            crossorigin="anonymous"></script>


</head>
<body>

<div class="container">

    <div class="row">
        <div class="col-sm-4">
            <div class="card">
                <div class="card-body">
                    <h5 class="card-title">Mentors</h5>
                    <p class="card-text">count of mentors: ${"mentorsCount"}</p>
                    <a href="/mentors" class="btn btn-primary">Show mentors</a>
                </div>
            </div>
        </div>
        <div class="col-sm-4">
            <div class="card">
                <div class="card-body">
                    <h5 class="card-title">Students</h5>
                    <p class="card-text">count of students: ${"studentsCount"}</p>
                    <a href="/users" class="btn btn-primary">Show students</a>
                </div>
            </div>
        </div>
        <div class="col-sm-4">
            <div class="card">
                <div class="card-body">
                    <h5 class="card-title">Courses</h5>
                    <p class="card-text">count of courses: ${"coursesCount"}</p>
                    <a href="/courses" class="btn btn-primary">Show courses</a>
                </div>
            </div>
        </div>
    </div>

</div>
</body>
</html>
