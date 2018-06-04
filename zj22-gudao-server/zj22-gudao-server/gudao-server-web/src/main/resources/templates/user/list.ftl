<html>
<#include "../common/header.ftl">
<#--引入的css文件-->
<link href="/gudao/css/bootstrap.min.css" rel="stylesheet" />
<link href="/gudao/css/bootstrap-table.min.css" rel="stylesheet">
<#--引入的js文件-->
<script src="/gudao/js/jquery.min.js"></script>
<script src="/gudao/js/bootstrap.min.js"></script>
<script src="/gudao/js/bootstrap-table.min.js"></script>
<script src="/gudao/js/bootstrap-table-zh-CN.min.js"></script>
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
            var nickname = $("#nickname").val();
            alert(nickname);
            $.ajax({
                url: "/gudao/seller/user/list", //要将此次请求提交到哪个服务端去
                data: { "nickname": nickname }, //给服务端带的数据，可以没有，也可以多个
                type: "post", //传递的方式
                dataType: "json", //数据传递的格式
                success: function (data) {
                    alert(data);
                }
            });

        });

    });




//    $(function () {
//        $('#search').click(function () {
//            var nickname = $("#nickname").val();
//            location.href="/gudao/seller/user/list?nickname="+ nickname;
//            $('#testTable').bootstrapTable('refresh', { url: '/gudao/seller/user/list?nickname=' + nickname });
//        });
//
//    });
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
                            <label class="myLabel-content">昵称：</label>
                            <div class="myText-content">
                                <input id="nickname" name="nickname" type="text" class="form-control" placeholder="请输入要查找的用户昵称">
                            </div>
                        </div>

                        <div class="myBtn-content">
                            <button id="search" type="button" class="btn btn-primary">查询</button>
                        </div>
                    </div>

                    <table id="testTable" class="table table-bordered table-condensed">
                        <thead>
                        <tr>
                            <th>用户id</th>
                            <th>用户openid</th>
                            <th>昵称</th>
                            <th>性别</th>
                            <th>国家</th>
                            <th>省</th>
                            <th>城市</th>
                            <th>头像</th>
                            <th>兑换积分</th>
                            <th>总积分</th>
                            <th>创建时间</th>
                        </tr>
                        </thead>
                        <tbody>

                        <#list wechatUserPage.list as wechatUser>
                        <tr>
                            <td>${wechatUser.userId}</td>
                            <td>${wechatUser.openid}</td>
                            <td>${wechatUser.nickname}</td>
                            <td>${wechatUser.sex}</td>
                            <td>${wechatUser.country}</td>
                            <td>${wechatUser.province}</td>
                            <td>${wechatUser.city}</td>
                            <td><img height="100" width="100" src="${wechatUser.headImgUrl}" alt=""></td>
                            <td>${wechatUser.integral}</td>
                            <td>${wechatUser.total}</td>
                            <td>${wechatUser.getCreateTimeToString()}</td>
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
                        <li><a href="/gudao/seller/user/list?page=${currentPage - 1}&size=${size}">上一页</a></li>
                    </#if>

                    <#list 1..wechatUserPage.getShowPages() as index>
                        <#if currentPage == index>
                            <li class="disabled"><a href="#">${index}</a></li>
                        <#else>
                            <li><a href="/gudao/seller/user/list?page=${index}&size=${size}">${index}</a></li>
                        </#if>
                    </#list>

                    <#if currentPage gte wechatUserPage.getShowPages()>
                        <li class="disabled"><a href="#">下一页</a></li>
                    <#else>
                        <li><a href="/gudao/seller/user/list?page=${currentPage + 1}&size=${size}">下一页</a></li>
                    </#if>
                    </ul>
                </div>
            </div>
        </div>
    </div>

</div>
</body>
</html>