<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/jsps/common/taglibs.jsp"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>修改项目信息</title>
    <link href="<c:url value="/css/home/home.css"/>" type="text/css" rel="stylesheet" />
    <link href="<c:url value="/css/documents/addDocument.css"/>" type="text/css" rel="stylesheet" />
    <script src="<c:url value="/js/jquery-3.2.1.min.js"/>"></script>
    <script src="<c:url value="/js/home/home.js"/>"></script>
</head>
<body>
<div class="title">
    <p>修改${item.itemName}的项目信息</p>
</div>
<div class="box">
    <form:form class="formStyle" method="post" action="${pageContext.request.contextPath}/item/update" modelAttribute="item">
        <input type="hidden" name="_method" value="PUT"/>
        <div>
            <label class="labelFirst">序号:</label>
            <input type="text" value="${item.itemId}" disabled/>
            <form:hidden path="itemId" />
        </div>
        <div>
            <label class="labelFirst">所属客户:</label>
            <form:select path="clientId">
                <form:options items="${clientList}" itemLabel="clientPerson" itemValue="clientId" />
            </form:select>
        </div>
        <div>
            <label class="labelFirst">项目名:</label>
            <form:input path="itemName"/>
        </div>
        <div>
            <label class="labelFirst">状态:</label>
            <form:select path="itemState">
                <form:options items="${stateList}" itemLabel="stateStrName" itemValue="stateStr" />
            </form:select>
        </div>
        <div>
            <label class="labelFirst">起始日期:</label>
            <input name="dateItemStartDate" type="date" class="dateBegin" value=""/>
        </div>
        <div>
            <label class="labelFirst">结束日期:</label>
            <input name="dateItemDeadline" type="date" class="dateEnd" value=""/>
        </div>
        <div>
            <label class="labelFirst">审核人:</label>
            <form:select path="userAccount">
                <form:options items="${userList}" itemValue="userAccount" itemLabel="userName" />
            </form:select>
        </div>
        <div>
            <label class="labelFirst">摘要:</label>
            <form:textarea path="itemContent" rows="6" cols="60" />
            </div>
        <div class="funcBtn">
            <div class="addStyle"><input type="submit" value="确定" /></div>
            <div class="addStyle"><input type="reset" value="重置" /></div>
        </div>
    </form:form>
    <script type="text/javascript">

        $(function(){

            /*开始时间控件*/
            var myDate = new Date('${item.itemStartDate}');
            var year = myDate.getFullYear();  //获取当前年
            var month = ("0" + (myDate.getMonth() + 1)).slice(-2);//获取当前月
            var day = ("0" + myDate.getDate()).slice(-2);//获取当前日，如果小于9，前面补0
            var today = year + "-" + month + "-" + day;
            $(".dateBegin").attr("value", today);

            /*结束时间控件*/
            var sDate = new Date('${item.itemDeadline}');
            var year = sDate.getFullYear();  //获取当前年
            var month = ("0" + (sDate.getMonth() + 1)).slice(-2);//获取当前月
            var day = ("0" + sDate.getDate()).slice(-2);//获取当前日，如果小于9，前面补0
            var today = year + "-" + month + "-" + day;
            $(".dateEnd").attr("value", today);
        });
    </script>
</body>
</html>