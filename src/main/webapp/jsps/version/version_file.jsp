<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/jsps/common/taglibs.jsp"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>文件页面</title>
    <link href="<c:url value="/css/home/home.css"/>" type="text/css" rel="stylesheet" />
    <link href="<c:url value="/css/folders/folder.css"/>" type="text/css" rel="stylesheet" />
    <link href="<c:url value="/css/folders/file.css"/>" type="text/css" rel="stylesheet" />
    <script src="<c:url value="/js/jquery-3.2.1.min.js"/>"></script>
</head>
<body>
<div class="box_function box2">
    <div class="btn_upload function">
        <form:form action="${pageContext.request.contextPath}/version/upLoad/${version.verId}/${theDocNum}" method="post" enctype="multipart/form-data">
            <span class="input-file input-fileup">
                <img />上传
                <input size="100" type="file" name="file" multiple="multiple" id="file">
            </span>
        </form:form>
    </div>
    <div class="btn_download function">
        <span class="txt_function"><img />下载</span>
    </div>
    <div class="btn_delete function">
        <span class="txt_function"><img />删除</span>
    </div>
</div><!--操作栏-->
<div class="box_position box2">
    <span id="mainClick" class="posNow">主页</span>
    <img src="<c:url value="/images/folders/advance.png"/>" />
    <span id="historyClick">版本信息</span>
    <img src="<c:url value="/images/folders/advance.png"/>" />
    <span>${getFileName}</span>
</div><!--当前位置-->
<div class="thirdFile box2">
    <c:forEach var="str" items="${stringList}" >
        <c:if test="${str!=''}">
            <div class="box_file">
                <img class="imgSelect" src="<c:url value="/images/folders/select.png"/>" />
                <div>
                    <img src="<c:url value="/images/folders/word.png"/>" />
                    <span>${str}</span>
                </div>
            </div>
        </c:if>
    </c:forEach>
