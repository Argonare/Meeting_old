<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/5/31
  Time: 15:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <%pageContext.setAttribute("APP_PATH", request.getContextPath());%>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>会议系统</title>
    <link rel="stylesheet" href="${APP_PATH}/static/css/layui/css/layui.css">
<%--    <link rel="stylesheet" href="${APP_PATH}/static/css/bootstrap/css/bootstrap_tagsinput.css">--%>

</head>
<body class="layui-layout-body">
    <div id="APP_PATH" style="display: none;">${APP_PATH}</div>
    <div class="layui-layout layui-layout-admin">
        <div class="layui-header">
            <div class="layui-logo">会议系统</div>
            <!-- 头部区域（可配合layui已有的水平导航） -->
            <ul class="layui-nav layui-layout-right">
                <li class="layui-nav-item">
                    <a id="username" href="javascript:;"><img src="http://t.cn/RCzsdCq" class="layui-nav-img">贤心</a>
                    <dl class="layui-nav-child">
                        <dd><a href="">基本资料</a></dd>
                        <dd><a href="">安全设置</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item"><a id ="log_out" href="">退了</a></li>
            </ul>
        </div>

        <div class="layui-side layui-bg-black">
            <div class="layui-side-scroll">
                <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
                <ul class="layui-nav layui-nav-tree"  lay-filter="test">
                    <li class="layui-nav-item"><a href="javascript:showBody('userinfo')">用户信息</a></li>
                    <li class="layui-nav-item"><a href="javascript:showBody('meetinginfo')">会议信息</a></li>
                    <li class="layui-nav-item"><a href="javascript:showBody('meetingroom')">会议室信息</a></li>
                    <li class="layui-nav-item"><a href="javascript:showBody('deptinfo')">部门信息</a></li>
                    <li class="layui-nav-item"><a href="javascript:showBody('myteam')">我的小组</a></li>
                </ul>
            </div>
        </div>

        <div class="layui-body" style="padding: 20px 20px">
            <h1></h1>
            <hr>
            <div class="body">

            </div>
        </div>
        <div class="layui-footer"></div>
    </div>
</body>
<!--=======================================================表格=======================================================================-->

<%--userType{1：超级管理员,2：普通管理员}--%>
    <!----------------------------用户信息表------------------------------>
<script type="text/html" id="userinfo_toolbarDemo">
    <div class="layui-btn-container">
        {{#  if(${userType} == 1){ }}
            <button class="layui-btn layui-btn-sm" lay-event="selectDelUsers_btn">删除选中项</button>
        {{#  } else { }}
            <button class="layui-btn layui-btn-sm layui-btn-disabled">删除选中项</button>
        {{#  } }}
        <button class="layui-btn layui-btn-sm" lay-event="insertUser_btn">添加用户</button>
    </div>
</script>
<script type="text/html" id="userinfo_barDemo">
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    {{#  if(${userType} == 1){ }}
        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
    {{#  } else { }}
        <a class="layui-btn layui-btn-danger layui-btn-xs layui-btn-disabled">删除</a>
    {{#  } }}

</script>

<!------------------------------会议信息表------------------------------>
<script type="text/html" id="meetinginfo_toolbarDemo">
    <div class="layui-btn-container">
        {{#  if(${userType} == 1){ }}
            <button class="layui-btn layui-btn-sm" lay-event="selectDelMeetinginfos_btn">删除选中项</button>
        {{#  } else { }}
            <button class="layui-btn layui-btn-sm layui-btn-disabled" >删除选中项</button>
        {{#  } }}
        <button class="layui-btn layui-btn-sm" lay-event="insertMeetinginfo_btn">创建会议</button>
    </div>
</script>
<script type="text/html" id="meetinginfo_barDemo">
    <a class="layui-btn layui-btn-xs" lay-event="qrcode">二维码</a>
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-xs" lay-event="signin">签到情况</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>
<!------------------------------会议室信息表------------------------------>
<script type="text/html" id="meetingroom_toolbarDemo">
    <div class="layui-btn-container">
        {{#  if(${userType} == 1){ }}
            <button class="layui-btn layui-btn-sm" lay-event="selectDelMeetinginfos_btn">删除选中项</button>
            <button class="layui-btn layui-btn-sm" lay-event="insertMeetinginfo_btn">添加会议室</button>
        {{#  } else { }}
            <button class="layui-btn layui-btn-sm layui-btn-disabled">删除选中项</button>
            <button class="layui-btn layui-btn-sm layui-btn-disabled">添加会议室</button>
        {{#  } }}

    </div>
</script>
<script type="text/html" id="meetingroom_barDemo">
    {{#  if(${userType} == 1){ }}
        <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
    {{#  } else { }}
        <a class="layui-btn layui-btn-xs layui-btn-disabled">编辑</a>
        <a class="layui-btn layui-btn-danger layui-btn-xs layui-btn-disabled">删除</a>
    {{#  } }}
</script>
<!------------------------------部门信息表------------------------------>
<script type="text/html" id="department_toolbarDemo">
    <div class="layui-btn-container">
        {{#  if(${userType} == 1){ }}
            <button class="layui-btn layui-btn-sm" lay-event="insertDepartment_btn">添加部门</button>
        {{#  } else { }}
            <button class="layui-btn layui-btn-sm layui-btn-disabled">添加部门</button>
        {{#  } }}
    </div>
</script>
<script type="text/html" id="department_barDemo">
    {{#  if(${userType} == 1){ }}
        <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
    {{#  } else { }}
        <a class="layui-btn layui-btn-xs layui-btn-disabled">编辑</a>
        <a class="layui-btn layui-btn-danger layui-btn-xs layui-btn-disabled">删除</a>
    {{#  } }}
</script>
<!------------------------------我的小组------------------------------>
<script type="text/html" id="team_toolbarDemo">
    <div class="layui-btn-container">
        {{#  if(${userType} == 1){ }}
            <button  class="layui-btn layui-btn-sm" lay-event="insertTeam_btn">添加小组</button>
        {{#  } else { }}
            <button class="layui-btn layui-btn-sm layui-btn-disabled">添加小组</button>
        {{#  } }}
    </div>
</script>
<script type="text/html" id="team_barDemo">
    {{#  if(${userType} == 1){ }}
        <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
    {{#  } else { }}
        <a class="layui-btn layui-btn-xs layui-btn-disabled">编辑</a>
        <a class="layui-btn layui-btn-danger layui-btn-xs layui-btn-disabled">删除</a>
    {{#  } }}
</script>
<!-- =======================================================JS代码======================================================-->
<script src="${APP_PATH}/static/js/jquery-3.0.0.min.js"></script>
<script src="${APP_PATH}/static/css/layui/layui.js"></script>
<%--<script src="${APP_PATH}/static/css/bootstrap/js/bootstrap.js"></script>--%>
<%--<script src="${APP_PATH}/static/css/bootstrap/js/bootstrap-tagsinput.js"></script>--%>
<script src="${APP_PATH}/static/js/meeting.js"></script>


</html>
