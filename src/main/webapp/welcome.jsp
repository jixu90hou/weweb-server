<%@ page import="org.weweb.common.WeUser" %>

<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/4/22
  Time: 22:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  WeUser weUser= (WeUser) session.getAttribute("weUser");
%>
欢迎<%=weUser.getUsername()%>光临！！！！
<br/>
<br/>
sessionId:<%=session.getId()%>
