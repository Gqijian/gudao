<html lang="en">
<head>
    <link rel="stylesheet" type="text/css" href="/gudao/lib/jquery-easyui-1.5/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="/gudao/lib/jquery-easyui-1.5/themes/icon.css">
    <script type="text/javascript" src="/gudao/lib/jquery-easyui-1.5/jquery.min.js"></script>
    <script type="text/javascript" src="/gudao/lib/jquery-easyui-1.5/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="/gudao/lib/jquery-easyui-1.5/datagrid-detailview.js"></script>
    <script type="text/javascript" src="/gudao/lib/jquery-easyui-1.5/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="/gudao/js/baseJS.js"></script>
    <script type="text/javascript" src="/gudao/js/goods.js"></script>

    <title>订单列表信息</title>
</head>
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
                idField:'orderId',
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
                                alert(ids[i]);
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
                                                alert("恭喜您 ，取消成功，共取消了"+html+"单");
                                            }else{
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
                            alert("请选择要取消的订单!");
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
                                alert(ids[i]);
                            }

                            parent.$.messager.confirm('对话框','您确认想要接单吗？',function(r){
                                if (r){
                                    $.ajax({
                                        url: "/gudao/seller/order/finish.action",
                                        type:"POST",
                                        //设置为传统方式传送参数
                                        traditional:true,
                                        data:{pks:ids},
                                        success:function(html){
                                            //异步检验返回的i值即html
                                            if(html>0){
                                                alert("恭喜您 ，接单成功，共"+html+"单");
                                            }else{
                                                alert("对不起 ，接单失败");
                                            }
                                            $("#dg").datagrid("reload");
                                            $("#dg").datagrid("clearSelections");
                                        },
                                        error: function (XMLHttpRequest, textStatus, errorThrown) {
                                            $.messager.alert('接单错误','请联系管理员！','error');
                                        },
                                        dataType:'json'
                                    });
                                }
                            });

                        }else{
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
                                width : 100
                            },
                            {field:'productId',
                                title:'商品id',
                                align:'center',
                                width:100
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
</body>

</html>
