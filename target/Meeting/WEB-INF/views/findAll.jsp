<%
    pageContext.setAttribute("APP_PATH", request.getContextPath());
%>
<link href="${APP_PATH}/static/css/layui/css/layui.css" rel="stylesheet" type="text/css">
<link href="${APP_PATH}/static/css/mycss/layui/login.css" rel="stylesheet" type="text/css">
<link href="${APP_PATH}/static/css/mycss/layui/admin.css" rel="stylesheet" type="text/css">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="${APP_PATH}/static/css/layui/css/layui.css">
<html>
<head>
    <title>用户列表</title>
</head>
<body>
<table id="demo" lay-filter="test"></table>

</body>
<script src="${APP_PATH}/static/css/layui/layui.all.js"></script>
<script src="${APP_PATH}/static/js/jquery-3.0.0.min.js"></script>
<script type="text/html" id="toolbarDemo">
    <div class="layui-btn-container">
        <button class="layui-btn layui-btn-sm" lay-event="getCheckData">获取选中行数据</button>
        <button class="layui-btn layui-btn-sm" lay-event="getCheckLength">获取选中数目</button>
        <button class="layui-btn layui-btn-sm" lay-event="isAll">清空</button>
        <button class="layui-btn layui-btn-sm" lay-event="refresh">刷新</button>
    </div>
</script>

<script type="text/html" id="barDemo1">
    <a class="layui-btn layui-btn-xs" lay-event="edit">用户</a>
</script>

<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <%--<a class="layui-btn layui-btn-xs" lay-event="add" >添加</a>--%>
    <a class="layui-btn layui-btn-xs" lay-event="detail">查看</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>
<script>
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
                            ,{field:'username', title: '用户名'}
                            //,{field:'password', title: '密码'}
                            ,{field:'name', title: '姓名', edit: 'text'}
                            ,{field:'phone', title: '手机号', edit: 'text'} //minWidth：局部定义当前单元格的最小宽度，layui 2.2.1 新增
                            ,{field:'wechar', title: '微信号'}
                            ,{field:'departId', title: '部门id', edit: 'text'}
                            ,{field:'userType', title: '用户类型',  edit: 'text'}
                            /*,{field:'addUid',  title: '添加用户管理员ID'}
                            ,{field:'addDate',  title: '添加时间'}
                            ,{field:'updateUid',  title: '修改用户管理员ID'}
                            ,{field:'updateDate',  title: '修改时间'}
                            ,{field:'deleteFlag',  title: '是否被删除'}
                            ,{field:'deleteUid',  title: '删除用户管理员ID'}
                            ,{field:'deleteDate',  title: '删除时间'}*/
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
                layer.alert('姓名:'+ data.name+'<br>序号:'+ data.id+'<br>工号:'+data.userId+'<br>手机号：'+data.phone+'<br>微信号:'+data.wechar+'<br>部门:'+data.departId+'<br>用户类型:'+data.userType);
            } else if(layEvent === 'del'){ //删除
                layer.confirm('真的删除ID为：'+data.userId+"的用户吗?", function(index){
                    //console.log(data.p_id);
                    //向服务端发送删除指令
                    $.ajax({
                        url:'${APP_PATH}/deleteUser',/*?id='+data.userId,*/
                        type:'get',
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
                            body.find('#LAY-user-login-number').val(data.id)
                            body.find('#LAY-user-login-userId').val(data.wechar)
                            body.find('#LAY-user-login-phone').val(data.phone)
                            body.find('#LAY-user-login-dept').val(data.departId)
                            body.find('#LAY-user-login-type').val(data.userType)
                            body.find('#LAY-user-login-submit').val(data.userType)
                            body.find('#LAY-user-login-canler').val(data.userType)
                        }
                    });
                });


                //     //同步更新缓存对应的值
                //     layer.confirm('要修改ID为：'+data.userId+"的用户吗?", function(index){
                //         console.log(data.inexType);
                //         var useredit={
                //             "userId":data.userId,
                //             "jobNo":data.jobNo,
                //             "userName":data.userName,
                //             "pwd":data.pwd,
                //             "class":data.class
                //         };
                //         $.ajax({
                //         url:'../php/users.edit.php',
                //         type:'post',
                //         data:useredit,
                //                         //dataType:'json',
                //                         success:function(sess){
                //                     if(sess==200){
                //                         layer.msg("编辑成功",{icon:1});
                //                     }
                //                     else{
                //                         layer.msg("编辑失败",{icon:5});
                //                     }
                //                 },
                //                         error:function (xhr,status,error) {
                //                             console.log(xhr);
                //                             console.log(status);
                //                             console.log(error);
                //                         }
                //    });
                //     });

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



        // //监听行工具事件
        // table.on('tool(test)', function(obj){
        //     var data = obj.data;
        //     //console.log(obj)
        //     if(obj.event === 'del'){
        //         layer.confirm('真的删除行么', function(index){
        //             obj.del();
        //             layer.close(index);
        //         });
        //
        //     } else if(obj.event === 'edit'){
        //         layer.prompt({
        //             formType: 2
        //             ,value: data.email
        //         }, function(value, index){
        //             obj.update({
        //                 email: value
        //             });
        //             layer.close(index);
        //         });
        //     }
        // });
        // //监听单元格编辑
        // table.on('edit(test)', function(obj){
        //     var value = obj.value //得到修改后的值
        //         ,data = obj.data //得到所在行所有键值
        //         ,field = obj.field; //得到字段
        //     layer.msg('[ID: '+ data.id +'] ' + field + ' 字段更改为：'+ value);
        // });
    })

</script>
<style>

</style>
</html>
