<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2019/5/30
  Time: 7:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加会议室</title>
    <%pageContext.setAttribute("APP_PATH", request.getContextPath());%>
    <title>login.jsp</title>
    <link href="${APP_PATH}/static/css/layui/css/layui.css" rel="stylesheet" type="text/css">
    <link href="${APP_PATH}/static/css/mycss/layui/login.css" rel="stylesheet" type="text/css">
    <link href="${APP_PATH}/static/css/mycss/layui/admin.css" rel="stylesheet" type="text/css">
    <style>
        .mid{margin-top:50px;}
    </style>
</head>
<body>
<form action="/###" method="post">
    <div class="layadmin-user-login-main mid">
        <label class="layui-form-label">会议室地点</label>
        <div class="layui-input-block">
            <input type="text" id="addressIpt" lay-verify="required" placeholder="" class="layui-input"><br/>
        </div>
        <label class="layui-form-label">管理员姓名</label>
        <div class="layui-input-block">
            <input type="text"  id="nameIpt" lay-verify="required" placeholder="" class="layui-input"><br/>
        </div>
<%--        <label class="layui-form-label">手机号</label>
        <div class="layui-input-block">
            <input type="text"  id="phoneIpt" lay-verify="required" placeholder="" class="layui-input"><br/>
        </div>--%>
    </div>
</form>
</body>
</html>
