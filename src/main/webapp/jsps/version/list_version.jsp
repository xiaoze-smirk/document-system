<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/jsps/common/taglibs.jsp"%>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Title</title>
	<link href="<c:url value="/css/home/home.css"/>" type="text/css" rel="stylesheet" />
	<script src="<c:url value="/js/jquery-3.2.1.min.js"/>"></script>
	<script src="<c:url value="/js/home/home.js"/>"></script>
</head>
<body>
<div class="title">
	<p>版本管理</p>
</div>
<div class="box">
	<table class="depMgr">
		<tr class="trNow">
			<th>序号</th>
			<th>文档号</th>
			<th>版本号</th>
			<th>修改时间</th>
			<th>修改人</th>
			<th>修改摘要</th>
			<th>操作</th>
		</tr>
		<c:forEach var="version" items="${page.list}" >
			<tr class="trNow">
				<td nowrap>${version.verId}</td>
				<td nowrap>${version.docNum}</td>
				<td nowrap>${version.verNum}</td>
				<td nowrap>${version.verAlertTime}</td>
				<td nowrap>${version.verAlertPeople}</td>
				<td nowrap>${version.verContent}</td>
				<td>
					<div><button class="update" href="${pageContext.request.contextPath}/version/preWatch/${version.verId}">查看</button></div>
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
			<li class="prevBtn"><a class="linkspan" id="two" href="#">上一页</a></li>
			<li><span class="pageNow">${page.pageNum}</span></li>
			<li class="nextBtn"><a class="linkspan" id="three"  href="#">下一页</a></li>
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

        $(".update").click(function(){
            var href = $(this).attr("href");
            $(location).attr("href",href);
        });

        $("#pageSize").change(function () {
            var pageSize = $("#pageSize").val();
            var href="?pageSize="+pageSize;
            var searchDocNum = "${searchDocNum}";
            if(searchDocNum!=null&&searchDocNum!="")
                var href="?pageNo="+pageNo+"&pageSize=" + pageSize+"&searchDocNum=" + searchDocNum;
            $(location).attr("href",href);
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
            var searchDocNum = "${searchDocNum}";
            if(searchDocNum!=null&&searchDocNum!="")
                var href="?pageNo="+pageNo+"&pageSize=" + pageSize+"&searchDocNum=" + searchDocNum;
            $(location).attr("href",href);
            return false;

        });

    });

</script>
</body>
</html>