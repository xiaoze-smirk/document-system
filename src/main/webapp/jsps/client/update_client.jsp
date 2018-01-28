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
    <p>修改客户号为${client.clientId}的信息</p>
</div>
<div class="box">
    <form:form class="formStyle" action="${pageContext.request.contextPath}/client/update" method="post" modelAttribute="client">
        <input type="hidden" name="_method" value="PUT"/>
        <div>
            <label class="labelFirst">客户编号:</label>
            <form:input class="clientNumber" path="clientId" />
        </div>
        <div>
            <label class="labelFirst">公司名称:</label>
            <form:input class="compName" path="clientCompany" />
        </div>
        <div>
            <label class="labelFirst">客户姓名:</label>
            <form:input class="clientName" path="clientPerson" />
        </div>
        <div>
            <label class="labelFirst">联系电话:</label>
            <form:input class="clientPhone" path="clientPhone" />
        </div>
        <div>
            <label class="labelFirst">邮箱:</label>
            <form:input class="clientEmail" path="clientEmail" />
        </div>
        <div>
            <label class="labelFirst">公司地址:</label>
            <form:input class="clientAddr" path="clientAddr" />
        </div>
        <div>
            <div class="addStyle"><input type="submit" value="确定" /></div>
            <div class="addStyle"><input type="reset" value="重置" /></div>
        </div>
    </form:form>
</div>
</body>
</html>