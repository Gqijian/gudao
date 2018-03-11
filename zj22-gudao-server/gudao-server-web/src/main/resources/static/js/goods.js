/*
function saveGoods() {
    $("#fm").form("submit", {
        url: url,
        onSubmit: function () {
            return $(this).form("validate");
        },
        success:function(data){
            var data=eval('('+data+')');
            if (data.code == 0){
                $.messager.show({title:'系统提示',msg:'保存成功！', timeout:1000, showType:'slide',style: {right:'', bottom:''}});
                closeSaveDialog();
                $("#dg").datagrid("reload");
            }else{
                $.messager.alert('系统提示',"<font color=red>"+data.message+"</font>");
                return;
            }
        }
    });
}
*/


/*删除商品*/
function deleteOperatorDialog(deleteUrl){
    var arrId = [];
    var ids;
    var selectedRows=$("#dg").datagrid('getSelections');
    if(selectedRows.length==0){
        $.messager.alert('系统提示','请选择要删除的数据！','warning');
        return;
    }
    $.each(selectedRows,function (i,data) {

        if(data.product_status == 1){
            $.messager.alert('系统提示', '正在上架的商品，请下架在删除！','warning');
            return;
        }else {
            arrId.push(data.product_id);
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
/*上架、下架商品*/
function upAndDownGoods(upAndDownGoodsUrl,chooseType) {
    var arrIds=[];
    var ids;
    var selectionRows = $("#dg").datagrid('getSelections');
    if (selectionRows.length < 1){
        $.messager.alert("请选择要"+"chooseType"+"的商品");
    }
    $.each(selectionRows,function (i,data) {
        arrIds.push(data.product_id);
        ids = arrIds.join(',');
    });
    $.messager.confirm("系统提示","您确认要"+chooseType+"这<font color=red>"+selectedRows.length+"</font>条商品吗？",function(r){
        if(r){
            $.post(upAndDownGoodsUrl,{Ids:ids},function(result){
                if(result.code == 0){
                    $.messager.alert('系统提示',"您已成功"+chooseType+" "+selectedRows.length+"条数据！");
                    $("#dg").datagrid("reload");
                }else{
                    $.messager.alert('系统提示',result.message);
                }
            },"json");
        }
    });
}

