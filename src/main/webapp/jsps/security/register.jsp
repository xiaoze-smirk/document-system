<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/jsps/common/taglibs.jsp"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>注册</title>
    <link href="<c:url value="/css/register.css"/>" rel="stylesheet" type="text/css">
    <script src="<c:url value="/js/jquery-3.2.1.min.js"/>"></script>
    <script src="<c:url value="/js/home/home.js"/>"></script>
</head>
<body>
<div class="box_title">
    <img src="${pageContext.request.contextPath}/images/logo.png" class="logo">
    <span class="title">项目文档管理系统</span>
    <span class="reg">已有账号，去</span>
    <div class="btn_reg"><span>登录</span></div>
</div>
<div class="box_register">
    <p>创建新用户</p>
    <span class="userReg">用户注册</span>
    <div class="areaRegister">
        <form:form class="form" action="${pageContext.request.contextPath}/security/register" method="post" modelAttribute="user" enctype="multipart/form-data">
            <div class="label">
                <label class="labelFirst">账号:</label>
                <form:input path="userAccount" class="user" />
                <label class="labelSecond">请输入6-10个数字</label>
            </div>
            <div class="label">
                <label class="labelFirst">姓名:</label>
                <form:input path="userName" class="user" />
                <label class="labelSecond">请输入2-8个汉字、字母、数字组合</label>
            </div>
            <div class="label">
                <label class="labelFirst">密码:</label>
                <form:password path="userPassword" class="pwd" />
                <label class="labelSecond">请输入8-16个字母、数字、符号组合</label>
            </div>
            <div class="label">
                <label class="labelFirst">确认密码:</label>
                <input type="password" name="checkPassword" class="rpwd" />
                <label id="affPassword" class="labelSecond"></label>
            </div>
            <div class="label ">
                <label class="labelFirst">性别:</label>
                <%
                    Map<String, String> status = new HashMap<String, String>();
                    status.put("W", "女");
                    status.put("M", "男");


                    request.setAttribute("status", status);
                %>
                <div class="sexSelect">
                    <form:radiobuttons path="userSex" items="${status}"/>
                </div>
                <label class="labelSecond"></label>
            </div>
            <div class="label">
                <label class="labelFirst">手机号:</label>
                <form:input path="userPhone" class="phoneNum" />
                <label class="labelSecond"></label>
            </div>
            <div class="label">
                <label class="labelFirst">邮箱:</label>
                <form:input path="userEmail" class="email" />
                <label class="labelSecond"></label>
            </div>
            <div class="label">
                <label class="labelFirst">个人证照:</label>
                <input id="personPhoto" accept="image/*" type="file" name="personPhoto" />
                <label class="labelSecond"></label>
            </div>
            <div class="label">
                <div class="addStyle"><input type="submit" class="sbutton" value="确定" /></div>
                <div class="addStyle"><input type="reset" id="rbutton" value="重置" /></div>
            </div>
        </form:form>
    </div>
</div><!--注册区-->
<div class="preview">
    <canvas id="cvs" width="200" height="200"></canvas>
    <span>证照预览</span>
</div>
<script type="text/javascript">
    $(function(){
        $("#userSex1").attr("checked",true);
        var faceError="${faceError}";

        if(faceError!="")
            alert(faceError);

        document.querySelector('input[id=personPhoto]').onchange = function(e){
            readFile(e.target.files[0]);
        }

        function readFile(files){
            var file = files;//获取input输入的图片
            if(!/image\/\w+/.test(file.type)){
                alert("请确保文件为图像类型");
                return false;
            }//判断是否图片，在移动端由于浏览器对调用file类型处理不同，虽然加了accept = 'image/*'，但是还要再次判断
            var reader = new FileReader();
            reader.readAsDataURL(file);//转化成base64数据类型
            reader.onload = function(e){
                drawToCanvas(this.result);
            }
        }
        function drawToCanvas(imgData){
            var cvs = document.querySelector('#cvs');

            var ctx = cvs.getContext('2d');
            var img = new Image;
            img.src = imgData;
            img.onload = function(){//必须onload之后再画
                ctx.drawImage(img,25,0,150,200);
                strDataURI = cvs.toDataURL();//获取canvas base64数据
            }
        }



        $(".label").find("input").blur(function(){

            var tIndex=0;

            var index = $(this).parent().index();    /*点击的文本框*/
            var value = $(this).val();     /*点击的文本框的值*/
            var length = value.replace(/[^/x00-\xff]/g,"**").length;    /*文本框输入的长度*/
            var right = "<img src='${pageContext.request.contextPath}/images/folders/selected.png' />";     /*输入正确时*/
            var wrong = "<img src='${pageContext.request.contextPath}/images/folders/wrong.png' />"+"<p>输入错误！请重新输入！</p>";       /*输入错误时*/
            var firstPwd = $(".pwd").val();
            var secondPwd = $(".rpwd").val();
            if(index == 0){
                if(length != 0) {
                    if(length > 6 && length < 10) {
                        $(this).parent().find(".labelSecond").html(right);
                    } else
                        $(this).parent().find(".labelSecond").html(wrong);
                }
            }
            if(index == 1){
                if(length != 0) {
                    if (length > 2 && length < 9){
                        $(this).parent().find(".labelSecond").html(right);
                    } else
                        $(this).parent().find(".labelSecond").html(wrong);
                }
            }
            if(index == 2){
                if(length != 0) {
                    if (length > 7 && length < 16){
                        $(this).parent().find(".labelSecond").html(right);
                        tIndex=1;
                    } else
                        $(this).parent().find(".labelSecond").html(wrong);
                }
            }
            if (index == 3) {
                if (length!= 0 ) {
                    if (firstPwd == secondPwd) {
                        $(this).parent().find(".labelSecond").html(right);
                    } else
                        $(this).parent().find(".labelSecond").html(wrong);
                }
            }
            if(index == 5){
                var phone = /^1[34578]\d{9}$/;
                if(length != 0 ) {
                    if (phone.test(value)){
                        $(this).parent().find(".labelSecond").html(right);
                    } else
                        $(this).parent().find(".labelSecond").html(wrong);
                }
            }
            if(index == 6){
                var email = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;
                if(length != 0 ) {
                    if (value.match(email)) {
                        $(this).parent().find(".labelSecond").html(right);
                    } else
                        $(this).parent().find(".labelSecond").html(wrong);
                }
            }
        });

        var validCode = true;
        $(".getCode").on("click",function () {
            var time = 5;
            if (validCode) {
                validCode = false;
                $(".get").attr("disabled","disabled");  /*使按钮不可点击*/
                var ttt = setInterval(function  () {
                    time --;
                    $(".get").attr("value",time+"秒");
                    if (time == 0) {
                        clearInterval(ttt);
                        $(".get").removeAttr("disabled");  /*使按钮可点击*/
                        $(".get").attr("value","重新获取");
                        validCode=true;
                    }
                },1000)
            }
        })
        $(".sbutton").click(function () {

            var i = 0;
            for(i = 0;i < 4;i ++) {
                if($(".form").find("input[type='text']").eq(i).val() == ""){
                    break;
                }
            }
            if(i!=4){
                alert("表单输入有误！");
                return false;
            }
            if($(".form").find("input[type='password']").val() == ""){
                alert("表单输入有误！");
                return false;
            }
            if($(".form").find("input[type='file']").val() == ""){
                alert("表单输入有误！");
                return false;
            }
            $("form:eq(0)").submit();
            return false;
        });

        $(".btn_reg").click(function () {
            $(location).attr("href","${pageContext.request.contextPath}/security/toLogin");
        });

    });


</script>
</body>
</html>