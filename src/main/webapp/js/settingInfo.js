/**
 * Created by 傅彰炜 on 2017/11/23.
 */
$(document).ready(function() {
    /*判断主页中点击的选项，默认选中*/
    var setNum = window.location.href,
        value = setNum.substr(setNum.length-1,1),
        nowLi = $(".setting_menu").find("ul").find("li"),
        setting_title = $(".setting_title").find("p"),
        show = $(".box_detail").find(".form");
    nowLi.eq(value).addClass("nowLi").siblings().removeClass("nowLi");
    setting_title.text(nowLi.eq(value).text());
    show.eq(value).addClass("active").siblings().removeClass("active");
    
    nowLi.click(function () {
        var index = $(this).index();
        $(this).addClass("nowLi").siblings().removeClass("nowLi");
        setting_title.text(nowLi.eq(index).text());
        show.eq(index).addClass("active").siblings().removeClass("active");
    })

    /*返回主页*/
    $(".back_settingInfo").click( function () {
        window.history.back(-1);
        $(location).attr("href","${pageContext.request.contextPath}/../main/main.html");
    })

    /*input失去焦点时，如何值发生变化，则可保存*/
    $("input").bind('input propertychange', function() {
        $(".saveInfo").css("color","#008CBA");
        $(".saveInfo").css("border-color","#008CBA");
        $(".saveInfo").removeAttr("disabled");
        $(".saveInfo").click(function () {
            alert("数据已保存！");
        })
    });

});