<%--
  Created by IntelliJ IDEA.
  User: jackshen
  Date: 2017/3/18
  Time: 下午4:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <meta charset="utf-8">
    <title>Bootstrap 新闻列表</title>
    <!-- 包含头部信息用于适应不同设备 -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 包含 bootstrap 样式表 -->
    <link rel="stylesheet" href="https://apps.bdimg.com/libs/bootstrap/3.2.0/css/bootstrap.min.css">
</head>

<body>
<div class="container">
    <h2>表格</h2>
    <div class="table-responsive">
        <table class="table table-striped table-bordered">
            <thead>
            <tr>
                <th>ID</th>
                <th>Title</th>
                <th>CreateTime</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="news" items="${newsList}" varStatus="status">
                <tr>
                    <td>${news.id}</td>
                    <td><a href="<%=basePath%>news/findNewsDetail?id=${news.id}">${news.title}</a></td>
                    <td>${news.createTime}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <%-- <h2>图像</h2>
     <p>创建相应式图片(将扩展到父元素)。 另外：图片以椭圆型展示：</p>
     <img src="cinqueterre.jpg" class="img-responsive img-circle" alt="Cinque Terre" width="304" height="236">

     <h2>图标</h2>
     <p>插入图标:</p>
     <p>云图标: <span class="glyphicon glyphicon-cloud"></span></p>
     <p>信件图标: <span class="glyphicon glyphicon-envelope"></span></p>
     <p>搜索图标: <span class="glyphicon glyphicon-search"></span></p>
     <p>打印图标: <span class="glyphicon glyphicon-print"></span></p>
     <p>下载图标：<span class="glyphicon glyphicon-download"></span></p>--%>
</div>

<!-- JavaScript 放置在文档最后面可以使页面加载速度更快 -->
<!-- 可选: 包含 jQuery 库 -->
<script src="https://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
<!-- 可选: 合并了 Bootstrap JavaScript 插件 -->
<script src="https://apps.bdimg.com/libs/bootstrap/3.2.0/js/bootstrap.min.js"></script>
</body>

</html>