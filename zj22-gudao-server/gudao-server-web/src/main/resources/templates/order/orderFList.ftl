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
                url: '/gudao/seller/order/flist.action',
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
