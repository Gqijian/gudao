/**
 * Created by Administrator on 2018/3/31 0031.
 */
function Car() {
    this.products = [];
    this.allPrice = 0;

}

Car.prototype = {
    bindDom:function () {
        var that= this;
        $.ajax({
            type:"get",
            url:"http://localhost:9876/gudao/cart/carts",
            dataType:"jsonp",
            jsonp:"cb",
            jsonpCallback:"callback",
            success:function(data){
                var str='';
                for(var i= 0;i < data.length;i++){
                    str+='<li data-id="'+data[i].productId+'">';
                    str+='<span class="circle"></span>';
                    str+='<img src="../upload/'+data[i].productIconOne+'">';
                    str+='<div>';
                    str+='<p class="title"><a href="javascript:false;" ><font id="name">'+data[i].productName+'</font><span id="info">'+data[i].description+'</span></a></p>';
                    str+='<p class="money"><font  id="price">'+data[i].productPrice+'</font>';
                    str+='<input class="add" type="button" value="+" /><input class="num" type="text" value="1" /><input class="del" type="button" value="-" /></p>';
                    str+='</div>';
                    str+='</li>';
                }
                $("#main_con-goods-ul").html(str);
                var $main_con_list=$(".main_con_goods ul > li").length;
                if($main_con_list === 0){
                    $(".main_con").css("display","none");
                    $(".no_goods").css("display","block");
                }else {

                    that.bindEvent();
                }
            }


        });

        /*var  goodobj = JSON.parse(sessionStorage.good);
         var str=''
         for(var i= 0;i < goodobj.length;i++){
         str+='<li>';
         str+='<span class="circle"></span>';
         str+='<img src="'+goodobj[i].images[0]+'">';
         str+='<div>'
         str+='<p class="title"><a href=""><font>'+goodobj[i].name+'</font>'+goodobj[i].info+'</a></p>';
         str+='<p class="money"><font>'+goodobj[i].price+'</font>';
         str+='<input class="add" type="button" value="+" /><input class="num" type="text" value="1" /><input class="del" type="button" value="-" /></p>';
         str+=' </div>';
         str+='</li>';
         }
         $("#main_con-goods-ul").html(str);*/
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
            var ids = new Array();
            for (var i = 0; i < nums; i++) {
                if( $(".main_con_goods li").eq(i).find(".circle").css("background-color") == "rgb(191, 57, 42)" ){
                    $(".main_con_goods ul > li").eq(i).remove();
                    ids.push($(".main_con_goods ul li").eq(i).data("id"));
                }
            }
            $.ajax({
                type:"get",
                url:url,
                dataType:"json",
                success:function (data) {
                    alert("删除成功");
                },error:function (e) {

                }
            });
            $(".del_tc").css("display","none");//关闭弹框
            that.bindDom();//重新加载
        })

        //提交订单
        $("#submitOrder").click(function () {
            var nums = $(".main_con_goods li").length;
            var goods = new Array();
            var memberfilter = new Array();//商品信息
            memberfilter[0] = "info";
            memberfilter[1] = "price";
            memberfilter[2] = "images";
            memberfilter[3] = "num";
            memberfilter[4] ='name';
            memberfilter[5] = 'id';
            memberfilter[6]="phone";
            memberfilter[7]="address";
            memberfilter[8]="openid";
            memberfilter[9]="proName";
            for (var i = 0; i < nums; i++) {
                if( $(".main_con_goods ul li").eq(i).find(".circle").css("background-color") == "rgb(191, 57, 42)" ){
                    var pro = new Object();
                    pro.phone= 1234567890;
                    pro.address='1212';
                    pro.openid="34232352m35jhkhjkhjk";
                    pro.address="adfasdfsdf";
                    pro.name="sdfasd";
                    pro.proName = $("#name").html();
                    pro.info = $("#info").html();
                    pro.price = $("#price").html();
                    pro.images = $(".main_con_goods ul li").eq(i).find("img")[0].src;
                    pro.num =parseInt($(".main_con_goods ul li").eq(i).find(".money").find(".num").val());
                    pro.id= $(".main_con_goods ul li").eq(i).data("id");
                    var jsonText = JSON.stringify(pro, memberfilter);
                    goods.push(JSON.parse(jsonText));
                }
            }
            $.ajax({
                type:"post",
                url:"http://localhost:9876/gudao/order/createOrder",
                data:goods.toString(),
                dataType:"json",
                success:function (data) {
                    location.href = "orderInfo.html";
                },error:function (e) {

                }
            });
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
        var price_temp = 0;//商品单价
        for (var i = 0; i < nums; i++) {
            if($(".main_con_goods ul li").eq(i).find(".circle").css("background-color") == "rgb(191, 57, 42)"){
                num_temp += parseInt($(".main_con_goods ul li").eq(i).find(".num").val());//获取商品数量
                var tmep = $(".main_con_goods ul li").eq(i).find("div").find(".money").find("font").html();//获取商品价格
                tmep = tmep.substring(0,tmep.length);//截取价钱
                price_temp += parseFloat(tmep);
                pricss+= num_temp*price_temp;//总的价格
            }
        }
        $(".settlement_left").html("<font class=\"zongji\">总计：</font><font class=\"money\">￥"+pricss+"</font><br />（共"+num_temp+"件，不包含运费）");

    },
}