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
      <script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery-3.1.1.min.js"></script>
      <script type="text/javascript">

          $(function(){

              $(".download").click(function(){
                  var href = $(this).attr("href");
                  $(location).attr("href",href);
                  return false;
              });

          });

      </script>
  </head>
  
  <body style="padding:8px;">
    <form:form action="${pageContext.request.contextPath}/document/update" enctype="multipart/form-data" method="post" modelAttribute="document">
    <h3 class="title">修改${document.docTitle}的信息</h3>
       <input type="hidden" name="_method" value="PUT"/>
        <form:hidden path="docNum" />
        <div>
            <span>序号: ${document.docNum}</span>
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
                <option value="">${state.stateStrName}</option>
                <form:options items="${stateList}" itemLabel="stateStrName" itemValue="stateStr" />
            </form:select>
        </div>
        <div>
            <span>审核人:</span>
            <form:input path="docCheckPerson"/>
        </div>
        <div>
            <span>摘要:</span>
            <form:textarea path="docContent" rows="3" cols="60" />
        </div>
        <div>
            <span>测试计划编号:${document.testJh}</span>
            <span>测试计划文件:</span>
            <input type="file" name="files" />
            <span><button class="download" href="${pageContext.request.contextPath}/document/download/${document.docNum}/${document.testJh}">下载</button></span>
        </div>
        <div>
            <span>测试用例编号:${document.testYl}</span>
            <span>测试用例文件:</span>
            <input type="file" name="files" />
            <span><button class="download" href="${pageContext.request.contextPath}/document/download/${document.docNum}/${document.testYl}">下载</button></span>
        </div>
        <div>
            <span>测试记录编号:${document.testJl}</span>
            <span>测试记录文件:</span>
            <input type="file" name="files" />
            <span><button class="download" href="${pageContext.request.contextPath}/document/download/${document.docNum}/${document.testJl}">下载</button></span>
        </div>
        <div>
            <span>缺陷报告编号:${document.testQx}</span>
            <span>缺陷报告文件:</span>
            <input type="file" name="files" />
            <span><button class="download" href="${pageContext.request.contextPath}/document/download/${document.docNum}/${document.testQx}">下载</button></span>
        </div>
        <div>
            <span>测试报告编号:${document.testBg}</span>
            <span>测试报告文件:</span>
            <input type="file" name="files" />
            <span><button class="download" href="${pageContext.request.contextPath}/document/download/${document.docNum}/${document.testBg}">下载</button></span>
        </div>
        <div>
         <input type="submit" value="确定"/>
       </div>
    </form:form>
  </body>
</html>
