var APP_PATH = document.getElementById("APP_PATH").innerText;
//窗口打开就显示用户信息
showBody("userinfo");
function showBody(flag){
    $(".layui-body .body").empty();
    $(".layui-body .body").append("<table id='demo' lay-filter='test'></table>");
    if(flag=="userinfo"){
        $(".layui-body h1").text("用户信息");
        userinfo_table();
    }else if(flag=="meetinginfo"){
        $(".layui-body h1").text("会议信息");
        meetinginfo_table();
    }else if(flag=="meetingroom"){
        $(".layui-body h1").text("会议室信息");
        meetingroom_table();
    }else if(flag=="deptinfo"){
        $(".layui-body h1").text("部门信息");
        deptinfo_table();
    }else if(flag=="myteam"){
        $(".layui-body h1").text("我的分组");
        teaminfo_table();
    }
}
//校验UserInfo用户表
function validate_UserInfo() {
    var body = layer.getChildFrame('body');
    //校验工号
    var UserName =  body.find("#LAY-user-login-username").val();
    var regUserName = /(^[0-9]{10}$)/;//数字正则表达式
    if (!regUserName.test(UserName)){
        alert("工号不足10位")
        return false;
    }
    //校验微信号
    var Wechar=  body.find("#LAY-user-login-wechat").val();
    var regWechar = /^[a-zA-Z0-9_]{5,19}$/;
    if (!regWechar.test(Wechar)){
        alert("微信号错误");
        return false;
    }

    //校验姓名
    var Name = body.find('#LAY-user-login-name').val();
    var regName = /^[\u4E00-\u9FA5\uf900-\ufa2d·s]{2,20}$/;
    if (!regName.test(Name)){
        alert("姓名错误");
        return false;
    }

    //校验手机号
    var Phone =  body.find("#LAY-user-login-phone").val();
    var regPhone = /^[1][3,4,5,7,8][0-9]{9}$/;
    if (!regPhone.test(Phone)){
        alert("手机号错误");
        return false;
    }
    return true;
}
//校验MeetingRoom会议室表
function validate_MeetingRoom() {
    var body = layer.getChildFrame('body');
    //校验会议室地点
    var addressIpt = $(body).find("#addressIpt").val();
    var regaddressIpt = /^[\u4e00-\u9fa50-9]+$/;//中文+数字正则表达式
    if (!regaddressIpt.test(addressIpt)){
        alert("输入的会议室地点错误，请重新输入");
        return false;
    }
    //校验管理员工号
    var nameIpt = $(body).find("#nameIpt").val();
    var regnameIpt = /(^[0-9]{10}$)/;
    if (!regnameIpt.test(nameIpt)){
        alert("工号不足10位")
        return false;
    }

    return true;
}
//校验Department部门表
function  validate_Department() {
    var body = layer.getChildFrame('body');
    //校验部门名称
    var departName = $(body).find("#adddepart").val();
    var regdepartName =/^[\u4e00-\u9fa5]+$/;//中文正则表达式
    if (!regdepartName.test(departName)) {
        alert("输入的部门名称错误，请重新输入");
        return false;
    }
    return true;
}
//校验MeetingInfo会议表
function validate_MeetingInfo() {
    var body = layer.getChildFrame('body');

    var meetingName = $(body).find("#meetingName").val();//会议名称
    var regmeetingName=/^[\u4e00-\u9fa5_a-zA-Z0-9]+$/;//中文+数字+英文正则表达式
    if (!regmeetingName.test(meetingName)){
        alert("输入的会议名称错误，请重新输入");
        return false;
    }

    var meetingIntro = $(body).find("#meetingIntro").val();//会议简介
    var regmeetingIntro=/^[\u4e00-\u9fa5_a-zA-Z0-9]+$/;//中文+数字+英文正则表达式
    if (!regmeetingIntro.test(meetingIntro)){
        alert("输入的会议简介错误，请重新输入");
        return false;
    }
    return true;
}

