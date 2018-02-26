<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/jsps/common/taglibs.jsp"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>face++</title>
    <link href="${pageContext.request.contextPath}/css/faces/face_login.css" type="text/css" rel="stylesheet" />
</head>
<body>
<div>
    <form action="${pageContext.request.contextPath}/login" method="post" >
        <div class="account">
            <label>账号：</label>
            <input name="username" type="text" id="username" placeholder="请填入您的账号">
            <input name="password" type="hidden" id="password">
        </div>
        <div class="remember-me">
            <input name="remember-me" id="remember-me" type="checkbox" />
            <label for="remember-me">记住我</label>
        </div>
        <div class="preview">
            <img src="${pageContext.request.contextPath}/images/face/cover1.png" class="img1" />
            <img src="${pageContext.request.contextPath}/images/face/cover.png" class="img2 bg" />
            <video autoplay></video>
        </div>
        <div class="drawImg">
            <img src="${pageContext.request.contextPath}/images/face/drawImg2.png" class="img3" />
            <img src="${pageContext.request.contextPath}/images/face/drawImg.png" class="img4 bg" />
            <canvas id="myCanvas"></canvas>
        </div>
        <div class="btn">
            <input type="button" id="capture" value="拍照登录" />
        </div>
    </form>
    <div class="notice">
        <img src="${pageContext.request.contextPath}/images/face/notice.jpg" />
    </div>
</div>
<script src="<c:url value="/js/jquery-3.2.1.min.js"/>"></script>
<script type="text/javascript">
    function hasUserMedia(){//判断是否支持调用设备api，因为浏览器不同所以判断方式不同哦
        return !!(navigator.getUserMedia || navigator.webkitGetUserMedia || navigator.mozGetUserMedia || navigator.msGetUserMedia);
    }
    if(hasUserMedia()){
        //alert(navigator.mozGetUserMedia)
        navigator.getUserMedia = navigator.getUserMedia || navigator.webkitGetUserMedia || navigator.mozGetUserMedia || navigator.msGetUserMedia;
        var video=document.querySelector("video");
        var canvas=document.querySelector("canvas");
        var streaming = false;
        navigator.getUserMedia({
            video:true,//开启视频
            audio:false//先关闭音频，因为会有回响，以后两台电脑通信不会有响声
        },function(stream){//将视频流交给video
            video.src=window.URL.createObjectURL(stream);
            streaming = true;
        },function(err){
            console.log("capturing",err)
        });
        $("#capture").click(function(){
            if(streaming){
                //alert(video.clientHeight)
                //canvas.width = video.clientWidth;
                //canvas.height= video.clientHeight;
                canvas.width = 650;
                canvas.height = 650;
                var context = canvas.getContext('2d');
                context.drawImage(video,10,80);
                var info = {
                    number: $("#username").val(),
                    imgString: canvas.toDataURL("image/png")
                }

                $.post("${pageContext.request.contextPath}/security/faceContrast",info,function(data){
                    if(data.rNum=="1"){
                        $("#password").val(data.rPassword);
                        $(".spinner").css("display","block");
                        $("form:eq(0)").submit();
                        return false;
                    }
                    $(".spinner").css("display","none");
                    alert(data.rInfo);

                },"json")
            }
        });
        $(document).keyup(function(event){
            if(event.keyCode == 13){
                $("#capture").click();
            }
        });
    }else{
        alert("浏览器暂不支持")
    }
</script>
</body>
</html>