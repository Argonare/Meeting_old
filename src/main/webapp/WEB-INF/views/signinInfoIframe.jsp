<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
    <%
        pageContext.setAttribute("APP_PATH", request.getContextPath());
    %>

<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/echarts.min.js"></script>
<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts-gl/echarts-gl.min.js"></script>
<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts-stat/ecStat.min.js"></script>
<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/extension/dataTool.min.js"></script>
<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/map/js/china.js"></script>
<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/map/js/world.js"></script>
<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/extension/bmap.min.js"></script>
<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/simplex.js"></script>

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
            <%--<table class="layui-hide" id="test" lay-filter="test"></table>--%>
                <div style="float: left;"><table id="arrivedTable" lay-filter="arrivedTable"></table></div>
                <div id="main" style="width: 800px;height: 400px;margin-top: 20px;float: right"></div>
        </div>

        <div class="layui-tab-item">
            <div style="float: left"><table id="deptTable" lay-filter="deptTable"></table></div>
            <div id="depart" style="width: 850px;height: 500px;float: right"></div>
        </div>
    </div>
</div>
</body>
<!-- 改变签到状态-->
<script type="text/html" id="selectGxmc" >
    <%--<select name='jtcyGxmc' lay-filter='testSelect' lay-search=''>--%>
        <%--<option value="已到">已到</option>--%>
        <%--<option value="迟到">迟到</option>--%>
        <%--<option value="未到">未到</option>--%>
        <%--<option value="请假">请假</option>--%>
    <%--</select>--%>
</script>
<script src="${APP_PATH}/static/js/jquery-3.0.0.min.js"></script>
<script src="${APP_PATH}/static/css/layui/layui.js"></script>
<script>
    // function init_table() {
    //     layui.use(['table','form'], function () {
    //         var table = layui.table;
    //         table.render({
    //             elem: '#test',
    //             data: data,
    //             page:false,
    //             limit:1000,
    //             cols: [[
    //                 {field: 'id', width: 40, title: 'ID', sort: true}
    //                 , {field: 'username', title: '工号'}
    //                 , {field: 'name', title: '姓名'}
    //                 , {field: 'status', title: '签到状态', templet: '#selectGxmc', sort: true}
    //             ]], even: true,
    //         });
    //         updateCacheOrForm(table,"test", "test", "form")
    //         var form=layui.form;
    //         form.render();
    //         form.on('select(testSelect)', function(obj){
    //             updateCacheOrForm(table,"test", "test", "cache")
    //             console.log(table.cache)
    //         });
    //     });
    // }
    // function updateCacheOrForm(table,tableId, tableCacheId, op){
    //     op = op || "form";
    //     var divForm = $("#" + tableId).next();
    //     var tableCache = table.cache[tableCacheId];
    //     var trJqs = divForm.find(".layui-table-body tr");
    //     trJqs.each(function(){
    //         var trJq = $(this);
    //         var dataIndex = trJq.attr("data-index");
    //         trJq.find("td").each(function(){
    //             var tdJq = $(this);
    //             var fieldName = tdJq.attr("data-field");
    //             var selectJq = tdJq.find("select");
    //             if(selectJq.length == 1){
    //                 if(op == "cache"){
    //                     tableCache[dataIndex][fieldName] = selectJq.eq(0).val();
    //                 }else if(op == "form"){
    //                     selectJq.eq(0).val(tableCache[dataIndex][fieldName])
    //                 }
    //             }
    //         });
    //     });
    //     return tableCache;
    // }
    // init_table();

