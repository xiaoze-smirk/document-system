<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/jsps/common/taglibs.jsp"%>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>部门管理</title>
	<link href="<c:url value="/css/home/home.css"/>" type="text/css" rel="stylesheet" />
	<link href="<c:url value="/css/departments/depMgr.css"/>" type="text/css" rel="stylesheet" />
	<script src="<c:url value="/js/jquery-3.2.1.min.js"/>"></script>
	<script src="<c:url value="/js/home/home.js"/>"></script>
</head>
<body>
<div class="title">
	<p>部门管理</p>
	<div class="searchBar">
		<form method="post" class="search" action="${pageContext.request.contextPath}/department/list">
			<input value="${searchDeptname}" id="searchDeptname" name="searchDeptname" type="text" placeholder="请输入您要搜索的部门名称">
			<button type="submit"></button>
		</form>
	</div>
</div>
<div class="box">

	<form action="" method="POST">
		<input type="hidden" name="_method" value="DELETE"/>
	</form>
	<table class="depMgr">
		<tr class="trNow">
			<td>部门编号</td>
			<td>部门名称</td>
			<td>部门职能</td>
			<td>操作</td>
		</tr>
		<c:forEach var="department" items="${page.list}" >
			<tr class="trNow">
				<td>${department.deptId}</td>
				<td id="deptName" title="${department.deptName}" >${department.deptName}</td>
				<td>${department.deptContent}</td>
				<td>
					<div><button class="update" href="${pageContext.request.contextPath}/department/preUpdate/${department.deptId}">修改</button></div>
					<div><button class="delete" href="${pageContext.request.contextPath}/department/remove/${department.deptId}">删除</button></div>
				</td>
			</tr>
		</c:forEach>
	</table>
	<div class="fStyle">
		<p>共<span class="pageAll">${page.total}</span>条</p>
		<select name="number" id="pageSize" class="number ">
			<option value="3" >&nbsp;&nbsp;3</option>
			<option value="5" >&nbsp;&nbsp;5</option>
			<option value="10">&nbsp;&nbsp;10</option>
			<option value="20">&nbsp;&nbsp;20</option>
			<option value="50">&nbsp;&nbsp;50</option>
		</select>
		<div class="addStyle"><input id="one" type="button" class="first linkspan" value="首页" /></div>
		<ul class="pageTurn">
			<li class="prevBtn linkspan" id="two"><img src="${pageContext.request.contextPath}/images/prev.jpg"/></li>
			<li><span class="pageNow">${page.pageNum}</span></li>
			<li class="nextBtn linkspan" id="three"><img src="${pageContext.request.contextPath}/images/next.jpg"/></li>
		</ul>
		<div class="addStyle"><input id="four" class="last linkspan" type="button" value="尾页" /></div>
		<input id="pageNo" type="text" class="jumpTo" placeholder="输入页码" />
		<div class="addStyle"><input class="linkspan" id="five" type="button" class="go" value="跳转" /></div>
	</div>
</div><!--部门管理-->

<script type="text/javascript">

    $(function(){

        var rePageSize = String(${pageSize});
        $("#pageSize").val(rePageSize);


        $(".delete").click(function(){
            var href = $(this).attr("href");
            var deptName=$(this).parent().parent().parent().children("#deptName").attr("title");
            if(confirm("确定要删除 ["+deptName+"] 的信息吗?")){
                $("form:eq(1)").attr("action",href).submit();
                return false;
            }

        });

        $(".update").click(function(){
            var href = $(this).attr("href");
            $(location).attr("href",href);
        });

        $("#pageSize").change(function () {
            var pageSize = $("#pageSize").val();
            var href="?pageSize="+pageSize;
            $("form:eq(0)").attr("action",href).submit();
            return false;
        });

        $(".linkspan").click(function () {

            var pageNo=${page.pageNum};
            var totalPageNum=${page.pages};
            var re = /^[0-9]+.?[0-9]*$/;

            if(String($(this).attr("id"))==String("one"))
                pageNo=1;

            if(String($(this).attr("id"))==String("two")){
                if(pageNo>1)
                	pageNo=pageNo-1;
                else
                    return;
            }

            if(String($(this).attr("id"))==String("three")) {
                if(pageNo<totalPageNum)
                	pageNo = pageNo + 1;
                else
                    return;

            }

            if(String($(this).attr("id"))==String("four"))
                pageNo=totalPageNum;

            if(String($(this).attr("id"))==String("five")) {
                var num =$.trim($("#pageNo").val()) ;


                if(!re.test(num))
                {
                    alert("输入的页数不是数字!");
                    return;
                }
                pageNo=parseInt(num);
                if(pageNo<1 || pageNo>totalPageNum)
                {
                    alert("页号超出范围，有效范围：[1-"+totalPageNum+"]!");
                    return;
                }

            }

            var size = $.trim($("#pageSize").val());
            var pageSize=parseInt(size);
            var href="?pageNo="+pageNo+"&pageSize=" + pageSize;
            $("form:eq(0)").attr("action",href).submit();
            return false;

        });

    });

</script>
</body>
</html>