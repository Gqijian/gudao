
$(function(){
    $.post("/login/cookie",function(result){
        $("#name").text(result.name);
        $("#role").text('['+result.role+']');
    },"json");
    $("#tree").tree({
        lines:true,
        url:'/system/operation/menus',
        onLoadSuccess:function(){
            $("#tree").tree('expandAll');
        },
        onClick:function(node){
            /*openTab(node);*/
            console.log(node.attributes.url);
            if ($('#tabs').tabs('exists', node.text)){
                $('#tabs').tabs('select', node.text);
            } else {
                var content = '<iframe scrolling="auto" frameborder="0"  src="'+node.attributes.url+'" style="width:100%;height:100%;"></iframe>';
                $('#tabs').tabs('add',{
                    title:node.text,
                    content:content,
                    closable:true
                });
            }
        }
    });

    $("#tree").tree({
        data: [{
            id:1,
            text: '系统管理',
            state: 'open',
            children: [{
                id:11,
                text: '角色管理',
                attributes:{
                    "url":"#"
                }
            },{
                id:12,
                text: '人员管理',
                attributes:{
                    "url":"#"
                }

            }]
        },{
            id:2,
            url:"",
            state:"closed",
            text: '商品管理',
            attributes:{
                "url":"../base/goURL/product/productList.action"
            }
        },{
            id:3,
            url:"",
            state:"closed",
            text:'邮费管理',
            attributes:{
                "url":""
            }
        },{
            id:4,
            url:"",
            state:"open",
            text:'订单管理',
            children:[{
                    id:13,
                    url:"",
                    text:'未付款',
                    attributes:{
                        "url":"orderManagement/nonPay.html"
                    }
                },{
                    id:14,
                    url:"",
                    text:'付款成功',
                    attributes:{
                        "url":"orderManagement/payment.html"
                    }
                },{
                    id:15,
                    url:"",
                    text:'已退款',
                    attributes:{
                        "url":"orderManagement/refund.html"
                    }
                }
            ]
        },{
            id:5,
            url:"",
            state:"open",
            text:'返点管理',
            children:[{
                id:16,
                url:"",
                text:'返点比例',
                attributes:{
                    "url":"rebateManagement/rebateScale.html"
                }
            },{
                id:17,
                url:"",
                text:'红包返现',
                attributes:{
                    "url":"rebateManagement/returnCash.html"
                }
            },{
                id:18,
                url:"",
                text:'历史点数',
                attributes:{
                    "url":"rebateManagement/historyRebate.html"
                }
            }]
        }]

    });

    function openTab(node){
        $.ajax({
            url:"/login/tag",
            data:{},
            dataType:'json',
            type:'post',
            success:function(data){
                if (data.code == 0){
                    if($("#tabs").tabs("exists",node.text)){
                        $("#tabs").tabs("select",node.text);
                    }else{
                        var content="<iframe frameborder=0 scrolling='auto' style='width:100%;height:100%' src="+node.attributes.authPath+"></iframe>"
                        $("#tabs").tabs("add",{
                            title:node.text,
                            iconCls:node.iconCls,
                            closable:true,
                            content:content
                        });
                    }
                }else{
                    $.messager.alert("系统提示",data.message);
                    window.location.href='/login';
                }
            }
        })
    }
});

function logout(){
    $.ajax({
        url:"/login/tag",
        data:{},
        dataType:'json',
        type:'post',
        success:function(data){
            if (data.code == 0){
                $.messager.confirm('系统提示','您确定要退出系统吗？',function(r){
                    if(r){
                        window.location.href='/login/out';
                    }
                });
            }else{
                $.messager.alert("系统提示",data.message);
                window.location.href='/login';
            }
        }
    })
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
