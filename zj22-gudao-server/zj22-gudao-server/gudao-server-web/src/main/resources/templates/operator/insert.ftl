<!DOCTYPE html>
<html lang="en">
<#include "../common/header.ftl">


<body style="overflow：hidden">

<form id="ff" method="post">
    <table>
        <tr>
            <td><span>管理者名称：</span></td>
            <td>
                <input style="width: 250px" class="easyui-textbox" name="operatorName" id="operatorName" type="text" required="true"/>
            </td>
        </tr>

        <tr>
            <td><span>姓名：</span></td>
            <td>
                <input class="easyui-textbox" style="width: 250px"  name="realName" id="realName" type="text" required="true"/>
            </td>
        </tr>
        <tr>
            <td><span>性别：</span></td>
            <td>
                <input class="easyui-textbox" style="width: 250px" type="text" id="sex" name="sex" required="true"/>
            </td>
        </tr>
        <#--<tr>-->
            <#--<td><span>密码：</span></td>-->
            <#--<td>-->
                <#--<input style="width: 250px" class="easyui-numberbox" type="text" id="password" name="password" required="true"/>-->
            <#--</td>-->
        <#--</tr>-->
        <tr>
            <td><span>电话号码：</span></td>
            <td>
                <input class="easyui-textbox" type="text" style="width: 250px" id="cellPhoneNum" name="cellPhoneNum"/>
            </td>
        </tr>
        <tr>
            <td><span>qq号：</span></td>
            <td>
                <input class="easyui-textbox" type="text" style="width: 250px"  id="qq" name="qq"/>
            </td>
        </tr>
        <tr>
            <td><span>邮箱：</span></td>
            <td>
                <input class="easyui-textbox"  type="text" style="width: 250px"  id="email" name="email"/>
            </td>
        </tr>
        <tr>
            <td><span>添加角色：</span></td>
            <td>
            <select id="cc" class="easyui-combobox" name="roleId" style="width:200px;">
            <#if role?exists>
                <#list role as r>
                    <option value="${r.roleId}">${r.roleName}</option>
                </#list>
            </#if>
            </select>
            </td>
        </tr>
    </table>
    <br>
    <br>
    <div id="dlg-buttons" class="dialog-button">
        <a id="btn" class="easyui-linkbutton" iconCls="icon-ok" >保存</a>
        <a  href="javascript:closeDialog()" class="easyui-linkbutton" iconCls="icon-cancel" >关闭</a>
    </div>
</form>

</body>


<script>
    /*关闭窗口*/
    function closeDialog(){
        var win = parent.$("iframe[title='后台用户管理']").get(0).contentWindow;
        parent.$("#win").window("close");
    }

    $(function(){

        var win = parent.$("iframe[title='后台用户管理']").get(0).contentWindow;//返回ifram页面文档（window)
        //更新操作得到选择的行
        var row = win.$('#dg').datagrid("getSelected");

        //禁用验证
        $("#ff").form("disableValidation");


        $("#btn").click(function() {
            $("#ff").form("enableValidation");

            if($('#ff').form('validate')){
                $('#ff').form('submit', {
                    url:"/gudao/seller/operator/add.action",
                    onSubmit: function(){
                        return true;
                    },
                    success:function(data){
                        if(data==1){
                            alert('成功!');
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