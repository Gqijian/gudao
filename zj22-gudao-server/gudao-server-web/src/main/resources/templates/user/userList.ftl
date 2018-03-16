<html lang="en">
<#include "../common/header.ftl">
    <style type="text/css">

        .searchbox{
            margin:-3px;
        }
        .*{
            margin: 0;
            padding: 0;
        }
    </style>

    <script type="text/javascript">
        $(function(){

            $('#dg').datagrid({
                url: '/gudao/seller/user/list.action',
                fitColumns:true,
                nowrapL:true,
                idField:'userId',
                rownumbers:true,//显示行号
                pagination:true,
                pageSize:10,
                pageList:[2,5,10,20],
                loadMsg:'数据加载中...',

                onLoadError: function () {
                    layer.msg("没有查询到记录！");
                },

                queryParams: {
                    openid: '%%',
                    nickname: '%%'
                },


                toolbar: [{
                    iconCls: 'icon-edit',
                    text:'查看积分兑换详情',
                    handler: function(){
                        alert('后期开放');
                    }
                },'-',{
                    text:"名称：<input type='text' id='openid' name='openid' placeholder='输入您要查找的用户openid'/>"
                },'-',{
                    text:"名称：<input type='text' id='nickname' name='nickname'/>"
                }],


                columns : [ [
                    {
                        field:'ck',
                        checkbox:true,
                    }, {
                        field : 'openid',
                        title : '用户openid',
                        align : 'center',
                        width : 100,
                        formatter: function(value){
                            return "<span title='" + value + "'>" + value + "</span>";
                        }
                    },{
                        field : 'nickname',
                        title : '昵称',
                        align : 'center',
                        width : 50,
                        formatter: function(value){
                            return "<span title='" + value + "'>" + value + "</span>";
                        }
                    },{
                        field : 'sex',
                        title : '性别',
                        align : 'center',
                        width : 50,
                        formatter: function(value){
                            if(value == 0)
                                return "男";
                            else
                                return "女";
                        }
                    },{
                        field : 'country',
                        title : '国家',
                        align : 'center',
                        width : 50
                    }, {
                        field : 'province',
                        title : '省',
                        align : 'center',
                        width : 50,

                    }, {
                        field : 'city',
                        title : '城市',
                        align : 'center',
                        width : 50
                    }, {
                        field : 'headImgUrl',
                        title : '头像',
                        align : 'center',
                        width : 100,
                        formatter:function(value,row){
                            var str = "";
                            if(value!="" || value!=null){
                                str =  "<img style=\"height: 70px;width: 100px;\" src=\""+"/gudao/upload/"+value+"\"/>";
                                return str;
                            }
                        }
                    }, {
                        field : 'integral',
                        title : '兑换积分',
                        align : 'center',
                        width : 50,
                    }, {
                        field : 'total',
                        title : '总积分',
                        align : 'center',
                        width : 50,
                    }, {
                        field : 'createTime',
                        title : '创建时间',
                        align : 'center',
                        width : 100,
                        formatter: function(value){
                            if (value){
                                return "<span title='" + new Date(parseInt(value)).toLocaleString() + "'>" + new Date(parseInt(value)).toLocaleString() + "</span>";
                            }else{
                                return '';
                            }
                        }
                    }] ],




            });

            $('#nickname').searchbox({
                searcher:function(value,name){
                    alert(value + "," + name);
                    $('#dg').datagrid('load',{
                        nickname:'%'+value+'%',
                        openid:'%'+$('#openid').val()+'%'
                    });
                },
                prompt:'搜索用户'
            });

        });


    </script>

<body>
<table id="dg"></table>
</body>

</html>
