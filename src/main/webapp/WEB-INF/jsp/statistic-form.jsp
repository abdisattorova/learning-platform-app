<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: TEXNO
  Date: 2/19/2022
  Time: 12:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <!--     Fonts and icons     -->
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

    <link type="text/css" href="https://fonts.googleapis.com/css?family=Montserrat:400,700,200" rel="stylesheet"/>
    <link type="text/css" href="https://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css"
          rel="stylesheet">
    <!-- CSS Files -->
    <link type="text/css" href="../assets/css/bootstrap.min.css" rel="stylesheet"/>
    <link type="text/css" href="../assets/css/paper-dashboard.css?v=2.0.1" rel="stylesheet"/>
    <link type="text/css" href="../assets/demo/demo.css" rel="stylesheet"/>

    <style type="text/css">
        <%@include file="../../assets/css/bar.css" %>
    </style>
</head>
<body class="">

<div class="wrapper ">
    <div class="main-panel">
        <!-- Navbar -->
        <!-- End Navbar -->
        <div class="content">
            <div class="col-md-12">
                <div class="container">

                    <div class="row">
                        <div class="col-sm-4">
                            <div class="card">
                                <div class="card-body">
                                    <h5 class="card-title">Mentors</h5>
                                    <p class="card-text">${authors_count}</p>
                                    <a href="/mentors" class="btn btn-primary">Show mentors</a>
                                </div>
                            </div>
                        </div>
                        <div class="col-sm-4">
                            <div class="card">
                                <div class="card-body">
                                    <h5 class="card-title">Students</h5>
                                    <p class="card-text">${students_count}</p>
                                    <a href="/students" class="btn btn-primary">Show students</a>
                                </div>
                            </div>
                        </div>
                        <div class="col-sm-4">
                            <div class="card">
                                <div class="card-body">
                                    <h5 class="card-title">Courses</h5>
                                    <p class="card-text">${course_count}</p>
                                    <a href="/courses" class="btn btn-primary">Show courses</a>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
            <%--            <div class="row">--%>
            <%--                <div class="col-lg-3 col-md-6 col-sm-6">--%>
            <%--                    <div class="card card-stats">--%>
            <%--                        <div class="card-body ">--%>
            <%--                            <div class="row">--%>
            <%--                                <div class="col-5 col-md-4">--%>
            <%--                                    <div class="icon-big text-center icon-warning">--%>
            <%--                                        <i class="nc-icon nc-hat-3 text-warning"></i>--%>
            <%--                                    </div>--%>
            <%--                                </div>--%>
            <%--                                <div class="col-7 col-md-8">--%>
            <%--                                    <div class="numbers">--%>
            <%--                                        <p class="card-category">Students</p>--%>
            <%--                                        <p class="card-title mx-3">${students_count}<p>--%>
            <%--                                    </div>--%>
            <%--                                </div>--%>
            <%--                            </div>--%>
            <%--                        </div>--%>
            <%--                        <div class="card-footer ">--%>
            <%--                            <hr>--%>
            <%--                            <div class="stats">--%>

            <%--                            </div>--%>
            <%--                        </div>--%>
            <%--                    </div>--%>
            <%--                </div>--%>
            <%--                <div class="col-lg-3 col-md-6 col-sm-6">--%>
            <%--                    <div class="card card-stats">--%>
            <%--                        <div class="card-body ">--%>
            <%--                            <div class="row">--%>
            <%--                                <div class="col-5 col-md-4">--%>
            <%--                                    <div class="icon-big text-center icon-warning">--%>
            <%--                                        <i class="nc-icon nc-single-02 text-success"></i>--%>
            <%--                                    </div>--%>
            <%--                                </div>--%>
            <%--                                <div class="col-7 col-md-8">--%>
            <%--                                    <div class="numbers">--%>
            <%--                                        <p class="card-category">Authors</p>--%>
            <%--                                        <p class="card-title mx-3">${authors_count}<p>--%>
            <%--                                    </div>--%>
            <%--                                </div>--%>
            <%--                            </div>--%>
            <%--                        </div>--%>
            <%--                        <div class="card-footer ">--%>
            <%--                            <hr>--%>

            <%--                        </div>--%>
            <%--                    </div>--%>
            <%--                </div>--%>
            <%--                <div class="col-lg-3 col-md-6 col-sm-6">--%>
            <%--                    <div class="card card-stats">--%>
            <%--                        <div class="card-body ">--%>
            <%--                            <div class="row">--%>
            <%--                                <div class="col-5 col-md-4">--%>
            <%--                                    <div class="icon-big text-center icon-warning">--%>
            <%--                                        <i class="nc-icon nc-single-copy-04 text-danger"></i>--%>
            <%--                                    </div>--%>
            <%--                                </div>--%>
            <%--                                <div class="col-7 col-md-8">--%>
            <%--                                    <div class="numbers">--%>
            <%--                                        <p class="card-category">Courses</p>--%>
            <%--                                        <p  class="card-title mx-3">${course_count}<p>--%>
            <%--                                    </div>--%>
            <%--                                </div>--%>
            <%--                            </div>--%>
            <%--                        </div>--%>
            <%--                        <div class="card-footer ">--%>
            <%--                            <hr>--%>

            <%--                        </div>--%>
            <%--                    </div>--%>
            <%--                </div>--%>
            <%--                <div class="col-lg-3 col-md-6 col-sm-6">--%>
            <%--                    <div class="card card-stats">--%>
            <%--                        <div class="card-body ">--%>
            <%--                            <div class="row">--%>
            <%--                                <div class="col-5 col-md-4">--%>
            <%--                                    <div class="icon-big text-center icon-warning">--%>
            <%--                                        <i class="nc-icon nc-chart-bar-32 text-primary"></i>--%>
            <%--                                    </div>--%>
            <%--                                </div>--%>
            <%--                                <div class="col-7 col-md-8">--%>
            <%--                                    <div class="numbers">--%>
            <%--                                        <p class="card-category">Tasks</p>--%>
            <%--                                        <p class="card-title">${task_count}<p>--%>
            <%--                                    </div>--%>
            <%--                                </div>--%>
            <%--                            </div>--%>
            <%--                        </div>--%>
            <%--                        <div class="card-footer ">--%>
            <%--                            <hr>--%>

            <%--                        </div>--%>
            <%--                    </div>--%>
            <%--                </div>--%>
            <%--            </div>--%>
            <table class="graph">
                <br>
                <caption>Course tasks</caption>
                <thead>
                <tr>
                    <th scope="col">Item</th>
                    <th scope="col">Percent</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="course" items="${courseStatistics}">
                    <c:set var="one" scope="session" value="${course.count/sum*100}"/>
                    <tr style="height:${one}%">
                        <th scope="row">${course.name}</th>
                        <td><span>${one}%</span></td>
                    </tr>
                </c:forEach>

                </tbody>
            </table>

        </div>

    </div>
</div>
<!--   Core JS Files   -->
<script src="../assets/js/core/jquery.min.js"></script>
<script src="../assets/js/core/popper.min.js"></script>
<script src="../assets/js/core/bootstrap.min.js"></script>
<script src="../assets/js/plugins/perfect-scrollbar.jquery.min.js"></script>
<!--  Google Maps Plugin    -->
<script src="https://maps.googleapis.com/maps/api/js?key=YOUR_KEY_HERE"></script>
<!-- Chart JS -->
<script src="../assets/js/plugins/chartjs.min.js"></script>
<!--  Notifications Plugin    -->
<script src="../assets/js/plugins/bootstrap-notify.js"></script>
<!-- Control Center for Now Ui Dashboard: parallax effects, scripts for the example pages etc -->
<script src="../assets/js/paper-dashboard.min.js?v=2.0.1" type="text/javascript"></script>
<!-- Paper Dashboard DEMO methods, don't include it in your project! -->
<script src="../assets/demo/demo.js"></script>
<script>
    $(document).ready(function () {
        // Javascript method's body can be found in assets/assets-for-demo/js/demo.js
        demo.initChartsPages();
    });
</script>
</body>
</html>
