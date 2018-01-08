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
    <h3 class="title">新增版本信息</h3>
    <form:form action="${pageContext.request.contextPath}/version/create" method="post" modelAttribute="version">
       <div>
            <span>文档号:</span>
            <form:input path="docNum"/>
       </div>
        <div>
            <span>版本号:</span>
            <form:input path="verNum"/>
        </div>
        <div>
            <span>修改时间:</span>
            <form:input path="verAlertTimeStr"/>
        </div>
        <div>
            <span>修改人:</span>
            <form:input path="verAlertPeople"/>
        </div>
        <div>
            <span>修改摘要:</span>
            <form:textarea path="verContent" rows="6" cols="60" />
        </div>
       <div>
         <input type="submit" value=" 确定 "/>
       </div>
    </form:form>
  </body>
</html>
