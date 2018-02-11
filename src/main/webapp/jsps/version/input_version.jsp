<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/jsps/common/taglibs.jsp"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <%--<link href="<c:url value="/css/home/home.css"/>" type="text/css" rel="stylesheet" />--%>
    <link href="<c:url value="/css/versions/addVer.css"/>" type="text/css" rel="stylesheet" />
    <script src="<c:url value="/js/jquery-3.2.1.min.js"/>"></script>
    <script src="<c:url value="/js/home/home.js"/>"></script>
</head>
<body>
<div class="title">
    <p>新增版本</p>
</div>
<div class="box">
    <form:form class="formStyle" action="${pageContext.request.contextPath}/version/create" method="post" modelAttribute="version">
        <div>
            <label class="labelFirst">文档号:</label>
            <form:select path="docNum">
                <option value="">=请选择=</option>
                <form:options items="${documentList}" itemLabel="docNum" itemValue="docNum" />
            </form:select>
        </div>
        <div>
            <label class="labelFirst">所属项目:</label>
            <form:select path="forItem">
                <option value="">=请选择=</option>
                <form:options items="${itemList}" itemLabel="itemId" itemValue="itemId" />
            </form:select>
        </div>
        <div>
            <label class="labelFirst">版本号:</label>
            <form:input path="verNum"/>
        </div>
        <div>
            <label class="labelFirst">修改时间:</label>
            <input name="dateVerAlertTime" type="datetime-local" value="2018-01-27T13:59:59" />
        </div>
        <div>
            <label class="labelFirst">修改人:</label>
            <form:input path="verAlertPeople"/>
        </div>
        <div>
            <label class="labelFirst">修改摘要:</label>
            <form:textarea path="verContent" rows="6" cols="60" />
        </div>
        <div class="funcBtn">
            <div class="addStyle"><input type="submit" value="确定" /></div>
            <div class="addStyle"><input type="reset" value="重置" /></div>
        </div>
    </form:form>
</div>
</body>
</html>