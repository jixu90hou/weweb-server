<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/4/22
  Time: 22:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>欢迎来到WeWeb世界</title>
    <link rel="stylesheet" href="https://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<div class="jumbotron">
    <h3>欢迎来到WeWeb世界</h3>
</div>
<form action="<%=basePath%>user/login" class="form-horizontal" role="form">
    <div class="form-group">
        <label class="col-sm-2 control-label">用户名</label>
        <div class="col-sm-8">
            <input type="text" class="form-control" placeholder="account" name="account">
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label">密码:</label>
        <div class="col-sm-8">
            <input type="password" class="form-control" placeholder="password" name="password">
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <input type="submit" class="btn btn-success" value="登录">
            <input type="reset" class="btn btn-default" value="重置">
        </div>
    </div>
</form>
</body>
</html>
