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
    <form:form action="${pageContext.request.contextPath}/item/update" method="post" modelAttribute="item">
    <h3 class="title">修改项目名为${item.itemName}的信息</h3>
       <input type="hidden" name="_method" value="PUT"/>

        <form:hidden path="autoId" />
        <div>
            <span>编号: ${autoId}</span>
        </div>
        <div>
            <span>项目号:</span>
            <form:input path="itemId"/>
        </div>
        <div>
            <span>所属客户:</span>
            <form:select path="clientId">
                <option value="">${client.clientPerson}</option>
                <form:options items="${clientList}" itemLabel="clientPerson" itemValue="clientId" />
            </form:select>
        </div>
        <div>
            <span>项目名:</span>
            <form:input path="itemName"/>
        </div>
        <div>
            <span>起始日期:</span>
            <form:input path="itemStartDateStr"/>
        </div>
        <div>
            <span>结束日期:</span>
            <form:input path="itemDeadlineStr"/>
        </div>
        <div>
            <span>业务负责人:</span>
            <form:input path="itemPrincipal"/>
        </div>
       <div>
         <input type="submit" value="确定"/>
       </div>              
    </form:form>
  </body>
</html>
