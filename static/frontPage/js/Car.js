/**
 * Created by Administrator on 2018/3/31 0031.
 */
function Car() {
    this.products = [];
    this.allPrice = 0;
}

Car.prototype = {
    bindDom:function () {
        var  goodobj = JSON.parse(sessionStorage.good);
        var str=''
        for(var i= 0;i < goodobj.length;i++){
            str+='<li>';
            str+='<span class="circle"></span>';
            str+='<img src="'+goodobj[i].images[0]+'">';
            str+='<div>'
            str+='<p class="title"><a href=""><font>'+goodobj[i].name+'</font>'+goodobj[i].description+'</a></p>';
            str+='<p class="money"><font>'+goodobj[i].price+'</font>';
            str+='<input class="add" type="button" value="+" /><input class="num" type="text" value="1" /><input class="del" type="button" value="-" /></p>';
            str+=' </div>';
            str+='</li>';
        }
        $("#main_con-goods-ul").html(str);
    },
    bindEvent:function () {
        
    },
    getPrice:function () {
        
    },
    addNum:function () {
        
    },
    delNum:function () {
        
    }
}