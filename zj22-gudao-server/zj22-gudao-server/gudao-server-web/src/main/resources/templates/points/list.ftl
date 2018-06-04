<html>
<#include "../common/header.ftl">
<style>
    .my-container {
        float: left;
        display: inline-block;
        margin-right:30px;
    }

    .myLabel-content ,.myText-content,.myBtn-content{
        float: left;
        display: inline-block;
        margin-left: 10px;
    }
    .myLabel-content,.myText-content input[type='text'],.myBtn-content .btn {
        height: 20px;
        font-size: 15px;
    }
    .myBtn-content .btn {
        height: 25px;
        margin-bottom: 12px;
    }
</style>
<script>
    $(function () {
        $('#search').click(function () {
            var orderId = $('#orderId').val();
            $('#testTable').bootstrapTable('refresh', { url: '/gudao/seller/point/findByOrderId?orderId=' + orderId });
        });

    });
</script>
<body>
<div id="wrapper" class="toggled">

    <#--边栏sidebar-->
    <#include "../common/nav.ftl">

    <#--主要内容content-->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12 column">

                    <div id="toolbar">
                        <div class="my-container">
                            <label class="myLabel-content">订单号：</label>
                            <div class="myText-content">
                                <input id="orderId" type="text" class="form-control" placeholder="输入订单号">
                            </div>
                        </div>

                        <div class="myBtn-content">
                            <button id="search" type="button" class="btn btn-primary">搜索</button>
                        </div>
                    </div>

                    <table id="testTable" class="table table-bordered table-condensed">
                        <thead>
                        <tr>

                        </tr>
                        </thead>
                        <tbody>

                        <#list PointsPage.list as PointsPage>
                        <tr>
                            <td>${PointsPage.pointsId}</td>
                            <td>${PointsPage.orderId}</td>
                            <td>${PointsPage.userId}</td>
                            <td>${PointsPage.pointsRecord}</td>
                            <td>${PointsPage.getAllFlag()}</td>
                            <td>${PointsPage.getCreateTimeToString()}</td>
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
                        <li><a href="/gudao/seller/point/list?page=${currentPage - 1}&size=${size}">上一页</a></li>
                    </#if>

                    <#list 1..PointsPage.getShowPages() as index>
                        <#if currentPage == index>
                            <li class="disabled"><a href="#">${index}</a></li>
                        <#else>
                            <li><a href="/gudao/seller/point/list?page=${index}&size=${size}">${index}</a></li>
                        </#if>
                    </#list>

                    <#if currentPage gte PointsPage.getShowPages()>
                        <li class="disabled"><a href="#">下一页</a></li>
                    <#else>
                        <li><a href="/gudao/seller/point/list?page=${currentPage + 1}&size=${size}">下一页</a></li>
                    </#if>
                    </ul>
                </div>
            </div>
        </div>
    </div>

</div>
</body>
</html>