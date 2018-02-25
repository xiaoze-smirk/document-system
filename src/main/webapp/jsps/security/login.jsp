<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/jsps/common/taglibs.jsp"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>项目文档管理系统</title>
    <link href="<c:url value="/css/login/login.css"/>" type="text/css" rel="stylesheet" />
    <script src="${pageContext.request.contextPath}/js/login/login.js"></script>
</head>
<body>
<div class="box_title">
    <img src="${pageContext.request.contextPath}/images/logo.png" class="logo">
    <p class="title">项目文档管理系统</p>
</div>
<div class="login">
    <p>账号密码登录</p>
    <img id="faceLogin" src="${pageContext.request.contextPath}/images/login/Face_gray.png" class="faceId" />
    <form action="${pageContext.request.contextPath}/login" method="post" >
        <div class="input-style">
            <span class="login-hint">用户名：</span>
            <input id="username" type="text" name="username" placeholder="用户名" />
        </div>
        <div class="input-style">
            <span class="login-hint">密&nbsp;&nbsp;&nbsp; 码：</span>
            <input id="password" type="password" name="password" placeholder="密码" />
        </div>
        <div class="input-style">
            <span class="login-hint">验证码：</span>
            <input type="text" class="iCode" placeholder="验证码" />
            <span class="code"></span>
        </div>

        <div class="input-style">
            <input name="remember-me" type="checkbox" checked/>
            <span class="login-hint pwd">记住密码</span>
        </div>
        <div class="input-style">
            <span class="login-hint account-reg">没有账号？</span>
            <input type="button" class="btnReg" value="点击注册" />
        </div>
        <div class="input-style denglu">
            <div class="demo_line"></div>
            <button class="btn_login">登 录</button>
            <div class="demo_line"></div>
        </div>
    </form>
</div>
<div class="show_advantage">
    <div class="active_advantage" ><span>优点1</span></div>
    <div><span>优点2</span></div>
    <div><span>优点3</span></div>
    <div><span>优点4</span></div>
</div>
<div class="slideShow">
    <!--图片布局开始-->
    <div class="active_img"><img src="${pageContext.request.contextPath}/images/login/1.jpg" /></div>
    <div><img src="${pageContext.request.contextPath}/images/login/2.jpg" /></div>
    <div><img src="${pageContext.request.contextPath}/images/login/3.jpg" /></div>
    <div><img src="${pageContext.request.contextPath}/images/login/4.jpg" /></div>
    <!--图片布局结束-->
</div>
<!--按钮布局开始-->
<div class="showNav">
    <span class="active">1</span>
    <span>2</span>
    <span>3</span>
    <span>4</span>
</div>
<!--按钮布局结束-->
<script src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js"></script>

<script>

    var errorMsg="${errorMsg}";

    if(errorMsg!="")
        alert(errorMsg);

    /****************************第三方登录***************************/
    var imgHover = "${pageContext.request.contextPath}/images/login/Face.png";
    var images = "${pageContext.request.contextPath}/images/login/Face_gray.png";
    $(".login").find(".faceId").mouseover(function () {
        $(this).attr("src",imgHover);
    });
    $(".login").find(".faceId").mouseleave(function () {
        $(this).attr("src",images);
    });

    $(".btnReg").click(function () {
        $(location).attr("href","${pageContext.request.contextPath}/security/toRegister");
    });
    $("#faceLogin").click(function () {
        $(location).attr("href","${pageContext.request.contextPath}/security/toFaceLogin");
    });


    /****************************校验码实现***************************/
    var codes;// 重新初始化验证码
    function change(){
        // 验证码组成库
        var arrays=new Array(
            '1','2','3','4','5','6','7','8','9','0',
            'a','b','c','d','e','f','g','h','i','j',
            'k','l','m','n','o','p','q','r','s','t',
            'u','v','w','x','y','z',
            'A','B','C','D','E','F','G','H','I','J',
            'K','L','M','N','O','P','Q','R','S','T',
            'U','V','W','X','Y','Z'
        );
        var red = parseInt(Math.random()*257).toString(16);
        var blue = parseInt(Math.random()*257).toString(16);
        var green= parseInt(Math.random()*257).toString(16);
        var color = '#'+red+blue+green;/*随机颜色*/
        codes='';
        for(var i = 0; i < 4; i++){
            // 随机获取一个数组的下标
            var r = parseInt(Math.random()*arrays.length);
            codes += arrays[r];
        }
        // 验证码添加到input里
        $(".code").text(codes);
        $(".code").css("color",color);
    }
    change();
    $(".code").click(change);
    //单击验证
    $(".btn_login").click(function(){
        var inputCode = $(".iCode").val().toUpperCase(); //取得输入的验证码并转化为大写
        if(inputCode.length == 0) { //若输入的验证码长度为0
            alert("请输入验证码！"); //则弹出请输入验证码
        }
        else if(inputCode != codes.toUpperCase()) { //若输入的验证码与产生的验证码不一致时
            alert("验证码错误，请重新输入！"); //则弹出验证码输入错误
            change();//刷新验证码
            $(".iCode").val("");//清空文本框
            return false;
        }else { //输入正确时
            $("form:eq(0)").submit();
        }
        return false;
    });

</script>
</body>
</html>