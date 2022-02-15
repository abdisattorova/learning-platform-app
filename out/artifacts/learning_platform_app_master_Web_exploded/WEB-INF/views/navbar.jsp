<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Akbarali
  Date: 2/12/2022
  Time: 1:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

    <link rel="stylesheet" href="navbar-css.css">
    <link href="//netdna.bootstrapcdn.com/bootstrap/3.0.3/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>

    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <meta http-equiv="x-ua-compatible" content="ie=edge"/>
    <title>Material Design for Bootstrap</title>
    <!-- MDB icon -->
    <link rel="icon" href="img/mdb-favicon.ico" type="image/x-icon"/>
    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://use.fontawesome.com/
    releases/v5.15.2/css/all.css"/>
    <!-- Google Fonts Roboto -->
    <link
            rel="stylesheet"
            href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700;900&display=swap"
    />
    <!-- MDB -->
    <link rel="stylesheet" href="css/mdb.min.css"/>

</head>
<body style="background: #ebe8e2">

<%--<%@include file="/jsp/main-jsp/navbar.jsp" %>--%>

<div class="row-cols-5">

    <div class="col-md-4">
        <!-- Scrollspy -->
        <div style="color: #4CAF50; background: #142F43" id="scrollspy-collapsible" class="sticky-top">
            <ul class="nav flex-column nav-pills menu-sidebar">
                <c:forEach var="course1" items="${courseList}">
                    <li style="color: white" class="nav-item">
                        <a style="color: #FFAB4C" class="nav-link" href="#${course1.id}">
                            <h2>
                                    ${course1.name}
                            </h2>
                        </a>
                    </li>
                </c:forEach>
                <%--                <li class="nav-item">--%>
                <%--                    <a class="nav-link" href="#example-2-collapsible">Section 2</a>--%>
                <%--                </li>--%>
                <%--                &lt;%&ndash;                <li class="nav-item">&ndash;%&gt;--%>
                <%--                &lt;%&ndash;                    <a class="nav-link collapsible-scrollspy" href="#example-3-collapsible">Section 3</a>&ndash;%&gt;--%>
                <%--                &lt;%&ndash;                    <ul class="nav flex-column ps-3">&ndash;%&gt;--%>
                <%--                &lt;%&ndash;                        <li class="nav-item">&ndash;%&gt;--%>
                <%--                &lt;%&ndash;                            <a class="nav-link" href="#example-sub-A-collapsible">Subsection A</a>&ndash;%&gt;--%>
                <%--                &lt;%&ndash;                        </li>&ndash;%&gt;--%>
                <%--                &lt;%&ndash;                        <li class="nav-item">&ndash;%&gt;--%>
                <%--                &lt;%&ndash;                            <a class="nav-link" href="#example-sub-B-collapsible">Subsection B</a>&ndash;%&gt;--%>
                <%--                &lt;%&ndash;                        </li>&ndash;%&gt;--%>
                <%--                &lt;%&ndash;                    </ul>&ndash;%&gt;--%>
                <%--                &lt;%&ndash;                </li>&ndash;%&gt;--%>
                <%--                <li class="nav-item">--%>
                <%--                    <a class="nav-link" href="#example-4-collapsible">Section 4</a>--%>
                <%--                </li>--%>
            </ul>
        </div>
        <!-- Scrollspy -->
    </div>


    <div style="background: #FFAB4C" class="col-md-8">
        <!-- Spied element -->
        <div
                data-mdb-spy="scroll"
                data-mdb-target="#scrollspy-collapsible"
                data-mdb-offset="0"
                class="scrollspy-example">
            <c:forEach var="course" items="${courseList}">
                <section id=${course.id}>
                    <h3 style="width: auto">
                            <%--                        Section 1--%>
                            ${course.description}

                    </h3>

                </section>

            </c:forEach>
            <%--            <section id="example-2-collapsible">--%>
            <%--                <h3>Section 2--%>
            <%--                </h3>--%>
            <%--                ...--%>
            <%--            </section>--%>
            <%--            <section id="example-3-collapsible">--%>
            <%--                <h3>Section 3--%>

            <%--                </h3>--%>
            <%--                ...--%>
            <%--                <section id="example-sub-A-collapsible">--%>
            <%--                    <h3>Subsection A--%>

            <%--                    </h3>--%>
            <%--                    ...--%>
            <%--                </section>--%>
            <%--                <section id="example-sub-B-collapsible">--%>
            <%--                    <h3>Subsection B--%>


            <%--                    </h3>--%>
            <%--                    ...--%>
            <%--                </section>--%>
            <%--            </section>--%>
            <%--            <section id="example-4-collapsible">--%>
            <%--                <h3>Section 4--%>

            <%--                </h3>--%>
            <%--                ...--%>
            <%--            </section>--%>
        </div>
        <!-- Spied element -->
    </div>


</div>
</body>
</html>
