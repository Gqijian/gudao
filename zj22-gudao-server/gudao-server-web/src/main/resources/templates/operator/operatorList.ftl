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
            view: detailview, //注意点
            idField: 'operatorId',
            rownumbers: true,//显示行号
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
                        title: '添加角色',
                        width: 570,
                        height: 450,
                        modal: true,
                        content: "<iframe scrolling='auto' src='/gudao/base/goURL/operator/insert.action' height='100%' width='100%' frameborder='0' ></iframe>"
                    });
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
                                alert("该账户已经冻结过");
                                return false;
                            }
                            ids[i] = array[i].operatorId;
                            alert(ids[i]);
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
                                            alert("恭喜您 ，冻结成功");
                                        } else {
                                            alert("对不起 ，冻结失败");
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
                        alert("请选择要冻结的账号!");
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
                                alert("该账户已经解冻过");
                                return false;
                            }
                            var ids = new Array();
                            for (var i = 0; i < array.length; i++) {
                                ids[i] = array[i].operatorId;
                                alert(ids[i]);
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
                                                alert("恭喜您 ，解冻成功");
                                            } else {
                                                alert("对不起 ，解冻失败");
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
                            alert("请选择要解冻的账号!");
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
                            cascadeCheck: false,
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
                        alert("请选择一条记录添加");
                    }


                }
            }, '-', {
                iconCls: 'icon-user_add',
                text: '添加未有权限',
                handler: function () {
                    var array = $('#dg').datagrid("getSelections");
                    if (array.length == 1) {
                        var id = new Array();
                        id[0] = array[0].roleId;
                        $("#dlg2").dialog("open").dialog("setTitle", "添加角色权限");
                        var url = "/gudao/seller/operator/addPower.action?roleId=" + id;
                        $("#tree").tree({
                            lines: true,
                            url: url,
                            checkbox: true,
                            cascadeCheck: false,
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
                        alert("请选择一条记录添加");
                    }


                }
            }, '-', {
                iconCls: 'icon-remove',
                text: '删除',
                handler: function () {
                    var array = $('#dg').datagrid("getSelections");
                    if (array.length > 0) {
                        var ids = new Array();
                        for (var i = 0; i < array.length; i++) {
                            ids[i] = array[i].operatorId;
                            alert(ids[i]);
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
                                            alert("恭喜您 ，删除成功，共删除了" + html + "条记录");
                                        } else {
                                            alert("对不起 ，删除失败");
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
                        alert("请选择要删除的角色!");
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
                },
                {
                    field: 'password',
                    title: '密码',
                    align: 'center',
                    width: 100
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
                }, {
                    field: 'createTime',
                    title: '创建时间',
                    align: 'center',
                    width: 100,
                    formatter: function (value) {
                        if (value) {
                            return "<span title='" + new Date(parseInt(value)).toLocaleString() + "'>" + new Date(parseInt(value)).toLocaleString() + "</span>";
                        } else {
                            return '';
                        }
                    }
                }, {
                    field: 'updateTime',
                    title: '修改时间',
                    align: 'center',
                    width: 100,
                    formatter: function (value) {
                        if (value) {
                            return "<span title='" + new Date(parseInt(value)).toLocaleString() + "'>" + new Date(parseInt(value)).toLocaleString() + "</span>";
                        } else {
                            return '';
                        }
                    }
                }]],

            detailFormatter: function (index, row) {//注意2
                return '<div style="padding:2px"><table id="ddv-' + index + '"></table></div>';
            },
            onExpandRow: function (index, row) {//注意3
                $('#ddv-' + index).datagrid({
                    url: '/gudao/seller/operation/detail.action?roleId=' + (row.roleId),
                    fitColumns: true,
//                    singleSelect:true,
                    idField: 'opId',
                    rownumbers: true,//显示行号
                    height: 'auto',


                    toolbar: [{
                        iconCls: 'icon-remove',
                        text: '权限删除',
                        handler: function () {
                            var array = $('#ddv-' + index).datagrid("getSelections");
                            if (array.length > 0) {
                                var ids = new Array();
                                for (var i = 0; i < array.length; i++) {
                                    ids[i] = array[i].opId;
                                    alert(ids[i]);
                                }

                                parent.$.messager.confirm('删除对话框', '您确认想要删除记录吗？', function (r) {
                                    if (r) {
                                        $.ajax({
                                            url: "/gudao/seller/operation/delete.action",
                                            type: "POST",
                                            //设置为传统方式传送参数
                                            traditional: true,
                                            data: {pks: ids, roleId: row.roleId},
                                            success: function (html) {
                                                //异步检验返回的i值即html
                                                if (html > 0) {
                                                    alert("恭喜您 ，删除成功，共删除了" + html + "条记录");
                                                } else {
                                                    alert("对不起 ，删除失败");
                                                }
                                                $('#ddv-' + index).datagrid("reload");
                                                $('#ddv-' + index).datagrid("clearSelections");
                                            },
                                            error: function (XMLHttpRequest, textStatus, errorThrown) {
                                                $.messager.alert('删除错误', '请联系管理员！', 'error');
                                            },
                                            dataType: 'json'
                                        });
                                    }
                                });

                            } else {
                                alert("请选择要取消的用户权限!");
                            }

                        }
                    }],

                    rowStyler: function (index, row) {
                        if (row.opType == "menu") {
                            return 'background-color:#6293ff;color:#fff;font-weight:bold;disable:true';
                        }
                    },

                    columns: [[{
                        field: 'ck',
                        checkbox: true,
                    },
                        {
                            field: 'opName',
                            width: 100,
                            align: 'center',
                            title: '操作名称'
                        },
                        {
                            field: 'opHref',
                            title: '操作路径',
                            width: 100
                        },
                        {
                            field: 'opType',
                            title: '操作类型',
                            align: 'center',
                            width: 100
                        }
                    ]],

                    onResize: function () {
                        $('#dg').datagrid('fixDetailRowHeight', index);
                    },

                    onLoadSuccess: function () {
                        setTimeout(function () {
                            $('#dg').datagrid('fixDetailRowHeight', index);
                        }, 0);
                    }
                });

                $('#dg').datagrid('fixDetailRowHeight', index);
            },

        });

        $('#sName').searchbox({
            searcher: function (value, name) {
                alert(value + "," + name);
                $('#dg').datagrid('load', {
                    keyWord: '%' + value + '%'
                });
            },
            prompt: '输入真实名字查询'
        });

    });



    function saveTree(){
        var array = $('#dg').datagrid("getSelections");

        var nodes=$('#tree').tree('getChecked');
        var ids = new Array();
//alert(array[0].roleId);
        //var authArrIds=[];
        for(var i=0;i<nodes.length;i++){
            ids[i]=nodes[i].id;
            alert(ids[i]);

            //authArrIds.push(nodes[i].id);
        }
        //var authIds=authArrIds.join(",");
        parent.$.messager.confirm('对话框', '您确认想要添加权限吗？', function (r) {
            if (r) {
                $.ajax({
                    url: "/gudao/seller/operation/addRolePermission.action",
                    type: "POST",
                    //设置为传统方式传送参数
                    traditional: true,
                    data: {pks: ids, roleId: array[0].roleId},
                    success: function (html) {
                        //异步检验返回的i值即html
                        if (html > 0) {
                            alert("恭喜您 ，添加成功");
                        } else {
                            alert("对不起 ，添加失败");
                        }
                        $("#dg").datagrid("reload");
                        $("#dg").datagrid("clearSelections");
                    },
                    error: function (XMLHttpRequest, textStatus, errorThrown) {
                        $.messager.alert('添加错误', '请联系管理员！', 'error');
                    },
                    dataType: 'json'
                });
            }
        });
    }



</script>

<body>
<table id="dg"></table>


<!--添加角色权限 start -->
<div id="dlg2" class="easyui-dialog" style="width: 300px;height: 450px;padding: 10px 20px"
     closed="true" buttons="#dlg2-buttons">
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
    <ul id="tree3" class="easyui-tree"></ul>
</div>
<div id="dlg3-buttons">
    <a href="javascript:closeDialog3()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
</div>
<!--查看角色权限 end-->


</body>

</html>
