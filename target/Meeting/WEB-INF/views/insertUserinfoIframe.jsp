
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>创建会议</title>
    <%pageContext.setAttribute("APP_PATH", request.getContextPath());%>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>会议系统</title>
    <link rel="stylesheet" href="${APP_PATH}/static/css/layui/css/layui.css">
</head>
<body style="padding-top: 30px">
<center>
    <div>
        <div style="width: 500px;margin-top:10px">
            <form class="layui-form" action="">
                <div class="layui-form-item">
                    <label class="layui-form-label" >工号 ：</label>
                    <div class="layui-input-block">
                        <input type="text" name="userId" id="LAY-user-login-username" lay-verify="required" placeholder="工号" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">微信号：</label>
                    <div class="layui-input-block">
                        <input type="text"  name="userPwd" id="LAY-user-login-wechat" lay-verify="required" placeholder="微信号"  class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">姓名：</label>
                    <div class="layui-input-block">
                        <input type="text" name="userPwd" id="LAY-user-login-name" lay-verify="required" placeholder="姓名" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">手机号：</label>
                    <div class="layui-input-block">
                        <input type="text" name="userPwd" id="LAY-user-login-phone" lay-verify="required" placeholder="手机号" class="layui-input">
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label">所在部门：</label>
                    <div class="layui-input-block">
                        <select lay-verify="required" id="dept_select">
                        </select>
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label">用户类型：</label>
                    <div class="layui-input-block">
                        <select lay-verify="required" id="type_select">
                            <option value="1">普通用户</option>
                            <option value="2">管理员</option>
                        </select>
                    </div>
                </div>

            </form>
        </div>
    </div>
</center>
</body>
<script src="${APP_PATH}/static/css/layui/layui.js"></script>
<script src="${APP_PATH}/static/js/jquery-3.0.0.min.js"></script>

<script>
    $.ajax({
        url:"${APP_PATH}/department/findAllDept",
        type:"GET",
        async:false,
        success:function (res) {
            var depts = res.extend.depts;
            for(var i=0 ; i<depts.length ; i++){
                $("<option></option>").attr("value",depts[i].id).text(depts[i].name).appendTo("#dept_select");
            }
        }
    })
    layui.use(['form', 'layer','jquery'], function(){
    });
</script>
</html>
