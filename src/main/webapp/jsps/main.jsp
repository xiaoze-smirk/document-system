<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/jsps/common/taglibs.jsp"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>主界面</title>
    <link href="${pageContext.request.contextPath}/css/main/main.css" type="text/css" rel="stylesheet" />
    <script src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js"></script>
</head>
<body>
<div class="box_title">
    <span class="title">项目文档管理系统</span>
    <div class="personal_info">
        <img src="${pageContext.request.contextPath}/images/main/person-img.png" class="person-img">
        <span class="user_name">${loginUser.userName}</span>
        <ul>
            <li><a target="_blank">个人信息</a></li>
            <li><a target="_blank">修改密码</a></li>
            <li><a target="_blank">头像设置</a></li>
        </ul>
    </div>
    <div class="box_setting">
        <span><img src="${pageContext.request.contextPath}/images/main/setting.png" class="setting"/></span>
        <ul>
            <li><a class="logout" target="_self">注销账号</a></li>
            <li class="quit"><a>我要退出</a></li>
        </ul>
    </div>
</div><!--标题栏-->
<div class="box_menu">
    <div class="personInfo">
        <div>
            <img src="${pageContext.request.contextPath}/images/main/person-img.png">
        </div>
        <div class="information">
            <span class="username">${loginUser.userName}</span>
            <span class="manager">${loginUser.authority.authorityChineseName}</span>
        </div>
    </div>
    <ul id="nav_dot" class="nav_dot">
        <li>
            <h4 >用户管理</h4>
            <div class="list-item none">
                <a href="<c:url value="/user/list"/>" target="contentFrame">用户管理</a>
            </div>
        </li>
        <li>
            <h4 >员工管理</h4>
            <div class="list-item none">
                <a href="<c:url value="/employee/toInput"/>" target="contentFrame">新增员工</a>
                <a href="<c:url value="/employee/list"/>" target="contentFrame">员工管理</a>
            </div>
        </li>
        <li>
            <h4>部门管理</h4>
            <div class="list-item none">
                <a href="<c:url value="/department/toInput"/>" target="contentFrame">新增部门</a>
                <a href="<c:url value="/department/list"/>" target="contentFrame">部门管理</a>
            </div>
        </li>
        <li>
            <h4 >客户管理</h4>
            <div class="list-item none">
                <a href="<c:url value="/client/toInput"/>" target="contentFrame">新增客户</a>
                <a href="<c:url value="/client/list"/>" target="contentFrame">客户管理</a>
            </div>
        </li>
        <li>
            <h4 >项目管理</h4>
            <div class="list-item none">
                <a href="<c:url value="/item/toInput"/>" target="contentFrame">新增项目</a>
                <a href="<c:url value="/item/list"/>" target="contentFrame">项目管理</a>
            </div>
        </li>
        <li>
            <h4 >文档管理</h4>
            <div class="list-item none">
                <a href="<c:url value="/document/toInput"/>" target="contentFrame">新增文档</a>
                <a href="<c:url value="/document/list"/>" target="contentFrame">文档管理</a>
            </div>
        </li>
        <li>
            <h4 >版本管理</h4>
            <div class="list-item none">
                <a href="<c:url value="/version/toInput"/>" target="contentFrame">新增版本</a>
                <a href="<c:url value="/version/list"/>" target="contentFrame">版本管理</a>
            </div>
        </li>
    </ul>
</div><!--菜单栏-->
<div class="content">
    <iframe id="contentFrame" width="100%" scrolling="yes" height="600px" frameborder="0" name="contentFrame" allowtransparency="true" src="${pageContext.request.contextPath}/jsps/welcome.jsp">
    </iframe>
</div>

<div class="footer">
    <p>
        <span>项目文档管理系统-测试版（版本:Beta1.0 Build:#2 2018-01-18）&nbsp;&nbsp;&nbsp;&nbsp;开发团队：项目文档管理系统项目组</span>
        <span>(C)2014-2018 福建师范大学数学与信息学院</span>
    </p>
</div><!--底部注释-->

<script type="text/javascript">

    $(function(){

        /****************************设置页面布局不变************************/
        var bwidth = window.screen.width;
        $(".box_title").width(bwidth);
        $(".box_detail").width(bwidth * 0.85);

        /*****************************设置信息跳转****************************/
        /*跳转至个人信息页面*/
        var setInfo = $(".personal_info").find("ul").find("li");
        setInfo.click(function () {
            var setNum = $(this).index();
            $(this).find("a").attr("href","${pageContext.request.contextPath}/user/toSettingInfo?setNum="+setNum);
        });
        /*注销账号跳转至登录页面*/
        $(".logout").click(function () {
            $(location).attr("href","${pageContext.request.contextPath}/security/logOut");
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

    });

</script>
</body>
</html>