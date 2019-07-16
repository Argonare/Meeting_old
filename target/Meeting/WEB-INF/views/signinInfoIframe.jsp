<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
    <%
        pageContext.setAttribute("APP_PATH", request.getContextPath());
    %>
<link href="${APP_PATH}/static/css/layui/css/layui.css" rel="stylesheet" type="text/css">
<link href="${APP_PATH}/static/css/mycss/layui/login.css" rel="stylesheet" type="text/css">
<link href="${APP_PATH}/static/css/mycss/layui/admin.css" rel="stylesheet" type="text/css">
<style>
    .layui-table-cell {            overflow: visible !important;        }
    /* 使得下拉框与单元格刚好合适 */
    td .layui-form-select{
        margin-top: -10px;
        margin-left: -15px;
        margin-right: -15px;
    }
    .layui-table-view .layui-table-box .layui-table-body{
        height: 100%;
    }
</style>
<body>
<table class="layui-hide" id="test" lay-filter="test"></table>
<script type="text/html" id="selectGxmc" >
    <select name='jtcyGxmc' lay-filter='testSelect' lay-search=''>
        <option value="已到">已到</option>
        <option value="迟到">迟到</option>
        <option value="未到">未到</option>
        <option value="请假">请假</option>
    </select>
</script>
</body>
<script src="${APP_PATH}/static/js/jquery-3.0.0.min.js"></script>
<script src="${APP_PATH}/static/css/layui/layui.js"></script>
<script>
    var data="";
    function get_meeting_id(){
        var str=location.href;
        return str.split("=")[1];
    }
    $.ajax({
        url:"${APP_PATH}/meetingSignin/findMeetingSignInfo",
        type:"GET",
        async:false,
        data:{"meetingId":get_meeting_id()},
        success:function (result) {
            data=result.extend.signinInfo
        }
    })
    function init_table() {
        layui.use(['table','form'], function () {
            var table = layui.table;
            table.render({
                elem: '#test',
                data: data,
                cols: [[
                    {field: 'id', width: 40, title: 'ID', sort: true}
                    , {field: 'username', title: '工号'}
                    , {field: 'name', title: '姓名'}
                    , {field: 'status', title: '签到状态', templet: '#selectGxmc', sort: true}
                ]], even: true,
            });
            updateCacheOrForm(table,"test", "test", "form")
            var form=layui.form;
            form.render();
            form.on('select(testSelect)', function(obj){
                updateCacheOrForm(table,"test", "test", "cache")
                console.log(table.cache)
            });
        });
    }
    function updateCacheOrForm(table,tableId, tableCacheId, op){
        op = op || "form";
        var divForm = $("#" + tableId).next();
        var tableCache = table.cache[tableCacheId];
        var trJqs = divForm.find(".layui-table-body tr");
        trJqs.each(function(){
            var trJq = $(this);
            var dataIndex = trJq.attr("data-index");
            trJq.find("td").each(function(){
                var tdJq = $(this);
                var fieldName = tdJq.attr("data-field");
                var selectJq = tdJq.find("select");
                if(selectJq.length == 1){
                    if(op == "cache"){
                        tableCache[dataIndex][fieldName] = selectJq.eq(0).val();
                    }else if(op == "form"){
                        selectJq.eq(0).val(tableCache[dataIndex][fieldName])
                    }
                }
            });
        });
        return tableCache;
    }
    init_table();
</script>

</html>
