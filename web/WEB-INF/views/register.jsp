<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 02/13/2022
  Time: 5:50 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign up</title>
    <link href="//netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css"
          rel="stylesheet" id="bootstrap-css">
</head>
<body>
<h1>${msg}</h1>
<div class="container">
    <form action="/users" method="post" >
        <div style="margin-top:50px;" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
            <div class="panel panel-info">
                <div class="panel-heading">
                    <div class="panel-title">Sign up</div>
                    <div style="float:right; font-size: 80%; position: relative; top:-10px">
                    </div>
                </div>
                <div style="padding-top:30px" class="panel-body">

                    <div style="display:none" id="login-alert" class="alert alert-danger col-sm-12"></div>

                    <%--                    <form id="loginform" class="form-horizontal" role="form">--%>
                    <div style="margin-bottom: 25px" class="input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                        <input type="text" required class="form-control" name="fullName" value=""
                               placeholder="full name">
                    </div>
                    <div style="margin-bottom: 25px" class="input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                        <input type="text" required class="form-control" name="username" value=""
                               placeholder="username">
                    </div>
                    <div style="margin-bottom: 25px" class="input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                        <input type="password" required class="form-control" name="password"
                               placeholder="password">
                    </div>
                    <%--                        <div style="margin-bottom: 25px" class="input-group">--%>
                    <%--                            <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>--%>
                    <%--                            <input  type="password" class="form-control" name="confirm"--%>
                    <%--                                   placeholder="rewrite password">--%>
                    <%--                        </div>--%>
                    <div style="margin-top:10px" class="form-group">
                        <div class="col-sm-12 controls">
                            <input type="submit" value="Sign up" class="btn btn-primary">
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-md-12 control">
                            <div style="border-top: 1px solid#888; padding-top:15px; font-size:85%">
                                Already have an account?
                                <a href="/login">
                                    Sign in
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </form>
</div>
</body>
</html>