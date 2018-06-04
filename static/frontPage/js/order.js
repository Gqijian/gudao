
/**
 * Created by Administrator on 2018/5/1 0001.
 */


function checkTime(time) {
    var nowTime = new Date().getTime();
    var remainTime =1800000 - (nowTime - time);
    if (remainTime > 0){
        var intDiff = parseInt(remainTime /1000);//倒计时总秒数量
        timer(intDiff);
        return true;
    }else {
        return false;
    }
}
function timer(intDiff){
    var timer = setInterval(function(){
        var minute=0,
            second=0;//时间默认值
        if(intDiff > 0){
            /* day = Math.floor(intDiff / (60 * 60 * 24));
             hour = Math.floor(intDiff / (60 * 60)) - (day * 24);*/
            minute = Math.floor(intDiff / 60);
            second = Math.floor(intDiff - minute * 60);
            if (minute <= 9) minute = '0' + minute;
            if (second <= 9) second = '0' + second;
            $(".orderTime span").eq(0).html(minute);
            $(".orderTime span").eq(2).html(second);
            intDiff--;
        }else {
            clearInterval(timer);
            $(".orderState").css("display","inline-block");
            $(".orderTime").css("display","none");
        }
    }, 1000);
}




$(function(){

   /* data={
        orderInfo:[
            {
                time:1525175682388,
                orderId:1,
                allPrice:120,
                shopList:[
                    {
                       prductId:1,
                       imgSrc:'images/u20.png',
                        name:'小面包',
                        num:1,
                        price: 100,
                    },{
                        prductId:1,
                        imgSrc:'images/u20.png',
                        name:'小面包',
                        num:1,
                        price: 100,

                    }
                ]

            },{
                time:1525168136676,
                orderId:1,
                allPrice:120,
                shopList:[
                    {
                        prductId:1,
                        imgSrc:'images/u20.png',
                        name:'小面包',
                        num:1,
                        price: 100,
                    },{
                        prductId:1,
                        imgSrc:'images/u20.png',
                        name:'小面包',
                        num:1,
                        price: 100,

                    }
                ]
            }
        ]
    };
    insert(data);
    function insert(data) {
        var str="";
        for (var i=0;i<data.orderInfo.length;i++){
            var state= checkTime(data.orderInfo[i].time)?'orderInfo.html?orderId='+data.orderInfo[i].orderId:'javascript:false';
            var str1="";
            str+='<a href="'+state+'" ><figure>';
            str+='<div class="orderHeader">';
            str+= '<div class="fl"><div>店铺：酱豆</div><div>订单编号：<span class="orderId">E201831512564563165</span></div></div>';
            str+='<div class="fr"> 订单状态：';
            if(state != 'javascript:false'){//如果为true 则 显示时间
                str+='<span class="orderState state">交易关闭</span>';
                str+='<span class="orderTime"><span>20</span><span>:</span><span>00</span><span></span></span>';

            }else {
                str+='<span class="orderState">交易关闭</span>'
            }
            str+='</div></div>';
            str+='<div class="orderInfo clearfloat">';

            for(var j = 0;j<data.orderInfo[i].shopList.length;j++){
                str1 += '<ul class="clearfloat">';
                str1 += '<li class="li1"><div><img src="'+data.orderInfo[i].shopList[j].imgSrc+'" alt=""style="width: 60px;height: 60px"></div></li>';
                str1 += '<li class="li2"><span>法式小面包法式小面包法式小面包</span></li>';
                str1 += '<li class="li3"><span class="num">×1</span><br/>￥<span style="color: red" data-good_price="100.00">100.00</span></li>';
                str1 += '</ul>';
            }
            str+=str1;
            str+='<div>总价：<span class="allPrice">1000</span></div>';
            str+='</div></figure>';

        }
        $(".container").html(str);
    }

*/



  queryURl(Base.orderAllUrl,function (data) {
       for (var i=0;i<data.length;i++){
          var state= checkTime(data.time)?'orderInfo.html?orderId='+data[i].orderId:false;
           var str="";
           var str1="";
           str+='<a href="'+state+'" <figure>';
           str+='<div class="orderHeader">';
           str+= '<div class="fl"><div>店铺：酱豆</div><div>订单编号：<span class="orderId">E201831512564563165</span></div></div>';
           str+='<div class="fr"> 订单状态：<span class="orderState">交易关闭</span>';
           str+='<span class="orderTime"><span>20</span><span>:</span><span>00</span><span></span></span>';
           str+='</div></div>';
           str+='<div class="orderInfo clearfloat">';

           for(var j = 0;j<data.orderDetailList.length;j++){
               str1 = '<ul class="clearfloat">';
               str1 = '<li class="li1"><div><img src="images/u61.png" alt=""style="width: 60px;height: 60px"></div></li>';
               str1 = '<li class="li2"><span>法式小面包法式小面包法式小面包</span></li>';
               str1 = '<li class="li3"><span class="num">×1</span><br/>￥<span style="color: red" data-good_price="100.00">100.00</span></li>';
               str1 = '</ul>';
           }
           str+=str1;
           str+='</div></figure>';
       }
       $(".container").html(str);

    });
});