<!DOCTYPE html>
<html lang="Zh">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>系统主界面</title>
    <link rel="stylesheet" type="text/css" href="/gudao/lib/jquery-easyui-1.5/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="/gudao/lib/jquery-easyui-1.5/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="/gudao/css/main.css">
    <script type="text/javascript" src="/gudao/lib/jquery-easyui-1.5/jquery.min.js"></script>
    <script type="text/javascript" src="/gudao/lib/jquery-easyui-1.5/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="/gudao/lib/jquery-easyui-1.5/datagrid-detailview.js"></script>
    <script type="text/javascript" src="/gudao/lib/jquery-easyui-1.5/locale/easyui-lang-zh_CN.js"></script>
    <#--<script type="text/javascript" src="../js/main.js"></script>-->
</head>
<body class="easyui-layout">

<!-- 北边 -->
<div data-options="region:'north'" style="height:85px;padding:20px;background-color: #E0ECFF;">
    <div style="float:left"><span style="color:red; font-size:28px">后台管理</span></div>
    <div style="float:right; border:3px dashed #beceeb; padding: 5px;margin-bottom:10px; background-color: #E0ECFF">
        <span >欢迎：${(seller.realName)!""}</span>
        &nbsp;&nbsp;  &nbsp;&nbsp;   &nbsp;&nbsp;
        <font color="#000000">
            <script type="text/javascript">

                tmpDate = new Date();
                date = tmpDate.getDate();
                month= tmpDate.getMonth() + 1 ;
                year= tmpDate.getFullYear();
                document.write(year);
                document.write("年");
                document.write(month);
                document.write("月");
                document.write(date);
                document.write("日 ");

                myArray=new Array(6);
                myArray[0]="星期日";
                myArray[1]="星期一";
                myArray[2]="星期二";
                myArray[3]="星期三";
                myArray[4]="星期四";
                myArray[5]="星期五";
                myArray[6]="星期六";
                weekday=tmpDate.getDay();
                if (weekday==0 | weekday==6)
                {
                    document.write(myArray[weekday]);
                }
                else
                {
                    document.write(myArray[weekday]);
                };

            </script>
        </font>
    </div>

</div>

<!-- 南边 -->
<div data-options="region:'south'" style="height:30px;background-color: #E0ECFF;">
    <center><span style="color:red; font-size:15px">Copyright@2016-2017 版权所有，翻版必究</span></center>
</div>

<!-- 西边 -->
<div data-options="region:'west',split:true" title="我的管理" style="width:200px;">

    <ul id="treeID"></ul>


</div>

<!-- 中间 -->
<div data-options="region:'center'" style="padding:5px;background:#eee;">


    <div id="tabsID" class="easyui-tabs" data-options="fit:true,border:false">
        <div title="首页" data-options="iconCls:'icon-home'">
            <div align="center" style="padding-top: 100px;"><font color="red" size="10">系统主页面</font></div>
        </div>
    </div>
</div>

<div id="win" style="overflow:hidden" ></div><!-- supplier中的增加按钮，在主页面上进行操作，避免造成页面杂乱 -->

<script type="text/javascript">
    $(function(){
        $("#treeID").tree({
//            url: "/gudao/json/tree_product.json",
            lines:true,
            onClick : function(node){

                //获取点击树节点的文本
                var title = node.text;
                //去空格
                title = $.trim(title);
                //产生tab选项卡
                var flag = $("#tabsID").tabs("exists",title);

                //退出
                if(node.id==100){
                    logout();
                }
                var content="<iframe title="+node.text+" frameborder=0 scrolling='auto' style='width:100%;height:100%' src="+node.attributes.url+"></iframe>";
                //如果没有打开的话
                if(!flag){
                    //打开选项卡
                    $("#tabsID").tabs("add",{
                        "title" : title,
                        "closable" : true,
                        "content": content,
                        "iconCls" : "icon-search"
                    });
                }else{
                    $("#tabsID").tabs("select", title);
                }
            }
        });

        $("#treeID").tree({
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
                        "url":"/gudao/base/goURL/product/productList.action"
                    }

                }]
            },{
                id:2,
                url:"",
                state:"closed",
                text: '商品管理',
                attributes:{
                    "url":"/gudao/base/goURL/product/productList.action"
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
                    text:'新订单',
                    attributes:{
                        "url":"/gudao/base/goURL/order/orderNList.action"
                    }
                },{
                    id:14,
                    url:"",
                    text:'完结订单',
                    attributes:{
                        "url":"/gudao/base/goURL/order/orderFList.action"
                    }
                },{
                    id:15,
                    url:"",
                    text:'已取消',
                    attributes:{
                        "url":"/gudao/base/goURL/order/orderCList.action"
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
                        "url":"/gudao/base/goURL/rebate/rebateList.action"
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


        //安全退出
        function logout(){
            $.messager.confirm('系统提示','您确定要退出系统吗？',function(r){
                if(r){
                    window.location.href='/gudao/seller/logout.action';
                }
            });
        }
    });

</script>


<!--修改密码窗口-->
<#--<div id="dlg" class="easyui-dialog" style="width: 400px;height: 220px;padding: 10px 20px"-->
     <#--closed="true" buttons="#dlg-buttons" data-options="iconCls:'icon-modifyPassword'">-->
    <#--<form id="fm" method="post">-->
        <#--<table cellspacing="4px;">-->
            <#--<tr>-->
                <#--<td>原 密 码：</td>-->
                <#--<td><input type="password" class="easyui-textbox" name="oldPassword" id="oldPassword" style="width: 200px;" required="true" /></td>-->
            <#--</tr>-->
            <#--<tr>-->
                <#--<td>新 密 码：</td>-->
                <#--<td><input type="password" class="easyui-textbox" name="newPassword" id="newPassword" style="width: 200px;" required="true"  /></td>-->
            <#--</tr>-->
            <#--<tr>-->
                <#--<td>确认新密码：</td>-->
                <#--<td><input type="password" class="easyui-textbox" name="newPassword2" id="newPassword2" style="width: 200px;" required="true" /></td>-->
            <#--</tr>-->
        <#--</table>-->
    <#--</form>-->
<#--</div>-->
<#--<div id="dlg-buttons">-->
    <#--<a href="javascript:modifyPassword()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>-->
    <#--<a href="javascript:closePasswordModifyDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>-->
<#--</div>-->
</body>
</html>