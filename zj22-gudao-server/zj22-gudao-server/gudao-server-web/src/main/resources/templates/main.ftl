<!DOCTYPE html>
<html lang="Zh">
<#include "common/header.ftl">
<body class="easyui-layout">

<!-- 北边 -->

<div id="north" data-options="region:'north',split:'true',border:'false'" style="overflow: hidden;
        background:#E0ECFF;line-height: 20px;color: #000; font-family: Verdana, 微软雅黑,黑体">
    <div style=" padding: 10px;float: left;padding-bottom: 10px;padding-left: 40px;">
        <img src="/gudao/img/5-1.png" height="50px"/>
        <img src="/gudao/img/sys.png" height="50px"/>
    </div>
    <div style="float:right; padding-right:20px;padding-top:10px;">
        <table style="text-align: center;">
            <tr>
                <td colspan="2"><i class="icon-user-tie" style="font-size: 20px"></i><!--<img src="img/my.png" width="25px" height="25px"/>--></td>
                <td><i class="icon-quill" style="font-size: 20px"></i><!--<img src="img/xiugai.png" width="25px" height="25px"/>--></td>
                <td><i class="icon-switch" style="font-size: 20px"></i><!--<img  src="img/exit.png"  width="25px" height="25px"/>--></td>
            </tr>
            <tr>
                <td id="name">欢迎：</td>
                <td id="role">${seller.realName}</td>
                <td><a href="javascript:openPasswordModifyDialog()">修改密码</a></td>
                <td><a href="javascript:logout()" >退出系统</a></td>
            </tr>
        </table>
    </div>

</div>

<!-- 南边 -->
<div data-options="region:'south'" style="height:30px;background-color: #E0ECFF;">
    <center><span style="color:red; font-size:15px">Copyright@2017-2018 版权所有，翻版必究</span></center>
</div>

<!-- 西边 -->
<div data-options="region:'west',split:true" title="我的管理" style="width:200px;">

    <ul class="easyui-tree">
        <li  data-options="iconCls:'icon-home'">
            <span>后台管理系统</span>
        </li>
    </ul>
    <ul id="treeID"></ul>



</div>

<!-- 中间 -->
<div data-options="region:'center'" style="padding:5px;background:#eee;">


    <div id="tabsID" class="easyui-tabs" data-options="fit:true,border:false" style="overflow-y: hidden;">
        <div title="首页" data-options="iconCls:'icon-home'">
            <div align="center" style="background: url(/gudao/img/welcome.gif) #70BDE4 no-repeat top center;width: 100%;height: 100%;">
            </div>
        </div>
    </div>
</div>


<!--修改密码窗口-->
<div id="dlg6" class="easyui-dialog" style="width: 400px;height: 180px;padding: 10px 20px"
     closed="true" buttons="#dlg6-buttons" data-options="iconCls:'icon-modifyPassword'">
    <form id="fm" method="post">
        <table cellspacing="4px;">
            <#--<tr>-->
                <#--<td>原 密 码：</td>-->
                <#--<td><input type="password" class="easyui-textbox" name="oldPassword" id="oldPassword" style="width: 200px;" required="true" /></td>-->
            <#--</tr>-->
            <tr>
                <td>新 密 码：</td>
                <td><input type="password" class="easyui-textbox" name="newPassword" id="newPassword" style="width: 200px;" required="true"  /></td>
            </tr>
            <tr>
                <td>确认新密码：</td>
                <td><input type="password" class="easyui-textbox" name="newPassword2" id="newPassword2" style="width: 200px;" required="true" /></td>
            </tr>
        </table>
    </form>
</div>
<div id="dlg6-buttons">
    <a href="javascript:modifyPassword()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
    <a href="javascript:closePasswordModifyDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
</div>


<div id="win" style="overflow:hidden;position: relative;" ></div><!-- supplier中的增加按钮，在主页面上进行操作，避免造成页面杂乱 -->

<script type="text/javascript">
    $(function(){
        $("#treeID").tree({
            url: "/gudao/seller/tree",
            iconCls:'icon-home',
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
                var content="<iframe title="+node.text+" frameborder=0 scrolling='auto' style='width:100%;height:100%' src="+node.url+"></iframe>";
                //如果没有打开的话
                if(!flag){
                    //打开选项卡
                    $("#tabsID").tabs("add",{
                        "title" : title,
                        "closable" : true,
                        "content": content,
                        "iconCls" : "icon-ok"
                    });
                }else{
                    $("#tabsID").tabs("select", title);
                }
            }
        });

    });

</script>

<#--订单弹窗-->
<div id="dlg" class="easyui-dialog" title="新订单" style="width:400px;height:200px;padding:10px"
     data-options="iconCls: 'icon-save' ,buttons:'#dlg-buttons',closed:true">
    <h3>你有新的订单,请及时处理！</h3>
</div>
<#---按钮-->
<div id="dlg-buttons">
    <button onclick="javascript:document.getElementById('notice').pause();$('#dlg').dialog('close');" type="button" >关闭</button>
    <button onclick="javascrtpt:document.getElementById('notice').pause();$('#dlg').dialog('close');" type="button">确定</button>
</div>




<#--播放音乐-->
<audio id="notice" loop="loop">
    <source src="/gudao/mp3/song.mp3" type="audio/mpeg" />
</audio>

<script>
    var websocket = null;
    if('WebSocket' in window) {
        websocket = new WebSocket('ws://yuanpeng.free.ngrok.cc/gudao/webSocket');
    }else {
        alert('该浏览器不支持websocket!');
    }

    websocket.onopen = function (event) {
        console.log('建立连接');
    }

    websocket.onclose = function (event) {
        console.log('连接关闭');
    }

    websocket.onmessage = function (event) {
        console.log('收到消息:' + event.data)
        //弹窗提醒, 播放音乐
        $('#dlg').dialog('open');
        document.getElementById('notice').play();
    }

    websocket.onerror = function () {
        alert('websocket通信发生错误！');
    }

    window.onbeforeunload = function () {
        websocket.close();
    }

</script>

</body>
</html>