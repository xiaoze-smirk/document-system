<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/jsps/common/taglibs.jsp"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>新增文档信息</title>
    <link href="<c:url value="/css/home/home.css"/>" type="text/css" rel="stylesheet" />
    <link href="<c:url value="/css/documents/addDocument.css"/>" type="text/css" rel="stylesheet" />
    <script src="<c:url value="/js/jquery-3.2.1.min.js"/>"></script>
    <script src="<c:url value="/js/home/home.js"/>"></script>
</head>
<body>
<div class="title">
    <p>新增文档信息</p>
</div>
<div class="box">
    <form:form class="formStyle" method="post" action="${pageContext.request.contextPath}/document/create" modelAttribute="document">
        <div>
            <label class="labelFirst">序号:</label>
            <form:input path="docNum"/>
        </div>
        <div>
            <label class="labelFirst">标题:</label>
            <form:input path="docTitle"/>
        </div>
        <div>
            <label class="labelFirst">撰写人:</label>
            <form:input path="docAuthor"/>
        </div>
        <div>
            <label class="labelFirst">版本号:</label>
            <form:input path="verNum"/>
        </div>
        <div>
            <label class="labelFirst">状态:</label>
            <form:select path="docState">
                <option value="">=请选择=</option>
                <form:options items="${stateList}" itemLabel="stateStrName" itemValue="stateStr" />
            </form:select>
        </div>
        <div>
            <label class="labelFirst">发布日期:</label>
            <input name="dateDocReleaseDate" type="date" class="dateRelease"/>
        </div>
        <div>
            <label class="labelFirst">审核人:</label>
            <form:input path="docCheckPerson"/>
        </div>
        <div>
            <label class="labelFirst">摘要:</label>
            <form:textarea path="docContent" rows="6" cols="60" />
        </div>
        <div class="funcBtn">
            <div class="addStyle"><input type="submit" value="确定" /></div>
            <div class="addStyle"><input type="reset" value="重置" /></div>
        </div>
    </form:form>
<script type="text/javascript">

    $(function(){

        /*时间控件*/
        var myDate = new Date();
        var year = myDate.getFullYear();  //获取当前年
        var month = ("0" + (myDate.getMonth() + 1)).slice(-2);//获取当前月
        var day = ("0" + myDate.getDate()).slice(-2);//获取当前日，如果小于9，前面补0
        var today = year + "-" + month + "-" + day;
        $(".dateRelease").attr("value", today);
    });
</script>
</body>
</html>