<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/jsps/common/taglibs.jsp"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link href="<c:url value="/css/home/home.css"/>" type="text/css" rel="stylesheet" />
    <link href="<c:url value="/css/items/addItem.css"/>" type="text/css" rel="stylesheet" />
    <script src="<c:url value="/js/jquery-3.2.1.min.js"/>"></script>
    <script src="<c:url value="/js/home/home.js"/>"></script>
</head>
<body>
<div class="title">
    <p>新增项目</p>
</div>
<div class="box">
    <form:form class="formStyle" action="${pageContext.request.contextPath}/item/create" method="post" modelAttribute="item">

        <div>
            <label class="labelFirst">所属客户:</label>
            <form:select path="clientId">
                <option value="">=请选择=</option>
                <form:options items="${clientList}" itemLabel="clientPerson" itemValue="clientId" />
            </form:select>
        </div>
        <div>
            <label class="labelFirst">项目名称:</label>
            <form:input class="projName" path="itemName"/>
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
            <label class="labelFirst">负责人:</label>
            <form:input class="official" path="itemPrincipal"/>
        </div>
        <div>
            <div class="addStyle"><input type="submit" value="确定" /></div>
            <div class="addStyle"><input type="reset" value="重置" /></div>
        </div>
    </form:form>
</div>
<script type="text/javascript">

    $(function(){

        /*时间控件*/
        var myDate = new Date();
        var year = myDate.getFullYear();  //获取当前年
        var month = ("0" + (myDate.getMonth() + 1)).slice(-2);//获取当前月
        var day = ("0" + myDate.getDate()).slice(-2);//获取当前日，如果小于9，前面补0
        var today = year + "-" + month + "-" + day;
        $(".dateBegin").attr("value", today);
        $(".dateEnd").attr("value", today);

    });

</script>
</body>
</html>