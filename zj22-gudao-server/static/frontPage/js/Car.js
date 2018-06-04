/**
 * Created by Administrator on 2018/3/31 0031.
 */
function Car() {
}

Car.prototype = {
    bindDom:function () {
        var that = this;
        queryURl(Base.carUrl,function (data) {
            var str = '';
            if (data.length < 1) {
                $(".main_con").css("display", "none");
                $(".no_goods").css("display", "block");
            } else {
                for (var i = 0; i < data.length; i++) {
                    str += '<li data-id="' + data[i].productId + '">';
                    str += '<span class="circle"></span>';
                    str += '<img src="../upload/' + data[i].productIconOne + '">';
                    str += '<div>';
                    str += '<p class="title"><a href="javascript:false;" ><font class="name">' + data[i].productName + '</font><span class="info"></span></a></p>';
                    str += '<p class="money"><font  class="price">' + data[i].productPrice + '</font>';
                    str += '<input class="add" type="button" value="+" /><input class="num" type="text" value="1" /><input class="del" type="button" value="-" /></p>';
                    str += '</div>';
                    str += '</li>';
                }
                $("#main_con-goods-ul").html(str);
                /*购物车货物列别模块自适应配比*/
                $(".main_con_goods ul li div").width($(window).width()-140-10+"px");
                that.bindEvent();
            }
        });
    },
    /*显示删除按钮和全选*/
    bindEvent:function () {
        var that = this;
        //显示删除按钮 和改变 选中颜色
        $(".main_con_goods li .circle").click(function() {
            var num = $(this).parent().index();//获取li的序列
            var circle = $(".main_con_goods ul li").eq(num).find(".circle");
            if (circle.css("background-color") == "rgba(0, 0, 0, 0)") {
                circle.css("background-color", "#bf392a");
                circle.css("border", "1px solid #bf392a");
            } else {
                circle.css("background-color", "rgba(0, 0, 0, 0)");
                circle.css("border", "1px solid #bab9b9");
            }
            that.showDelBtn();//显示删除按钮
            that.getPrice();//得到总价和数量
        });

        /*全选按钮点击事件*/
        $(".main_con_allchoose .circle").click(function() {
            var nums = $(".main_con_goods li").length;
            if ($(this).css("background-color") == "rgba(0, 0, 0, 0)") {
                $(this).css("background-color", "#bf392a");
                $(this).css("border", "1px solid #bf392a");
                //全选选中时，需要选中所有商品列表
                for (var i = 0; i < nums; i++) {
                    $(".main_con_goods ul li").eq(i).find(".circle").css("background-color", "#bf392a");
                    $(".main_con_goods ul li").eq(i).find(".circle").css("border", "1px solid #bf392a");
                }
                //当选中一个商品时需要显示删除按钮
                $(".main_con_allchoose img").css("display", "block");
                that.getPrice();

            }else {
                $(this).css("background-color", "rgba(0, 0, 0, 0)");
                $(this).css("border", "1px solid #bab9b9");

                //全选选中时，需要选中所有商品列表
                for (var i = 0; i < nums; i++) {
                    $(".main_con_goods ul li").eq(i).find(".circle").css("background-color", "rgba(0, 0, 0, 0)");
                    $(".main_con_goods ul li").eq(i).find(".circle").css("border", "1px solid #bab9b9");
                }

                //当选中一个商品时需要隐藏删除按钮
                $(".main_con_allchoose img").css("display", "none");
            }

        });

        //增加商品数量
        $(".main_con_goods li div .money .add").click(function(){
            var num = $(this).parent().parent().parent().index();//获取li的序号
            var value = parseInt($(".main_con_goods ul li").eq(num).find(".money").find(".num").val());
            value++;
            if(value<=99){
                $(".main_con_goods ul li").eq(num).find(".money").find(".num").val(value);

            }
            that.getPrice();
        });

        //减少商品数量
        $(".main_con_goods li div .money .del").click(function(){
            var num = $(this).parent().parent().parent().index();//获取li的序号
            var value = parseInt($(".main_con_goods ul li").eq(num).find(".money").find(".num").val());            value--;
            if(value>=0){
                $(".main_con_goods ul li").eq(num).find(".money").find(".num").val(value);
            }
            that.getPrice();
        });

        //删除弹框
        $("#del_goods").click(function () {
            $(".del_tc").css("display","block");
        });
        //取消删除
        $("#del_goods_cancel").click(function () {
            $(".del_tc").css("display","none");
        });
        //删除
        $("#ok").click(function () {
            var nums = $(".main_con_goods li").length;
            var productIds = new Array();
            var lis = new  Array();
            for (var i = 0; i < nums; i++) {
                if( $(".main_con_goods li").eq(i).find(".circle").css("background-color") == "rgb(191, 57, 42)" ){
                  /*  $(".main_con_goods ul > li").eq(i).remove();*/
                    lis.push(i);
                    productIds.push($(".main_con_goods ul li").eq(i).data("id"));
                }
            }
            $(".del_tc").css("display","none");//关闭弹框
            for(var i = 0;i<lis.length;i++){
                $(".main_con_goods ul > li").eq(i).css("display","none");
            }
            queryURl(Base.delUrl+''+productIds,function (data) {
                webToast("删除成功","middle",300);
                window.location.reload(); //重新加载
            });

        });

        //提交订单
        $("#submitOrder").click(function () {
            var nums = $(".main_con_goods li").length;
            var goods = new Array();
            var memberfilter = new Array();//商品信息
            memberfilter[0] = "price";
            memberfilter[1] ='productQuantity';
            memberfilter[2] = 'productId';
            memberfilter[3] = 'productName';
            memberfilter[4] = 'productIconOne';
            for (var i = 0; i < nums; i++) {
                if( $(".main_con_goods ul li").eq(i).find(".circle").css("background-color") == "rgb(191, 57, 42)" ){
                    var pro = new Object();
                    var parent = $(".main_con_goods ul li").eq(i);
                    pro.productName =parent.find(".name").html();
                    pro.price = parent.find(".price").html();
                    pro.productIconOne = parent.find("img").attr("src");
                    pro.productQuantity =parent.find(".money").find(".num").val();
                    pro.productId= parent.data("id");
                    var jsonText = JSON.stringify(pro, memberfilter);
                    goods.push(JSON.parse(jsonText));
                }
            }
            if(goods.length === 0){
                webToast("请选择要购买的商品。","middle",300);
            }else{
                var orderDTO  = {
                    orderDetailList:goods
                };
                postUrl(Base.orderUrl,orderDTO,function(data){
                        //console.log(""+Base.orderInfo+''+data.orderId);
                        location.href = ""+Base.orderInfo+''+data.orderId;
                });



            }
        })
    },
    showDelBtn:function () {

        var nums = $(".main_con_goods li").length;//获取li的长度 商品的数量
        //当选中一个商品时需要显示删除按钮
        for (var i = 0; i < nums; i++) {
            if ($(".main_con_goods ul li").eq(i).find(".circle").css("background-color") == "rgb(191, 57, 42)") {
                $(".main_con_allchoose img").css("display", "block");
                break;
            }
            $(".main_con_allchoose img").css("display", "none");
        }

    },
    getPrice:function () {
        //当选中一个商品时需要计算一次商品的数量和总价
        var nums = $(".main_con_goods li").length;//获取li的长度 商品的数量
        var pricss = 0;//总价格
        var num_temp = 0;//商品数量
        var num_all=0;
        var price_temp = 0;//商品单价
        for (var i = 0; i < nums; i++) {
            if($(".main_con_goods ul li").eq(i).find(".circle").css("background-color") == "rgb(191, 57, 42)"){
                num_temp = parseInt($(".main_con_goods ul li").eq(i).find(".num").val());//获取商品数量
                num_all += num_temp;
                var tmep = $(".main_con_goods ul li").eq(i).find("div").find(".money").find(".price").html();//获取商品价格
                tmep = tmep.substring(0,tmep.length);//截取价钱
                price_temp = parseFloat(tmep);
                pricss+= num_temp*price_temp;//总的价格
            }
        }
        $(".settlement_left").html("<font class=\"zongji\">总计：</font><font class=\"money\">￥"+pricss.toFixed(2)+"</font><br />（共"+num_all+"件，不包含运费）");

    }
}