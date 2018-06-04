
var roleId;/*角色id*/
/*
/!*获取被选中的角色 并判断是否为一个*!/
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
*/


/*删除角色*/ /*一条和多条*/
function roleDeleteDialog(){
    var ids;
    var arrId=[];
    $("#fm").form("clear");
    var selectedRows = $("#dg").datagrid('getSelections');
    if (selectedRows.length==0){
        $.messager.alert('系统提示','请选择要操作的数据！');
        return;
    }
    $.each(selectedRows,function (i,data) {

        if(data.roleName == "超级管理员"){
            $.messager.alert('系统提示', '超级管理员禁止操作！');
            return;
        }else if (data.available == 0) {
            $.messager.alert('系统提示','有角色正在使用，无法删除！');
            return;
        }else {
            arrId.push(data.roleId);
            ids =arrId.join(",");
        }
    });
    $.messager.confirm("系统提示","您确认要对 <font color=red>"+selectedRows.length+"</font> 进行操作吗？",function(r){
        if(r){
            //console.log(ids);
            $.post(url,{id:ids},function(result){
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


/*冻结和解冻角色*/
function roleFreeze(whetherFreeze) {
    if (whetherFreeze == "freeze"){
        url ="";
    }else {
        url ="";
    }
    /*获取被选中的角色 并判断是否为一个*/
    var row = judjeChooseNumber()[0];
    $.messager.confirm("系统提示","您确认要对 <font color=red>"+row.roleName+"</font> 进行操作吗？",function(r){
        if(r){
            $.post(url,{id:row.roleId},function(result){
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


/*查看已有权限*/
function lookRoot(){
    var row = judjeChooseNumber()[0];
    var roleId=row.roleId;
    $("#dlg3").dialog("open").dialog("setTitle","角色已有权限");
    var url=""+roleId;
    $("#tree3").tree({
        lines:true,
        url:url,
        checkbox:false,/*定义是否在每一个借点之前都显示复选框*/
        cascadeCheck:false,/*定义是否层叠选中状态*/
        onLoadSuccess:function(node, data){
            $("#tree3").tree('expandAll');/*展开所有节点*/

        }
    });
}

/*添加未有权限*/
function addRoot(){
    var row = judjeChooseNumber()[0];
    var roleId=row.roleId;
    $("#dlg2").dialog("open").dialog("setTitle","添加角色权限");
    var url = ""+roleId;
    $("#tree").tree({
        lines:true,
        url:url,
        checkbox:true,
        cascadeCheck:false,
        onLoadSuccess:function(node, data){
            $("#tree").tree('expandAll');
            /*if (data.code == 0 || data.code == undefined){

            }else {
                $.messager.alert('系统提示',data.message);
            }*/
        },
        onCheck:function(node,checked){
            if(checked){
                checkNode($('#tree').tree('getParent',node.target),'getParent');
            }
        }
    });


}
/*删除已有权限*/
function delectRoot(){
    var row = judjeChooseNumber()[0];
    var roleId=row.roleId;
    $("#dlg2").dialog("open").dialog("setTitle","添加角色权限");
    var url = ""+roleId;
    $("#tree").tree({
        lines:true,
        url:url,
        checkbox:true,
        cascadeCheck:true,
        onLoadSuccess:function(node, data){
            $("#tree").tree('expandAll');
            /*if (data.code == 0 || data.code == undefined){

            }else {
                $.messager.alert('系统提示',data.message);
            }*/
        }
    });
}

function checkNode(node,name){
    if(!node){
        return;
    }else{
        checkNode($('#tree').tree(name,node.target),name);
        $('#tree').tree('check',node.target);
    }
}

function saveTree(){
    var nodes=$('#tree').tree('getChecked');
    var ids = new Array();
    //var authArrIds=[];
    for(var i=0;i<nodes.length;i++){
        ids[i]=nodes[i].opId;
        // alert(ids[i]);
        //authArrIds.push(nodes[i].id);
    }
    var url;

    //var authIds=authArrIds.join(",");
    $.post(url,{pks:ids,roleId:id},function(result){
        if(result.code == 0){
            $.messager.show({title:'系统提示',msg:'操作成功！', timeout:1000, showType:'slide',style: {right:'', bottom:''}});
            closeTreeDialog();
        }else{
            $.messager.alert('系统提示',result.message);
        }
    },"json");
}
