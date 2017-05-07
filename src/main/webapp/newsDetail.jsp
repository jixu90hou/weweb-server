<%--
  Created by IntelliJ IDEA.
  User: jackshen
  Date: 2017/3/18
  Time: 下午4:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>news detail</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>

<div class="container">
    <div class="jumbotron">
        <h1>广告招商</h1>
    </div>
    <div class="row">
        <div>
            <h3>${news.title}</h3>
            <p style="text-indent: 2em;">${news.content}</p>
        </div>
    </div>
</div>

</body>
</html>
