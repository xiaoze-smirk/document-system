<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/jsps/common/taglibs.jsp"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>用户管理</title>
    <link href="<c:url value="/css/home/home.css"/>" type="text/css" rel="stylesheet" />
    <link href="<c:url value="/css/users/userMgr.css"/>" type="text/css" rel="stylesheet" />
    <script src="<c:url value="/js/jquery-3.2.1.min.js"/>"></script>
    <script src="<c:url value="/js/home/home.js"/>"></script>
</head>
<body>
<div class="title">
    <p>用户管理</p>
</div>
<div class="box">
    <form action="" method="POST">
        <input type="hidden" name="_method" value="DELETE"/>
    </form>
    <table class="staffMgr">
        <tr class="trNow">
            <td>账号</td>
            <td>姓名</td>
            <td>性别</td>
            <td>手机号</td>
            <td>邮箱</td>
            <td>qq账号</td>
            <td>权限</td>
            <td>操作</td>
        </tr>
        <c:forEach var="user" items="${page.list}" >
            <tr class="trNow">
                <td>${user.userAccount}</td>
                <td id="userName" title="${user.userName}">${user.userName}</td>
                <td>
                    <c:choose>
                        <c:when test="${user.userSex=='M'}">男</c:when>
                        <c:when test="${user.userSex=='W'}">女</c:when>
                    </c:choose>
                </td>
                <td>${user.userPhone}</td>
                <td>${user.userEmail}</td>
                <td>${user.userQqAccount}</td>
                <td>${user.authority.authorityChineseName}</td>
                <td>
                    <div><button class="update" href="${pageContext.request.contextPath}/user/preUpdate/${user.userAccount}">修改</button></div>
                    <div><button class="delete" href="${pageContext.request.contextPath}/user/remove/${user.userAccount}">删除</button></div>
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
</div><!--用户管理-->
<script type="text/javascript">

    $(function(){

        var rePageSize = String(${pageSize});
        $("#pageSize").val(rePageSize);

        $(".delete").click(function(){
            var href = $(this).attr("href");
            var userName=$(this).parent().parent().parent().children("#userName").attr("title");
            if(confirm("确定要删除 ["+userName+"] 的信息吗?")){
                $("form:eq(0)").attr("action",href).submit();
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
            $(location).attr("href",href);
            return false;

        });

    });

</script>
</body>
</html>