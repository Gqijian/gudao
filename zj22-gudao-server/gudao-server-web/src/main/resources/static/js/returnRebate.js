/**
 * Created by Administrator on 2018/2/18 0018.
 */
/* 返点比例数据删除操作*/
function deleteDialog(delectUrl){
    var selectedRows=$("#dg").datagrid('getSelections');
    if(selectedRows.length==0){
        $.messager.alert('系统提示','请选择要删除的数据！');
        return;
    } else if(selectedRows.length > 1){
        $.messager.alert('系统提示','重要数据！只允许单条操作！');
        return;
    } else if(selectedRows[0].rebate_id == 1){
        $.messager.alert('系统提示','正在使用数据不支持该操作！，请禁用后在操作');
        return;
    }
    $.messager.confirm("系统提示","您确认要删除 <font color=red>"+selectedRows[0].rebate_ratio+"</font> 返点比例吗？",function(r){
        if(r){
            $.post(delectUrl,{id:selectedRows[0].rebate_id},function(result){
                if(result.code == 0){
                    $.messager.alert('系统提示',"您已成功删除该条数据！");
                    $("#dg").datagrid("reload");
                }else{
                    $.messager.alert('系统提示',result.message);
                }
            },"json");
        }
    });
}
/*开启、关闭返点比例*/
function onOrOffRebate (chooseUrl) {
    var onId,offId;/*关闭id、开启Id*/
    var selectedRows=$("#dg").datagrid('getSelections');
    if(selectedRows.length==0){
        $.messager.alert('系统提示','请选择一条要选择的数据！');
        return;
    }
    offId = selectedRows[0].rebate_id;
    var allDate = $("#dg").datagrid('getData');
    $.each(allDate,function (i,data) {
        if (data.available == 1) {
            onId = data.rebate_id;
        }
    });
    $.messager.confirm("系统提示","您确认要启用这个比例",function (r) {
        if (r) {
            $.post(chooseUrl,{onId: onId,offId: offId},function (result) {
                if (result.code == 0){
                    $.message.alert("系统提示","修改成功");
                    $("#dg").datagrid("reload");
                }else {
                    $.message.alert("系统提示",result.message);
                }
            })
        }
    })

}
/*红包返现*/
function returnCash(returnUrl) {
    var arrIds = [],IdString;
    var selectRows = $("#dg").datagrid("getSelections");
    $.each(selectRows,function (i,data) {
        if (data.flag == 0) {
            arrIds.push(data.user_id);
            IdString = arrIds.join(',');
        }
    });
    $.messager.confirm("系统提示","你确定要返现<font color='red'>"+ selectRows.length()+"</font> ",function (r) {
        if (r) {
            $.post(returnUrl,IdString,function (result) {
                if (result.code == 0) {
                    $.messager.alert("系统提示","体现成功");
                    $("#dg").datagrid("reload");
                }else {
                    $.message.alert("系统提示",result.message);
                }
            })
        }
    })

}