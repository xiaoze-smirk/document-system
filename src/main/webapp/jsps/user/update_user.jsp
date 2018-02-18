<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/jsps/common/taglibs.jsp"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link href="<c:url value="/css/home/home.css"/>" type="text/css" rel="stylesheet" />
    <script src="<c:url value="/js/jquery-3.2.1.min.js"/>"></script>
    <script src="<c:url value="/js/home/home.js"/>"></script>

</head>
<body>
<div class="title">
    <p>修改${user.userName}的信息</p>
</div>
<div class="box">
    <img id="textbookPic"
         alt="个人头像"
         style="float:right" src='<c:url value="/user/getPic/${user.userAccount}"/>'><br/>
    <img src="" class="img" />
    <form:form class="formStyle" action="${pageContext.request.contextPath}/user/update" method="post" modelAttribute="user" enctype="multipart/form-data">
        <input type="hidden" name="_method" value="PUT"/>
        <div>
            <label class="labelFirst">账号:</label>
            <form:input path="userAccount"/>
        </div>
        <div>
            <label class="labelFirst">姓名:</label>
            <form:input path="userName"/>
        </div>
        <div>
            <label class="labelFirst">性别:</label>
            <%
                Map<String, String> status = new TreeMap<String, String>();
                status.put("M", "男");
                status.put("W", "女");

                request.setAttribute("status", status);
            %>
            <form:radiobuttons  path="userSex" items="${status}"/>
        </div>
        <div>
            <label class="labelFirst">手机号:</label>
            <form:input path="userPhone"/>
        </div>
        <div>
            <label class="labelFirst">邮箱:</label>
            <form:input path="userEmail"/>
        </div>
        <div>
            <label class="labelFirst">qq账号:</label>
            <form:input path="userQqAccount"/>
        </div>
        <div class="label">
            <label class="labelFirst">个人证照:</label>
            <input id="personPhoto" style="margin-left: 10px;margin-top: 5.5px;" type="file" name="personPhoto" />
            <label class="labelSecond"></label>
        </div>
        <div>
            <label class="labelFirst">权限:</label>
            <form:select path="userAuthorityId">
                <form:options items="${authorityList}" itemLabel="authorityChineseName" itemValue="authorityId" />
            </form:select>
        </div>
        <div>
            <div class="addStyle"><input type="submit" value="确定" /></div>
            <div class="addStyle"><input type="reset" id="rbutton" value="重置" /></div>
        </div>
    </form:form>
</div>

</body>
</html>