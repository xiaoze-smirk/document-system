/**
 * Created by 傅彰炜 on 2017/11/7.
 */
$(document).ready(function(){

    /********************************轮播图*******************************/
    var slideShow=$(".slideShow").find("div"), //获取最外层框架的名称
        // ul=slideShow.find("div"),
        showadvantage=$(".show_advantage").find("div"),
        showNumber=$(".showNav").find("span");//获取按钮
    var timer=null; //定时器返回值，主要用于关闭定时器
    var iNow=0; //iNow为正在展示的图片索引值，当用户打开网页时首先显示第一张图，即索引值为0

    showNumber.on("click",function(){  //为每个按钮绑定一个点击事件
        $(this).addClass("active").siblings().removeClass("active"); //按钮点击时为这个按钮添加高亮状态，并且将其他按钮高亮状态去掉
        var index=$(this).index(); //获取哪个按钮被点击，也就是找到被点击按钮的索引值
        iNow=index;
        slideShow.eq(iNow).addClass("active_img").siblings().removeClass("active_img");
        showadvantage.eq(iNow).addClass("active_advantage").siblings().removeClass("active_advantage");
    });

    timer=setInterval(function(){ //打开定时器
        iNow++;    //让图片的索引值次序加1，这样就可以实现顺序轮播图片
        if(iNow>showNumber.length-1){ //当到达最后一张图的时候，让iNow赋值为第一张图的索引值，轮播效果跳转到第一张图重新开始
            iNow=0;
        }
        showNumber.eq(iNow).trigger("click"); //模拟触发数字按钮的click
    },6000); //6000为轮播的时间



    $(".btnReg").click(function () {
        $(location).attr('href','register.html');
    })
    $(".btn_login").click(function () {
        $(location).attr('href','main/main.html');
    })

    /****************************第三方登录***************************/
    var imgHover = ["../images/login/QQ.png","../images/login/weixin.png","../images/login/weibo.png"];
    var images = ["../images/login/QQ_gray.png","../images/login/weixin_gray.png","../images/login/weibo_gray.png"];
    $(".tAccount").find(".third_account").mouseover(function () {
        var index = $(this).index();
        $(this).attr("src",imgHover[index-1]);
    });
    $(".tAccount").find(".third_account").mouseleave(function () {
        var index = $(this).index();
        $(this).attr("src",images[index-1]);
    });

    $(".forget_pwd").click(function () {
        $(location).attr("href","${pageContext.request.contextPath}/../retrievePwd.html");
    })

})
