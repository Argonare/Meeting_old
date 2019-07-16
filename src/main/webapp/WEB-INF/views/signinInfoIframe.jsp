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

<div class="layui-tab">
    <ul class="layui-tab-title">
        <li class="layui-this">人员签到情况</li>
        <li>部门签到情况</li>
    </ul>
    <div class="layui-tab-content">
        <div class="layui-tab-item layui-show">
            <div id="main" style="width: 600px;height: 400px;margin: 0 auto;margin-top: 20px"></div>

            <%--<table class="layui-hide" id="test" lay-filter="test"></table>--%>

        </div>
        <div class="layui-tab-item">内容2</div>
    </div>
</div>
</body>
<!-- 改变签到状态-->
<%--<script type="text/html" id="selectGxmc" >--%>
    <%--<select name='jtcyGxmc' lay-filter='testSelect' lay-search=''>--%>
        <%--<option value="已到">已到</option>--%>
        <%--<option value="迟到">迟到</option>--%>
        <%--<option value="未到">未到</option>--%>
        <%--<option value="请假">请假</option>--%>
    <%--</select>--%>
<%--</script>--%>

<script src="${APP_PATH}/static/js/jquery-3.0.0.min.js"></script>
<script src="${APP_PATH}/static/css/layui/layui.js"></script>
<script>
    var data="";
    var meetingName="";
    function setMeetingName(val) {
        meetingName=val;
        console.log(meetingName)
    }
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
                page:false,
                limit:1000,
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
    layui.use('element', function(){
      var element = layui.element;
    })
</script>

<script src="${APP_PATH}/static/js/echarts.min.js"></script>
<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/echarts-all-3.js"></script>
<script type="text/javascript">
    // console.log(data);
    var myChart = echarts.init(document.getElementById('main'));
    var arrived=0;//已到
    var be_late=0;//迟到
    var not_arrived=0;//未到
    var leave=0;//请假
    for (var i=0;i<data.length;i++){
        if (data[i].status=='已到')
            arrived++;
        else if(data[i].status=='迟到')
            be_late++;
        else if(data[i].status=='未到')
            not_arrived++;
        else if (data[i].status=='请假')
            leave++;
    }
    // console.log(data)
    option = {
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b}: {c} ({d}%)"
        },
        legend: {
            orient: 'vertical',
            x: 'left',
            data:['已到','迟到','未到','请假']
        },
        series: [
            {
                name:'签到情况',
                type:'pie',
                radius: ['50%', '70%'],
                data:[
                    {value:arrived,name:'已到'},
                    {value:be_late, name:'迟到'},
                    {value:not_arrived, name:'未到'},
                    {value:leave, name:'请假'},
                ]
            }
        ]
    };

    myChart.on('click', function (param) {
        // alert(param.seriesName);  //legend的名称
        // alert(param.name);  //X轴的值
        if (param.name=='已到'){
            alert("1");
        }else if (param.name=='迟到'){
            alert("12");
        } else if (param.name=='未到'){
            alert("123");
        }else {
            alert("1234");
        }
    });
    myChart.setOption(option);
</script>
</html>
