<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/jsps/common/taglibs.jsp"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>找回密码</title>
    <link href="<c:url value="/css/home/home.css"/>" type="text/css" rel="stylesheet" />
    <link href="<c:url value="/css/retrievePwd.css"/>" type="text/css" rel="stylesheet" />
    <script src="<c:url value="/js/jquery-3.2.1.min.js"/>"></script>
</head>
<body>
<div class="box_title">
    <img src="<c:url value="/images/logo.png"/>" class="logo">
    <p class="title">项目文档管理系统</p>
</div>
<div class="title">
    <p>找回密码</p>
    <div class="backToLogin"><button name="backToLogin">返 回</button></div>
</div>
<div class="box">
    <form class="formStyle">
        <div>
            <label class="labelFirst">手机号:</label>
            <input type="text" class="user" placeholder="输入手机号" />
        </div>
        <div>
            <label class="labelFirst">验证码:</label>
            <input type="text" class="iCode" placeholder="验证码" />
            <div class="addStyle"><input type="button" value="获取" /></div>
        </div>
        <div>
            <div class="addStyle"><input type="submit" id="sbutton" value="确定" /></div>
            <div class="addStyle"><input type="reset" id="rbutton" value="重置" /></div>
        </div>
    </form>
</div>
<script type="text/javascript">
    $(document).ready(function() {
        $(".backToLogin").click(function () {
            $(location).attr("href","${pageContext.request.contextPath}/security/toLogin");
        })
    });
</script>
</body>
</html>