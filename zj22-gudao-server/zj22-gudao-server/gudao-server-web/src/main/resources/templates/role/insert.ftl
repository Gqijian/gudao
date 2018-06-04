<!DOCTYPE html>
<html lang="en">
<#include "../common/header.ftl">

<body overflow="hidden">


<form id="ff" method="post">
    <table cellspacing="5px;">
        <tr>
            <td>角色名称：</td>
            <td width="80%"><input type="text" id="roleName" name="roleName" class="easyui-textbox" required="true"/></td>
        </tr>
        <tr>
            <td valign="top">备注：</td>
            <td colspan="2">
                <textarea rows="7" cols="50" name="description" id="description"></textarea>
            </td>
        </tr>
    </table>
<#--<div>-->
<#--<input id="btn" type="button" value="提交" />-->
<#--</div>-->
    <br>
    <br>
    <div id="dlg-buttons"  class="dialog-button">
        <a id="btn" class="easyui-linkbutton" iconCls="icon-ok" >保存</a>
        <a  href="javascript:closeSaveDialog()" class="easyui-linkbutton" iconCls="icon-cancel" >关闭</a>
    </div>
</form>
</body>


<script>

    /*关闭窗口*/
    function closeSaveDialog(){
        var win = parent.$("iframe[title='角色管理']").get(0).contentWindow;
        $("#ff").form('clear');
        parent.$("#win").window("close");
    }
    $(function(){

        var win = parent.$("iframe[title='角色管理']").get(0).contentWindow;//返回ifram页面文档（window)

        //禁用验证
        $("#ff").form("disableValidation");

        $("#btn").click(function() {
            $("#ff").form("enableValidation");

            if($('#ff').form('validate')){
                $('#ff').form('submit', {
                    url:"/gudao/seller/role/save.action",
                    onSubmit: function(){
                        return true;
                    },
                    success:function(data){
                        if(data==1){
                            alert('添加成功!');
                        }
                        parent.$("#win").window("close");
                        win.$("#dg").datagrid("reload");//重载行。等同于'load'方法，但是它将保持在当前页。
                    }
                });
            }
        });

    });
</script>
</html>