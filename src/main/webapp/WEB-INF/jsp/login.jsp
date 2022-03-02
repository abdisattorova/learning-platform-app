<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 02/13/2022
  Time: 5:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link href="//netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css"
          rel="stylesheet" id="bootstrap-css">
    <%--    <script src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>--%>
    <%--    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>--%>

</head>
<body>
<c:choose>
    <c:when test="${msg!=null}">
        <div class="alert" style=" padding: 20px;
  background-color: #f44336;
  color: white;
 margin-top: 100px;
 margin-left: 450px;
 width: 40%;
justify-content: center;

 ">
    <span style="
  margin-left: 15px;
  color: white;
  font-weight: bold;
  float: right;
  font-size: 22px;
  line-height: 20px;
  cursor: pointer;
  transition: 0.3s;"
          class="closebtn" onclick="this.parentElement.style.display='none';">&times;</span>
                ${msg}
        </div>
    </c:when>
</c:choose>

<div class="container">
    <form action="/users/login" method="post">
        <div id="loginbox" style="margin-top:50px;" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
            <div class="panel panel-info">
                <div class="panel-heading">
                    <div class="panel-title">Sign In</div>
                    <div style="float:right; font-size: 80%; position: relative; top:-10px"><a href="#">Forgot
                        password?</a>
                    </div>
                </div>
                <div style="padding-top:30px" class="panel-body">
                    <div style="display:none" id="login-alert"
                         class="alert alert-danger col-sm-12"></div>
                    <form id="loginform" class="form-horizontal" role="form">
                        <div style="margin-bottom: 25px" class="input-group">
                            <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                            <input required id="login-username" type="text" class="form-control" name="username"
                                   value=""
                                   placeholder="username">
                        </div>
                        <div style="margin-bottom: 25px" class="input-group">
                            <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                            <input required id="login-password" type="password" class="form-control" name="password"
                                   placeholder="password">
                        </div>
                        <div style="margin-top:10px" class="form-group">
                            <!-- Button -->
                            <div class="col-sm-12 controls">
                                <input type="submit" value="Login" class="btn btn-primary">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-12 control">
                                <div style="border-top: 1px solid#888; padding-top:15px; font-size:85%">
                                    Don't have an account?
                                    <a href="/register">
                                        Sign Up Here
                                    </a>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </form>
</div>
</body>
</html>
