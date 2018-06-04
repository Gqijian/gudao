<html>
<#include "../common/header.ftl">

<body>
<div id="wrapper" class="toggled">

<#--边栏sidebar-->
<#include "../common/nav.ftl">

<#--主要内容content-->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <form role="form" method="post" action="/gudao/seller/product/save">
                        <div class="form-group">
                            <label>名称</label>
                            <input name="productName" type="text" class="form-control" value="${(productInfo.productName)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>价格</label>
                            <input name="productPrice" type="text" class="form-control" value="${(productInfo.productPrice)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>成本</label>
                            <input name="cost" type="text" class="form-control" value="${(productInfo.cost)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>重量</label>
                            <input name="weight" type="text" class="form-control" value="${(productInfo.weight)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>库存</label>
                            <input name="productStock" type="number" class="form-control" value="${(productInfo.productStock)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>简介</label>
                            <input name="productAbout" type="text" class="form-control" value="${(productInfo.productAbout)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>描述</label>
                            <input name="productDescription" type="text" class="form-control" value="${(productInfo.productDescription)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>图片1</label>
                            <img height="100" width="100" src="${(productInfo.productIconOne)!''}" alt="">
                            <input name="productIconOne" type="text" class="form-control" value="${(productInfo.productIconOne)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>图片2</label>
                            <img height="100" width="100" src="${(productInfo.productIconTwo)!''}" alt="">
                            <input name="productIconTwo" type="text" class="form-control" value="${(productInfo.productIconTwo)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>图片3</label>
                            <img height="100" width="100" src="${(productInfo.productIconThree)!''}" alt="">
                            <input name="productIconThree" type="text" class="form-control" value="${(productInfo.productIconThree)!''}"/>
                        </div>

                        <input hidden type="text" name="productId" value="${(productInfo.productId)!''}">
                        <button type="submit" class="btn btn-default">提交</button>
                    </form>
                </div>
            </div>
        </div>
    </div>

</div>
</body>
</html>