</script>
<script src="${APP_PATH}/static/js/echarts.min.js"></script>
<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/echarts-all-3.js"></script>
<script type="text/javascript">
    layui.use('table',function () {
        var not_arrivedData=[];
        var table = layui.table;
        table.render({
            elem:'#arrivedTable'
            ,id:'arrivedTable'
            ,height: 500 //容器高度
            ,width:375
            ,limit: 1000
            ,cols:[[
                {field: 'id', title: 'ID',width:70}
                ,{field: 'username', title: '工号',width:120}
                ,{field: 'name', title: '姓名',width:80}
                ,{field: 'status', title: '签到情况',width:100}
            ]]
            ,page:true
        });
        table.reload("arrivedTable",{
            data:not_arrivedData
        });
    })

    //Tab切换
    layui.use('element', function(){
      var element = layui.element;
    });

    var data="";
    var meetingName="";
    //会议名称
    function setMeetingName(val) {
        meetingName=val;
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
            // console.log(result)
            data=result.extend.signinInfo
            // console.log(data)
        }
    });
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
            layui.use('table',function () {
                var arrivedData=[];
                var table = layui.table;
                table.render({
                    elem:'#arrivedTable'
                    ,id:'arrivedTable'
                    ,height: 500 //容器高度
                    ,width:375
                    ,cols:[[
                        {field: 'id', title: 'ID',width:70}
                        ,{field: 'username', title: '工号',width:120}
                        ,{field: 'name', title: '姓名',width:80}
                        ,{field: 'status', title: '签到情况',width:100}
                    ]]            ,page:true
                });
                for (var i=0;i<data.length;i++){
                    if (data[i].status=='已到'){
                        console.log(data[i]);
                        arrivedData.push(data[i])
                    }
                }
                table.reload("arrivedTable",{
                    data:arrivedData
                });
            });
        }else if (param.name=='迟到'){
            layui.use('table',function () {
                var be_lateData=[];
                var table = layui.table;
                table.render({
                    elem:'#arrivedTable'
                    ,id:'arrivedTable'
                    ,height: 500 //容器高度
                    ,width:375
                    ,cols:[[
                        {field: 'id', title: 'ID',width:70}
                        ,{field: 'username', title: '工号',width:120}
                        ,{field: 'name', title: '姓名',width:80}
                        ,{field: 'status', title: '签到情况',width:100}
                    ]]            ,page:true
                });
                for (var i=0;i<data.length;i++){
                    if (data[i].status=='迟到'){
                        console.log(data[i]);
                        be_lateData.push(data[i])
                    }
                }
                table.reload("arrivedTable",{
                    data:be_lateData
                });
            })
        }else if (param.name=='未到'){
            layui.use('table',function () {
                var not_arrivedData=[];
                var table = layui.table;
                table.render({
                    elem:'#arrivedTable'
                    ,id:'arrivedTable'
                    ,height: 500 //容器高度
                    ,width:375
                    ,cols:[[
                        {field: 'id', title: 'ID',width:70}
                        ,{field: 'username', title: '工号',width:120}
                        ,{field: 'name', title: '姓名',width:80}
                        ,{field: 'status', title: '签到情况',width:100}
                    ]]            ,page:true
                });
                for (var i=0;i<data.length;i++){
                    if (data[i].status=='未到'){
                        console.log(data[i]);
                        not_arrivedData.push(data[i])
                    }
                }
                table.reload("arrivedTable",{
                    data:not_arrivedData
                });
            })
        }else {
            layui.use('table',function () {
                var leaveData=[];
                var table = layui.table;
                table.render({
                    elem:'#arrivedTable'
                    ,id:'arrivedTable'
                    ,height: 500 //容器高度
                    ,width:375
                    ,cols:[[
                        {field: 'id', title: 'ID',width:70}
                        ,{field: 'username', title: '工号',width:120}
                        ,{field: 'name', title: '姓名',width:80}
                        ,{field: 'status', title: '签到情况',width:100}
                    ]]            ,page:true
                });
                for (var i=0;i<data.length;i++){
                    if (data[i].status=='请假'){
                        console.log(data[i]);
                        leaveData.push(data[i])
                    }
                }
                table.reload("arrivedTable",{
                    data:leaveData
                });
            })
        }
    });
    myChart.setOption(option);
</script>

