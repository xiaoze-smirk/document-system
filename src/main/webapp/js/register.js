/**
 * Created by 傅彰炜 on 2018/1/19.
 */
$(document).ready(function() {
    $(".btn_reg").click(function () {
        $(location).attr("href","${pageContext.request.contextPath}/../login.html");
    })

    var t = 0;  /*文本框输入正确的个数*/
    $(".label").find("input").blur(function(){
        var index = $(this).parent().index();    /*点击的文本框*/
        var value = $(this).val();     /*点击的文本框的值*/
        var length = value.replace(/[^/x00-\xff]/g,"**").length;    /*文本框输入的长度*/
        var right = "<img src='../images/folders/selected.png' />";     /*输入正确时*/
        var wrong = "<img src='../images/folders/wrong.png' />"+"<p>输入错误！请重新输入！</p>";       /*输入错误时*/
        var firstPwd = $(".pwd").val();
        var secondPwd = $(".rpwd").val();
        if(index == 0){
            if(length != 0) {
                if(length > 3 && length < 9) {
                    $(this).parent().find(".labelSecond").html(right);
                    t ++;
                } else
                    $(this).parent().find(".labelSecond").html(wrong);
            }
        }
        if(index == 1){
            if(length != 0) {
                if (length > 8 && length < 16){
                    $(this).parent().find(".labelSecond").html(right);
                    t ++;
                } else
                    $(this).parent().find(".labelSecond").html(wrong);
            }
        }
        if(index == 2){
            if(length != 0) {
                if (firstPwd == secondPwd) {
                    $(this).parent().find(".labelSecond").html(right);
                    t ++;
                } else
                    $(this).parent().find(".labelSecond").html(wrong);
            }
        }
        if(index == 4){
            var phone = /^1[34578]\d{9}$/;
            if(length != 0 ) {
                if (phone.test(value)){
                    $(this).parent().find(".labelSecond").html(right);
                    t ++;
                } else
                    $(this).parent().find(".labelSecond").html(wrong);
            }
        }
        if(index == 5){
            var email = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;
            if(length != 0 ) {
                if (value.match(email)) {
                    $(this).parent().find(".labelSecond").html(right);
                    t ++;
                } else
                    $(this).parent().find(".labelSecond").html(wrong);
            }
        }
    });

    var validCode = true;
    $(".getCode").on("click",function () {
        var time = 5;
        if (validCode) {
            validCode = false;
            $(".get").attr("disabled","disabled");  /*使按钮不可点击*/
            var ttt = setInterval(function  () {
                time --;
                $(".get").attr("value",time+"秒");
                if (time == 0) {
                    clearInterval(ttt);
                    $(".get").removeAttr("disabled");  /*使按钮可点击*/
                    $(".get").attr("value","重新获取");
                    validCode=true;
                }
            },1000)
        }
    })
    $(".sbutton").click(function () {
        var val = $('select option:selected').val();
        if(t != 5){
            alert("表单输入有误！请重新确认！");
        }
        else if(val == "请选择")
            alert("请选择部门！");
        else {
            alert("注册成功！快去登录吧！");
        }
    })

});