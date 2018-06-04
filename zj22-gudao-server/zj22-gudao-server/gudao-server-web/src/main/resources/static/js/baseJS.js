/**$(function () {
    $("#dg").datagrid('getPager').pagination('refresh', {pageSize:20});
});
  单条数据操作  */
var url;
function BaseJS(perurl,dourl) {
    $.ajax({
        url:perurl,
        data:{},
        dataType:'json',
        type:'post',
        success:function(data){
            if (data.code == 0){
                var selectedRows=$("#dg").datagrid('getSelections');
                if(selectedRows.length==0){
                    $.messager.alert('系统提示','请选择要操作的数据！');
                    return;
                } else if(selectedRows.length > 1){
                    $.messager.alert('系统提示','重要数据！只允许单条操作！');
                    return;
                }else if(selectedRows[0].id == 1){
                    $.messager.alert('系统提示','该数据不支持操作！');
                    return;
                }
                $.messager.confirm("系统提示","您确认要对 <font color=red>"+selectedRows[0].name+"</font> 进行操作吗？",function(r){
                    if(r){
                        $.post(dourl,{id:selectedRows[0].id},function(result){
                            if(result.code == 0){
                                $.messager.alert('系统提示',"操作成功！");
                                $("#dg").datagrid("reload");
                            }else{
                                $.messager.alert('系统提示',result.message);
                            }
                        },"json");
                    }
                });
            }else{
                $.messager.alert('系统提示',data.message);
            }
        }
    })
}

/**  模糊查询操作  */
function Namesearch(){
    $.ajax({
        url:"/login/tag",
        data:{},
        dataType:'json',
        type:'post',
        success:function(data){
            if (data.code == 0){
                $('#dg').datagrid('load',{
                    s_name:$("#s_name").val()
                });
            }else{
                $.messager.alert('系统提示',data.message);
            }
        }
    })
}

/*获取被选中的角色 并判断是否为一个*/
function judjeChooseNumber(){
    var selectedRows = $("#dg").datagrid('getSelections');
    if (selectedRows.length != 1) {
        $.messager.alert('系统提示', '请选择一条要编辑的数据！');
        return;
    }else if(selectedRows[0].roleName == "超级管理员"){
        $.messager.alert('系统提示', '超级管理员不能被操作！');
        return;
    }else if(selectedRows[0].available == 1){
        $.messager.alert('系统提示','该角色不可用！');
        return;
    }else {
        return selectedRows;
    }
}

/**  打开添加页面 */
function openAddDialog(newurl){
    $("#fm").form('clear');
    $("#dlg").dialog("open").dialog("setTitle","添加信息");
    url= newurl/*url 指向创建的url*/
}

/**  打开修改页面  */
function roleOpenModifyDialog(newurl,chooseType) {

    $("#fm").form("clear");
    /*获取被选中的数量 并判断是否为一个*/
    var temp = judjeChooseNumber();
    var row = temp[0];
    $("#dlg").dialog("open").dialog("setTitle", "修改信息");
    if ( chooseType === "role"){
        /*修改角色*/
        url = newurl+row.roleId;
    }else if (chooseType == 'user') {
        /*修改用户*/
        url = newurl.row.operator_id;
        $("#pass").hide();//隐藏确认密码框
    } else if (chooseType == 'product'){
        url = newurl.row.product_id;
    }else if (chooseType == 'rebate_id'){
        url = newurl.row.rebate_id;
    }
    $("#fm").form("load", row);


}
//保存
function Basesave(){
    $.ajax({
        url:url,
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

/**  多条数据删除操作  */
function deleteMoreDialog(delecturl,dourl){
    $.ajax({
        url:delecturl,
        data:{},
        dataType:'json',
        type:'post',
        success:function(data){
            if (data.code == 0){
                var selectedRows=$("#dg").datagrid('getSelections');
                if(selectedRows.length==0){
                    $.messager.alert('系统提示','请选择要删除的数据！');
                    return;
                }
                var strIds=[];
                for(var i=0;i<selectedRows.length;i++){
                    strIds.push(selectedRows[i].id);
                }
                var ids = strIds.join(",");
                $.messager.confirm("系统提示","您确认要删除这<font color=red>"+selectedRows.length+"</font>条数据吗？",function(r){
                    if(r){
                        $.post(dourl,{Ids:ids},function(result){
                            if(result.code == 0){
                                $.messager.show({title:'系统提示',msg:"您已成功删除"+selectedRows.length+"条数据！", timeout:1000, showType:'slide',style: {right:'', bottom:''}});
                                $("#dg").datagrid("reload");
                            }else{
                                $.messager.alert('系统提示',result.message);
                            }
                        },"json");
                    }
                });
            }else{
                $.messager.alert('系统提示',data.message);
            }
        }
    })
}


/*判断角色是否可用*/
function deal_available(val,row){
    if(val == 0){
        return "可用";
    }else {
        return "不可用";
    }
}

function deal_order_status(val,row) {
    if (val == 0) {
        return "未支付";
    }else if ( val == 1){
        return "已付款";
    }else {
        return "已退款"
    }
}

/*关闭窗口*/
function closeSaveDialog(){
    $("#dlg").dialog("close");
    $("#fm").form('clear');
}

function closeTreeDialog(){
    $("#dlg2").dialog("close");
}

function closeDialog3(){
    $("#dlg3").dialog("close");
}



function formatProPic(val,row){
    return "<img width=100 height=100 src='"+val+"'>";
}



function deal_time(val,row){
    var timestamp3 = val;
    var newDate = new Date();
    newDate.setTime(timestamp3 * 1000);
    return newDate.toLocaleString();
    /*var now=new Date(val);
    var year=now.getYear();
    var month=now.getMonth()+1;
    var date=now.getDate();
    var hour=now.getHours();
    var minute=now.getMinutes();
    var second=now.getSeconds();
    return "20"+year+"-"+month+"-"+date+" "+hour+":"+minute+":"+second;*/
}

function deal_price(val,row){
    return (val / 100);
}