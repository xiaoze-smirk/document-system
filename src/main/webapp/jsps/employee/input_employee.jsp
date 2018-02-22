<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/jsps/common/taglibs.jsp"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>新增员工</title>
    <link href="<c:url value="/css/home/home.css"/>" type="text/css" rel="stylesheet" />
    <link href="<c:url value="/css/staffs/addStaff.css"/>" type="text/css" rel="stylesheet" />
    <script src="<c:url value="/js/jquery-3.2.1.min.js"/>"></script>
    <script src="<c:url value="/js/home/home.js"/>"></script>
</head>
<body>
<div class="title">
    <p>新增员工</p>
</div>
<div class="box">
    <form:form class="formStyle" action="${pageContext.request.contextPath}/employee/create" method="post" modelAttribute="employee">
        <div>
            <label class="labelFirst">工号:</label>
            <form:input path="empId"/>
        </div>
        <div>
            <label class="labelFirst">姓名:</label>
            <form:input path="empName"/>
        </div>
        <div>
            <label class="labelFirst">性别:</label>
            <%
                Map<String, String> status = new TreeMap<String, String>();
                status.put("M", "男");
                status.put("W", "女");

                request.setAttribute("status", status);
            %>
            <form:radiobuttons  path="empSex" items="${status}"/>
        </div>
        <div>
            <label class="labelFirst">手机号:</label>
            <form:input path="empPhone"/>
        </div>
        <div>
            <label class="labelFirst">邮箱:</label>
            <form:input path="empEmail"/>
        </div>
        <div>
            <label class="labelFirst">部门:</label>
            <form:checkboxes path="empDept" items="${deptList}" itemLabel="deptName" itemValue="deptId"/>
        </div>
        <div>
            <label class="labelFirst">职务:</label>
            <form:input path="empDuty"/>
        </div>
        <div>
            <div class="addStyle"><input type="submit" value="确定" /></div>
            <div class="addStyle"><input type="reset" id="rbutton" value="重置" /></div>
        </div>
    </form:form>
</div>
</body>
</html>