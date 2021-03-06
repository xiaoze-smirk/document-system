<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/jsps/common/taglibs.jsp"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>项目管理</title>
    <link href="<c:url value="/css/documents/documentMgr.css"/>" type="text/css" rel="stylesheet" />
    <script src="<c:url value="/js/jquery-3.2.1.min.js"/>"></script>
</head>
<body>
<div class="title">
    <p>项目管理</p>
    <div class="searchBar">
        <form class="search" method="post" action="${pageContext.request.contextPath}/item/list">
            <input value="${searchItemName}" id="searchItemName" name="searchItemName" type="text" placeholder="请输入您要搜索的项目名">
            <button type="submit"></button>
        </form>
    </div>
</div>
<div class="box">
    <form action="" method="POST">
        <input type="hidden" name="_method" value="DELETE"/>
    </form>
    <table class="projMgr">
        <tr class="trNow">
            <td>序号</td>
            <td>项目号</td>
            <td>项目名</td>
            <td>状态</td>
            <td>起始日期</td>
            <td>结束日期</td>
            <td>审核人</td>
            <td>摘要</td>
            <td>操作</td>
        </tr>
        <tr>
            <td>
                <div class="progress">
                <span class="container">
                        <span class="progressBar"></span>
                </span>
                </div>
            </td>
        </tr>
        <c:forEach var="item" items="${page.list}" >
            <tr class="trNow">
                <td>${item.itemId}</td>
                <td>${item.itemNum}</td>
                <td id="itemName" title="${item.itemName}">${item.itemName}</td>
                <td>
                    <c:choose>
                        <c:when test="${item.itemState=='a'}">未测</c:when>
                        <c:when test="${item.itemState=='b'}">启动</c:when>
                        <c:when test="${item.itemState=='c'}">计划</c:when>
                        <c:when test="${item.itemState=='d'}">用例</c:when>
                        <c:when test="${item.itemState=='e'}">报告</c:when>
                        <c:when test="${item.itemState=='f'}">结束</c:when>
                        <c:when test="${item.itemState=='g'}">投诉</c:when>
                    </c:choose>
                </td>
                <td>${item.itemStartDate}</td>
                <td>${item.itemDeadline}</td>
                <td>${item.user.userName}</td>
                <td>${item.itemContent}</td>
                <td>
                    <div class="btn"><button class="update" href="${pageContext.request.contextPath}/item/preUpdate/${item.itemId}">修改</button></div>
                    <div class="btn"><button class="delete" href="${pageContext.request.contextPath}/item/remove/${item.itemId}">删除</button></div>
                    <div class="btn"><button class="history" href="${pageContext.request.contextPath}/version/preWatchFolder/${item.itemId}">历史</button></div>
                </td>
            </tr>
            <tr>
                <td>
                    <div class="progress">
                    <span class="container">
                            <span class="progressBar"></span>
                    </span>
                    </div>
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
</div>
<script type="text/javascript">

    $(function(){

        var rePageSize = String(${pageSize});
        $("#pageSize").val(rePageSize);


        $(document).ready(function () {
            var value=${intList};          /*停止的值*/
            var bar = $(".projMgr").find(".progressBar");
            for(var i = 1;i < bar.length;i ++){
                var widthTemp = ((value[i-1]-50) / 10).toFixed(1) + '%';
                bar.eq(i).css('width', widthTemp);
                bar.eq(i).text(value[i-1]/10+ '%');
            }
        });


        $(".delete").click(function(){
            var href = $(this).attr("href");
            var itemName=$(this).parent().parent().parent().children("#itemName").attr("title");
            if(confirm("确定要删除 ["+itemName+"] 的信息吗?")){
                $("form:eq(1)").attr("action",href).submit();
                return false;
            }
        });

        $(".update").click(function(){
            var href = $(this).attr("href");
            $(location).attr("href",href);
            return false;
        });

        $(".history").click(function(){
            var href = $(this).attr("href");
            $(location).attr("href",href);
            return false;
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