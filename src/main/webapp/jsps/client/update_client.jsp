<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/jsps/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title></title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/style.css"/>">
  </head>
  
  <body style="padding:8px;">
    <form:form action="${pageContext.request.contextPath}/client/update" method="post" modelAttribute="client">
    <h3 class="title">修改客户号为${client.clientId}的信息</h3>
       <input type="hidden" name="_method" value="PUT"/>

        <div>
            <span>客户号:</span>
            <form:input path="clientId"/>
        </div>
        <div>
            <span>公司名称:</span>
            <form:input path="clientCompany"/>
        </div>
        <div>
            <span>联系人:</span>
            <form:input path="clientPerson"/>
        </div>
        <div>
            <span>联系电话:</span>
            <form:input path="clientPhone"/>
        </div>
        <div>
            <span>邮件:</span>
            <form:input path="clientEmail"/>
        </div>
        <div>
            <span>地址:</span>
            <form:input path="clientAddr"/>
        </div>
       <div>
         <input type="submit" value="确定"/>
       </div>              
    </form:form>
  </body>
</html>
