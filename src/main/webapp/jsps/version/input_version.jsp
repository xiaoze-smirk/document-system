<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/jsps/common/taglibs.jsp"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>新增版本</title>
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
            <label class="labelFirst">所属项目:</label>
            <form:select path="itemId">
                <option value="">=请选择=</option>
                <form:options items="${itemList}" itemLabel="itemName" itemValue="itemId" />
            </form:select>
        </div>
        <div>
            <label class="labelFirst">版本号:</label>
            <form:input path="verNum" id="verNum" />
        </div>
        <div>
            <label class="labelFirst">修改时间:</label>
            <input name="dateVerAlertTime" type="datetime-local" value="2018-04-22T13:59:59" />
        </div>
        <div>
            <label class="labelFirst">修改人:</label>
            <form:select path="verAlertPeople">
                <option value="">=请选择=</option>
                <form:options items="${userList}" itemValue="userAccount" itemLabel="userName" />
            </form:select>
        </div>
        <div>
            <label class="labelFirst">修改摘要:</label>
            <form:textarea path="verContent" rows="6" cols="60" />
        </div>
        <div class="funcBtn">
            <div class="addStyle"><input id="button" type="button" value="确定" /></div>
            <div class="addStyle"><input type="reset" value="重置" /></div>
        </div>
    </form:form>
</div>
<script type="text/javascript">

    $(function(){

        $("#button").click(function(){
            var re = /^[0-9]+.?[0-9]*$/;
            var num =$.trim($("#verNum").val());

            if(!re.test(num))
            {
                alert("输入的版本号不是数字!");
                return false;
            }

            $("form:eq(0)").submit();

        });

    });
</script>
</body>
</html>