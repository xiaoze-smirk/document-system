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
    <h3 class="title">新增员工</h3>
    <form:form action="${pageContext.request.contextPath}/employee/create" method="post" modelAttribute="employee">
       <div>
         <span>工号:</span>
         <form:input path="empId"/>
       </div>
        <div>
            <span>姓名:</span>
            <form:input path="empName"/>
        </div>
        <div>
            <span>性别:</span>
            <form:input path="empSex"/>
        </div>
        <div>
            <span>职务:</span>
            <form:input path="empDuty"/>
        </div>
        <div>
            <span>部门:</span>
            <form:input path="empDepartment"/>
        </div>
        <div>
            <span>电话:</span>
            <form:input path="empPhone"/>
        </div>
        <div>
            <span>邮件:</span>
            <form:input path="empEmail"/>
        </div>
                 
       <div>
         <input type="submit" value=" 确定 "/> 
       </div>              
    </form:form>
  </body>
</html>
