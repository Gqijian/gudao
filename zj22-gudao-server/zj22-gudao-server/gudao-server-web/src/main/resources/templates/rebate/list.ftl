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
                    <table class="table table-bordered table-condensed">
                        <thead>
                        <tr>
                            <th>折扣id</th>
                            <th>返点比例</th>
                            <th>是否可用</th>
                            <th>创建时间</th>
                            <th>修改时间</th>
                            <th>创建人</th>
                            <th>修改人</th>
                        </tr>
                        </thead>
                        <tbody>

                        <#list rebateInfoPage.list as rebateInfo>
                        <tr>
                            <td>${rebateInfo.rebateId}</td>
                            <td>${rebateInfo.rebateRatio}</td>
                            <td>${rebateInfo.getAvailableStatus()}</td>
                            <td>${rebateInfo.getCreateTimeToString()}</td>
                            <td>${rebateInfo.getUpdateTimeToString()}</td>
                            <td>${rebateInfo.createUser}</td>
                            <td>${rebateInfo.updateUser}</td>
                        </tr>
                        </#list>
                        </tbody>
                    </table>
                </div>

            <#--分页-->
                <div class="col-md-12 column">
                    <ul class="pagination pull-right">
                    <#if currentPage lte 1>
                        <li class="disabled"><a href="#">上一页</a></li>
                    <#else>
                        <li><a href="/gudao/seller/rebate/list?page=${currentPage - 1}&size=${size}">上一页</a></li>
                    </#if>

                    <#list 1..rebateInfoPage.getShowPages() as index>
                        <#if currentPage == index>
                            <li class="disabled"><a href="#">${index}</a></li>
                        <#else>
                            <li><a href="/gudao/seller/rebate/list?page=${index}&size=${size}">${index}</a></li>
                        </#if>
                    </#list>

                    <#if currentPage gte rebateInfoPage.getShowPages()>
                        <li class="disabled"><a href="#">下一页</a></li>
                    <#else>
                        <li><a href="/gudao/seller/rebate/list?page=${currentPage + 1}&size=${size}">下一页</a></li>
                    </#if>
                    </ul>
                </div>
            </div>
        </div>
    </div>

</div>
</body>
</html>