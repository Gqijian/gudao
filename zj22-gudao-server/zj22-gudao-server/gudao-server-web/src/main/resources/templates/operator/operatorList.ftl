<html lang="en">
<#include "../common/header.ftl">
<style type="text/css">

    .searchbox {
        margin: -3px;
    }

    . * {
        margin: 0;
        padding: 0;
    }
</style>

<script type="text/javascript">
    $(function () {

        $('#dg').datagrid({
            url: '/gudao/seller/operator/list.action',
            fitColumns: true,
            nowrapL: true,
            idField: 'operatorId',
            fit:true,//底部分页
            rownumbers: true,//显示行号
            autoRowHeight:false,//定义是否设置基于该行内容的行高度
            pagePosition:"bottom",
            pagination: true,
            pageSize: 5,
            pageList: [2, 5, 10, 20],
            loadMsg: '数据加载中...',

            onLoadError: function () {
                layer.msg("没有查询到记录！");
            },

            queryParams: {
                KeyWord: '%%'
            },


            toolbar: [{
                iconCls: 'icon-add',
                text: '添加后台用户',
                handler: function () {
                    parent.$('#win').window({
                        title: '添加后台用户',
                        width: 570,
                        height: 320,
                        modal: true,
                        content: "<iframe scrolling='auto' src='/gudao/seller/role/findRole.action' height='100%' width='100%' frameborder='0' ></iframe>"
                    });//src='/gudao/base/goURL/operator/insert.action'
                }
            }, '-', {
                iconCls: 'icon-tip',
                text: '冻结该数据',
                handler: function () {
                    var array = $('#dg').datagrid("getSelections");
                    if (array.length > 0) {
                        var ids = new Array();
                        var av = new Array();
                        for (var i = 0; i < array.length; i++) {
                            av[i] = array[i].available;
                            if (av[i] == 0) {
                                $.messager.alert('提示','该账户已经冻结过!','info');
                                //alert("该账户已经冻结过");
                                return false;
                            }
                            ids[i] = array[i].operatorId;
                        }

                        parent.$.messager.confirm('对话框', '您确认想要冻结账号吗？', function (r) {
                            if (r) {
                                $.ajax({
                                    url: "/gudao/seller/operator/freeze.action",
                                    type: "POST",
                                    //设置为传统方式传送参数
                                    traditional: true,
                                    data: {pks: ids},
                                    success: function (html) {
                                        //异步检验返回的i值即html
                                        if (html > 0) {
                                            $.messager.alert('提示','恭喜您 ，冻结成功!','info');
                                            //alert("恭喜您 ，冻结成功");
                                        } else {
                                            $.messager.alert('提示','对不起 ，冻结失败!','info');
                                            //alert("对不起 ，冻结失败");
                                        }
                                        $("#dg").datagrid("reload");
                                        $("#dg").datagrid("clearSelections");
                                    },
                                    error: function (XMLHttpRequest, textStatus, errorThrown) {
                                        $.messager.alert('冻结错误', '请联系管理员！', 'error');
                                    },
                                    dataType: 'json'
                                });
                            }
                        });

                    } else {
                        $.messager.alert('提示','请选择要冻结的账号!','info');
                        //alert("请选择要冻结的账号!");
                    }


                }
            }, '-', {
                iconCls: 'icon-undo',
                text: '解冻该数据',
                handler: function () {
                    var array = $('#dg').datagrid("getSelections");
                    if (array.length > 0) {
                        var ids = new Array();
                        var av = new Array();
                        for (var i = 0; i < array.length; i++) {
                            av[i] = array[i].available;
                            if (av[i] == 1) {
                                $.messager.alert('提示','该账户已经解冻过!','info');
                                //alert("该账户已经解冻过");
                                return false;
                            }
                            var ids = new Array();
                            for (var i = 0; i < array.length; i++) {
                                ids[i] = array[i].operatorId;
                            }

                            parent.$.messager.confirm('对话框', '您确认想要解冻账号吗？', function (r) {
                                if (r) {
                                    $.ajax({
                                        url: "/gudao/seller/operator/nofreeze.action",
                                        type: "POST",
                                        //设置为传统方式传送参数
                                        traditional: true,
                                        data: {pks: ids},
                                        success: function (html) {
                                            //异步检验返回的i值即html
                                            if (html > 0) {
                                                $.messager.alert('提示','恭喜您 ，解冻成功!','info');
                                                a//lert("恭喜您 ，解冻成功");
                                            } else {
                                                $.messager.alert('提示','对不起 ，解冻失败!','info');
                                                //alert("对不起 ，解冻失败");
                                            }
                                            $("#dg").datagrid("reload");
                                            $("#dg").datagrid("clearSelections");
                                        },
                                        error: function (XMLHttpRequest, textStatus, errorThrown) {
                                            $.messager.alert('解冻错误', '请联系管理员！', 'error');
                                        },
                                        dataType: 'json'
                                    });
                                }
                            });

                        }
                    }
                    else
                        {
                            $.messager.alert('提示','请选择要解冻的账号!','info');
                            //alert("请选择要解冻的账号!");
                        }
                    }
                 }, '-',{
                iconCls: 'icon-user',
                text: '查看已有权限',
                handler: function () {
                    var array = $('#dg').datagrid("getSelections");
                    if (array.length == 1) {
                        var id = new Array();
                        id[0] = array[0].roleId;
                        $("#dlg2").dialog("open").dialog("setTitle", "添加角色权限");
                        var url = "/gudao/seller/operator/findPower.action?roleId=" + id;
                        $("#tree").tree({
                            lines: true,
                            url: url,
                            checkbox: false,
                            cascadeCheck:true,//即级联勾选
                            onLoadSuccess: function (node, data) {
                                $("#tree").tree('expandAll');
                                /*if (data.code == 0 || data.code == undefined){

                                }else {
                                    $.messager.alert('系统提示',data.message);
                                }*/
                            },
                            onCheck: function (node, checked) {
                                if (checked) {
                                    checkNode($('#tree').tree('getParent', node.target), 'getParent');
                                }
                            }
                        });

                    } else {
                        $.messager.alert('提示','请选择一条记录添加!','info');
                        //alert("请选择一条记录添加");
                    }


                }
            },
// '-', {
//                iconCls: 'icon-user_add',
//                text: '添加未有权限',
//                handler: function () {
//                    var array = $('#dg').datagrid("getSelections");
//                    if (array.length == 1) {
//                        var id = new Array();
//                        id[0] = array[0].roleId;
//                        $("#dlg2").dialog("open").dialog("setTitle", "添加角色权限");
//                        var url = "/gudao/seller/operator/addPower.action?roleId=" + id;
//                        $("#tree").tree({
//                            lines: true,
//                            url: url,
//                            checkbox: true,
//                            cascadeCheck: false,
//                            onLoadSuccess: function (node, data) {
//                                $("#tree").tree('expandAll');
//                                /*if (data.code == 0 || data.code == undefined){
//
//                                }else {
//                                    $.messager.alert('系统提示',data.message);
//                                }*/
//                            },
//                            onCheck: function (node, checked) {
//                                if (checked) {
//                                    checkNode($('#tree').tree('getParent', node.target), 'getParent');
//                                }
//                            }
//                        });
//
//                    } else {
//                        alert("请选择一条记录添加");
//                    }
//
//
//                }
//            },
                '-', {
                iconCls: 'icon-remove',
                text: '删除',
                handler: function () {
                    var array = $('#dg').datagrid("getSelections");
                    if (array.length > 0) {
                        var ids = new Array();
                        for (var i = 0; i < array.length; i++) {
                            ids[i] = array[i].operatorId;
                        }

                        parent.$.messager.confirm('删除对话框', '您确认想要删除记录吗？', function (r) {
                            if (r) {
                                $.ajax({
                                    url: "/gudao/seller/operator/delete.action",
                                    type: "POST",
                                    //设置为传统方式传送参数
                                    traditional: true,
                                    data: {pks: ids},
                                    success: function (html) {
                                        //异步检验返回的i值即html
                                        if (html > 0) {
                                            $.messager.alert('提示',"恭喜您 ，删除成功，共删除了" + html + "条记录",'info');
                                        } else {
                                            $.messager.alert('提示','对不起 ，删除失败','info');
                                        }
                                        $("#dg").datagrid("reload");
                                        $("#dg").datagrid("clearSelections");
                                    },
                                    error: function (XMLHttpRequest, textStatus, errorThrown) {
                                        $.messager.alert('删除错误', '请联系管理员！', 'error');
                                    },
                                    dataType: 'json'
                                });
                            }
                        });

                    } else {
                        $.messager.alert('提示','请选择要删除的角色!','info');
                    }

                }
            }, '-', {
                text: "名称：<input type='text' id='sName' name='sName'/>"
            }],

            rowStyler: function (index, row) {//不可用，冻结
                if (row.available == 0) {
                    return 'background-color:#6293ff;color:#fff;font-weight:bold;readonly:true';
                }
            },


            columns: [[
                {
                    field: 'ck',
                    checkbox: true,
                }, {
                    field: 'operatorName',
                    title: '操作者',
                    align: 'center',
                    width: 100,
                    formatter: function (value) {
                        return "<span title='" + value + "'>" + value + "</span>";
                    }
                }, {
                    field: 'realName',
                    title: '真实姓名',
                    align: 'center',
                    width: 100,
                    formatter: function (value) {
                        return "<span title='" + value + "'>" + value + "</span>";
                    }
                }, {
                    field: 'sex',
                    title: '性别',
                    align: 'center',
                    width: 50,
                    formatter: function (value) {
                        if (value == 1)
                            return "男";
                        else
                            return "女";
                    }
                }, {
                    field: 'cellPhoneNum',
                    title: '联系方式',
                    align: 'center',
                    width: 100

                }, {
                    field: 'qq',
                    title: 'qq号',
                    align: 'center',
                    width: 100
                }, {
                    field: 'creatUser',
                    title: '创建人',
                    align: 'center',
                    width: 50,
                }, {
                    field: 'updateUser',
                    title: '修改人',
                    align: 'center',
                    width: 50,
                }]]

        });

        $('#sName').searchbox({
            searcher: function (value, name) {
                $('#dg').datagrid('load', {
                    keyWord: '%' + value + '%'
                });
            },
            prompt: '输入真实名字查询'
        });

    });




</script>

<body>
<table id="dg"></table>


<!--添加角色权限 start -->
<div id="dlg2" class="easyui-dialog" style="width: 300px;height: 450px;padding: 10px 20px"
     closed="true" buttons="#dlg2-buttons">
    <ul class="easyui-tree">
        <li  data-options="iconCls:'icon-home'">
            <span>后台管理系统</span>
        </li>
    </ul>
    <ul id="tree" class="easyui-tree"></ul>
</div>
<div id="dlg2-buttons">
    <a href="javascript:saveTree()" class="easyui-linkbutton" iconCls="icon-ok">确定</a>
    <a href="javascript:closeTreeDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
</div>
<!--添加角色权限 end-->

<!--查看角色权限 start-->
<div id="dlg3" class="easyui-dialog" style="width: 300px;height: 450px;padding: 10px 20px"
     closed="true" buttons="#dlg3-buttons">
    <ul class="easyui-tree">
        <li  data-options="iconCls:'icon-home'">
            <span>后台管理系统</span>
        </li>
    </ul>
    <ul id="tree3" class="easyui-tree"></ul>
</div>
<div id="dlg3-buttons">
    <a href="javascript:closeDialog3()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
</div>
<!--查看角色权限 end-->


</body>

</html>
