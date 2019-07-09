<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <%pageContext.setAttribute("APP_PATH", request.getContextPath());%>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>会议系统</title>
    <link rel="stylesheet" href="${APP_PATH}/static/css/layui/css/layui.css">
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo">会议系统</div>
        <!-- 头部区域（可配合layui已有的水平导航） -->
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">
                <a href="javascript:;">
                    <%--<img src="http://t.cn/RCzsdCq" class="layui-nav-img">--%>
                    贤心
                </a>
                <dl class="layui-nav-child">
                    <dd><a href="">基本资料</a></dd>
                    <dd><a href="">安全设置</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item"><a href="">退了</a></li>
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
            </ul>
        </div>
    </div>

    <div class="layui-body">
        <!-- 内容主体区域 -->
        <%--<div style="padding: 15px;">内容主体区域</div>--%>
        <%--<table id="demo" lay-filter="test"></table>--%>
        <!--部门-->
    </div>

    <div class="layui-footer">
        <!-- 底部固定区域 -->
        © layui.com - 底部固定区域
    </div>
</div>

<!--=======================================================user表格=======================================================================-->

<script type="text/html" id="toolbarDemo">
    <div class="layui-btn-container">
        <button class="layui-btn layui-btn-sm" lay-event="getCheckData">获取选中行数据</button>
        <button class="layui-btn layui-btn-sm" lay-event="getCheckLength">获取选中数目</button>
        <button class="layui-btn layui-btn-sm" lay-event="isAll" id="delete_Users">删除选中项</button>
        <button class="layui-btn layui-btn-sm" lay-event="refresh">刷新</button>
    </div>
</script>

<script type="text/html" id="barDemo1">
    <a class="layui-btn layui-btn-xs" lay-event="edit">用户</a>
</script>

<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <%--<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="add" >删除多个</a>--%>
    <a class="layui-btn layui-btn-xs" lay-event="detail">查看</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>

