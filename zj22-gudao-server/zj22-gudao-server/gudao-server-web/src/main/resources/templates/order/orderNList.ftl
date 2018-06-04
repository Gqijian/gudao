<html lang="en">
<#include "../common/header.ftl">
    <style type="text/css">

        .searchbox{
            margin:-3px;
        }
        .*{
            margin: 0;
            padding: 0;
        }
    </style>

    <script type="text/javascript">
        $(function(){
            //变量用来支持单元格编辑时使用
            var editIndex = undefined;

            $('#dg').datagrid({
                url: '/gudao/seller/order/nlist.action',
                fitColumns:true,
                view: detailview, //注意点
                nowrapL:true,
                fit:true,
                idField:'orderId',
                singleSelect:true,//单选
                rownumbers:true,//显示行号
                pagination:true,
                pageSize:10,
                pageList:[2,5,10,20],
                loadMsg:'数据加载中...',

                onLoadError: function () {
                    layer.msg("没有查询到记录！");
                },

                queryParams: {
                    keyWord: '%%'
                },


                toolbar: [{
                    iconCls: 'icon-cut',
                    text:'取消订单',
                    handler: function(){
                        var array = $('#dg').datagrid("getSelections");
                        if(array.length>0){
                            var ids = new Array();
                            for(var i=0; i<array.length; i++){
                                ids[i]=array[i].orderId;
                            }

                            parent.$.messager.confirm('对话框','您确认想要取消订单吗？',function(r){
                                if (r){
                                    $.ajax({
                                        url: "/gudao/seller/order/cancel.action",
                                        type:"POST",
                                        //设置为传统方式传送参数
                                        traditional:true,
                                        data:{pks:ids},
                                        success:function(html){
                                            //异步检验返回的i值即html
                                            if(html>0){
                                                $.messager.alert('提示',"恭喜您 ，取消成功，共取消了"+html+"单",'info');
                                                //alert("恭喜您 ，取消成功，共取消了"+html+"单");
                                            }else{
                                                $.messager.alert('提示',"对不起 ，取消失败",'info');
                                                alert("对不起 ，取消失败");
                                            }
                                            $("#dg").datagrid("reload");
                                            $("#dg").datagrid("clearSelections");
                                        },
                                        error: function (XMLHttpRequest, textStatus, errorThrown) {
                                            $.messager.alert('取消错误','请联系管理员！','error');
                                        },
                                        dataType:'json'
                                    });
                                }
                            });

                        }else{
                            $.messager.alert('提示',"请选择要取消的订单!",'info');
                           // alert("请选择要取消的订单!");
                        }

                    }
                },'-',{
                    iconCls: 'icon-add',
                    text:'接单',
                    handler: function(){
                        var array = $('#dg').datagrid("getSelections");
                        if(array.length>0){
                            var ids = new Array();
                            for(var i=0; i<array.length; i++){
                                ids[i]=array[i].orderId;
//                                alert(ids[i]);
                            }

                            parent.$.messager.confirm('对话框','您确认想要接单吗？',function(r){
                                if (r){

                                    $("#dlg4").dialog("open").dialog("setTitle", "添加订单号");

//                                    $.ajax({
//                                        url: "/gudao/seller/order/finish.action",
//                                        type:"POST",
//                                        //设置为传统方式传送参数
//                                        traditional:true,
//                                        data:{pks:ids},
//                                        success:function(html){
//                                            //异步检验返回的i值即html
//                                            if(html>0){
//                                                alert("恭喜您 ，接单成功，共"+html+"单");
//                                            }else{
//                                                alert("对不起 ，接单失败");
//                                            }
//                                            $("#dg").datagrid("reload");
//                                            $("#dg").datagrid("clearSelections");
//                                        },
//                                        error: function (XMLHttpRequest, textStatus, errorThrown) {
//                                            $.messager.alert('接单错误','请联系管理员！','error');
//                                        },
//                                        dataType:'json'
//                                    });
                                }
                            });
                    }
                        else{
                            alert("请选择要接单的订单!");
                        }

                    }
                },'-',{
                    text:"名称：<input type='text' id='receiverName' name='receiverName'/>"
                } ],


                columns : [ [
                    {
                        field:'ck',
                        checkbox:true,
                    }, {
                        field : 'orderId',
                        title : '订单id',
                        align : 'center',
                        width : 100,
                        formatter: function(value){
                            return "<span title='" + value + "'>" + value + "</span>";
                        }
                    },{
                        field : 'receiverName',
                        title : '姓名',
                        align : 'center',
                        width : 50
                    },{
                        field : 'receiverPhone',
                        title : '手机号',
                        align : 'center',
                        width : 50
                    },{
                        field : 'address',
                        title : '地址',
                        align : 'center',
                        width : 100
                    }, {
                        field : 'orderAmount',
                        title : '金额',
                        align : 'center',
                        width : 50,

                    }, {
                        field : 'postage',
                        title : '邮费',
                        align : 'center',
                        width : 50,
                    }, {
                        field : 'createTime',
                        title : '创建时间',
                        align : 'center',
                        width : 100,
                        formatter: function(value){
                            if (value){
                                return "<span title='" + new Date(parseInt(value)).toLocaleString() + "'>" + new Date(parseInt(value)).toLocaleString() + "</span>";
                            }else{
                                return '';
                            }
                        }
                    }, {
                        field : 'operationTime',
                        title : '修改时间',
                        align : 'center',
                        width : 100,
                        formatter: function(value){
                            if (value){
                                return "<span title='" + new Date(parseInt(value)).toLocaleString() + "'>" + new Date(parseInt(value)).toLocaleString() + "</span>";
                            }else{
                                return '';
                            }
                        }
                    }] ],
                detailFormatter:function(index,row){//注意2
                    return '<div style="padding:2px"><table id="ddv-' + index + '"></table></div>';
                },
                onExpandRow:function(index,row){//注意3
                    $('#ddv-'+index).datagrid({
                        url:'/gudao/seller/order/detail.action?orderId='+(row.orderId),
                        fitColumns:true,
                        singleSelect:true,
                        height:'auto',
                        columns:[[
                            {field:'detailId',
                                width : 100,
                                align:'center',
                                title:'订单详情ID'
                            },
                            {field:'orderId',
                                title:'订单id',
                                align:'center',
                                width : 100
                            },
                            {field:'productId',
                                title:'商品id',
                                align:'center',
                                width:100
                            },{
                                field : 'productName',
                                title : '名称',
                                align : 'center',
                                width : 100,
                                formatter: function(value){
                                    return "<span title='" + value + "'>" + value + "</span>";
                                }
                            },
                            {field:'price',
                                align:'center',
                                title:'价格',
                                width:100
                            },
                            {field:'productQuantity',
                                title:'数量',
                                width : 100,
                                align:'center'
                            }
                        ]],
                        onResize:function(){
                            $('#tt').datagrid('fixDetailRowHeight',index);
                        },
                        onLoadSuccess:function(){
                            setTimeout(function(){
                                $('#tt').datagrid('fixDetailRowHeight',index);
                            },0);
                        }
                    });
                    $('#tt').datagrid('fixDetailRowHeight',index);
                },

                onDblClickCell: function(index,field,value){
                    if(editIndex != undefined){
                        $(this).datagrid('endEdit',editIndex);
                        editIndex = undefined;
                    }
                    //设置可编辑行
                    $(this).datagrid('beginEdit', index);
                    editIndex = index;
                    var ed = $(this).datagrid('getEditor', {index:index,field:field});
                    $(ed.target).focus();
                },
                //单击
                onClickCell: function(index,field,value){
                    if(editIndex!= undefined){
                        //结束编辑行
                        $(this).datagrid('endEdit', editIndex);
                        editIndex = undefined;
                    }
                }






            });

            $('#receiverName').searchbox({
                searcher:function(value,name){
                    alert(value + "," + name);
                    $('#dg').datagrid('load',{
                        KeyWord:'%'+value+'%'
                    });
                },
                prompt:'搜索订单用户'
            });

        });


    </script>

