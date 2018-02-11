<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/jsps/common/taglibs.jsp"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link href="<c:url value="/css/home/home.css"/>" type="text/css" rel="stylesheet" />
    <link href="<c:url value="/css/folders/folder.css"/>" type="text/css" rel="stylesheet" />
    <script src="<c:url value="/js/jquery-3.2.1.min.js"/>"></script>
    <script src="<c:url value="/js/home/home.js"/>"></script>
</head>
<body>
<div class="box fileDetail">
    <div class="box_function box2">
        <div class="btn_upload function">
            <span class="input-file input-fileup" href="javascript:;">
                <img />上传
                <input size="100" type="file" name="file" multiple="multiple" id="file">
            </span>
        </div>
        <div class="btn_download function">
            <span class="txt_function"><img />下载</span>
        </div>
        <div class="btn_delete function">
            <span class="txt_function"><img />删除</span>
        </div>
    </div><!--操作栏-->
    <div class="box_position box2">
        <span id="mainClick">主页</span>
        <img src="<c:url value="/images/folders/advance.png"/>" />
        <span>版本信息</span>
    </div><!--当前位置-->
    <div class="secondFile box2">
        <div class="box_file">
            <img class="imgOn" title="JH" src="<c:url value="/images/folders/file.png"/>" >
            <span>测试计划</span>
        </div>
        <div class="box_file">
            <img class="imgOn" title="YL" src="<c:url value="/images/folders/file.png"/>" >
            <span>测试用例</span>
        </div>
        <div class="box_file">
            <img class="imgOn" title="JL" src="<c:url value="/images/folders/file.png"/>" >
            <span>测试记录</span>
        </div>
        <div class="box_file">
            <img class="imgOn" title="QX" src="<c:url value="/images/folders/file.png"/>" >
            <span>缺陷报告</span>
        </div>
        <div class="box_file">
            <img class="imgOn" title="BG" src="<c:url value="/images/folders/file.png"/>" >
            <span>测试报告</span>
        </div>
    </div><!--二级文件区-->
    <div class="infoDetail">
        <form:form class="infoForm"  action="${pageContext.request.contextPath}/version/updateFolder" method="post" modelAttribute="version">
            <form:hidden path="verId"/>
            <div class="labelInfo">
                <div class="edit"><button>编辑</button></div>
            </div>
            <div class="labelInfo">
                <label class="labelFirst">文档号:</label>
                    <input type="text" class="noedit" value="${version.docNum}" disabled />
            </div>
            <div class="labelInfo">
                <label class="labelFirst">版本号:</label>
                    <input type="text" class="noedit" value="${version.verNum}" disabled />
            </div>
            <div class="labelInfo">
                <label class="labelFirst">修改时间:</label>
                <input id="dateVerAlertTime" name="dateVerAlertTime" type="datetime-local" value="2018-01-27T13:59:59" disabled />
            </div>
            <div class="labelInfo">
                <label class="labelFirst">修改人:</label>
                <form:input path="verAlertPeople" disabled="true" />
            </div>
            <div class="labelInfo">
                <label class="labelFirst">修改摘要:</label>
                <form:textarea path="verContent" rows="6" cols="60" disabled="true" />
            </div>
        </form:form>
    </div><!--文件详细信息-->
</div>
<script type="text/javascript">

    $(function(){

        $(".input-file").find("input").css("display","none");   /*不可上传*/
        /**************************设置操作按钮背景***************************/
        var ImgGray = ["${pageContext.request.contextPath}/images/folders/upload_gray.png",
            "${pageContext.request.contextPath}/images/folders/download_gray.png",
            "${pageContext.request.contextPath}/images/folders/delete_gray.png"];        /*表示操作栏不可点击时*/
        var bgImgBefore = $(".box_function").find(".function").find("span");
        $.each(ImgGray, function (n, value) {       /*操作栏初始化*/
            bgImgBefore.eq(n).find("img").attr('src', value);
        });

        /*时间控件*/
        var myDate = new Date('${version.verAlertTime}');
        var year = myDate.getFullYear();  //获取当前年
        var month = ("0" + (myDate.getMonth() + 1)).slice(-2);//获取当前月
        var day = ("0" + myDate.getDate()).slice(-2);//获取当前日，如果小于9，前面补0
        var h = ("0" + myDate.getHours()).slice(-2);
        var m = ("0" + myDate.getMinutes()).slice(-2);
        var se = ("0" + myDate.getSeconds()).slice(-2);
        var today = year + "-" + month + "-" + day + "T" + h + ":" + m + ":" + se;

        $("#dateVerAlertTime").attr("value", today);

        /*双击文件夹进入二级目录*/
        $(".imgOn").on("dblclick", function () {
            var fileName = $(this).attr("title");
            var theVerId = ${version.verId};
            $(location).attr("href","${pageContext.request.contextPath}/version/preWatchFile/"+fileName+"/"+theVerId);
        });

        $("#mainClick").click(function () {

            $(location).attr("href","${pageContext.request.contextPath}/version/welcome");
        });

        /**************************文件信息编辑按钮**************************/
        var edit = $(".edit").find("button");
        $(".labelInfo").find("input").css("border","1px solid #EDF8F1");
        $(".labelInfo").find("input").css("background-color","#EDF8F1");
        edit.click(function () {
            if($(this).html() == "编辑"){
                edit.html("保存");
                $(".labelInfo").find("input").attr("disabled",false);
                $(".labelInfo").find("input").css("border","1px solid gray");
                $(".labelInfo").find("input").css("background-color","white");

                $(".labelInfo").find("textarea").attr("disabled",false);
                $(".labelInfo").find("textarea").css("border","1px solid gray");
                $(".labelInfo").find("textarea").css("background-color","white");

                $(".labelInfo").find(".noedit").attr("disabled",true);
                $(".labelInfo").find(".noedit").css("border","1px solid #EDF8F1");
                $(".labelInfo").find(".noedit").css("background-color","#EDF8F1");
            } else {
                $("form:eq(0)").submit();
            }
            return false;
        });

    });
</script>
</body>
</html>