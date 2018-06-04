<!DOCTYPE html>
<html lang="en">
<#include "../common/header.ftl">


<body>
    <form id="ff" method="post">
        <table>
            <tr>
                <td><span>折扣率：</span></td>
                <td>
                    <input style="width: 250px" class="easyui-textbox" name="rebateRatio" id="rebateRatio" type="text" required="true"/>
                </td>
            </tr>
        </table>
        <#--<div>-->
            <#--<input id="btn" type="button" value="提交" />-->
        <#--</div>-->
        <br>
        <br>
        <div id="dlg-buttons" class="dialog-button">
            <a id="btn" class="easyui-linkbutton" iconCls="icon-ok" >保存</a>
            <a href="javascript:closeSaveDialog()" class="easyui-linkbutton" iconCls="icon-cancel" >关闭</a>
        </div>
    </form>


</body>


<script>
    $(function(){

        var win = parent.$("iframe[title='返点比例']").get(0).contentWindow;//返回ifram页面文档（window)

        //禁用验证
        $("#ff").form("disableValidation");

        $("#btn").click(function() {
            $("#ff").form("enableValidation");


            if($('#ff').form('validate')){
                $('#ff').form('submit', {
                    url:"/gudao/seller/rebate/save.action",
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