<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <%
        pageContext.setAttribute("APP_PATH", request.getContextPath());
    %>
    <title>Title</title>
    <link href="${APP_PATH}/static/css/layui/css/layui.css" rel="stylesheet" type="text/css">
    <link href="${APP_PATH}/static/css/mycss/layui/login.css" rel="stylesheet" type="text/css">
    <link href="${APP_PATH}/static/css/mycss/layui/admin.css" rel="stylesheet" type="text/css">
</head>

<style>
    #signed{
        max-height: 45%;
        overflow-y: auto;
    }
</style>
<body>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>扫描下方二维码签到</legend>
</fieldset>
<div style="padding: 20px; background-color: #F2F2F2;">
    <div class="layui-row layui-col-space15" style="text-align: center">
        <div class="layui-col-md6">
            <div class="layui-card">
                <div class="layui-card-header">二维码</div>
                <div class="layui-card-body" id="qrcode_img">

                </div>
            </div>
        </div>
        <div class="layui-col-md6">
            <div class="layui-card">
                <div class="layui-card-header">已签到人员 <span id="count"></span></div>
                <div class="layui-card-body" id="signed">
                </div>
            </div>
        </div>
        <button class="layui-btn layui-btn-sm" id="refresh">刷新</button>
    </div>
</div>
</body>
<script src="${APP_PATH}/static/js/jquery-3.0.0.min.js"></script>
<script src="${APP_PATH}/static/css/layui/layui.js"></script>
<script>
    function get_meeting_id(){
        var str=location.href;
        return str.split("=")[1];
    }
$(document).ready(function () {
    get_img();
    $.ajax({
        url:"${APP_PATH}/meetingInfo/getQcodeType",
        type:"GET",
        data:{"meetingId":get_meeting_id()},
        success:function (result) {
            if (result.extend.type==1) {
                timer = setInterval(get_img,60000);
            }
        }
    })

    function get_img(){
        $.ajax({
            url:"${APP_PATH}/meetingRoom/getPrcode",
            type:"GET",
            success:function (result) {
                if (result.code==100)
                    $("#qrcode_img").html("<img id=\"qrcode_img\"src=\"https://bshare.optimix.asia/barCode?site=weixin&url=http://"+result.extend.url+"?meeting_id="+get_meeting_id()+"%26timestamp="+Date.parse(new Date())+"\"style=\"width: 50%\">")
            }
        })
    }
    timers = setInterval(get_signuser,5000);
    function get_signuser(){
        $.ajax({
            url:"${APP_PATH}/meetingRoom/refresh_qrcode",
            type: "GET",
            async: false,
            data:{"meeting_id":get_meeting_id()},
            success:function (result) {
                console.log(result)
                if (result.code==200)
                    $("#signed").html("还没有人签到");
                else{
                    var meeting_info="";
                    for (var i = result.extend.meeting.length-1; i >=0 ; i--)
                        meeting_info+=result.extend.meeting[i]+"<br/>"
                    $("#signed").html(meeting_info)
                    $("#count").html(" ("+result.extend.meeting.length+"人) ")
                }

            }
        })
    }

    $("#refresh").click(function () {
        get_signuser();
    })
})
</script>
</html>
