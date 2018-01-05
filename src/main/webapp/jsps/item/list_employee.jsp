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

			  $(".delete").click(function(){
				  var href = $(this).attr("href");
				  var empName=$(this).parent().parent().children("#empName").attr("title");
				  if(confirm("确定要删除 ["+empName+"] 的信息吗?")){
					  $("form:eq(0)").attr("action",href).submit();
					  return false;
				  }

			  });

			  $(".update").click(function(){
				  var href = $(this).attr("href");
				  $(location).attr("href",href);
			  });

			  $(".linkspan").click(function () {

				  var pageNo=${page.pageNum};
				  var totalPageNum=${page.pages};
				  var re = /^[0-9]+.?[0-9]*$/;

				  if(String($(this).attr("id"))==String("one"))
					  pageNo=1;

				  if(String($(this).attr("id"))==String("two"))
					  pageNo=pageNo-1;

				  if(String($(this).attr("id"))==String("three"))
					  pageNo=pageNo+1;

				  if(String($(this).attr("id"))==String("four"))
					  pageNo=totalPageNum;

				  if(String($(this).attr("id"))==String("five")) {
					  var num =$.trim($("#pageNo").val()) ;

					  if(!re.test(num))
					  {
						  alert("输入的不是数字!");
						  return;
					  }
					  pageNo=parseInt(num);
					  if(pageNo<1 || pageNo>totalPageNum)
					  {
						  alert("页号超出范围，有效范围：[1-"+totalPageNum+"]!");
						  return;
					  }

				  }

                  var href="?pageNo="+pageNo;
                  $(location).attr("href",href);
                  return false;

			  });

		  });

	  </script>

	
  </head>
  
  <body style="padding:8px;">
    <h3 class="title">员工管理</h3>

	<form action="" method="POST">
		<input type="hidden" name="_method" value="DELETE"/>
	</form>

	    <table border="0" cellspacing="0">
	       <tr>
	        <th>工号</th>
	        <th>姓名</th>
	        <th>性别</th>
			<th>职务</th>
			<th>部门</th>
			<th>电话</th>
			<th>邮件</th>
			<th>操作</th>
	       </tr>
	       <c:forEach var="employee" items="${page.list}" >
	         <tr>
	             <td nowrap>${employee.empId}</td>
	             <td id="empName" title="${employee.empName}" nowrap>${employee.empName}</td>
				 <td nowrap>
					 <c:choose>
						 <c:when test="${employee.empSex=='M'}">男</c:when>
						 <c:when test="${employee.empSex=='W'}">女</c:when>
					 </c:choose>
				 </td>
				 <td nowrap>${employee.empDuty}</td>
				 <td nowrap>${employee.empDeptName}</td>

				 <td nowrap>${employee.empPhone}</td>
				 <td nowrap>${employee.empEmail}</td>
				 <td nowrap>
					 <button class="update" href="${pageContext.request.contextPath}/employee/preUpdate/${employee.empId}">修改</button>
					 <button class="delete" href="${pageContext.request.contextPath}/employee/remove/${employee.empId}">删除</button>
				 </td>
	         </tr>   
	       </c:forEach>
	    </table>
		<div id="pageinfo">
			共${page.total}条, 当前显示${page.startRow}-${page.endRow}条, 第${page.pageNum}/${page.pages}页
			|
			<c:if test="${page.pageNum>1}">
				<a href="#"><span class="linkspan" id="one">首页</span>&nbsp;</a>
			</c:if>
			<c:if test="${page.pageNum>1}">
				<a href="#"><span class="linkspan" id="two">上一页</span>&nbsp;</a>
			</c:if>
			<c:if test="${page.pageNum<page.pages}">
				<a href="#"><span class="linkspan" id="three">下一页</span>&nbsp;</a>
			</c:if>
			<c:if test="${page.pageNum!=page.pages}">
				<a href="#"><span class="linkspan" id="four">末页</span>&nbsp;</a>
			</c:if>
			|
			到<input type="text" id="pageNo" size=4 style="text-align:right;" onkeypress="onlynumber();"/> 页
			<button class="linkspan" id="five" style="color:black;text-decoration:none;"> 跳 转 </button>
		</div>
  </body>
</html>
