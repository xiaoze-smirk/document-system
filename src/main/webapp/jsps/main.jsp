<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/jsps/common/taglibs.jsp"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>主界面</title>
    <link href="${pageContext.request.contextPath}/css/main/main.css" type="text/css" rel="stylesheet" />
    <script src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/main/main.js"></script>
</head>
<body>
<div class="box_title">
    <span class="title">项目文档管理系统</span>
    <div class="personal_info">
        <img src="${pageContext.request.contextPath}/images/main/person-img.png" class="person-img">
        <span class="user_name">高富帅</span>
        <ul>
            <li><a target="_blank">个人信息</a></li>
            <li><a target="_blank">修改密码</a></li>
            <li><a target="_blank">帮助中心</a></li>
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
            <span class="username">高富帅</span>
            <span class="manager">超级管理员</span>
            <span class="department">运营部</span>
        </div>
    </div>
    <ul id="nav_dot" class="nav_dot">
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
                <a href="../projects/addProj.html" target="contentFrame">新增项目</a>
                <a href="../projects/projMgr.html" target="contentFrame">项目管理</a>
            </div>
        </li>
    </ul>
</div><!--菜单栏-->
<div class="content">
    <iframe id="contentFrame" width="100%" scrolling="no" height="600px" frameborder="0" name="contentFrame" allowtransparency="true" src="${pageContext.request.contextPath}/jsps/welcome.jsp">
    </iframe>
</div>

<div class="footer">
    <p>
        <span>项目文档管理系统-测试版（版本:Beta1.0 Build:#2 2018-01-18）&nbsp;&nbsp;&nbsp;&nbsp;开发团队：项目文档管理系统项目组</span>
        <span>(C)2014-2018 福建师范大学数学与信息学院</span>
    </p>
</div><!--底部注释-->
</body>
</html>