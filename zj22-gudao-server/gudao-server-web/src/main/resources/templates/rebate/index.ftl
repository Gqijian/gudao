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
                    <form role="form" method="post" action="/gudao/seller/rebate/save">
                        <div class="form-group">
                            <label>返点比例</label>
                            <input name="rebateRatio" type="text" class="form-control" value="${(rebateInfo.productName)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>是否可用</label>
                            <select name="available" class="form-control">
                                <option value="0">不可用</option>
                                <option value="1">可用</option>
                            </select>
                        </div>

                        <input hidden type="text" name="rebateId" value="${(rebateInfo.rebateId)!''}">
                        <button type="submit" class="btn btn-default">提交</button>
                    </form>
                </div>
            </div>
        </div>
    </div>

</div>
</body>
</html>