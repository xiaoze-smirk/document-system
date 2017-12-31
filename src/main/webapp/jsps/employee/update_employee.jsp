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
    <form:form action="${pageContext.request.contextPath}/employee/update" method="post" modelAttribute="employee">
    <h3 class="title">修改${employee.empName}的信息</h3>
       <input type="hidden" name="_method" value="PUT"/>

        <div>
            <span>工号:</span>
            <form:input id="updateEmpId" path="empId"/>
        </div>
        <div>
            <span>姓名:</span>
            <form:input path="empName"/>
        </div>
        <div>
            <span>性别:</span>
            <%
                Map<String, String> status = new TreeMap<String, String>();
                status.put("M", "男");
                status.put("W", "女");

                request.setAttribute("status", status);
            %>
            <form:radiobuttons  path="empSex" items="${status}"/>
        </div>
        <div>
            <span>职务:</span>
            <form:input path="empDuty"/>
        </div>
        <div>
            <span>部门:</span>
            <form:checkboxes path="empDept" items="${deptList}" itemLabel="deptName" itemValue="deptId"/>
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
         <input type="submit" value="确定"/>
       </div>              
    </form:form>
  </body>
</html>
