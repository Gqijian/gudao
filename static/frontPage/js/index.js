/**
 * Created by Administrator on 2018/3/17 0017.
 */
$(function(){

    /*返回顶部*/
    $(window).scroll(function() {
        if($(window).scrollTop() >= 100){
            $('.actGotop').fadeIn('slow');
        }else{
            $('.actGotop').fadeOut('slow');
        }
    });
    $('.actGotop').click(function(){$('html,body').animate({scrollTop: '0px'}, 300);});

    /*点击全部 下拉列表的出现和消失*/
    $(".xlmenu").on("click",function () {
        $(".theme-popover-mask").css("display","block");
        var nums = $(".ycmenu > ul > li ").length;
        $(".ycmenu").css("display","block").animate({height:nums * 2+'rem'},300);
    });
    $(".theme-popover-mask").on("click",function () {
        $(this).css("display","none");
        $(".ycmenu").animate({height:0},300,function () {
            $(".ycmenu").css("display","none");
        });

    })
});
