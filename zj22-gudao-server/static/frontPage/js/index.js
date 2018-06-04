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
});
