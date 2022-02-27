<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 02/26/2022
  Time: 11:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>FAQ</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <jsp:include page="../../assets/headers/faq-header.jsp"/>
</head>
<body>
<div class="container">
    <br/>
    <br/>
    <br/>
    <c:choose>
        <c:when test="${user.role.name().equals('USER')}">
            <details>
                <summary>Can I upload my course here?</summary>
                <p>
                    <br>
                    Of course you can, just click the button below to ask admin
                    <br>
                    <br>
                <form method="get" action="/messages/contact-with-admin">
                    <button type="submit" class="btn btn-primary">
                        Contact with admin
                    </button>
                </form>
                </p>
            </details>
        </c:when>
    </c:choose>
</div>
</body>
</html>
