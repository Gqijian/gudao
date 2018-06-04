
/*删除用户*/
function deleteOperatorDialog(deleteUrl){
    var arrId = [];
    var ids;
    var selectedRows=$("#dg").datagrid('getSelections');
    if(selectedRows.length==0){
        $.messager.alert('系统提示','请选择要删除的数据！','warning');
        return;
    }
    $.each(selectedRows,function (i,data) {

        if(data.operator_id == 1){
            $.messager.alert('系统提示', '超级管理员禁止操作！','warning');
            return;
        }else {
            arrId.push(data.operator_id);
            ids =arrId.join(",");
        }
    });
    $.messager.confirm("系统提示","您确认要删除这<font color=red>"+selectedRows.length+"</font>条数据吗？",function(r){
        if(r){
            $.post(deleteUrl,{Ids:ids},function(result){
                if(result.code == 0){
                    $.messager.alert('系统提示',"您已成功删除"+selectedRows.length+"条数据！");
                    $("#dg").datagrid("reload");
                }else{
                    $.messager.alert('系统提示',result.message);
                }
            },"json");
        }
    });
}

/*冻结、激活用户*/
function userStatus(statusUrl) {
    var selectedRows=$("#dg").datagrid('getSelections');
    if(selectedRows.length==0){
        $.messager.alert('系统提示','请选择要操作的数据！','warning');
        return;
    } else if(selectedRows.length > 1){
        $.messager.alert('系统提示','重要数据！只允许单条操作！','warning');
        return;
    }else if(selectedRows[0].operatorId == 1){
        $.messager.alert('系统提示','超级管理员禁止操作！','warning');
        return;
    }
    $.messager.confirm("系统提示","您确认要对 <font color=red>"+selectedRows[0].real_name+"</font> 进行操作吗？",function(r){
        if(r){
            $.post(statusUrl,{id:selectedRows[0].operator_id,use:selectedRows[0].available},function(result){
                if(result.code == 0){
                    $.messager.show({title:'系统提示',msg:'操作成功！', timeout:1000, showType:'slide',style: {right:'', bottom:''}});
                    $("#dg").datagrid("reload");
                }else{
                    $.messager.alert('系统提示',result.message);
                }
            },"json");
        }
    });
}

/*重置密码*/
function openPassDialog(passUrl) {
    var selectedRows = $("#dg").datagrid('getSelections');
    if (selectedRows.length != 1) {
        $.messager.alert('系统提示', '请选择一条要编辑的数据！','warning');
        return;
    }else if(selectedRows[0].operator_id == 1){
        $.messager.alert('系统提示','超级管理员禁止操作！','warning');
        return;
    }else if(selectedRows[0].available == 1){
        $.messager.alert('系统提示','该账号不可用！','warning');
        return;
    }
    $.post(passUrl,{id:selectedRows[0].operator_id},function(result){
        if(result.code == 0){
            $.messager.show({title:'系统提示',msg:'重置密码成功！', timeout:1000, showType:'slide',style: {right:'', bottom:''}});
            $("#dg").datagrid("reload");
        }else{
            $.messager.alert('系统提示',result.message);
        }
    },"json");
}

function Passsave(){
    $.ajax({
        url:url,
        data:$("#fm2").serialize(),
        dataType:'json',
        type:'post',
        success:function(data){
            if (data.code == 0){
                closePassDialog();
                $.messager.show({title:'系统提示',msg:'保存成功！', timeout:1000, showType:'slide',style: {right:'', bottom:''}});

            }else{
                $.messager.alert('系统提示',"<font color=red>"+data.message+"</font>");
                return;
            }
        }
    })
}

function closePassDialog(){
    $("#dlg2").dialog("close");
    $("#fm2").form('clear');
}


