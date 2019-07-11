<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>编辑会议小组</title>
    <%pageContext.setAttribute("APP_PATH", request.getContextPath());%>
    <link rel="stylesheet" href="${APP_PATH}/static/css/layui/css/layui.css">
    <link rel="stylesheet" href="${APP_PATH}/static/css/bootstrap/css/bootstrap.css">
    <link rel="stylesheet" href="${APP_PATH}/static/css/bootstrap/css/bootstrap_tagsinput.css">
</head>
<body>
<div class="layui-tab-content">
    <div class="layui-tab-item layui-show">
        <div style="width: 500px;margin-top:10px;margin: 0 auto;">
            <form class="layui-form" action="">
                <div class="layui-form-item">
                    <label class="layui-form-label">名称：</label>
                    <div class="layui-input-block">
                        <input id="meetingTeanUsername" type="text" name="title" required  lay-verify="required" placeholder="请输入标题" autocomplete="off" class="layui-input" style="width: 450px">
                    </div>
                </div>
            </form>
        </div>
    </div>

    <div id="leftDiv" style="/*background-color: red;*/width:45%;float: left">
        <div style="width: 100%;height: 40px;padding-top: 5px">
            <div style="float:left;margin-left: 10px"><input id="usernameIpt" class="layui-input" type="text" autocomplete="off" style="width: 120px;height: 38px" placeholder="工号"></div>
            <div style="float:left;margin-left: 10px"><input id="nameIpt" class="layui-input" type="text" autocomplete="off" style="width: 120px;height: 38px" placeholder="姓名"></div>
            <div style="float: left;margin-left: 10px">
                <form class="layui-form" action="" style="width: 150px">
                    <select lay-verify="required" id="dept_select1">
                        <option value=""></option>
                    </select>
                </form>
            </div>
            <div style="float: left">
                <div class="layui-btn layui-inline" id="searchBtn" style="margin-left: 15px;">搜索</div>
                <%--<button class="layui-btn layui-inline" id="searchBtn" style="margin-left: 15px;">搜索</button>--%>
            </div>
        </div>
        <table id="leftTable" lay-filter="leftTable"></table>
    </div>
    <div id="midDiv" style="/*background-color: green;*/width: 10%;float: left">
        <div style="margin-left:30px;margin-top: 250px">
            <button id="selectAddBtn" class="layui-btn"><i class="layui-icon">&#xe602;</i></button>
            <br><br><br>
            <button id="selectDelBtn" class="layui-btn"><i class="layui-icon">&#xe603;</i></button>
        </div>
    </div>
    <div id="rightDiv" style="/*background-color: blue;*/width: 45%;float: left">
        <div style="width: 100%;height: 40px;padding-top: 5px">
            <input type="file" id="excel-file">
        </div>
        <table id="rightTable" lay-filter="rightTable"></table>
        <%--<button onclick="getUsernameIptVal()">testBtn</button>--%>
    </div>
