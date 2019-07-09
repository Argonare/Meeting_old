<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>
    <title>Title</title>
    <%
        pageContext.setAttribute("APP_PATH", request.getContextPath());
    %>
    <link href="${APP_PATH}/static/css/layui/css/layui.css" rel="stylesheet" type="text/css">
    <link href="${APP_PATH}/static/css/mycss/layui/login.css" rel="stylesheet" type="text/css">
    <link href="${APP_PATH}/static/css/mycss/layui/admin.css" rel="stylesheet" type="text/css">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
</head>
<body>
<div style="padding: 20px; background-color: #F2F2F2;">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md12">
            <div class="layui-card">
                <div class="layui-card-header">信息</div>
                <div class="layui-card-body" id="sign_msg">

                </div>
            </div>
        </div>

        <div class="layui-col-md12">
            <div class="layui-card">
                <div class="layui-card-header">以下是您最近的会议安排</div>
                <div class="layui-card-body">
                    <div class="layui-collapse" lay-filter="test" id="meeting_message">

                    </div>

                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script src="static/js/jquery-3.0.0.min.js"></script>
<script src="${APP_PATH}/static/css/layui/layui.js"></script>
<script>
    $(document).ready(function () {
        var userinfo;
        $.ajax({
            url:"${APP_PATH}/userInfo/getGuestUsername.do",
            async:false,
            type:"GET",
            success:function (result) {
                userinfo=result.extend.userInfo
            }
        })
        function get_meeting_id(){
            var str=location.href;
            return str.split("=")[1].split("&")[0];
        }
        function get_timestamp(){
            var str=location.href.split("=")[2].split("&")[0];
            var tm=Date.parse(new Date());
            if (tm-str>65000) //一分钟后该二维码失效
                return false;
            return true;
        }
        if (get_timestamp()==true) {
            $.ajax({
                url:"${APP_PATH}/meetingSignin/updateMeetingSigninByMySelf",
                async: false,
                type: "POST",
                data:{
                    userId :userinfo.id,
                    meetingId:get_meeting_id(),
                    signinFlag:1,
                    signinTime:parseInt(Date.parse(new Date()))
                },
                success:function (result) {
                    if (result.code==100)
                        $("#sign_msg").html("签到成功")
                    else
                        $("#sign_msg").html(result.msg)
                }
            })
        }
        else $("#sign_msg").html("该二维码已过期,请重新扫码")

        layui.use(['element', 'layer','util'], function(){
            var element=layui.element

            var data="";
            $.post({
                url:'${APP_PATH}/meetingSignin/selectMeetingByUsername.do',
                data:{"userId":userinfo.id},
                success:function (result) {
                    data=result.extend.meetInfo
                    for (var i=0;i<data.length;i++){
                        // var time=
                        var htmls='<div class="layui-colla-item"><h2 class="layui-colla-title">'+ data[i].meetingName+'</h2><div class="layui-colla-content"><p>会议地点 : '+data[i].room+'</p><p>开始时间 : '+layui.util.toDateString(data[i].starTime,"yyyy年MM月dd日HH点mm分")+'</p></div></div>';
                        $("#meeting_message").html(htmls)
                    }
                    element.render("test")
                }
            })
        });
    })
</script>
</html>
