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
            <div id="main" style="width: 600px;height: 400px;margin: 0 auto;margin-top: 20px"></div>
            <%--<table class="layui-hide" id="test" lay-filter="test"></table>--%>
            <table id="arrivedTable" lay-filter="arrivedTable" ></table>
        </div>

        <div class="layui-tab-item">
            <div id="depart" style="width: 600px;height: 400px;margin: 0 auto;margin-top: 20px"></div>
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
    //Tab切换
    layui.use('element', function(){
      var element = layui.element;
    });

    var data="";
    var meetingName="";
    //会议名称
    function setMeetingName(val) {
        meetingName=val;
        // console.log(meetingName)
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
                    ,width:805
                    ,cols:[[
                        {field: 'id', title: 'ID',width:200}
                        ,{field: 'username', title: '工号',width:200}
                        ,{field: 'name', title: '姓名',width:200}
                        ,{field: 'status', title: '签到情况',width:200}
                    ]]
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
                    ,width:805
                    ,cols:[[
                        {field: 'id', title: 'ID'}
                        ,{field: 'username', title: '工号'}
                        ,{field: 'name', title: '姓名'}
                        ,{field: 'status', title: '签到情况'}
                    ]]
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
                    ,width:805
                    ,cols:[[
                        {field: 'id', title: 'ID'}
                        ,{field: 'username', title: '工号'}
                        ,{field: 'name', title: '姓名'}
                        ,{field: 'status', title: '签到情况'}
                    ]]
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
                    ,width:805
                    ,cols:[[
                        {field: 'id', title: 'ID'}
                        ,{field: 'username', title: '工号'}
                        ,{field: 'name', title: '姓名'}
                        ,{field: 'status', title: '签到情况'}
                    ]]
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
    var data2=[];
    var dataarrived=[];//已到
    var databe_late=[];//迟到
    var datanot_arrived=[];//未到
    var dataleave=[];//请假
    $.ajax({
        url:"${APP_PATH}/meetingSignin/getDepartSiginInfo",
        type:"GET",
        async: false,
        data:{meetingId:get_meeting_id()},
        success:function (res) {
            // console.log(res);
            // data2=res.extend.data

            for (var i in res.extend.data){
                data2.push(res.extend.data[i].dept)
                dataarrived.push(res.extend.data[i].signin)
                databe_late.push(res.extend.data[i].late)
                datanot_arrived.push(res.extend.data[i].notsignin)
                dataleave.push(res.extend.data[i].leave);
            }
            console.log(data2+"!!!!!!!!!!!!!!!!!!")
        }
    });

    var mydepart = echarts.init(document.getElementById('depart'));
    // var arr = []
    // arr.push()

    var posList = [
        'left', 'right', 'top', 'bottom',
        'inside',
        'insideTop', 'insideLeft', 'insideRight', 'insideBottom',
        'insideTopLeft', 'insideTopRight', 'insideBottomLeft', 'insideBottomRight'
    ];

    mydepart.configParameters = {
        rotate: {
            min: -90,
            max: 90
        },
        align: {
            options: {
                left: 'left',
                center: 'center',
                right: 'right'
            }
        },
        verticalAlign: {
            options: {
                top: 'top',
                middle: 'middle',
                bottom: 'bottom'
            }
        },
        position: {
            options: echarts.util.reduce(posList, function (map, pos) {
                map[pos] = pos;
                return map;
            }, {})
        },
        distance: {
            min: 0,
            max: 100
        }
    };

    mydepart.config = {
        rotate: 90,
        align: 'left',
        verticalAlign: 'middle',
        position: 'insideBottom',
        distance: 15,
        onChange: function () {
            var labelOption = {
                normal: {
                    rotate: app.config.rotate,
                    align: app.config.align,
                    verticalAlign: app.config.verticalAlign,
                    position: app.config.position,
                    distance: app.config.distance
                }
            };
            myChart.setOption({
                series: [{
                    label: labelOption
                }, {
                    label: labelOption
                }, {
                    label: labelOption
                }, {
                    label: labelOption
                }]
            });
        }
    };


    var labelOption = {
        normal: {
            show: true,
            position: mydepart.config.position,
            distance: mydepart.config.distance,
            align: mydepart.config.align,
            verticalAlign: mydepart.config.verticalAlign,
            rotate: mydepart.config.rotate,
            formatter: '{c}  {name|{a}}',
            fontSize: 16,
            rich: {
                name: {
                    textBorderColor: '#fff'
                }
            }
        }
    };

    option = {
        color: ['#003366', '#006699', '#4cabce', '#e5323e'],
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'shadow'
            }
        },
        legend: {
            data: ['已到','迟到', '未到',  '请假']
        },
        toolbox: {
            show: true,
            orient: 'vertical',
            left: 'right',
            top: 'center',
            feature: {
                mark: {show: true},
                dataView: {show: true, readOnly: false},
                magicType: {show: true, type: ['line', 'bar', 'stack', 'tiled']},
                restore: {show: true},
                saveAsImage: {show: true}
            }
        },
        calculable: true,
        xAxis: [
            {
                type: 'category',
                axisTick: {show: false},
                data: data2
            }
        ],
        yAxis: [
            {
                type: 'value'
            }
        ],
        series: [
            {
                name: '已到',
                type: 'bar',
                barGap: 0,
                label: labelOption,
                data: dataarrived
            },
            {
                name: '迟到',
                type: 'bar',
                label: labelOption,
                data: databe_late
            },
            {
                name: '未到',
                type: 'bar',
                label: labelOption,
                data: datanot_arrived
            },
            {
                name: '请假',
                type: 'bar',
                label: labelOption,
                data: dataleave
            }
        ]
    };

    mydepart.setOption(option);
</script>
</html>