</div>
</body>
<script src="${APP_PATH}/static/css/layui/layui.js"></script>
<script src="${APP_PATH}/static/js/jquery-3.0.0.min.js"></script>
<script>
    //会议地点下拉框加载
    $.ajax({
        url:"${APP_PATH}/meetingRoom/findAllMeetingRoom",
        type:"GET",
        async:false,
        success:function (res) {
            var address = res.extend.meetingroom;
            for(var i=0 ; i<address.length ; i++){
                $("<option></option>").attr("value",address[i].id).text(address[i].address).appendTo("#address_select");
            }
        }
    })

    //会议人员（加载部门列表）
    $.ajax({
        url:"${APP_PATH}/department/findAllDept",
        type:"GET",
        async:false,
        success:function (res) {
            var depts = res.extend.depts;
            for(var i=0 ; i<depts.length ; i++){
                $("<option></option>").attr("value",depts[i].id).text(depts[i].name).appendTo("#dept_select");
                $("<option></option>").text(depts[i].name).appendTo("#dept_select1");
            }
        }
    })

    var rightTableData =[];
    $.ajax({
        url:"${APP_PATH}/meetingTeam/getMeetingTeamRight",
        data:{teamId:window.location.toString().split('=')[1]},
        success:function (res) {
            // console.log("meetingId:"+$("#meetingId").text());
            if(res.code == 100){
                rightTableData = res.extend.userInfoReturnRight
            }
        }
    })
    layui.use(['form','element','laydate','table','layer','transfer'], function(){
        var element = layui.element;
        var laydate = layui.laydate;
        var form = layui.form;
        var table = layui.table;
        var transfer = layui.transfer
        var layer = layui.layer

        //日期
        laydate.render({
            type:'datetime'
            ,elem: '#date2'
        });
        laydate.render({
            type:'datetime'
            ,elem: '#date1'
        });


        table.render({//因为leftTable需要用到rightTable中的数据所以需要先创建rightTable
            elem: '#rightTable'
            ,id:"rightTable"

            ,height: 450 //容器高度
            ,width:540
            ,page:false
            ,cols: [[{type: 'checkbox', fixed: 'left'}
                ,{field: 'id', title: 'ID',style:'display:none'}
                ,{field: 'username', title: '工号', width: 122}
                ,{field: 'name', title: '姓名',width:120}
                ,{field: 'departName', title: '部门名称',width:245}

            ]]

        });

        table.reload("rightTable", {
            data : rightTableData
        });
        table.render({
            elem: '#leftTable'
            ,id: 'leftTable'//表格名命名
            ,url:"${APP_PATH}/userInfo/findAllByExample"
            ,height: 472 //容器高度
            ,width:540
            ,limit:10
            ,page: { //支持传入 laypage 组件的所有参数（某些参数除外，如：jump/elem） - 详见文档
                layout: ['count', 'prev', 'page', 'next', 'skip'] //自定义分页布局
            }
            ,cols: [[{type: 'checkbox', fixed: 'left'}
                ,{field: 'id', title: 'ID',style:'display:none'}
                ,{field: 'username', title: '工号', width: 122}
                ,{field: 'name', title: '姓名',width:120}
                ,{field: 'departName', title: '部门名称',width:245}
            ]]
            ,parseData:function(res){
                var leftTableData = res.extend.userInfoReturn.list;
                var rightTableData = layui.table.cache.rightTable;
                for (var i =0 ; i<leftTableData.length ; i++){
                    for(var j = 0 ; j<rightTableData.length; j++){
                        if(leftTableData[i].id==rightTableData[j].id){//rightTableData中已有数据
                            leftTableData[i].LAY_CHECKED=true;//将复选框置为选中状态
                            leftTableData[i].flag = 1;//并标记为1，表示是因为rightTableData中已有数据而选中
                        }
                    }
                }
                return {
                    "code": res.code == 100 ? 0: 1, //解析接口状态
                    "msg": res.msg, //解析提示文本
                    "count": res.extend.userInfoReturn.total, //解析数据长度
                    "data": leftTableData //数据列表
                };
            }
        });

        var lay$ = layui.$,active = {
            reload: function(){
                //执行重载
                table.reload('leftTable', {
                    page: {curr: 1} //重新从第 1 页开始
                    ,where: {
                        username:lay$('#usernameIpt').val(),
                        name:lay$("#nameIpt").val(),
                        departName:lay$("#dept_select1").val()
                    }
                }, 'data');
                $("[data-field='id']").css('display','none');
            }
        };

        $('#searchBtn').on('click', function(){
            var type = $(this).data('type');
            type='reload';
            active[type] ? active[type].call(this) : '';
        });

        $('#selectAddBtn').on('click', function(){
            var leftTableData = layui.table.cache.leftTable;//获取左表数据
            var rightTableData = layui.table.cache.rightTable;//获取右表数据
            for(var i = 0 ;i<leftTableData.length ; i++){
                if(leftTableData[i].LAY_CHECKED==true && leftTableData[i].flag != 1){//将左表新选中的数据添加至右表
                    rightTableData.push(leftTableData[i]);//将数据添加至右表
                    leftTableData[i].flag=1;//标记为右表已有数据
                    rightTableData[rightTableData.length-1].LAY_CHECKED=false;//添加时的数据为选中状态所以重置
                }else if(leftTableData[i].LAY_CHECKED!=true && leftTableData[i].flag == 1){//右表已有但是左表复选框未选择，则除掉右表的数据
                    for(var j = 0 ; j<rightTableData.length ;j++){
                        if(rightTableData[j].id == leftTableData[i].id){
                            rightTableData.splice(j,1);//删除
                            leftTableData[i].flag=0;//去除标记
                            break;
                        }
                    }
                }
            }
            table.reload("leftTable", {
                data : leftTableData
            })

            table.reload("rightTable", {
                data : rightTableData
            })
            $("[data-field='id']").css('display','none');
        });

        $('#selectDelBtn').on('click',function () {
            var leftTableData = layui.table.cache.leftTable;//获取左表数据
            var rightTableData = layui.table.cache.rightTable;//获取右表数据
            for(var i = rightTableData.length-1 ; i>=0 ; i--){
                if(rightTableData[i].LAY_CHECKED == true){//复选框选中
                    for(var j = 0 ; j<leftTableData.length ;j++){
                        if(leftTableData[j].id == rightTableData[i].id){
                            //重置右表数据
                            leftTableData[j].flag=0;
                            leftTableData[j].LAY_CHECKED=false;
                        }
                    }
                    rightTableData.splice(i,1);//删除左表数据
                }
            }

            table.reload("leftTable", {
                data : leftTableData
            })
            table.reload("rightTable", {
                data : rightTableData
            })
            $("[data-field='id']").css('display','none');
        });
        $("[data-field='id']").css('display','none');
    });
</script>
</html>
