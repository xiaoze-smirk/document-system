<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/jsps/common/taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>    
    <title>教务管理系统-演示版</title>    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
		
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/style.css"></c:url>">
	
  </head>
  
  <body>
     <div id="header">
        <div id="productName">教务管理系统-演示版</div>
        <div></div>
        <div style="float:right; margin:10px;">
               操作员:小泽&nbsp;
             <span>离开系统</span>
        </div>
     </div>
     <div>
        <div id="navigator">
            <div class="menuitem">
               <a href="<c:url value="/employee/toInput"/>" target="contentFrame">新增员工</a>
            </div>
            <div class="menuitem">
               <a href="<c:url value="/employee/list"/>"  target="contentFrame">员工管理</a>
            </div>
            <%--<div class="seperator"></div>--%>
            <%--<div class="menuitem">--%>
               <%--<a href="<c:url value="/course/toInput"/>"  target="contentFrame">开课登记</a>--%>
            <%--</div>--%>
            <%--<div class="menuitem">--%>
               <%--<a href="<c:url value="/course/list"/>" target="contentFrame">课程管理</a>--%>
            <%--</div>                        --%>
        </div>
        <div id="content">
          <iframe id="contentFrame" width="100%" scrolling="no" height="480px" frameborder="0" name="contentFrame" allowtransparency="true" src="<c:url value="/welcome.jsp"/>">              
          </iframe>           
        </div>
     </div>
     <%@ include file="/jsps/footer.jsp"%>
  </body>
</html>