//将时间戳格式化
function getDate(val) {
    var time = new Date(val);
    y=time.getFullYear();
    m=time.getMonth()+1;
    d=time.getDate();
    return y + "-" + (m < 10 ? "0" + m : m) + "-" + (d < 10 ? "0" + d : d) + " " + time.toTimeString().substr(0, 8);
}
//********************************用户信息表*********************************
function userinfo_table() {
    layui.use('table', function aa() {
        var table = layui.table;

        table.render({
            elem: '#demo',
            url:APP_PATH+"/userInfo/findAll",
            cellMinWidth: 80,
            toolbar: '#userinfo_toolbarDemo',
            height:718,
            /*totalRow: true,*/
            limit: 15,//每页15条记录
            page: { //支持传入 laypage 组件的所有参数（某些参数除外，如：jump/elem） - 详见文档
                layout: ['count', 'prev', 'page', 'next', 'skip'] //自定义分页布局
            },
            parseData:function(res){
                // console.log(res);
                return {
                    "code": res.code == 100 ? 0: 1, //解析接口状态
                    "msg": res.msg, //解析提示文本
                    "count": res.extend.userinfo.total, //解析数据长度
                    "data": res.extend.userinfo.list //解析数据列表
                };
            },
            cols: [[
                {type:'numbers', title: '序号'}
                ,{type: 'checkbox'}
                ,{field:'username', title: '工号'}
                ,{field:'name', title: '姓名'}
                ,{field:'departName', title: '部门名称'}
                ,{field:'wechar', title: '微信号'}
                ,{field:'phone', title: '手机号'} //minWidth：局部定义当前单元格的最小宽度，layui 2.2.1 新增
                ,{fixed: 'right', title:'操作', toolbar: '#userinfo_barDemo', width:150,align:'center'}
            ]]

        });

        table.on('tool(test)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
            var tr = obj.tr; //获得当前行 tr 的DOM对象
            if(layEvent === 'del'){ //删除
                layer.confirm('真的删除工号为:'+data.username+"的用户吗?", function(index){
                    //向服务端发送删除指令
                    $.ajax({
                        url:APP_PATH+'/userInfo/deleteUser',
                        type:'POST',
                        data:{'id':data.id},//向服务端发送删除的id
                        success:function(suc){
                            if(suc.code==100){
                                obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                                layer.close(index);
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
                    // layui.code
                    layer.open({
                        type: 2, //
                        btn: ['保存', '取消'],
                        content: APP_PATH+'/jumpPage/updateUserinfoIframe',
                        area: ['800px', '500px'],
                        success: function(layero, index){//成功打开子窗口
                            var body = layer.getChildFrame('body', index);
                            var iframeWin = window[layero.find('iframe')[0]['name']]; //得到iframe页的窗口对象，执行iframe页的方法：
                            iframeWin.method;
                            body.find('#LAY-user-login-id').val(data.id);
                            body.find('#LAY-user-login-username').val(data.username)    // console.log(body.html()) //得到iframe页的body内容
                            body.find('#LAY-user-login-name').val(data.name)
                            body.find('#LAY-user-login-wechat').val(data.wechar)
                            body.find('#LAY-user-login-phone').val(data.phone)
                            body.find('#LAY-user-login-dept').val(data.departId)
                            body.find('#LAY-user-login-type').val(data.type)
                        },
                        yes: function(index, layero){//单击保存按钮
                            //事件
                            var body = layer.getChildFrame('body', index);
                            var id = body.find('#LAY-user-login-id').val();
                            var iframeWin = window[layero.find('iframe')[0]['name']]; //得到iframe页的窗口对象，执行iframe页的方法：
                            var username =  body.find("#LAY-user-login-username").val();
                            var wechar=  body.find("#LAY-user-login-wechat").val();
                            var name=  body.find("#LAY-user-login-name").val();
                            var phone =  body.find("#LAY-user-login-phone").val();
                            var departId = body.find("#dept_select").parent().find(".layui-this").attr("lay-value");
                            var type = body.find("#type_select").parent().find(".layui-this").attr("lay-value");
                            var id_depart=data.id;

                            $.ajax({
                                url:APP_PATH+"/userInfo/checkUpdateUserinfoPhone",
                                data:{
                                    phone:phone,
                                    userinfo:id_depart
                                },
                                type:"POST",
                                success:function (data) {
                                    if (data.code==100){
                                        $.ajax({
                                            url:APP_PATH+"/userInfo/checkUpdateUserinfoWechar",
                                            data:{
                                                wechar:wechar,
                                                userinfo:id_depart
                                            },
                                            type:"POST",
                                            success:function (data) {
                                                if (data.code==100){
                                                    $.ajax({
                                                        url:APP_PATH+"/userInfo/updateUser",
                                                        data:{
                                                            id:id,
                                                            username:username,
                                                            wechar:wechar,
                                                            name:name,
                                                            phone:phone,
                                                            departId:departId,
                                                            type:type
                                                        },
                                                        type:"POST",
                                                        success:function (res) {
                                                            if (res.code==100){
                                                                layer.close(index);
                                                                aa();
                                                                layer.msg(res.extend.msg,{icon:1});
                                                            }else if(res.code==200){
                                                                layer.msg(res.extend.msg,{icon:5});
                                                            }
                                                        }
                                                    });
                                                }else if(data.code==200){
                                                    layer.msg("微信号已存在",{icon:5})
                                                }
                                            }
                                        });
                                    }else if(data.code==200){
                                        layer.msg("手机号已存在",{icon:5})
                                    }
                                }
                            });
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
                case 'selectDelUsers_btn':
                    //多条删除
                    var data = JSON.stringify(checkStatus.data);
                    var json = $.parseJSON(data);
                    var ids="";
                    for(var i=0 ; i<json.length; i++){
                        if(i!=0)ids+=",";
                        ids+=json[i].id;
                    }
                    if(ids.length==0){
                        layer.msg("请选择需要删除的记录",{icon:5});
                        return false;
                    }
                    layer.confirm("确认是否删除",function (index) {
                        $.ajax({
                            url:APP_PATH+"/userInfo/deleteByExampleSelectiveUser/"+ids,
                            type:"POST",
                            success:function (result) {
                                // console.log(result)
                                aa();//重新加载数据
                            }
                        })
                        layer.close(index);
                    })
                    break;
                case 'insertUser_btn':
                    // var data = checkStatus.data;
                    // layer.msg('选中了：'+ data.length + ' 个');
                    layui.use('layer',function () {
                        var layer = layui.layer;
                        // layer.msg('hello');
                        layui.code
                        layer.open({
                            type: 2,
                            btn: ['保存', '取消'],
                            content: APP_PATH+'/jumpPage/insertUserinfoIframe',
                            area: ['800px', '500px'],
                            success: function(layero, index){
                                var body = layer.getChildFrame('body', index);
                                var iframeWin = window[layero.find('iframe')[0]['name']]; //得到iframe页的窗口对象，执行iframe页的方法：
                                iframeWin.method;
                            },
                            yes: function(index, layero){
                                var body = layer.getChildFrame('body', index);
                                var iframeWin = window[layero.find('iframe')[0]['name']]; //得到iframe页的窗口对象，执行iframe页的方法：
                                var username =  body.find("#LAY-user-login-username").val();
                                var wechar=  body.find("#LAY-user-login-wechat").val();
                                var name=  body.find("#LAY-user-login-name").val();
                                var phone =  body.find("#LAY-user-login-phone").val();
                                var departId = body.find("#dept_select").parent().find(".layui-this").attr("lay-value");
                                var type = body.find("#type_select").parent().find(".layui-this").attr("lay-value");
                                var flag=0;
                                if (!validate_UserInfo()){
                                    return false;
                                }
                                $.ajax({
                                    url:APP_PATH+"/userInfo/checkAddUserInfoUsername",
                                    data:"username="+username,
                                    type:"POST",
                                    success:function (data) {
                                        if (data.code==100){
                                            $.ajax({
                                                url:APP_PATH+"/userInfo/checkAddUserInfoWeChate",
                                                data: "wechar="+wechar,
                                                type:"POST",
                                                success:function (data) {
                                                    if (data.code==100){
                                                        $.ajax({
                                                            url:APP_PATH+"/userInfo/checkAddUserInfoPhone",
                                                            data:"phone="+phone,
                                                            type:"POST",
                                                            success:function (data) {
                                                                if (data.code==100){
                                                                    $.ajax({
                                                                        url: APP_PATH + "/userInfo/insertUser",
                                                                        data: {
                                                                            username: username,
                                                                            wechar: wechar,
                                                                            name: name,
                                                                            phone: phone,
                                                                            departId: departId,
                                                                            type: type
                                                                        },
                                                                        type: "POST",
                                                                        success: function (res) {
                                                                            if (res.code == 100) {
                                                                                layer.close(index);
                                                                                aa();
                                                                                layer.msg(res.extend.msg, {icon: 1});
                                                                            } else if (res.code == 200) {
                                                                                layer.msg(res.extend.msg, {icon: 5});
                                                                            }
                                                                        }
                                                                    });
                                                                }else if(data.code==200){

                                                                    layer.msg("手机号已存在",{icon:5})
                                                                }
                                                            }
                                                        });
                                                    }else if(data.code==200){
                                                        layer.msg("微信号已存在",{icon:5})
                                                    }
                                                }
                                            });
                                        }else if(data.code==200){
                                            layer.msg("用户名已存在",{icon:5})
                                        }
                                    }
                                });

                            }
                        });
                    });
                    break;
                case 'refresh':
                    aa();
                    break;

            };
        });
    })
}
//********************************会议信息*********************************
function meetinginfo_table(){
    //用户表格
    layui.use('table', function aa() {
        var table = layui.table;
        table.render({
            elem: '#demo',
            url:APP_PATH+"/meetingInfo/findAllMeetingInfo",
            cellMinWidth: 80,
            toolbar: '#meetinginfo_toolbarDemo',
            height:718,
            limit:15,
            page: { //支持传入 laypage 组件的所有参数（某些参数除外，如：jump/elem） - 详见文档
                layout: ['count', 'prev', 'page', 'next', 'skip'] //自定义分页布局
            },
            parseData:function(res){
                return {
                    "code": res.code == 100 ? 0: 1, //解析接口状态
                    "msg": res.msg, //解析提示文本
                    "count": res.extend.meetinginfo.total, //解析数据长度
                    "data": res.extend.meetinginfo.list //解析数据列表
                };
            },
            cols: [[
                {type:'numbers', title: '序号'}
                ,{type: 'checkbox'}
                ,{field:'name', title: '会议名称'}
                ,{field:'intro', title: '会议简介'} //minWidth：局部定义当前单元格的最小宽度，layui 2.2.1 新增
                // ,{field:'deptName', title: '部门名称'}
                ,{field:'address', title: '会议地点'}
                // ,{field:'type', title: '会议类型'}
                ,{field:'startTime', title: '开始时间', templet : "<div id='stratTime_td'>{{layui.util.toDateString(d.startTime, 'yyyy年MM月dd日HH点mm分')}}</div>"}
                ,{field:'endTime', title: '结束时间', templet : "<div id='endTime_td'>{{layui.util.toDateString(d.endTime, 'yyyy年MM月dd日HH点mm分')}}</div>"}
                ,{fixed: 'right', title:'操作', toolbar: '#meetinginfo_barDemo',width:300,align:'center'}
            ]]
        });

        table.on('tool(test)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
            var tr = obj.tr; //获得当前行 tr 的DOM对象

            if(layEvent === 'del'){ //删除
                layer.confirm('真的删除ID为：'+data.name+"的用户吗?", function(index){
                    $.ajax({
                        url:APP_PATH+'/meetingInfo/deleteMeetingInfo',
                        type:'POST',
                        // data:"json",
                        data:{'id':data.id},//向服务端发送删除的id
                        success:function(suc){
                            if(suc.code==100){
                                obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                                layer.close(index);
                                // console.log(index);
                                layer.msg("删除成功",{icon:1});
                            }else{
                                layer.msg("删除失败",{icon:5});
                            }
                        }
                    });
                    layer.close(index);
                });
            }
            else if(layEvent === 'edit'){ //编辑
                layui.use('layer',function (obj) {
                    var layer = layui.layer;
                    // layer.msg('hello');
                    layui.code
                    layer.open({
                        title:"编辑会议",
                        type: 2,
                        btn: ['保存', '取消'],
                        content: APP_PATH+'/jumpPage/updateMeetinginfoIframe?meetingId='+data.id,
                        area: ['1260px', '80%'],
                        success: function(layero, index){
                            var body = layer.getChildFrame('body', index);
                            var iframeWin = window[layero.find('iframe')[0]['name']]; //得到iframe页的窗口对象，执行iframe页的方法：
                            iframeWin.method;
                            console.log(data);
                            $(body).find("#meetingId").text(data.id);
                            $(body).find("#meetingName").val(data.name);
                            $(body).find("#date1").val(getDate(data.startTime));
                            $(body).find("#date2").val(getDate(data.endTime));
                            $(body).find("#meetingIntro").val(data.intro);
                        },
                        yes:function (index,layero) {
                            var body = layer.getChildFrame('body', index);
                            var iframeWin = window[layero.find('iframe')[0]['name']]; //得到iframe页的窗口对象，执行iframe页的方法：

                            //发送ajax所需要的参数
                            var ids = [];//参加会议人员的id集合
                            var meetingName,meetingDept,meetingAddressId,date1,date2,meetingIntro;//会议名称,会议部门id，会议地点id，开始时间，结束时间,会议简介

                            //（**************获取会议信息开始***************
                            meetingName = $(body).find("#meetingName").val();//会议名称
                            meetingDept= body.find("#dept_select").parent().find(".layui-this").attr("lay-value");//会议地点id
                            meetingAddressId = body.find("#address_select").parent().find(".layui-this").attr("lay-value");//会议地点id
                            meetingIntro = $(body).find("#meetingIntro").val();//会议简介
                            // alert(meetingIntro)
                            date1 =$(body).find("#date1").val();
                            date2 =$(body).find("#date2").val();
                            //将格式化的时间转为时间戳
                            date1 = Date.parse(date1)//开始时间
                            date2 = Date.parse(date2)//结束时间
                            //***************获取会议信息结束**************）

                            //（***************获取参会人员id开始**************
                            var rightTable = body.find("tbody:eq(2)");//获得右边的表格
                            // var t= $(rightTable).find("[data-field='id'] div").text();
                            var divIds= $(rightTable).find("[data-field='id'] div");//获取所有储存id的div


                            //遍历每一个div将其内容取值
                            for(var i=0 ; i<divIds.length ;i++){
                                // console.log(divIds[i]);
                                var id = divIds[i].innerText;
                                ids.push(id);
                            }
                            //提交创建会议的信息
                            $.ajax({
                                url:APP_PATH+"/meetingInfo/updateMeetingInfoAndSignin",
                                type:"POST"
                                ,data:{
                                    id:data.id,
                                    ids:ids,
                                    name:meetingName,
                                    departId:meetingDept,
                                    roomId:meetingAddressId,
                                    startTime:date1,
                                    endTime:date2,
                                    meetingIntro:meetingIntro
                                }
                                ,success(res){
                                    if(res.code==100){
                                        layer.close(index);
                                        // alert("1");
                                        aa();
                                        layer.msg("创建成功",{icon:1});
                                    }else{
                                        layer.msg("创建失败",{icon:5});
                                    }
                                }
                            })
                            // var body = layer.getChildFrame('body', index);
                            // var iframeWin = window[layero.find('iframe')[0]['name']]; //得到iframe页的窗口对象，执行iframe页的方法：
                            //
                            // //数据准备
                            // var meetingId = data.id;
                            // var meetingName = $(body).find("#meetingName").val();
                            // var startTime = $(body).find("#date1").val();
                            // var endTime = $(body).find("#date2").val();
                            // var meetingIntro = $(body).find("#meetingIntro").val();
                            // startTime = Date.parse(startTime);
                            // endTime = Date.parse(endTime);
                            //
                            // var ids = [];
                            // for (var i=0 ;i<iframeWin.rightData.length;i++){
                            //     var id = iframeWin.rightData[i].id;
                            //     ids.push(id);
                            // }
                            // console.log(ids);
                            // $.ajax({
                            //     url:APP_PATH+"/updateMeetingInfoAndSignin",
                            //     type:"POST",
                            //     data:{
                            //         id:meetingId,
                            //         name:meetingName,
                            //         startTime:startTime,
                            //         endTime:endTime,
                            //         intro:meetingIntro,
                            //         ids:ids
                            //     },
                            //     success:function (res) {
                            //         if (res.code == 100){
                            //             layer.close(index);
                            //             layer.msg("修改成功",{icon:1});
                            //         } else{
                            //             layer.msg("修改失败",{icon:5});
                            //         }
                            //     }
                            // })
                        }
                    });
                });
            }else if (layEvent === 'qrcode') {
                window.open(APP_PATH+"/jumpPage/create_Qrcode?meeting_id="+data.id)
            }else if (layEvent==='signin'){
                layui.use('layer', function(obj) {
                    layui.code
                    layer.open({
                        type: 2,
                        btn: ['保存', '取消'],
                        content: APP_PATH + '/jumpPage/signinInfoIframe',
                        area: ['1000px', '700px'],
                        success: function(layero, index) {
                        },
                        yes: function(index, layero) {
                            var body = layer.getChildFrame('body', index);
                            var iframeWin = window[layero.find('iframe')[0]['name']];
                            var cache_data=iframeWin.layui.table.cache
                            cache_data=cache_data.test
                            for(var i=0;i<cache_data.length;i++){
                                if (cache_data[i].status=="已到")
                                    cache_data[i].status="signin"
                                else if(cache_data[i].status=="未到")
                                    cache_data[i].status="notsignin"
                                else if (cache_data[i].status=="请假")
                                    cache_data[i].status='leave'
                                else cache_data[i].status="late"
                            }
                            console.log(cache_data)
                            $.ajax({
                                url:APP_PATH+"/meetingSignin/updateSignByUserAndMeeting",
                                data:{ids:JSON.stringify(cache_data)},
                                type:"POST",
                                traditional: true,
                                success:function (result) {
                                    if (result.code==100){
                                        layer.msg("保存成功",{icon:1});
                                    }
                                }
                            })
                            layer.close(index);
                        }
                    });
                });
            }
        });

        //头工具栏事件
        table.on('toolbar(test)', function(obj){
            var checkStatus = table.checkStatus(obj.config.id);
            switch(obj.event){
                case 'insertMeetinginfo_btn':
                    // var data = checkStatus.data;
                    // layer.msg('选中了：'+ data.length + ' 个');
                    layui.use(['layer','form'],function () {
                        var layer = layui.layer;
                        var form = layui.form;
                        // layer.msg('hello');
                        layui.code
                        layer.open({
                            title:'新建会议',
                            type: 2,
                            btn:['保存','取消'],
                            content: APP_PATH+'/jumpPage/insertMeetinginfoIframe',
                            area: ['1260px', '80%'],
                            yes: function(index, layero) {
                                var body = layer.getChildFrame('body', index);
                                var iframeWin = window[layero.find('iframe')[0]['name']]; //得到iframe页的窗口对象，执行iframe页的方法：

                                //发送ajax所需要的参数
                                var ids = [];//参加会议人员的id集合
                                var meetingName,meetingDept,meetingAddressId,date1,date2,meetingIntro;//会议名称,会议部门id，会议地点id，开始时间，结束时间,会议简介

                                //（**************获取会议信息开始***************
                                meetingName = $(body).find("#meetingName").val();//会议名称
                                // meetingDept= body.find("#dept_select").parent().find(".layui-this").attr("lay-value");//会议地点id
                                meetingDept=$(body).find("#tagsinputval").val()
                                meetingAddressId = body.find("#address_select").parent().find(".layui-this").attr("lay-value");//会议地点id
                                meetingIntro = $(body).find("#meetingIntro").val();//会议简介
                                date1 =$(body).find("#date1").val();
                                date2 =$(body).find("#date2").val();
                                //将格式化的时间转为时间戳
                                date1 = Date.parse(date1)//开始时间
                                date2 = Date.parse(date2)//结束时间
                                var qcode_refresh=$(body).find("#qcode_refresh").val();;

                                console.log(meetingDept)
                                //***************获取会议信息结束**************）

                                //（***************获取参会人员id开始**************
                                var rightTable = body.find("tbody:eq(2)");//获得右边的表格
                                // var t= $(rightTable).find("[data-field='id'] div").text();
                                var divIds= $(rightTable).find("[data-field='id'] div");//获取所有储存id的div
                                //遍历每一个div将其内容取值
                                for(var i=0 ; i<divIds.length ;i++){
                                    // console.log(divIds[i]);
                                    var id = divIds[i].innerText;
                                    ids.push(id);
                                }
                                if (!validate_MeetingInfo()){
                                    return false;
                                }
                                console.log(ids);
                                //提交创建会议的信息
                                $.ajax({
                                    url:APP_PATH+"/meetingInfo/insertMeetingInfo",
                                    type:"POST"
                                    ,data:{
                                        ids:ids,
                                        name:meetingName,
                                        departName:meetingDept,
                                        roomId:meetingAddressId,
                                        startTime:date1,
                                        endTime:date2,
                                        meetingIntro:meetingIntro,
                                        qcode_refresh:qcode_refresh
                                    }
                                    ,success(res){
                                        if(res.code==100){
                                            layer.close(index);
                                            aa();
                                            layer.msg("创建成功",{icon:1});
                                        }else{
                                            layer.msg("创建失败",{icon:5});
                                        }
                                    }
                                })
                            }
                        });
                    });
                    break;
                case 'selectDelMeetinginfos_btn':
                    //多条删除
                    var data = JSON.stringify(checkStatus.data);
                    var json = $.parseJSON(data);
                    var ids="";
                    for(var i=0 ; i<json.length; i++){
                        if(i!=0)ids+=",";
                        ids+=json[i].id;
                    }
                    if(ids.length==0){
                        layer.msg("请选择需要删除的记录",{icon:5});
                        return false;
                    }
                    layer.confirm('真的删除id为:'+ids+"的会议吗?", function(index){
                        $.ajax({
                            url:APP_PATH+"/meetingInfo/deleteByExampleSelectiveMeetingInfo/"+ids,
                            type:"POST",
                            success:function(suc){
                                if(suc.code==100){
                                    //obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                                    layer.close(index);
                                    aa();
                                    layer.msg("删除成功",{icon:1});
                                }
                                else{
                                    layer.msg("删除失败",{icon:5});
                                }
                            }
                        });
                    });
                    break;
            };
        });
    });
}
//********************************会议室信息表*********************************
function meetingroom_table() {
    layui.use('table', function aa() {
        var table = layui.table;
        table.render({
            elem: '#demo',
            url:APP_PATH+"/meetingRoom/selectMeetingRoomPage",
            type:"GET",
            toolbar: '#meetingroom_toolbarDemo',
            height:718,
            /*totalRow: true,*/
            limit: 15,//每页15条记录
            page: { //支持传入 laypage 组件的所有参数（某些参数除外，如：jump/elem） - 详见文档
                layout: ['count', 'prev', 'page', 'next', 'skip'] //自定义分页布局
            },
            parseData:function(res){
                // console.log(res);
                return {
                    "code": res.code == 100 ? 0: 1, //解析接口状态
                    "msg": res.msg, //解析提示文本
                    "count": res.extend.meetingroom.total, //解析数据长度
                    "data": res.extend.meetingroom.list //解析数据列表
                };
            }

            ,cols: [[
                {type:'numbers', title: '序号'}
                ,{type: 'checkbox'}
                ,{field:'address', title: '会议室地址'}
                ,{field:'adminName', title: '会议室管理员'}
                // ,{field:'adminPhone',title:'管理员联系方式'}
                // ,{field:'insertUid', title: '添加会议室用户'}
                ,{fixed: 'right', title:'操作', toolbar: '#meetingroom_barDemo', align:'center'}
            ]]

        });

        table.on('tool(test)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
            var tr = obj.tr; //获得当前行 tr 的DOM对象
            if(layEvent === 'del'){ //删除
                console.log(data)
                layer.confirm('真的删除地址为:'+data.address+"的会议室吗?", function(index){
                    //向服务端发送删除指令
                    $.ajax({
                        url:APP_PATH+'/meetingRoom/deleteMeetingRoom',
                        type:'POST',
                        data:{'id':data.id},//向服务端发送删除的id
                        success:function(res){
                            if(res.code==100){
                                obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                                layer.close(index);
                                layer.msg(res.extend.msg,{icon:1});
                            }
                            else{
                                layer.msg(res.extend.msg,{icon:5});
                            }
                        }
                    });
                    layer.close(index);
                });
            }else if(layEvent === 'edit'){ //编辑
                layui.use('layer',function () {
                    var layer = layui.layer;
                    layer.open({
                        type: 2, //
                        btn: ['保存', '取消'],
                        content: APP_PATH+'/jumpPage/updateMeetingRoomIframe',
                        area: ['700px', '600px'],
                        success: function(layero, index){
                            var body = layer.getChildFrame('body', index);
                            var iframeWin = window[layero.find('iframe')[0]['name']]; //得到iframe页的窗口对象，执行iframe页的方法：
                            iframeWin.method;
                            $(body).find("#addressIpt").val(data.address);
                            $(body).find("#nameIpt").val(data.adminName);
                        },
                        yes: function(index, layero){
                            //事件
                            var body = layer.getChildFrame('body', index);
                            var iframeWin = window[layero.find('iframe')[0]['name']]; //得到iframe页的窗口对象，执行iframe页的方法：
                            var address = $(body).find("#addressIpt").val();
                            var id_depart=data.id;
                            var admin_name=$(body).find("#nameIpt").val();
                            console.log(address);
                            console.log(id_depart);
                            console.log(admin_name)
                            $.ajax({
                                url:APP_PATH+"/meetingRoom/checkUpdateMeetingRoomAddress",
                                type:"POST",
                                data:{
                                    address: $(body).find("#addressIpt").val(),
                                    id:id_depart},
                                success:function (data) {
                                    if (data.code==100){
                                        $.ajax({
                                            url:APP_PATH+"/meetingRoom/updateMeetingRoom",
                                            type:"POST",
                                            data:{
                                                address:address,
                                                id:id_depart,
                                                admin_name:admin_name
                                            },
                                            success:function (res) {
                                                if (res.code == 100){
                                                    layer.close(index);
                                                    layer.msg("修改成功",{icon:1});
                                                    aa();
                                                } else{
                                                    layer.msg("修改失败",{icon:5});
                                                }
                                            }
                                        });
                                    }else{
                                        layer.msg("会议地点已存在",{icon:5})
                                    }
                                }
                            });
                        }
                    });
                });
            }
        });

        //头工具栏事件
        table.on('toolbar(test)', function(obj){
            var checkStatus = table.checkStatus(obj.config.id);
            switch(obj.event){
                case 'selectDelMeetinginfos_btn':

                    //多条删除
                    var data = JSON.stringify(checkStatus.data);
                    var json = $.parseJSON(data);
                    var ids="";
                    for(var i=0 ; i<json.length; i++){
                        if(i!=0)ids+=",";
                        ids+=json[i].id;
                    }
                    if(ids.length==0){
                        layer.msg("请选择需要删除的记录",{icon:5});
                        return false;
                    }
                    layer.confirm("确认是否删除",function (index) {
                        $.ajax({
                            url:APP_PATH+"/meetingRoom/deleteMeetingRoomByIds/"+ids,
                            type:"POST",
                            success:function (res) {
                                // console.log(result)
                                aa();//重新加载数据
                            }
                        })
                        layer.close(index);
                    })
                    break;
                case 'insertMeetinginfo_btn':
                    layui.use('layer',function () {
                        var layer = layui.layer;
                        layui.code
                        layer.open({
                            title:"添加会议室",
                            type: 2,
                            btn: ['保存', '取消'],
                            content: APP_PATH+'/jumpPage/insertMeetingRoomIframe',
                            area: ['700px', '600px'],
                            success: function(layero, index){
                                var body = layer.getChildFrame('body', index);
                                var iframeWin = window[layero.find('iframe')[0]['name']]; //得到iframe页的窗口对象，执行iframe页的方法：
                                iframeWin.method;
                            },
                            yes: function(index, layero){
                                var body = layer.getChildFrame('body', index);
                                var iframeWin = window[layero.find('iframe')[0]['name']];
                                var addressIpt = $(body).find("#addressIpt").val();
                                var nameIpt = $(body).find("#nameIpt").val();
                                if (!validate_MeetingRoom()){
                                    return false;
                                }
                                $.ajax({
                                    url:APP_PATH+"/meetingRoom/checkAddMeetingRoomaddress",
                                    data:"address="+addressIpt,
                                    type:"POST",
                                    success:function (data) {
                                        if (data.code==100){
                                            $.ajax({
                                                url:APP_PATH+"/meetingRoom/insertMeetingRoom",
                                                type:"POST",
                                                data:{
                                                    address:addressIpt,
                                                    username:nameIpt,
                                                },
                                                success:function (res) {
                                                    if(res.code==100){
                                                        layer.close(index);
                                                        layer.msg("添加成功",{icon:1});
                                                    }
                                                    else{
                                                        layer.msg("添加失败",{icon:5});
                                                    }
                                                    aa();
                                                }
                                            })
                                        }else if(data.code==200){
                                            layer.msg("会议地点已存在",{icon:5})
                                        }
                                    }
                                });

                            }
                        });
                    });
                    break;
                case 'refresh':
                    aa();
                    break;

            };
        });
    })
}
//********************************部门信息表*********************************
function deptinfo_table() {
    layui.use('table', function aa() {
        var table = layui.table;
        table.render({
            elem: '#demo',
            url:APP_PATH+"/department/findDeptPage",
            type:"GET",
            cellMinWidth: 80,
            toolbar: '#department_toolbarDemo',
            height:718,
            /*totalRow: true,*/
            limit: 15,//每页15条记录
            page: { //支持传入 laypage 组件的所有参数（某些参数除外，如：jump/elem） - 详见文档
                layout: ['count', 'prev', 'page', 'next', 'skip'] //自定义分页布局
            },
            parseData:function(res){
                // console.log(res.extend.depts.list);
                return {
                    "code": res.code == 100 ? 0: 1, //解析接口状态
                    "msg": res.msg, //解析提示文本
                    "count": res.extend.depts.total, //解析数据长度
                    "data": res.extend.depts.list //解析数据列表
                };
            },
            cols: [[
                {type:'numbers', title: '序号'}
                ,{type: 'checkbox'}
                // ,{field:'id', title: 'id'}
                ,{field:'name', title: '部门名',width:150}
                ,{field:'', title: ''}
                ,{fixed: 'right', title:'操作', toolbar: '#userinfo_barDemo', width:150,align:'center'}
            ]]

        });

        table.on('tool(test)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
            var tr = obj.tr; //获得当前行 tr 的DOM对象
            if(layEvent === 'del'){ //删除
                layer.confirm('真的删除:'+data.name+"吗?", function(index){
                    //向服务端发送删除指令
                    $.ajax({
                        url:APP_PATH+'/department/deleteDepartment',
                        type:'POST',
                        data:{'id':data.id},//向服务端发送删除的id
                        success:function(suc){
                            if(suc.code==100){
                                obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                                layer.close(index);
                                layer.msg("删除成功",{icon:1});
                            }
                            else{
                                layer.msg("删除失败",{icon:5});
                            }
                            aa();
                        }
                    });
                    layer.close(index);
                });
            }else if(layEvent === 'edit'){ //编辑
                layui.use('layer',function (obj) {
                    var layer = layui.layer;
                    layer.open({
                        type:2,
                        btn:['保存','取消'],
                        content: APP_PATH+'/jumpPage/updateDepartmentIframe',
                        area:['500px','25%'],
                        success:function (layero, index) {
                            // console.log(data);
                            var body = layer.getChildFrame('body',index);
                            var iframeWin = window[layero.find('iframe')[0]['name']]; //得到iframe页的窗口对象，执行iframe页的方法：
                            iframeWin.method;
                            $(body).find("#departname").val(data.name);
                        },
                        yes:function (index,layero) {
                            var body = layer.getChildFrame('body',index);
                            var iframeWin = window[layero.find('iframe')[0]['name']]; //得到iframe页的窗口对象，执行iframe页的方法：
                            var departname = data.name;
                            var id_depart=data.id;
                            console.log(id_depart)
                            console.log(departname)
                            $.ajax({
                                url:APP_PATH+"/department/checkUpdateDepartmentName" ,
                                type:"POST",
                                data:{
                                    departname:departname,
                                    name:$(body).find("#departname").val()
                                },
                                success:function (data) {
                                    if (data.code==100){
                                        $.ajax({
                                            url:APP_PATH+"/department/updateDepartment",
                                            type:"POST",
                                            data:{
                                                id:id_depart,
                                                name:$(body).find("#departname").val(),

                                            },success:function (res) {
                                                if (res.code == 100){
                                                    layer.close(index);
                                                    layer.msg("修改成功",{icon:1});
                                                } else{
                                                    layer.msg("修改失败",{icon:5});
                                                }
                                                aa();
                                            }
                                        });
                                    }else{
                                        layer.msg("部门名已存在",{icon:5})
                                    }
                                }
                            });
                        }
                    })
                });
            }
        });

            //头工具栏事件
        table.on('toolbar(test)', function(obj){
            var checkStatus = table.checkStatus(obj.config.id);
            switch(obj.event){
                case 'insertDepartment_btn':
                    // alert("123456");
                    layui.use('layer',function (obj) {
                        var layer = layui.layer;
                        layer.open({
                            title:"添加部门",
                            type:2,
                            btn:['保存','取消'],
                            content:APP_PATH+"/jumpPage/insertDepartmentIfame",
                            area:['500px','25%'],
                            success:function (layero,index) {
                                var body = layer.getChildFrame('body', index);
                                var iframeWin = window[layero.find('iframe')[0]['name']]; //得到iframe页的窗口对象，执行iframe页的方法：
                                iframeWin.method;
                            },
                            yes:function (index,layero) {
                                var body = layer.getChildFrame('body',index);
                                var departName = $(body).find("#adddepart").val();
                                // alert(departName);
                                if (!validate_Department()) {
                                    return false;
                                }
                                $.ajax({
                                    url:APP_PATH+"/department/checkAddDepartmentName",
                                    data:"departName="+departName,
                                    type:"POST",
                                    success:function (data) {
                                        if (data.code==100){
                                            $.ajax({
                                                url:APP_PATH+"/department/insertDepartment",
                                                type:"POST",
                                                data:{
                                                    name:departName
                                                },
                                                success:function (res) {
                                                    if(res.code==100){
                                                        layer.close(index);
                                                        layer.msg("部门添加成功",{icon:1});
                                                    }
                                                    else{
                                                        layer.msg("部门添加失败",{icon:5});
                                                    }
                                                    aa();
                                                }
                                            });
                                        }
                                        else{
                                            layer.msg("部门名已存在",{icon:5})
                                        }
                                    }
                                });

                            }
                        })
                    });
                    break;
                case 'refresh':
                    aa();
                    break;
            };
        });
    })
}
//********************************用户登录*********************************
function checkUsername(username){
    $.ajax({
        url:APP_PATH + "/checkUsername",
        type:"GET",
        data:{username:username},
        success:function (res) {
            if (res.code==100)
                return true;
            else if (res.code==200)
                return false;
        }
    })
}
//JavaScript代码区域
layui.use('element', function(){
    var element = layui.element;
});
//JavaScript代码区域
layui.use('element', function(){
    var element = layui.element;
});
//获取用户名
var username = ""
$(document).ready(function() {
    $.ajax({
        url: APP_PATH + '/userInfo/getUsername.do',
        type: 'GET',
        async:false,
        success: function(result) {
            // console.log(result)
            username = result.extend.userInfo.name
            $("#username").html('<img src="http://t.cn/RCzsdCq" class="layui-nav-img">' + username)
        }
    })
})
//退出
$("#log_out").click(function() {
    $.ajax({
        url: APP_PATH + '/userInfo/log_out.do',
        type: 'GET',
        success: function(result) {
            window.location.href = "/"
        }
    })
})
function teaminfo_table() {
    layui.use('table', function aa() {
        var table = layui.table;
        table.render({
            elem: '#demo',
            // url:APP_PATH+"/userInfo/findAll",
            // cellMinWidth: 80,
            // toolbar: '#userinfo_toolbarDemo',
            // height:718,
            /*totalRow: true,*/
            // limit: 15,//每页15条记录
            page: { //支持传入 laypage 组件的所有参数（某些参数除外，如：jump/elem） - 详见文档
                layout: ['count', 'prev', 'page', 'next', 'skip'] //自定义分页布局
            },
            // parseData:function(res){
            //     // console.log(res);
            //     return {
            //         "code": res.code == 100 ? 0: 1, //解析接口状态
            //         "msg": res.msg, //解析提示文本
            //         "count": res.extend.userinfo.total, //解析数据长度
            //         "data": res.extend.userinfo.list //解析数据列表
            //     };
            // },
            cols: [[
                {type:'numbers', title: '序号'}
                ,{type: 'checkbox'}
                ,{field:'teamname', title: '小组名称'}
                ,{fixed: 'right', title:'操作', toolbar: '#userinfo_barDemo', width:150,align:'center'}
            ]]

        });
    })
}
