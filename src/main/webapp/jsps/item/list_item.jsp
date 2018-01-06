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
				  var itemName=$(this).parent().parent().children("#itemName").attr("title");
				  if(confirm("确定要删除项目名为 ["+itemName+"] 的信息吗?")){
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
	        <th>编号</th>
			<th>项目号</th>
	        <th>项目名</th>
			<th>起始日期</th>
			<th>结束日期</th>
			<th>业务负责人</th>
			<th>操作</th>
	       </tr>
	       <c:forEach var="item" items="${page.list}" >
	         <tr>
	             <td nowrap>${item.autoId}</td>
				 <td nowrap>${item.itemId}</td>
	             <td id="itemName" title="${item.itemName}" nowrap>${item.itemName}</td>
				 <td nowrap>${item.itemStartDate}</td>
				 <td nowrap>${item.itemDeadline}</td>
				 <td nowrap>${item.itemPrincipal}</td>
				 <td nowrap>
					 <button class="update" href="${pageContext.request.contextPath}/item/preUpdate/${item.autoId}">修改</button>
					 <button class="delete" href="${pageContext.request.contextPath}/item/remove/${item.autoId}">删除</button>
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
