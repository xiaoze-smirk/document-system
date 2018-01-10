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
    <h3 class="title">新增文档信息</h3>
    <form:form action="${pageContext.request.contextPath}/document/create" method="post" enctype="multipart/form-data" modelAttribute="document">
       <div>
            <span>序号:</span>
            <form:input path="docNum"/>
       </div>
        <div>
            <span>标题:</span>
            <form:input path="docTitle"/>
        </div>
        <div>
            <span>撰写人:</span>
            <form:input path="docAuthor"/>
        </div>
        <div>
            <span>版本号:</span>
            <form:input path="verNum"/>
        </div>
        <div>
            <span>状态:</span>
            <form:select path="docState">
                <option value="">=请选择=</option>
                <form:options items="${stateList}" itemLabel="stateStrName" itemValue="stateStr" />
            </form:select>
        </div>
        <div>
            <span>审核人:</span>
            <form:input path="docCheckPerson"/>
        </div>
        <div>
            <span>测试计划编号:</span>
            <form:input path="testJh"/>
            <span>测试计划文件:</span>
            <input type="file" name="files" />
        </div>
        <div>
            <span>测试用例编号:</span>
            <form:input path="testYl"/>
            <span>测试用例文件:</span>
            <input type="file" name="files" />
        </div>
        <div>
            <span>测试记录编号:</span>
            <form:input path="testJl"/>
            <span>测试记录文件:</span>
            <input type="file" name="files" />
        </div>
        <div>
            <span>缺陷报告编号:</span>
            <form:input path="testQx"/>
            <span>缺陷报告文件:</span>
            <input type="file" name="files" />
        </div>
        <div>
            <span>测试报告编号:</span>
            <form:input path="testBg"/>
            <span>测试报告文件:</span>
            <input type="file" name="files" />
        </div>
        <div>
            <span>摘要:</span>
            <form:textarea path="docContent" rows="6" cols="60" />
        </div>
       <div>
         <input type="submit" value=" 确定 "/>
       </div>
    </form:form>
  </body>
</html>