<script type="text/javascript">
    var meetingSinginInfo=[];
    var legendData = [];
    var series1Data = []
    var series2Data = []
    $.ajax({
        url:"${APP_PATH}/meetingSignin/getDepartSiginInfo",
        type:"GET",
        async: false,
        data:{meetingId:get_meeting_id()},
        success:function (res) {
            res = res.extend.data;
            for(var i=0 ;i<res.length ;i++){
                res[i]["total"]=res[i].signin+res[i].late+res[i].notsignin+res[i].leave;//总人数
                legendData.push(res[i].dept);
                if (i == 0)
                    series1Data.push({"value":res[i].total,"name":res[i].dept,"selected":true});
                else
                    series1Data.push({"value":res[i].total,"name":res[i].dept,"selected":false});

                var tempData = []
                tempData.push({"value":res[i].signin,"name":"已到"});
                tempData.push({"value":res[i].notsignin,"name":"未到"});
                tempData.push({"value":res[i].late,"name":"迟到"});
                tempData.push({"value":res[i].leave,"name":"请假"});
                series2Data.push(tempData)
            }
            meetingSinginInfo=res;
        }
    });

    // console.log(meetingSinginInfo)

    layui.use('table',function () {
        var not_deptData=[];
        var table = layui.table;
        table.render({
            elem:'#deptTable'
            ,id:'deptTable'
            ,height: 500 //容器高度
            ,width:421
            ,limit: 1000
            ,cols:[[
                {field:'dept',title:'部门',width:135}
                ,{field:'signin',title:'已到',width:70}
                ,{field:'notsignin',title:'未到',width:70}
                ,{field:'late',title:'迟到',width:70}
                ,{field:'leave',title:'请假',width:70}
            ]]
            ,page:true
        });
        table.reload("deptTable",{
            data:meetingSinginInfo
        });
    })


    var mydepart = echarts.init(document.getElementById('depart'));

    function getDeptEcharts(legendData,series1Data,series2Data){
        option = {
            tooltip: {
                trigger: 'item',
                formatter: "{a} <br/>{b}: {c} ({d}%)"
            },
            legend: {
                orient: 'vertical',
                x: 'left',
                data:legendData
            },
            series: [
                {
                    name:'学院：总人数',
                    type:'pie',
                    selectedMode: 'single',
                    radius: [0, '40%'],

                    label: {
                        normal: {
                            position: 'inner'
                        }
                    },
                    labelLine: {
                        normal: {
                            show: false
                        }
                    },
                    data:series1Data
                },
                {
                    name:'签到情况：人数',
                    type:'pie',
                    radius: ['65%', '50%'],
                    labelLine:{
                        normal:{
                            length:50,
                        }
                    },
                    label: {
                        normal: {
                            // formatter: '{a|{a}}{abg|}\n{hr|}\n  {b|{b}：}{c}  {per|{d}%}  ',
                            // backgroundColor: '#eee',
                            // borderColor: '#aaa',
                            // borderWidth: 1,
                            // borderRadius: 4,
                            rich: {
                                a: {
                                    color: '#999',
                                    lineHeight: 22,
                                    align: 'center'
                                },
                                hr: {
                                    borderColor: '#aaa',
                                    width: '100%',
                                    borderWidth: 0.5,
                                    height: 0
                                },
                                b: {
                                    fontSize: 16,
                                    lineHeight: 33
                                },
                                per: {
                                    color: '#eee',
                                    backgroundColor: '#334455',
                                    padding: [2, 4],
                                    borderRadius: 2
                                }
                            }
                        }
                    },
                    data:series2Data //初始显示第一个
                }
            ]
        };
        return option
    }

    mydepart.setOption(getDeptEcharts(legendData,series1Data,series2Data[0]));

    mydepart.on('click',function (param) {
        var flag = true;
        var index = 0
        for (var i=0 ; i<series1Data.length ; i++){
            series1Data[i].selected = false;
            if(param.name == series1Data[i].name){
                flag = false;
                index = i;
                // alert(param.name)

            }
        }
        if (flag) return false;
        series1Data[index].selected = true;
        mydepart.setOption(getDeptEcharts(legendData,series1Data,series2Data[index]));
    })
</script>
</html>
