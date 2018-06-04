<!DOCTYPE html>
<html lang="en">
<#include "../common/header.ftl">

<body overflow="hidden">

    <form id="ff" method="post" enctype="multipart/form-data">
        <table>
            <tr>
                <td><span>商品名称：</span></td>
                <td>
                    <input style="width: 250px" class="easyui-textbox" name="productName" id="product_name" type="text" required="true"/>
                </td>
            </tr>
            <tr>
                <td><span>售价(<font color="red">*元</font>)：</span></td>
                <td>
                    <input style="width: 250px" class="easyui-numberbox" name="productPrice" id="product_price" type="text" required="true"/>
                </td>
            </tr>
            <tr>
                <td><span>成本价(<font color="red">*元</font>)：</span></td>
                <td>
                    <input style="width: 250px" class="easyui-numberbox" type="text" id="cost" name="cost" required="true"/>
                </td>
            </tr>
            <tr>
                <td><span>重量(<font color="red">*g</font>)：</span></td>
                <td>
                    <input style="width: 250px" class="easyui-numberbox" type="text" id="weight" name="weight" required="true"/>
                </td>
            </tr>
            <tr>
                <td><span>商品图片1：</span></td>
                <td><input data-options="prompt:'选择图片'" class="easyui-filebox" style="width: 250px" id="imgfile1"  name="files"/>
                    <#--<input type="hidden" id="product_icon_one" name="product_icon_one"/>-->
                </td>
            </tr>
            <tr>
                <td><span>商品图片2：</span></td>
                <td><input data-options="prompt:'选择图片'" class="easyui-filebox" style="width: 250px"  id="imgfile2" name="files"/>
                    <#--<input type="hidden" id="product_icon_two" name="product_icon_two"/>-->
                </td>
            </tr>
            <tr>
                <td><span>商品图片3：</span></td>
                <td>
                    <input style="width: 250px" data-options="prompt:'选择图片'" class="easyui-filebox" id="imgfile3" name="files"/>
                    <#--<input type="hidden" id="product_icon_three" name="product_icon_three"/>-->
                </td>
            </tr>
            <tr>
                <td><span>库存(<font color="red">*</font>)：</span></td>
                <td>
                    <input style="width: 250px" class="easyui-numberbox" type="text" id="productStock" name="productStock" required="true"/>
                </td>
            </tr>
            <tr>
                <td><span>商品简介：</span></td>
                <td><textarea  name="productAbout" id="product_about" rows="5" cols="40"></textarea></td>
            </tr>
            <tr>
                <td><span>商品详情：</span></td>
                <td><textarea  name="productDescription" id="productDescription" rows="5" cols="40"></textarea></td>
            </tr>
        </table>
        <#--<div>-->
            <#--<input id="btn" type="button" value="提交" />-->
        <#--</div>-->
        <br>
        <br>
        <div id="dlg-buttons" class="dialog-button">
            <a id="btn"  class="easyui-linkbutton" iconCls="icon-ok" >保存</a>
            <a href="javascript:closeSaveDialog()" class="easyui-linkbutton" iconCls="icon-cancel" >关闭</a>
        </div>
    </form>
</body>


<script>
    /*关闭窗口*/
    function closeSaveDialog(){
        var win = parent.$("iframe[title='商品管理']").get(0).contentWindow;
        $("#ff").form('clear');
        parent.$("#win").window("close");
    }
    $(function(){

        var win = parent.$("iframe[title='商品管理']").get(0).contentWindow;//返回ifram页面文档（window)

        //禁用验证
        $("#ff").form("disableValidation");

        $("#btn").click(function() {
            $("#ff").form("enableValidation");
            //$.messager.progress();
            if($(".file").val()==""){
                $.messager.alert("提示","请选择文件");
                return false;
            }

            if($('#ff').form('validate')){
                $('#ff').form('submit', {
                    url:"/gudao/seller/product/save.action",
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