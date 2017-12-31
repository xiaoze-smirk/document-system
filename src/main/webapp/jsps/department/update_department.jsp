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
    <form:form action="${pageContext.request.contextPath}/department/update" method="post" modelAttribute="department">
    <h3 class="title">修改${department.deptName}的信息</h3>
       <input type="hidden" name="_method" value="PUT"/>
        <div>
            <span>部门编号:</span>
            <form:input path="deptId"/>
        </div>
        <div>
            <span>部门名称:</span>
            <form:input path="deptName"/>
        </div>
        <div>
            <span>部门职能:</span>
            <form:input path="deptContent"/>
        </div>
       <div>
         <input type="submit" value="确定"/>
       </div>              
    </form:form>
  </body>
</html>