</div>
<div class="infoDetail">
    <form:form class="infoForm"  action="${pageContext.request.contextPath}/version/updateFile" method="post" modelAttribute="version">
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
            <label class="labelFirst">文档编号:</label>
            <input type="text" class="noedit" value="${theDocNum}" disabled />
            <input id="theDocNum" name="theDocNum" type="hidden" value="${theDocNum}" disabled />
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
<script type="text/javascript">

    $(function(){

        var h = document.documentElement.clientHeight;
        $(".infoDetail").height(h);

        /*文件图片实现*/
        for(var i = 0;i < $(".box_file").length;i ++ ){
            var type = $(".box_file").eq(i).find("span").html();
            var index = type .lastIndexOf(".");
            type  = type .substring(index + 1, type .length);
            if (type == "doc" || type == "docx")
                $(".box_file").eq(i).find("div").find("img").attr("src","${pageContext.request.contextPath}/images/folders/word.png");
            else if (type == "xls" || type == "xlsx")
                $(".box_file").eq(i).find("div").find("img").attr("src","${pageContext.request.contextPath}/images/folders/excel.png");
            else if(type == "txt")
                $(".box_file").eq(i).find("div").find("img").attr("src","${pageContext.request.contextPath}/images/folders/txt.png");
            else if (type == "jpg" || type == "jpeg")
                $(".box_file").eq(i).find("div").find("img").attr("src","${pageContext.request.contextPath}/images/folders/img.png");
        }


        /*表示操作栏可点击时*/
        var ImgPosition = ["${pageContext.request.contextPath}/images/folders/upload.png",
            "${pageContext.request.contextPath}/images/folders/download.png",
            "${pageContext.request.contextPath}/images/folders/delete.png"];
        /*表示操作栏点击时*/
        var ImgHover = ["${pageContext.request.contextPath}/images/folders/upload_hover.png",
            "${pageContext.request.contextPath}/images/folders/download_hover.png",
            "${pageContext.request.contextPath}/images/folders/delete_hover.png"];

        var fnMyFunc,fnMyFunc1;     //函数变量
        var bgImgBefore = $(".box_function").find(".function").find("span");

        $.each(ImgPosition, function (n, value) {           /*操作栏显示颜色，表示可点击*/
            bgImgBefore.eq(n).find("img").attr('src', value);
            bgImgBefore.eq(n).addClass("tFunc");
        });
        bgImgBefore.bind("mouseover",fnMyFunc = function(){
            var index = $(this).parent().index();
            $(this).find("img").attr('src', ImgHover[index]);
            $(this).addClass("funcNow");
        });
        bgImgBefore.bind("mouseleave",fnMyFunc1 = function () {
            var index = $(this).parent().index();
            $(this).find("img").attr('src', ImgPosition[index]);
            $(this).removeClass("funcNow");
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

        $("#file").change(function () {
            $("form:eq(0)").submit();
            return false;
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
                $("form:eq(1)").submit();
            }
            return false;
        });

        /**********************第三级文件选择事件**********************/
        var show = new Array();    /*判断文件是否选中*/
        var t = new Array();      /*文件点击奇偶次数*/
        var num = 0;
        var download;   /*下载按钮点击事件*/
        var del;   /*删除按钮点击事件*/
        var tt = $(".thirdFile");
        for (var i=0;i<tt.children("div").length;i++){
            show[i] = true;
            t[i] = 0;
        };
        tt.find(".box_file").mouseover(function () {
            $(this).find(".imgSelect").css("opacity",1);
        });
        tt.find(".box_file").mouseleave(function () {
            var index = $(this).index();
            if (show[index] == false){
                $(this).children("img").css("opacity",1);
            }
            else{
                $(this).children("img").css("opacity",0);
            }
        });
        tt.find(".box_file").children("img").click(function () {
            var index = $(this).parent().index();
            if(t[index] == 0){     /*选中*/
                $(this).attr("src","${pageContext.request.contextPath}/images/folders/selected.png");
                $(this).parent().addClass("fileNow");
                show[index] = false;
                t[index] = 1;
                num ++;
            } else {        /*未选中*/
                $(this).attr("src","${pageContext.request.contextPath}/images/folders/select.png");
                $(this).parent().removeClass("fileNow");
                show[index] = true;
                t[index] = 0;
                num --;
            }
        });

        /*下载*/
        $(".btn_download").bind("click",download = function () {
            if(num == 0)
                alert("您还未选择任何文件！");
            else {
                var l = $(".thirdFile").find(".box_file");
                var download = new Array();      /*记录被选中文件的位置*/
                var d = 0;  /*文件被选中的个数*/
                var allValue="";
                for(var i = 0;i < l.length;i++){
                    if(t[i]== 1){
                        var value = $(".thirdFile").find(".box_file").eq(i).find("span").text();
                        download[d] = value;
                        d ++;
                    }
                }
                if(d==1){
                    $(location).attr("href","${pageContext.request.contextPath}/version/downloadOne/"+ download[0] + "/${version.verId}/${theDocNum}" );
                }
                else{
                    allValue=download[0];
                    for(var i = 1;i<download.length;i++){
                        allValue = allValue +"!"+ download[i];
                    }
                    $(location).attr("href","${pageContext.request.contextPath}/version/downloadMany/"+ allValue +"/${version.verId}/${theDocNum}" );
                }
                return false;
            }
        });

        /*删除*/
        $(".btn_delete").bind("click",del = function () {
            var l = $(".thirdFile").find(".box_file");
            var allValue="";
            if(num == 0)
                alert("您还未选择任何文件！");
            else {
                var del = new Array();      /*记录被选中文件的位置*/
                var de = 0;
                for(var i = 0;i < l.length;i++){
                    if(t[i]== 1){
                        var value = $(".thirdFile").find(".box_file").eq(i).find("span").text();
                        del[de] = value;
                        de ++;
                    }
                }
                if(de==1)
                    allValue=del[0];
                else{
                    allValue=del[0];
                    for(var i = 1;i<del.length;i++){
                        allValue = allValue +"!"+ del[i];
                    }

                }
                $(location).attr("href","${pageContext.request.contextPath}/version/removeFile/" + allValue+"/${version.verId}/${theDocNum}");
                return false;
            }
        });

        $("#mainClick").click(function () {

            $(location).attr("href","${pageContext.request.contextPath}/version/welcome");
        });

        $("#historyClick").click(function () {

            $(location).attr("href","${pageContext.request.contextPath}/version/preWatch/${version.verId}");
        });

    });
</script>
</body>
</html>