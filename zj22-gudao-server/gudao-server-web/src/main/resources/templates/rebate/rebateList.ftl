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

    <title>返点列表</title>
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
                url: '/gudao/seller/rebate/list.action',
                fitColumns:true,
                nowrapL:true,
                idField:'rebateId',
                rownumbers:true,//显示行号
                pagination:true,
                pageSize:10,
                pageList:[2,5,10,20],
                loadMsg:'数据加载中...',

                onLoadError: function () {
                    alert("没有查询到记录！");
                },

                queryParams: {
                    KeyWord: '%%'
                },


                toolbar: [{
                    iconCls: 'icon-add',
                    text:'新增',
                    handler: function(){
                        parent.$('#win').window({
                            title:'添加返点',
                            width:500,
                            height:150,
                            modal:true,
                            content:"<iframe src='/gudao/base/goURL/rebate/insert.action' height='100%' width='100%' frameborder='0px' ></iframe>"
                        });
                    }
                },'-',{
                    iconCls: 'icon-edit',
                    text:'修改',
                    handler: function(){
                        alert('修改按钮');
                        var array = $('#dg').datagrid("getSelections");
                        if(array.length!=1){
                            alert("请选择需要修改的记录，并且只能选中一条！");
                            return false;
                        };
                        parent.$('#win').window({
                            title:'修改返点率',
                            width:500,
                            height:300,
                            modal:true,
                            content:"<iframe src='/gudao/base/goURL/rebate/update.action' height='100%' width='100%' frameborder='0px' ></iframe>"
                        });

                    }
                },'-',{
                    iconCls: 'icon-remove',
                    text:'删除',
                    handler: function(){
                        var array = $('#dg').datagrid("getSelections");
                        if(array.length>0){
                            var ids = new Array();
                            for(var i=0; i<array.length; i++){
                                ids[i]=array[i].rebateId;
                                alert(ids[i]);
                            }

                            parent.$.messager.confirm('删除对话框','您确认想要删除记录吗？',function(r){
                                if (r){
                                    alert('确认删除');
                                    $.ajax({
                                        url: "/gudao/seller/rebate/delete.action",
                                        type:"POST",
                                        //设置为传统方式传送参数
                                        traditional:true,
                                        data:{pks:ids},
                                        success:function(html){
                                            //异步检验返回的i值即html
                                            if(html>0){
                                                alert("恭喜您 ，删除成功，共删除了"+html+"条记录");
                                            }else{
                                                alert("对不起 ，删除失败");
                                            }
                                            $("#dg").datagrid("reload");
                                            $("#dg").datagrid("clearSelections");
                                        },
                                        error: function (XMLHttpRequest, textStatus, errorThrown) {
                                            $.messager.alert('删除错误','请联系管理员！','error');
                                        },
                                        dataType:'json'
                                    });
                                }
                            });

                        }else{
                            alert("请选择要删除的折扣!");
                        }

                    }
                }],


                columns : [ [
                    {
                        field:'ck',
                        checkbox:true,
                    }, {
                        field : 'rebateId',
                        title : '折扣id',
                        align : 'center',
                        width : 100,
                        formatter: function(value){
                            return "<span title='" + value + "'>" + value + "</span>";
                        }
                    },{
                        field : 'rebateRatio',
                        title : '返点比例',
                        align : 'center',
                        width : 50
                    },{
                        field : 'productPrice',
                        title : '是否可用',
                        align : 'center',
                        width : 50,
                        formatter: function(value){
                            if(value==0){
                                return "可用";
                            }else{
                                return "不可用"
                            }
                        }
                    }, {
                        field : 'createUser',
                        title : '创建人',
                        align : 'center',
                        width : 50,
                    }, {
                        field : 'updateUser',
                        title : '修改人',
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
                        field : 'updateTime',
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



            });

        });


    </script>

<body>
<table id="dg"></table>
</body>

</html>
