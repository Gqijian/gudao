<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="/gudao/lib/jquery-easyui-1.5/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="/gudao/lib/jquery-easyui-1.5/themes/icon.css">
    <script type="text/javascript" src="/gudao/lib/jquery-easyui-1.5/jquery.min.js"></script>
    <script type="text/javascript" src="/gudao/lib/jquery-easyui-1.5/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="/gudao/lib/jquery-easyui-1.5/datagrid-detailview.js"></script>
    <script type="text/javascript" src="/gudao/lib/jquery-easyui-1.5/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="/gudao/js/baseJS.js"></script>
    <script type="text/javascript" src="/gudao/js/goods.js"></script>

    <title>修改商品</title>
</head>


<body>
<div id="dlg" class="easyui-dialog" style="width: 570px;padding: 10px 20px"
     closed="true" buttons="#dlg-buttons">
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
                    <select id="cc" class="easyui-combobox" name="available" style="width:200px;">
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
</div>
<div id="dlg-buttons">
    <a id="btn" class="easyui-linkbutton" iconCls="icon-ok" >保存</a>
    <a href="javascript:closeSaveDialog()" class="easyui-linkbutton" iconCls="icon-cancel" >关闭</a>
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
                            alert('修改成功!');
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