<!--================================================js代码=====================================================-->
<script src="${APP_PATH}/static/js/jquery-3.0.0.min.js"></script>
<script src="${APP_PATH}/static/css/layui/layui.js"></script>
<script>

    function showBody(flag){
        $(".layui-body").empty();
        $(".layui-body").append("<table id='demo' lay-filter='test'></table>");
        if(flag=="userinfo"){
            //********************************用户信息*********************************
            // <table id="demo" lay-filter="test"></table>
            layui.use('table', function aa() {
                var table = layui.table;
                var datass = ''
                $.ajax({
                    type: "GET",
                    url: "${APP_PATH}/findAll",
                    success: function(result) {
                        // alert(result.extend.list[1].id);
                        // console.log(result)
                        datass = result.extend.list,
                            table.render({
                                elem: '#demo',
                                cellMinWidth: 80,
                                toolbar: '#toolbarDemo',
                                /*totalRow: true,*/
                                data: datass,
                                page: { //支持传入 laypage 组件的所有参数（某些参数除外，如：jump/elem） - 详见文档
                                    layout: ['limit', 'count', 'prev', 'page', 'next', 'skip'] //自定义分页布局
                                    //,curr: 5 //设定初始在第 5 页
                                    ,groups: 1 //只显示 1 个连续页码
                                    ,first: false //不显示首页
                                    ,last: false //不显示尾页
                                },
                                cols: [[
                                    {type:'numbers', title: '序号'}
                                    ,{type: 'checkbox'}
                                    // ,{field:'id', title:'用户Id', fixed: 'left', unresize: true, sort: true, totalRowText: '合计'}
                                    ,{field:'id',  title: '工号'/*, totalRow: true*/}
                                    // ,{field:'userPwd', title: 'userPwd'}
                                    ,{field:'username', title: '姓名', edit: 'text'}
                                    ,{field:'phone', title: '手机号', edit: 'text'} //minWidth：局部定义当前单元格的最小宽度，layui 2.2.1 新增
                                    ,{field:'wechar', title: '微信号'}
                                    ,{field:'departId', title: '部门id', edit: 'text'}
                                    /* ,{field:'type', title: '用户类型',  edit: 'text'}
                                     ,{field:'addUid',  title: '添加用户管理员ID'}
                                     ,{field:'addDate',  title: '添加时间'}
                                     ,{field:'updateUid',  title: '修改用户管理员ID'}
                                     ,{field:'updateDate',  title: '修改时间'}
                                     ,{field:'deleteFlag',  title: '是否被删除'}
                                     ,{field:'deleteUid',  title: '删除用户管理员ID'}
                                     ,{field:'deleteDate',  title: '删除时间'}*/
                                    ,{fixed: 'right', title:'操作', toolbar: '#barDemo', width:150,align:'center'}
                                ]]
                            });
                    }
                });

                table.on('tool(test)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
                    var data = obj.data; //获得当前行数据
                    var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
                    var tr = obj.tr; //获得当前行 tr 的DOM对象

                    if(layEvent === 'detail'){ //查看
                        //do somehing
                        layer.alert('姓名:'+ data.name+'<br>序号:'+ data.id+'<br>工号:'+data.username+'<br>手机号：'+data.phone+'<br>微信号:'+data.wechar+'<br>部门:'+data.departId+'<br>用户类型:'+data.type);
                    } else if(layEvent === 'del'){ //删除
                        layer.confirm('真的删除ID为：'+data.username+"的用户吗?", function(index){
                            //console.log(data.p_id);
                            //向服务端发送删除指令
                            $.ajax({
                                url:'${APP_PATH}/deleteUser',/*?id='+data.userId,*/
                                type:'POST',
                                // data:"json",
                                data:{'id':data.id},//向服务端发送删除的id
                                success:function(suc){
                                    if(suc.code==100){
                                        obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                                        layer.close(index);
                                        console.log(index);
                                        layer.msg("删除成功",{icon:1});
                                    }
                                    else{
                                        layer.msg("删除失败",{icon:5});
                                    }
                                }
                            });
                            layer.close(index);
                        });
                    }else if(layEvent === 'edit'){ //编辑
                        layui.use('layer',function () {
                            var layer = layui.layer;// layer.msg('hello');
                            layui.code
                            layer.open({
                                type: 2, // btn: ['保存', '取消'],
                                content: '${APP_PATH}/updateUserinfoIframe',
                                area: ['800px', '500px'],
                                success: function(layero, index){
                                    var body = layer.getChildFrame('body', index);
                                    var iframeWin = window[layero.find('iframe')[0]['name']]; //得到iframe页的窗口对象，执行iframe页的方法：
                                    iframeWin.method;
                                    body.find('#LAY-user-login-username').val(data.name)                                    // console.log(body.html()) //得到iframe页的body内容
                                    body.find('#LAY-user-login-number').val(data.username)
                                    body.find('#LAY-user-login-userId').val(data.wechar)
                                    body.find('#LAY-user-login-phone').val(data.phone)
                                    body.find('#LAY-user-login-dept').val(data.departId)
                                    body.find('#LAY-user-login-type').val(data.type)
                                    body.find('#LAY-user-login-submit').val(data.userType)
                                    body.find('#LAY-user-login-canler').val(data.userType)
                                }
                            });
                        });
                    }
                });

                //头工具栏事件
                table.on('toolbar(test)', function(obj){
                    var checkStatus = table.checkStatus(obj.config.id);
                    switch(obj.event){
                        case 'getCheckData':
                            var data = checkStatus.data;
                            layer.alert(JSON.stringify(data));
                            break;
                        case 'getCheckLength':
                            var data = checkStatus.data;
                            layer.msg('选中了：'+ data.length + ' 个');
                            break;
                        case 'isAll':
                            //多条删除
                            var data = JSON.stringify(checkStatus.data);
                            var json = $.parseJSON(data);
                            var ids="";
                            for(var i=0 ; i<json.length; i++){
                                if(i!=0)ids+=",";
                                ids+=json[i].id;
                            }
                            alert(ids);
                            $.ajax({
                                url:"${APP_PATH}/deleteByExampleSelectiveUser/"+ids,
                                type:"POST",
                                success:function (result) {
                                    console.log(result)
                                }
                            })
                            break;
                        case 'refresh':
                            aa();
                            break;

                    };
                });
            })
        }else if(flag=="meetinginfo"){
            //<table id="demo" lay-filter="test"></table>
            //********************************会议信息*********************************
            //用户表格
            layui.use('table', function aa() {
                var table = layui.table;
                var datass = ''
                $.ajax({
                    type: "POST",
                    url: "${APP_PATH}/findAllMeetingInfo",
                    success: function(result) {
                        // alert(result.extend.list[1].id);
                        // console.log(result)
                        datass = result.extend.list,
                            table.render({
                                elem: '#demo',
                                cellMinWidth: 80,
                                toolbar: '#toolbarDemo',
                                /*totalRow: true,*/
                                data: datass,
                                page: { //支持传入 laypage 组件的所有参数（某些参数除外，如：jump/elem） - 详见文档
                                    layout: ['limit', 'count', 'prev', 'page', 'next', 'skip'] //自定义分页布局
                                    //,curr: 5 //设定初始在第 5 页
                                    ,groups: 1 //只显示 1 个连续页码
                                    ,first: false //不显示首页
                                    ,last: false //不显示尾页
                                },
                                cols: [[
                                    {type:'numbers', title: '序号'}
                                    ,{type: 'checkbox'}
                                    // ,{field:'id', title:'用户Id', fixed: 'left', unresize: true, sort: true, totalRowText: '合计'}
                                    // ,{field:'id',  title: '工号'/*, totalRow: true*/}
                                    // ,{field:'userPwd', title: 'userPwd'}
                                    ,{field:'name', title: '会议名称', edit: 'text'}
                                    ,{field:'introId', title: '会议简介', edit: 'text'} //minWidth：局部定义当前单元格的最小宽度，layui 2.2.1 新增
                                    ,{field:'type', title: '会议类型'}
                                    ,{field:'startTime', title: '开始时间', edit: 'text'}
                                    ,{field:'endTime', title: '结束时间', edit: 'text'}
                                    ,{field:'roomId', title: '会议地点',  edit: 'text'}
                                    // ,{field:'insertUid',  title: 'insertUid'}
                                    // ,{field:'insertTime',  title: 'insertTime'}
                                    // ,{field:'updateUid',  title: 'updateUid'}
                                    // ,{field:'updateTime',  title: 'updateTime'}
                                    // ,{field:'deleteFlag',  title: 'deleteFlag'}
                                    // ,{field:'deleteUid',  title: 'deleteUid'}
                                    // ,{field:'deleteTime',  title: 'deleteTime'}
                                    ,{fixed: 'right', title:'操作', toolbar: '#barDemo', width:300,align:'center'}
                                ]]
                            });
                    }
                });

                table.on('tool(test)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
                    var data = obj.data; //获得当前行数据
                    var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
                    var tr = obj.tr; //获得当前行 tr 的DOM对象

                    if(layEvent === 'detail'){ //查看
                        layer.alert('工号:'+ data.id+'<br>会议名称:'+ data.name
                            +'<br>工号:'+data.introId+'<br>手机号：'+data.type
                            +'<br>微信号:'+data.startTime+'<br>部门:'+data.endTime+'<br>用户类型:'+data.roomId
                            +'<br>用户类型:'+data.insertUid+'<br>用户类型:'+data.insertTime+'<br>用户类型:'+data.updateUid
                            +'<br>用户类型:'+data.updateTime+'<br>用户类型:'+data.deleteFlag+'<br>用户类型:'+data.deleteUid
                            +'<br>用户类型:'+data.deleteTime);
                    } else if(layEvent === 'del'){ //删除
                        layer.confirm('真的删除ID为：'+data.name+"的用户吗?", function(index){
                            //console.log(data.p_id);
                            //向服务端发送删除指令
                            $.ajax({
                                url:'${APP_PATH}/deleteMeetingInfo',/*?id='+data.userId,*/
                                type:'POST',
                                // data:"json",
                                data:{'id':data.id},//向服务端发送删除的id
                                success:function(suc){
                                    if(suc.code==100){
                                        obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                                        layer.close(index);
                                        console.log(index);
                                        layer.msg("删除成功",{icon:1});
                                    }
                                    else{
                                        layer.msg("删除失败",{icon:5});
                                    }
                                }
                            });
                            layer.close(index);
                        });
                    }else if (layEvent === 'add'){
                        layui.use('layer',function () {
                            var layer = layui.layer;
                            // layer.msg('hello');
                            layui.code
                            layer.open({
                                type: 2,
                                // btn: ['保存', '取消'],
                                content: '${APP_PATH}/jsp/alertAdd.jsp',
                                area: ['800px', '500px'],
                                success: function(layero, index){
                                    var body = layer.getChildFrame('body', index);
                                    var iframeWin = window[layero.find('iframe')[0]['name']]; //得到iframe页的窗口对象，执行iframe页的方法：
                                    iframeWin.method;
                                    console.log(body.html()) //得到iframe页的body内容
                                    body.find('#LAY-user-login-username').val('')
                                    body.find('#LAY-user-login-number').val('')
                                    body.find('#LAY-user-login-userId').val('')
                                }
                            });
                        });
                    }else if(layEvent === 'edit'){ //编辑
                        layui.use('layer',function () {
                            var layer = layui.layer;
                            // layer.msg('hello');
                            layui.code
                            layer.open({
                                type: 2,
                                // btn: ['保存', '取消'],
                                content: '${APP_PATH}/updateUserinfoIframe',
                                area: ['800px', '500px'],
                                // data:"dicId="+ $("#dicId").val() +"&dicNumber=" + $("#dicNumber").val()
                                //     + "&dicName=" + $("#dicName").val()+ "&dicCode=" + $("#dicCode").val()
                                //     + "&dicUnit=" + $("#dicUnit").val()+ "&dicType=" + $("#dicType").val()
                                //     + "&dicIsMixture=" + $("#dicIsMixture").val()
                                //     + "&dicIsOds=" + $("#dicIsOds").val()
                                //     + "&dicOdpUp=" + $("#dicOdpUp").val()
                                //     + "&dicOdpDown=" + $("#dicOdpDown").val()
                                //     + "&dicOdpCurrent=" + $("#dicOdpCurrent").val()
                                //     + "&dicStatus=" + $("#dicStatus").val(),//表单数据
                                success: function(layero, index){
                                    var body = layer.getChildFrame('body', index);
                                    var iframeWin = window[layero.find('iframe')[0]['name']]; //得到iframe页的窗口对象，执行iframe页的方法：
                                    iframeWin.method;
                                    console.log(body.html()) //得到iframe页的body内容
                                    body.find('#LAY-user-login-username').val(data.name)
                                    body.find('#LAY-user-login-number').val(data.userId)
                                    body.find('#LAY-user-login-userId').val(data.wechar)
                                    body.find('#LAY-user-login-phone').val(data.phone)
                                    body.find('#LAY-user-login-dept').val(data.departId)
                                    body.find('#LAY-user-login-type').val(data.userType)
                                    body.find('#LAY-user-login-submit').val(data.userType)
                                    body.find('#LAY-user-login-canler').val(data.userType)
                                }
                            });
                        });
                    }
                });

                //头工具栏事件
                table.on('toolbar(test)', function(obj){
                    var checkStatus = table.checkStatus(obj.config.id);
                    switch(obj.event){
                        case 'getCheckData':
                            var data = checkStatus.data;
                            layer.alert(JSON.stringify(data));
                            break;
                        case 'getCheckLength':
                            var data = checkStatus.data;
                            layer.msg('选中了：'+ data.length + ' 个');
                            break;
                        case 'isAll':
                            layer.msg(checkStatus.isAll ? '全选': '未全选');
                            break;
                        case 'refresh':
                            aa();
                            break;

                    };
                });

            })
        }else if(flag=="meetingroom"){
            //<table id="demo" lay-filter="test"></table>
            //********************************会议室信息*********************************
            layui.use('table', function aa() {
                var table = layui.table;
                var datass = ''
                $.ajax({
                    type: "POST",
                    url: "${APP_PATH}/findAllMeetingRoom",
                    success: function(result) {
                        datass = result.extend.list,
                            table.render({
                                elem: '#demo',
                                cellMinWidth: 80,
                                toolbar: '#toolbarDemo',
                                data: datass,
                                page: { //支持传入 laypage 组件的所有参数（某些参数除外，如：jump/elem） - 详见文档
                                    layout: ['limit', 'count', 'prev', 'page', 'next', 'skip'] //自定义分页布局
                                    ,groups: 1 //只显示 1 个连续页码
                                    ,first: false //不显示首页
                                    ,last: false //不显示尾页
                                },
                                cols: [[
                                    {type: 'checkbox', fixed: 'button'}
                                    ,{field:'id', title:'序号', fixed: 'left', unresize: true, sort: true, totalRowText: '合计'}
                                    ,{field:'address',  title: '会议室地址', edit: 'text'}
                                    ,{field:'userId', title: '会议室管理员'}
                                    ,{field:'insertUid', title: '添加会议用户'} //minWidth：局部定义当前单元格的最小宽度，layui 2.2.1 新增
                                    ,{field:'insetTime', title: '添加时间'}
                                    ,{fixed: 'right', title:'操作', toolbar: '#barDemo', width:200,align:'center'}
                                ]]
                            });
                    }
                });

                table.on('tool(test)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
                    var data = obj.data; //获得当前行数据
                    var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
                    var tr = obj.tr; //获得当前行 tr 的DOM对象

                    if(layEvent === 'detail'){ //查看
                        //do somehing
                        layer.alert('地址:'+ data.address+'<br>是否已被删除:'+ data.deleteFlag);
                    } else if(layEvent === 'del'){ //删除
                        layer.confirm('真的删除ID为：'+data.id+"的用户吗?", function(index){
                            //console.log(data.p_id);
                            //向服务端发送删除指令
                            $.ajax({
                                url:'${APP_PATH}/deleteMeetingRoom',/*?id='+data.userId,*/
                                type:'POST',
                                // data:"json",
                                data:{'id':data.id},//向服务端发送删除的id
                                success:function(suc){
                                    if(suc.code==100){
                                        obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                                        layer.close(index);
                                        console.log(index);
                                        layer.msg("删除成功",{icon:1});
                                    }
                                    else{
                                        layer.msg("删除失败",{icon:5});
                                    }
                                }
                            });
                            layer.close(index);
                        });
                    }else if(layEvent === 'edit'){ //编辑
                        layui.use('layer',function () {
                            var layer = layui.layer;
                            // layer.msg('hello');
                            layui.code
                            layer.open({
                                type: 2,
                                // btn: ['保存', '取消'],
                                content: '${APP_PATH}/jsp/meetingRoomEdit1.jsp',
                                area: ['800px', '500px'],
                                success: function(layero, index){
                                    var body = layer.getChildFrame('body', index);
                                    var iframeWin = window[layero.find('iframe')[0]['name']]; //得到iframe页的窗口对象，执行iframe页的方法：
                                    iframeWin.method;
                                    console.log(body.html()) //得到iframe页的body内容
                                    body.find('#LAY-address').val(data.address)
                                    body.find('#LAY-admin').val(data.userId)
                                    body.find('#LAY-adduser').val(data.insertUid)
                                    body.find('#LAY-addtime').val(data.insetTime)
                                }
                            });
                        });

                    }
                });
                //头工具栏事件
                table.on('toolbar(test)', function(obj){
                    var checkStatus = table.checkStatus(obj.config.id);
                    switch(obj.event){
                        case 'getCheckData':
                            var data = checkStatus.data;
                            layer.alert(JSON.stringify(data));
                            break;
                        case 'getCheckLength':
                            var data = checkStatus.data;
                            layer.msg('选中了：'+ data.length + ' 个');
                            break;
                        case 'isAll':
                            layer.msg(checkStatus.isAll ? '全选': '未全选');
                            break;
                        case 'refresh':
                            aa();
                            break;

                    };
                });
            })
        }else if(flag=="deptinfo"){
            //********************************部门信息*********************************
            layui.use('table', function aa() {
                var table = layui.table;
                var datass = ''
                $.ajax({
                    type: "POST",
                    url: "${APP_PATH}/findAllDept",
                    success: function(result) {
                        // alert(result.extend.list[1].id);
                        // console.log(result)
                        datass = result.extend.list,
                            table.render({
                                elem: '#demo',
                                cellMinWidth: 80,
                                toolbar: '#toolbarDemo',
                                /*totalRow: true,*/
                                data: datass,
                                page: { //支持传入 laypage 组件的所有参数（某些参数除外，如：jump/elem） - 详见文档
                                    layout: ['limit', 'count', 'prev', 'page', 'next', 'skip'] //自定义分页布局
                                    //,curr: 5 //设定初始在第 5 页
                                    ,groups: 1 //只显示 1 个连续页码
                                    ,first: false //不显示首页
                                    ,last: false //不显示尾页
                                },
                                cols: [[
                                    {type:'numbers', title: '序号'}
                                    ,{type: 'checkbox'}
                                    // ,{field:'id', title:'用户Id', fixed: 'left', unresize: true, sort: true, totalRowText: '合计'}
                                    // ,{field:'id',  title: 'id'/*, totalRow: true*/}
                                    // ,{field:'userPwd', title: 'userPwd'}
                                    ,{field:'name', title: '部门名称', edit: 'text'}
                                    // ,{field:'insertUid', title: 'insertUid', edit: 'text'} //minWidth：局部定义当前单元格的最小宽度，layui 2.2.1 新增
                                    // ,{field:'insertTime', title: 'insertTime'}
                                    // ,{field:'updateUid', title: 'updateUid',  edit: 'text'}
                                    // ,{field:'updateTime',  title: 'updateTime'}
                                    // ,{field:'deleteFlag',  title: 'deleteFlag'}
                                    // ,{field:'deleteUid',  title: 'deleteUid'}
                                    // ,{field:'deleteTime',  title: 'deleteTime'}
                                    ,{fixed: 'right', title:'操作', toolbar: '#barDemo', width:300,align:'center'}
                                ]]
                            });
                    }
                });

                table.on('tool(test)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
                    var data = obj.data; //获得当前行数据
                    var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
                    var tr = obj.tr; //获得当前行 tr 的DOM对象

                    if(layEvent === 'detail'){ //查看
                        //do somehing
                        layer.alert('部门名:'+ data.name+'<br>序号:'+ data.insertUid+'<br>工号:'+data.insertTime+'<br>手机号：'+data.updateUid
                            +'<br>微信号:'+data.updateTime+'<br>部门:'+data.deleteFlag+'<br>用户类型:'+data.deleteUid+'<br>用户类型:'+data.deleteTime);
                    } else if(layEvent === 'del'){ //删除
                        layer.confirm('真的删除name为：'+data.name+"的部门吗?", function(index){
                            //console.log(data.p_id);
                            //向服务端发送删除指令
                            $.ajax({
                                url:'${APP_PATH}/deleteDepartment',/*?id='+data.userId,*/
                                type:'POST',
                                // data:"json",
                                data:{'id':data.id},//向服务端发送删除的id
                                success:function(suc){
                                    if(suc.code==100){
                                        obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                                        layer.close(index);
                                        console.log(index);
                                        layer.msg("删除成功",{icon:1});
                                    }
                                    else{
                                        layer.msg("删除失败",{icon:5});
                                    }
                                }
                            });
                            layer.close(index);
                        });
                    }else if (layEvent === 'add'){
                        layui.use('layer',function () {
                            var layer = layui.layer;
                            // layer.msg('hello');
                            layui.code
                            layer.open({
                                type: 2,
                                // btn: ['保存', '取消'],
                                content: '${APP_PATH}/jsp/alertAdd.jsp',
                                area: ['800px', '500px'],
                                success: function(layero, index){
                                    var body = layer.getChildFrame('body', index);
                                    var iframeWin = window[layero.find('iframe')[0]['name']]; //得到iframe页的窗口对象，执行iframe页的方法：
                                    iframeWin.method;
                                    console.log(body.html()) //得到iframe页的body内容
                                    body.find('#LAY-user-login-username').val('')
                                    body.find('#LAY-user-login-number').val('')
                                    body.find('#LAY-user-login-userId').val('')
                                }
                            });
                        });
                    }else if(layEvent === 'edit'){ //编辑
                        layui.use('layer',function () {
                            var layer = layui.layer;
                            // layer.msg('hello');
                            layui.code
                            layer.open({
                                type: 2,
                                // btn: ['保存', '取消'],
                                content: '${APP_PATH}/updateUserinfoIframe',
                                area: ['800px', '500px'],
                                success: function(layero, index){
                                    var body = layer.getChildFrame('body', index);
                                    var iframeWin = window[layero.find('iframe')[0]['name']]; //得到iframe页的窗口对象，执行iframe页的方法：
                                    iframeWin.method;
                                    console.log(body.html()) //得到iframe页的body内容
                                    body.find('#LAY-user-login-username').val(data.name)
                                    body.find('#LAY-user-login-number').val(data.userId)
                                    body.find('#LAY-user-login-userId').val(data.wechar)
                                    body.find('#LAY-user-login-phone').val(data.phone)
                                    body.find('#LAY-user-login-dept').val(data.departId)
                                    body.find('#LAY-user-login-type').val(data.userType)
                                    body.find('#LAY-user-login-submit').val(data.userType)
                                    body.find('#LAY-user-login-canler').val(data.userType)
                                }
                            });
                        });
                    }
                });
                //头工具栏事件
                table.on('toolbar(test)', function(obj){
                    var checkStatus = table.checkStatus(obj.config.id);
                    switch(obj.event){
                        case 'getCheckData':
                            var data = checkStatus.data;
                            layer.alert(JSON.stringify(data));
                            break;
                        case 'getCheckLength':
                            var data = checkStatus.data;
                            layer.msg('选中了：'+ data.length + ' 个');
                            break;
                        case 'isAll':
                            layer.msg(checkStatus.isAll ? '全选': '未全选');
                            break;
                        case 'refresh':
                            aa();
                            break;

                    };
                });

            })
        }
        <%--else if(flag=="sigininfo"){--%>
            <%--//********************************签到信息*********************************--%>
            <%--layui.use('table', function aa() {--%>
                <%--var table = layui.table;--%>
                <%--var datass = ''--%>
                <%--$.ajax({--%>
                    <%--type: "POST",--%>
                    <%--url: "${APP_PATH}/findAllMeetingSignin",--%>
                    <%--success: function(result) {--%>
                        <%--// alert(result.extend.list[1].id);--%>
                        <%--// console.log(result)--%>
                        <%--datass = result.extend.list,--%>
                            <%--table.render({--%>
                                <%--elem: '#demo',--%>
                                <%--cellMinWidth: 80,--%>
                                <%--toolbar: '#toolbarDemo',--%>
                                <%--/*totalRow: true,*/--%>
                                <%--data: datass,--%>
                                <%--page: { //支持传入 laypage 组件的所有参数（某些参数除外，如：jump/elem） - 详见文档--%>
                                    <%--layout: ['limit', 'count', 'prev', 'page', 'next', 'skip'] //自定义分页布局--%>
                                    <%--//,curr: 5 //设定初始在第 5 页--%>
                                    <%--,groups: 1 //只显示 1 个连续页码--%>
                                    <%--,first: false //不显示首页--%>
                                    <%--,last: false //不显示尾页--%>
                                <%--},--%>
                                <%--cols: [[--%>
                                    <%--{type:'numbers', title: '序号'}--%>
                                    <%--,{type: 'checkbox'}--%>
                                    <%--,{field:'id',  title: 'id'/*, totalRow: true*/}--%>
                                    <%--,{field:'userId', title: 'userId'}--%>
                                    <%--,{field:'meetingId', title: 'meetingId', edit: 'text'}--%>
                                    <%--,{field:'signinTime', title: 'signinTime', edit: 'text'} //minWidth：局部定义当前单元格的最小宽度，layui 2.2.1 新增--%>
                                    <%--,{field:'signoutTime', title: 'signoutTime'}--%>
                                    <%--,{field:'signinFlag', title: 'signinFlag', edit: 'text'}--%>
                                    <%--,{field:'signoutFlag', title: 'signoutFlag',  edit: 'text'}--%>
                                    <%--,{field:'leaveFlag',  title: 'leaveFlag'}--%>
                                    <%--,{field:'lateFlag',  title: 'lateFlag'}--%>
                                    <%--,{field:'deleteFlag',  title: 'deleteFlag'}--%>
                                    <%--,{field:'deleteUid',  title: 'deleteUid'}--%>
                                    <%--,{field:'deleteTime',  title: 'deleteTime'}--%>
                                    <%--// ,{field:'deleteUid',  title: '删除用户管理员ID'}--%>
                                    <%--// ,{field:'deleteDate',  title: '删除时间'}--%>
                                    <%--// ,{fixed: 'right', title:'操作', toolbar: '#barDemo', width:300,align:'center'}--%>
                                <%--]]--%>
                            <%--});--%>
                    <%--}--%>
                <%--});--%>

                <%--table.on('tool(test)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"--%>
                    <%--var data = obj.data; //获得当前行数据--%>
                    <%--var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）--%>
                    <%--var tr = obj.tr; //获得当前行 tr 的DOM对象--%>

                    <%--if(layEvent === 'detail'){ //查看--%>
                        <%--//do somehing--%>
                        <%--layer.alert('姓名:'+ data.name+'<br>序号:'+ data.id+'<br>工号:'+data.userId+'<br>手机号：'+data.phone+'<br>微信号:'+data.wechar+'<br>部门:'+data.departId+'<br>用户类型:'+data.userType);--%>
                    <%--} else if(layEvent === 'del'){ //删除--%>
                        <%--layer.confirm('真的删除ID为：'+data.userId+"的用户吗?", function(index){--%>
                            <%--//console.log(data.p_id);--%>
                            <%--//向服务端发送删除指令--%>
                            <%--$.ajax({--%>
                                <%--url:'${APP_PATH}/deleteUser',/*?id='+data.userId,*/--%>
                                <%--type:'get',--%>
                                <%--// data:"json",--%>
                                <%--data:{'id':data.id},//向服务端发送删除的id--%>
                                <%--success:function(suc){--%>
                                    <%--if(suc.code==100){--%>
                                        <%--obj.del(); //删除对应行（tr）的DOM结构，并更新缓存--%>
                                        <%--layer.close(index);--%>
                                        <%--console.log(index);--%>
                                        <%--layer.msg("删除成功",{icon:1});--%>
                                    <%--}--%>
                                    <%--else{--%>
                                        <%--layer.msg("删除失败",{icon:5});--%>
                                    <%--}--%>
                                <%--}--%>
                            <%--});--%>
                            <%--layer.close(index);--%>
                        <%--});--%>
                    <%--}else if(layEvent === 'edit'){ //编辑--%>
                        <%--layui.use('layer',function () {--%>
                            <%--var layer = layui.layer;--%>
                            <%--// layer.msg('hello');--%>
                            <%--layui.code--%>
                            <%--layer.open({--%>
                                <%--type: 2,--%>
                                <%--// btn: ['保存', '取消'],--%>
                                <%--content: '${APP_PATH}/updateUserinfoIframe',--%>
                                <%--area: ['800px', '500px'],--%>
                                <%--success: function(layero, index){--%>
                                    <%--var body = layer.getChildFrame('body', index);--%>
                                    <%--var iframeWin = window[layero.find('iframe')[0]['name']]; //得到iframe页的窗口对象，执行iframe页的方法：--%>
                                    <%--iframeWin.method;--%>
                                    <%--console.log(body.html()) //得到iframe页的body内容--%>
                                    <%--body.find('#LAY-user-login-username').val(data.name)--%>
                                    <%--body.find('#LAY-user-login-number').val(data.userId)--%>
                                    <%--body.find('#LAY-user-login-userId').val(data.wechar)--%>
                                    <%--body.find('#LAY-user-login-phone').val(data.phone)--%>
                                    <%--body.find('#LAY-user-login-dept').val(data.departId)--%>
                                    <%--body.find('#LAY-user-login-type').val(data.userType)--%>
                                    <%--body.find('#LAY-user-login-submit').val(data.userType)--%>
                                    <%--body.find('#LAY-user-login-canler').val(data.userType)--%>
                                <%--}--%>
                            <%--});--%>
                        <%--});--%>
                    <%--}--%>
                <%--});--%>

                <%--//头工具栏事件--%>
                <%--table.on('toolbar(test)', function(obj){--%>
                    <%--var checkStatus = table.checkStatus(obj.config.id);--%>
                    <%--switch(obj.event){--%>
                        <%--case 'getCheckData':--%>
                            <%--var data = checkStatus.data;--%>
                            <%--layer.alert(JSON.stringify(data));--%>
                            <%--break;--%>
                        <%--case 'getCheckLength':--%>
                            <%--var data = checkStatus.data;--%>
                            <%--layer.msg('选中了：'+ data.length + ' 个');--%>
                            <%--break;--%>
                        <%--case 'isAll':--%>
                            <%--layer.msg(checkStatus.isAll ? '全选': '未全选');--%>
                            <%--break;--%>
                        <%--case 'refresh':--%>
                            <%--aa();--%>
                            <%--break;--%>

                    <%--};--%>
                <%--});--%>
            <%--})--%>
        <%--}--%>
    }
    //JavaScript代码区域
    layui.use('element', function(){
        var element = layui.element;

    });
</script>
</body>
</html>