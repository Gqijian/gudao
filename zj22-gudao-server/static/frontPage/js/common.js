/**
 * Created by Administrator on 2018/4/29 0029.
 */
var Base = {
    //首页获取后台数据地址 index.html
    indexUrl:"http://yuanpeng.free.ngrok.cc/gudao/product/allproducts",

    //商品详情获取地址 spxq.html
    spxqUrl:'http://yuanpeng.free.ngrok.cc/gudao/product/productInfo?id=',

    //添加购物车地址
    addCarUrl:"http://10.18.39.145:9876/gudao/cart/add?num=1&id=",

    //购物车页面商品
    carUrl:'http://10.18.39.145:9876/gudao/cart/carts',

    //提交订单页面
    orderUrl:'http://yuanpeng.free.ngrok.cc/gudao/order/createOrder',

    //获取订单详情
    orderInfoUrl:'http://10.18.39.145:9876/gudao/order/orderDetail?orderId=',

    //配置微信接口信息
    wxConfUrl:'http://yuanpeng.free.ngrok.cc/gudao/static/getAddress?orderId=',

    //提交订单地址
    orderUpdata:"http://yuanpeng.free.ngrok.cc/gudao/order/updateOrder",

    //订单详情页面
    orderInfo:"http://yuanpeng.free.ngrok.cc/gudao/frontPage/orderInfo.html?orderId=",

    //所有的订单
    orderAllUrl:"http://yuanpeng.free.ngrok.cc/gudao/order/myOrders",

    //微信授权地址
    wxsqUrl:"http://yuanpeng.free.ngrok.cc/gudao/weixin/auth",
    //删除商品地址
    delUrl:'http://10.18.39.145:9876/gudao/cart/delete?productIds=',
    //获取邮费地址
    yfUrl:'http://yuanpeng.free.ngrok.cc/gudao/order/KdMessage'

}

/*以下为一些公共方法*/
//查询
function queryURl(url,fn){
    $.ajax({
        type:"get",
        url:url,
        dataType:"jsonp",
        jsonp:"cb",
        jsonpCallback:"callback",
        success:function (data) {
            fn(data);
        },error:function (e) {

        }
    })
}
//提交
function postUrl(url,data,fn) {
    $.ajax({
        type:"post",
        url:url,
        data: JSON.stringify(data),
        dataType : "JSON",
        contentType: "application/json;charset=utf-8",
        success:function (data) {
            fn(data);
        },error:function (e) {

        }

    })

}

//弹框
function webToast() {
    //默认设置
    var dcfg={
        msg:"提示信息",
        postion:"top",//展示位置，可能值：bottom,top,middle,默认top：是因为在mobile web环境下，输入法在底部会遮挡toast提示框
        time:3000,//展示时间
    };
    //*********************以下为参数处理******************
    var len = arguments.length;
    var arg0 =arguments[0];
    if(arguments.length>0){
        dcfg.msg =arguments[0];
        dcfg.msg=arg0;

        var arg1 =arguments[1];
        var regx = /(bottom|top|middle)/i;
        var numRegx = /[1-9]\d*/;
        if(regx.test(arg1)){
            dcfg.postion=arg1;
        }else if(numRegx.test(arg1)){
            dcfg.time=arg1;
        }

        var arg2 =arguments[2];
        var numRegx = /[1-9]\d*/;
        if(numRegx.test(arg2)){
            dcfg.time=arg2;
        }
    }
//*********************以上为参数处理******************
    var ret = "<div class='web_toast'><div class='cx_mask_transparent'></div>" + dcfg.msg + "</div>";
    if ($(".web_toast").length <= 0) {
        $("body").append(ret);
    } else {//如果页面有web_toast 先清除之前的样式
        $(".web_toast").css("left","");

        ret = "<div class='cx_mask_transparent'></div>" + dcfg.msg;
        $(".web_toast").html(ret);
    }
    var w = $(".web_toast").width(),ww = $(window).width();
    $(".web_toast").fadeIn();
    $(".web_toast").css("left",(ww-w)/2-20);
    if("bottom"==dcfg.postion){
        $(".web_toast").css("bottom",50);
        $(".web_toast").css("top","");//这里为什么要置空，自己琢磨，我就不告诉
    }else if("top"==dcfg.postion){
        $(".web_toast").css("bottom","");
        $(".web_toast").css("top",50);
    }else if("middle"==dcfg.postion){
        $(".web_toast").css("bottom","");
        $(".web_toast").css("top","");
        var h = $(".web_toast").height(),hh = $(window).height();
        $(".web_toast").css("bottom",(hh-h)/2-20);
    }
    setTimeout(function() {
        $(".web_toast").fadeOut();
    }, dcfg.time);
}

//获取 url中的参数
function GetRequest() {
    var url = location.search;
    console.log(url)//获取url中"?"符后的字串
    var theRequest = new Object();
    if (url.indexOf("?") != -1) {
        var str = url.substr(1);
        strs = str.split("&");
        for (var i = 0; i < strs.length; i++) {
            theRequest[strs[i].split("=")[0]] = unescape(strs[i].split("=")[1]);
        }
    }
    return theRequest;
}



