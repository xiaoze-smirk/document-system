/**
 * Created by 傅彰炜 on 2017/11/12.
 */
$(document).ready(function() {
    /****************************设置页面布局不变************************/
    var bwidth = window.screen.width;
    $(".box_title").width(bwidth);
    $(".box_detail").width(bwidth * 0.85);

    /*****************************设置信息跳转****************************/

    // $(".settingInfo").click(function () {
    // window.location.href="settingInfo.html";
    // window.location.replace("settingInfo.html");
    // $(location).attr('href', 'settingInfo.html');
    // $(window).attr('location','settingInfo.html');
    // $(location).prop('href', 'settingInfo.html');
    // })
    /*跳转至个人信息页面*/
    var setInfo = $(".personal_info").find("ul").find("li");
    setInfo.click(function () {
        var setNum = $(this).index();
        $(this).find("a").attr("href","../settingInfo.html?setNum="+setNum);
    });
    /*注销账号跳转至登录页面*/
    $(".logout").click(function () {
        $(location).attr("href","../login.html");
    })
    /********************************菜单*******************************/
    /*菜单*/
    var $obj = $("#nav_dot");
    $obj.find("h4").hover(function () {
        $(this).addClass("hover");
    }, function () {
        $(this).removeClass("hover");
    });
    /*一级菜单点击事件*/
    $obj.find("h4").click(function () {
        var $div = $(this).siblings(".list-item");
        if ($(this).parent().hasClass("selected")) {
            $div.slideUp(600);
            $(this).parent().removeClass("selected");
        }
        if ($div.is(":hidden")) {
            $("#nav_dot li").find(".list-item").slideUp(600);
            $("#nav_dot li").removeClass("selected");
            $(this).parent().addClass("selected");
            $div.slideDown(600);
        } else {
            $div.slideUp(600);
        }
    });/*菜单栏点击事件*/

    /*******************************详情页******************************/
    var nav = $(".nav_dot").find("li"),
        menuNow = $(".list-item").find("a"),
        titleNow = $(".title").find("p"),
        detail = $(".box_detail").find("div"),
        box_Mgr = $(".box_Mgr").find(".box");
    var index1;
    nav.click(function () {
        index1 = $(this).index();
    });
    menuNow.click(function () {
        detail.eq(0).removeClass("boxNow").siblings().addClass("boxNow");
        titleNow.text($(this).text());
        var index2 = $(this).index(),
            index = index1 * 2 + index2;
        if( index2 == 1 ) {
            $(".title").find("div").addClass("searchStyle");
        } else {
            $(".title").find("div").removeClass("searchStyle");
        }
        box_Mgr.eq(index).addClass("divNow").siblings().removeClass("divNow");
    });
})