<body>
<table id="dg"></table>


<div id="dlg" class="easyui-dialog" title="新订单" style="width:400px;height:200px;padding:10px"
     data-options="iconCls: 'icon-save' ,buttons:'#dlg-buttons',closed:true">
    你有新的订单
</div>
<#---按钮-->
<div id="dlg-buttons">
    <button onclick="javascript:document.getElementById('notice').pause()" type="button" >关闭</button>
    <button onclick="javascrtpt:location.href='/gudao/base/goURL/order/orderNList'" type="button">查看新的订单</button>
</div>




<#--播放音乐-->
<audio id="notice" loop="loop">
    <source src="/gudao/mp3/song.mp3" type="audio/mpeg" />
</audio>

<script>
    var websocket = null;
    if('WebSocket' in window) {
        websocket = new WebSocket('ws://yuanpeng.free.ngrok.cc/gudao/webSocket');
    }else {
        alert('该浏览器不支持websocket!');
    }

    websocket.onopen = function (event) {
        console.log('建立连接');
    }

    websocket.onclose = function (event) {
        console.log('连接关闭');
    }

    websocket.onmessage = function (event) {
        console.log('收到消息:' + event.data)
        //弹窗提醒, 播放音乐
        $('#dlg').dialog('open');
        document.getElementById('notice').play();
    }

    websocket.onerror = function () {
        $.messager.alert('提示',"websocket通信发生错误！",'error');
        //alert('websocket通信发生错误！');
    }

    window.onbeforeunload = function () {
        websocket.close();
    }

    function closeaddTrackNumberDialog(){
        $("#dlg4").dialog("close");
    }

    function addTrackNumber(){
        var array = $('#dg').datagrid("getSelections");
        var trackNumber = $('#trackNumber').val();
        if (array.length == 1) {
            var id = new Array();
            id[0] = array[0].orderId;

            $.ajax({
                url: "/gudao/seller/order/finish.action",
                type: "post",
                //设置为传统方式传送参数
                traditional: true,
                data: {pks: id, trackNumber: trackNumber},
                success: function (html) {
                    //异步检验返回的i值即html
                    if (html > 0) {
                        $("#dlg4").dialog("close");
                        $.messager.alert('提示',"恭喜您 ，接单成功，共" + html + "单",'info');
                        //alert("恭喜您 ，接单成功，共" + html + "单");
                    } else {
                        $.messager.alert('提示',"对不起 ，接单失败",'info');
                        //alert("对不起 ，接单失败");
                    }
                    $("#dg").datagrid("reload");
                    $("#dg").datagrid("clearSelections");
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    $.messager.alert('接单错误', '请联系管理员！', 'error');
                },
                dataType: 'json'
            });
        }

    }

</script>

<div id="dlg4" class="easyui-dialog" style="width: 300px;height: 450px;padding: 10px 20px"
     closed="true" buttons="#dlg4-buttons">
    <label><input type="text" placeholder="输入订单号" id="trackNumber" name="trackNumber"></input></label>
</div>
<div id="dlg4-buttons">
    <a href="javascript:addTrackNumber()" class="easyui-linkbutton" iconCls="icon-ok">确定</a>
    <a href="javascript:closeaddTrackNumberDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
</div>

</body>

</html>
