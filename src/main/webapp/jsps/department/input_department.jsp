<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/jsps/common/taglibs.jsp"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>新增部门</title>
    <link href="<c:url value="/css/home/home.css"/>" type="text/css" rel="stylesheet" />
    <link href="<c:url value="/css/departments/addDep.css"/>" type="text/css" rel="stylesheet" />
    <script src="<c:url value="/js/jquery-3.2.1.min.js"/>"></script>
    <script src="<c:url value="/js/home/home.js"/>"></script>
</head>
<body>
<div class="title">
    <p>新增部门</p>
</div>
<div class="box">
    <form:form class="formStyle" action="${pageContext.request.contextPath}/department/create" method="post" modelAttribute="department">
        <div>
            <label class="labelFirst">部门编号:</label>
            <form:input class="depNumber" path="deptId"/>
        </div>
        <div>
            <label class="labelFirst">部门名称:</label>
            <form:input class="depName" path="deptName"/>
        </div>
        <div>
            <label class="labelFirst">部门职能:</label>
            <form:input class="depFunction" path="deptContent"/>
        </div>
        <div class="funcBtn">
            <div class="addStyle"><input type="submit" value="确定" /></div>
            <div class="addStyle"><input type="reset" value="重置" /></div>
        </div>
    </form:form>
</div><!--新增部门-->
</body>
</html>