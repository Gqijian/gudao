/**
 * Created by Administrator on 2018/2/18 0018.
 */
var newUrl;
/*发货*/
function  deliverGoods(deliverUrl) {
    var selectedRows=$("#dg").datagrid('getSelections');
    if(selectedRows.length==0){
        $.messager.alert('系统提示','请选择要操作的数据！','warning');
        return;
    } else if(selectedRows.length > 1){
        $.messager.alert('系统提示','重要数据！只允许单条操作！','warning');
        return;
    }
    $("#fm").form('clear');
    $("#dlg").dialog("open").dialog("setTitle","添加信息");
    newUrl = deliverUrl + selectedRows[0].operator_id;
}


/*保存*/
function save() {
    $.ajax({
        url:newUrl,
        data:$("#fm").serialize(),
        dataType:'json',
        type:'post',
        success:function(data){
            if (data.code == 0){
                $.messager.show({title:'系统提示',msg:'保存成功！', timeout:1000, showType:'slide',style: {right:'', bottom:''}});
                closeSaveDialog();
                $("#dg").datagrid("reload");
            }else{
                $.messager.alert('系统提示',"<font color=red>"+data.message+"</font>");
                return;
            }
        }
    })
}

/*退款*/
function refund(refundUrl) {
    var selectedRows=$("#dg").datagrid('getSelections');
    if(selectedRows.length==0){
        $.messager.alert('系统提示','请选择要操作的数据！','warning');
        return;
    } else if(selectedRows.length > 1){
        $.messager.alert('系统提示','重要数据！只允许单条操作！','warning');
        return;
    }
    $.messager.confirm("系统提示","您确定要<font color='red'>取消该订单并退款</font>",function (r) {
        if (r) {
            $.post(refundUrl,{id:selectedRows[0].operator_id},function (result) {
                if (result.code == 1) {
                    $.messager.alert("系统提示","退款成功")
                }else {
                    $.messager.alert(result.message);
                }
            })
        }
    });
}