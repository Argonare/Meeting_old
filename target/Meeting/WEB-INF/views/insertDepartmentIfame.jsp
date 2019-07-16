<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加部门</title>
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
        <label class="layui-form-label">部门名称</label>
        <div class="layui-input-block">
            <input type="text" id="adddepart" lay-verify="required" placeholder="" class="layui-input"><br/>
        </div>
    </div>
</form>
</body>
</html>
