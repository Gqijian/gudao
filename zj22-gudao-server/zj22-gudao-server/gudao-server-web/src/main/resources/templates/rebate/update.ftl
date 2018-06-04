<!DOCTYPE html>
<html lang="en">
<#include "../common/header.ftl">


<body>

    <form id="ff" method="post">
        <table>
            <tr>
                <td><span>商品id：</span></td>
                <td>
                    <input readonly="true" style="width: 250px" class="easyui-textbox" name="rebateId" id="rebateId" type="text" required="true"/>
                </td>
            </tr>
            <tr>
                <td><span>折扣率：</span></td>
                <td>
                    <input style="width: 250px" class="easyui-textbox" name="rebateRatio" id="rebateRatio" type="text" required="true"/>
                </td>
            </tr>
            <tr>
                <td><span>商品状态：</span></td>
                <td>
                    <select id="cc" class="easyui-combobox" name="available" style="width:250px;">
                        <option value="0">可使用</option>
                        <option value="1">不可用</option>
                    </select>
                </td>
            </tr>

        </table>
        <#--<div>-->
            <#--<input id="btn" type="button" value="提交" />-->
        <#--</div>-->
    </form>
    <br>
    <br>
<div id="dlg-buttons" class="dialog-button">
    <a id="btn" class="easyui-linkbutton" iconCls="icon-ok" >保存</a>
    <a  href="javascript:closeSaveDialog()" class="easyui-linkbutton" iconCls="icon-cancel" >关闭</a>
</div>
</body>


<script>
    $(function(){

        var win = parent.$("iframe[title='返点比例']").get(0).contentWindow;//返回ifram页面文档（window)
        //更新操作得到选择的行
        var row = win.$('#dg').datagrid("getSelected");

        //禁用验证
        $("#ff").form("disableValidation");
        $('#ff').form('load',{
            rebateId:row.rebateId,
            rebateRatio:row.rebateRatio

        });

        //禁用验证
        $("#ff").form("disableValidation");

        $("#btn").click(function() {
            $("#ff").form("enableValidation");
            //$.messager.progress();
//            if($(".file").val()==""){
//                $.messager.alert("提示","请选择文件");
//                return false;
//            }

            if($('#ff').form('validate')){
                $('#ff').form('submit', {
                    url:"/gudao/seller/rebate/save.action",
                    onSubmit: function(){
                        return true;
                    },
                    success:function(data){
                        if(data==1){
                            $.messager.alert("提示","修改成功!");
                            //alert('修改成功!');
                        }
                        parent.$("#win").window("close");
                        win.$("#dg").datagrid("reload");//重载行。等同于'load'方法，但是它将保持在当前页。
                        win.$("#dg").datagrid("clearSelections");
                    }
                });
            }
        });

    });
</script>
</html>