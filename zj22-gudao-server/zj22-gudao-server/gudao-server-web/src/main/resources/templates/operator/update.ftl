<!DOCTYPE html>
<html lang="en">
<#include "../common/header.ftl">


<body style="overflow：hidden">

    <form id="ff" method="post" enctype="multipart/form-data">
        <table>
            <tr>
                <td><span>商品id：</span></td>
                <td>
                    <input readonly="true" style="width: 250px" class="easyui-textbox" name="productId" id="productId" type="text" required="true"/>
                </td>
            </tr>
            <tr>
                <td><span>商品名称：</span></td>
                <td>
                    <input style="width: 250px" class="easyui-textbox" name="productName" id="product_name" type="text" required="true"/>
                </td>
            </tr>
            <tr>
                <td><span>商品状态：</span></td>
                <td>
                    <select id="cc" class="easyui-combobox" name="dept" style="width:200px;">
                        <option value="0">上架</option>
                        <option value="1">下架</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td><span>售价(<font color="red">*元</font>)：</span></td>
                <td>
                    <input style="width: 250px"  name="productPrice" id="product_price" type="text" required="true"/>
                </td>
            </tr>
            <tr>
                <td><span>成本价(<font color="red">*元</font>)：</span></td>
                <td>
                    <input style="width: 250px" type="text" id="cost" name="cost" required="true"/>
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
                <td><img id="img1" src="" width=100 height=70/>
                    <input type="file" style="width: 250px" id="imgfile1" class="file" name="files"/>
                    <#--<input type="hidden" id="product_icon_one" name="product_icon_one"/>-->
                </td>
            </tr>
            <tr>
                <td><span>商品图片2：</span></td>
                <td><img id="img2" src="" width=100 height=70/>
                    <input type="file" style="width: 250px" class="file" id="imgfile2" name="files"/>
                    <#--<input type="hidden" id="product_icon_two" name="product_icon_two"/>-->
                </td>
            </tr>
            <tr>
                <td><span>商品图片3：</span></td>
                <td><img id="img3" src="" width=100 height=70/>
                <input type="file" style="width: 250px" class="file" id="imgfile3" name="files"/>
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
        <div id="dlg-buttons" style="margin-bottom:10px;">
            <a id="btn" style="margin-left: 85px;" class="easyui-linkbutton" iconCls="icon-ok" >保存</a>
            <a style="margin-left:105px;" href="javascript:closeSaveDialog()" class="easyui-linkbutton" iconCls="icon-cancel" >关闭</a>
        </div>
    </form>

</body>


<script>
    $(function(){

        var win = parent.$("iframe[title='商品管理']").get(0).contentWindow;//返回ifram页面文档（window)
        //更新操作得到选择的行
        var row = win.$('#dg').datagrid("getSelected");

        //禁用验证
        $("#ff").form("disableValidation");
        $('#ff').form('load',{
            productId:row.productId,
            productStatus:row.productStatus,
            productName:row.productName,
            productPrice:row.productPrice,
            cost:row.cost,
            weight:row.weight,
            productIconOne:row.productIconOne,
            productIconTwo:row.productIconTwo,
            productIconThree:row.productIconThree,
            productStock:row.productStock,
            productAbout:row.productAbout,
            productDescription:row.productDescription
        });
        $("#img1").attr("src","/gudao/upload/"+row.productIconOne);
        $("#img2").attr("src","/gudao/upload/"+row.productIconTwo);
        $("#img3").attr("src","/gudao/upload/"+row.productIconThree);

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
                    url:"/gudao/seller/product/save.action",
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