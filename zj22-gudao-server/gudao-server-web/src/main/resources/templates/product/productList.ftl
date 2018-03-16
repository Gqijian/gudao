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
                url: '/gudao/seller/product/list.action',
                fitColumns:true,
                nowrapL:true,
                idField:'productId',
                rownumbers:true,//显示行号
                pagination:true,
                pageSize:10,
                pageList:[2,5,10,20],
                loadMsg:'数据加载中...',

                onLoadError: function () {
                    layer.msg("没有查询到记录！");
                },

                queryParams: {
                    KeyWord: '%%'
                },


                toolbar: [{
                    iconCls: 'icon-add',
                    text:'上架',
                    handler: function(){
                        var array = $('#dg').datagrid("getSelections");
                        if(array.length==1){
                            var id = new Array();
                            id[0] = array[0].productId;

                            parent.$.messager.confirm('上架对话框','您确认想要上架商品吗？',function(r){
                                if (r){
                                    $.ajax({
                                        url: "/gudao/seller/product/on_sale.action",
                                        type:"POST",
                                        //设置为传统方式传送参数
                                        traditional:true,
                                        data:{productId:id},
                                        success:function(html){
                                            //异步检验返回的i值即html
                                            if(html>0){
                                                alert("恭喜您 ，操作成功");
                                            }else{
                                                alert("对不起 ，操作失败");
                                            }
                                            $("#dg").datagrid("reload");
                                            $("#dg").datagrid("clearSelections");
                                        },
                                        error: function (XMLHttpRequest, textStatus, errorThrown) {
                                            $.messager.alert('操作错误','商品已经上架！','error');
                                        },
                                        dataType:'json'
                                    });
                                }
                            });

                        }else{
                            alert("一次只能选择一件商品上架!");
                        }

                    }
                },'-',{
                    iconCls: 'icon-remove',
                    text:'下架',
                    handler: function(){
                        var array = $('#dg').datagrid("getSelections");
                        if(array.length==1){
                            var id = new Array();
                            id[0] = array[0].productId;

                            parent.$.messager.confirm('上架对话框','您确认想要下架商品吗？',function(r){
                                if (r){
                                    alert('确认下架');
                                    $.ajax({
                                        url: "/gudao/seller/product/off_sale.action",
                                        type:"POST",
                                        //设置为传统方式传送参数
                                        traditional:true,
                                        data:{productId:id},
                                        success:function(html){
                                            //异步检验返回的i值即html
                                            if(html>0){
                                                alert("恭喜您 ，操作成功");
                                            }else{
                                                alert("对不起 ，操作失败");
                                            }
                                            $("#dg").datagrid("reload");
                                            $("#dg").datagrid("clearSelections");
                                        },
                                        error: function (XMLHttpRequest, textStatus, errorThrown) {
                                            $.messager.alert('操作错误','商品已经下架！','error');
                                        },
                                        dataType:'json'
                                    });
                                }
                            });

                        }else{
                            alert("一次只能选择一件商品下架!");
                        }

                    }
                },'-',{
                    iconCls: 'icon-add',
                    text:'新增',
                    handler: function(){
                        parent.$('#win').window({
                            title:'添加商品',
                            width:570,
                            height:550,
                            modal:true,
                            content:"<iframe scrolling='auto' src='/gudao/base/goURL/product/insert.action' height='100%' width='100%' frameborder='0' ></iframe>"
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
                            title:'修改商品',
                            width:570,
                            height:560,
                            modal:true,
                            content:"<iframe src='/gudao/base/goURL/product/update.action' height='100%' width='100%' frameborder='0px' ></iframe>"
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
                                ids[i]=array[i].productId;
                                alert(ids[i]);
                            }

                            parent.$.messager.confirm('删除对话框','您确认想要删除记录吗？',function(r){
                                if (r){
                                    $.ajax({
                                        url: "/gudao/seller/product/delete.action",
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
                            alert("请选择要删除的商品!");
                        }

                    }
                },'-',{
                    text:"名称：<input type='text' id='sName' name='sName'/>"
                } ],


                columns : [ [
                    {
                        field:'ck',
                        checkbox:true,
                    }, {
                        field : 'productName',
                        title : '名称',
                        align : 'center',
                        width : 100,
                        formatter: function(value){
                            return "<span title='" + value + "'>" + value + "</span>";
                        }
                    },{
                        field : 'productStatus',
                        title : '商品状态',
                        align : 'center',
                        width : 50,
                        formatter: function(value){
                            if(value==0){
                                return "已上架";
                            }else{
                                return "已下架"
                            }
                        }
                    },{
                        field : 'productPrice',
                        title : '单价',
                        align : 'center',
                        width : 50,
                        editor:'numberbox'
                    },{
                        field : 'cost',
                        title : '成本',
                        align : 'center',
                        width : 50,
                        editor:'numberbox'
                    }, {
                        field : 'weight',
                        title : '重量',
                        align : 'center',
                        width : 50,

                    }, {
                        field : 'productStock',
                        title : '库存',
                        align : 'center',
                        width : 50,
                        editor:'numberbox'
                    }, {
                        field : 'productAbout',
                        title : '简介',
                        align : 'center',
                        width : 100,
                        formatter: function(value){
                            return "<span title='" + value + "'>" + value + "</span>";
                        }

                    }, {
                        field : 'productIconOne',
                        title : '图片',
                        align : 'center',
                        width : 100,
                        formatter:function(value,row){
                            var str = "";
                            if(value!="" || value!=null){
                                str =  "<img style=\"height: 70px;width: 100px;\" src=\""+"/gudao/upload/"+value+"\"/>";
                                return str;
                            }
                        }
                    }, {
                        field : 'creatUser',
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

            $('#sName').searchbox({
                searcher:function(value,name){
                    alert(value + "," + name);
                    $('#dg').datagrid('load',{
                        keyWord:'%'+value+'%'
                    });
                },
                prompt:'搜索商品'
            });

        });


    </script>

<body>
<table id="dg"></table>
</body>

</html>
