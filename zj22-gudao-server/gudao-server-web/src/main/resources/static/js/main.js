


//安全退出
function logout(){
    $.messager.confirm('系统提示','您确定要退出系统吗？',function(r){
        if(r){
            window.location.href='/gudao/seller/logout.action';
        }
    });
}

function openPasswordModifyDialog(){
    $.ajax({
        url:"/login/tag",
        data:{},
        dataType:'json',
        type:'post',
        success:function(data){
            if (data.code == 0){
                url='/system/operator/adminpassword';
                $("#dlg").dialog("open").dialog("setTitle","修改密码");
            }else{
                $.messager.alert("系统提示",data.message);
                window.location.href='/login';
            }
        }
    })
}


function modifyPassword(){
    $("#fm").form("submit",{
        url:url,
        onSubmit:function(){
            var newPassword=$("#newPassword").val();
            var newPassword2=$("#newPassword2").val();
            if(!$(this).form("validate")){
                return false;
            }
            if(newPassword!=newPassword2){
                $.messager.alert('系统提示','确认密码输入错误！');
                return false;
            }
            return true;
        },
        success:function(result){
            var result=eval('('+result+')');
            if(result.success){
                $.messager.alert('系统提示',result.errMsg);
                return;
            }else{
                $.messager.alert('系统提示','密码修改成功，下一次登录生效！');
                closePasswordModifyDialog();
            }
        }
    });
}

function closePasswordModifyDialog(){
    $("#dlg").form("clear");
    $("#dlg").dialog("close");
}
