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
<html>
  <head>
    <title>欢迎来到WeWeb世界</title>
  </head>
  <body>
  <form action="<%=basePath%>/user/login">
    <label>用户名:</label> <input type="text" name="username"><br>
    <label>密&nbsp;&nbsp;码:</label><input type="password" name="password"><br>
    <input type="submit" value="登录">
    <input type="reset" value="重置">
  </form>
  </body>
</html>
