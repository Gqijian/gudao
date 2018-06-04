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
                fit:true,
                autoRowHeight:false,//定义是否设置基于该行内容的行高度
                pagePosition:"bottom",
                pageSize:10,
                pageList: [5, 10, 20, 50],
                loadMsg:'数据加载中...',

                onLoadError: function () {
                    layer.msg("没有查询到记录！");
                },

                queryParams: {
//                    openid: '%%',
                    nickname: '%%'
                },


                toolbar: [{
                    iconCls: 'icon-edit',
                    text:'查看积分兑换详情',
                    handler: function(){
                        alert('后期开放');
                    }
                },'-',{
                    iconCls: 'icon-reload',
                    text:'按积分排序',
                    handler: function(){
                        $("#dg").datagrid("load",{
                            flag : '1'
                        });
                        $("#dg").datagrid("clearSelections");
                    }
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
                        hidden:true,
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
                                return "女";
                            else
                                return "男";
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
                    },
//                    {
//                        field : 'headImgUrl',
//                        title : '头像',
//                        align : 'center',
//                        width : 100,
//                        formatter:function(value,row){
//                            var str = "";
//                            if(value!="" || value!=null){
//                                str =  "<img style=\"height: 70px;width: 100px;\" src=\""+value+"\"/>";
//                                return str;
//                            }
//                        }
//                    },
                    {
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
                        title : '关注时间',
                        align : 'center',
                        width : 100,
                        formatter: function(value){
                            if (value){
                                return "<span title='" + new Date(parseInt(value)).toLocaleString() + "'>" + new Date(parseInt(value)).toLocaleString() + "</span>";
                            }else{
                                return '';
                            }
                        }
                    }, {
                        field: 'opt',
                        title: '操作',
                        width: 50,
                        align: 'center',
                        formatter:function(value,rec){
                            var btn = '<a class="editcls" onclick="editRow()" href="javascript:void(0)">发红包</a>';
                            return btn;
//            onclick="editRow(\''+rec.projectname+'\',\''+rec.projectpackage+'\')"
                        }
                    }] ],




            });

            $('#nickname').searchbox({
                searcher:function(value,name){
//                    alert(value + "," + name);
                    $('#dg').datagrid('load',{
                        nickname:'%'+value+'%',
//                        openid:'%'+$('#openid').val()+'%'
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
