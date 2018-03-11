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

    <title>修改折扣</title>
</head>


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
        <div id="dlg-buttons" style="margin-bottom:10px;">
            <a id="btn" style="margin-left: 85px;" class="easyui-linkbutton" iconCls="icon-ok" >保存</a>
            <a style="margin-left:105px;" href="javascript:closeSaveDialog()" class="easyui-linkbutton" iconCls="icon-cancel" >关闭</a>
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