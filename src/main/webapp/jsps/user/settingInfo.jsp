<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/jsps/common/taglibs.jsp"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>个人设置</title>
    <link href="<c:url value="/css/settingInfo.css"/>" rel="stylesheet" type="text/css">
    <script src="<c:url value="/js/jquery-3.2.1.min.js"/>"></script>
</head>
<body>
<div class="box_title">
    <img src="<c:url value="/images/logo.png"/>" class="logo">
    <span class="title">项目文档管理系统</span>
    <div class="back_settingInfo"><span>返回</span></div>
</div>
<div class="box_setting">
    <div class="setting_menu">
        <div>
            <img src="<c:url value="/images/main/person-img.png"/>" class="person-img">
            <span class="user_name">高富帅</span>
        </div>
        <ul>
            <li class="nowLi">基本资料</li>
            <li>修改密码</li>
            <li>头像设置</li>
            <li></li>
        </ul>
    </div>
    <div class="setting_detail">
        <div class="setting_title">
            <p>基本资料</p>
            <div class="saveInfo"><button id="saveInformation" name="saveInfo">保 存</button></div>
        </div>
        <div class="box_detail">
            <form:form class="form active" action="${pageContext.request.contextPath}/user/updateSelfInfo" method="post" modelAttribute="user" enctype="multipart/form-data">
                <div>
                    <label class="firstLabel">账号：</label>
                    <form:input path="userAccount"/>
                    <label></label>
                </div>
                <div>
                    <label class="firstLabel">姓名：</label>
                    <form:input path="userName"/>
                    <label></label>
                </div>
                <div>
                    <label class="firstLabel">性别：</label>
                    <%
                        Map<String, String> status = new TreeMap<String, String>();
                        status.put("M", "男");
                        status.put("W", "女");

                        request.setAttribute("status", status);
                    %>
                    <form:radiobuttons  path="userSex" items="${status}"/>
                </div>
                <div>
                    <label class="firstLabel">手机号：</label>
                    <form:input path="userPhone"/>
                    <label></label>
                </div>
                <div>
                    <label class="firstLabel">邮箱：</label>
                    <form:input path="userEmail"/>
                    <label></label>
                </div>
                <div>
                    <label class="firstLabel">QQ账号：</label>
                    <form:input path="userQqAccount"/>
                    <label></label>
                </div>
                <div>
                    <label class="firstLabel">个人证照：</label>
                    <input id="personPhoto" accept="image/*" style="margin-left: 0px;margin-top: 5.5px;" type="file" name="personPhoto" />
                    <label></label>
                </div>
                <div class="canvas">
                    <canvas id="cvs" width="200" height="200"></canvas>
                </div>
            </form:form><!--基本资料-->
            <form:form class="form" action="${pageContext.request.contextPath}/user/updatePassword" method="post">
                <div>
                    <label class="firstLabel">原始密码：</label>
                    <input id="originalPassword" type="password" name="originalPassword">
                    <label id="originalPasswordError" class="firstLabel" style="color: red"></label>
                </div>
                <div>
                    <label class="firstLabel">修改密码：</label>
                    <input id="alertPassword" type="password" name="alertPassword">
                </div>
                <div>
                    <label class="firstLabel">确认密码：</label>
                    <input id="affirmPassword" type="password" name="affirmPassword">
                    <label class="firstLabel" style="color: red"></label>
                    <label id="affirmPasswordError" class="firstLabel" style="color: red"></label>
                </div>
            </form:form><!--修改密码-->
            <div class="form">
                <div class="uploadImg">
                    <img src="<c:url value="/images/main/person-img.png"/>">
                    <div><input type="button" value="上传图片" style="padding-left: 5px;padding-right: 5px;"></div>
                    <div><input type="button" value="裁 剪"></div>
                </div><!--上传图片预览窗口-->
                <div class="preview">
                    <img src="<c:url value="/images/main/person-img.png"/>">
                    <span>预览</span>
                </div><!--裁剪图片预览窗口-->
                <div class="originalImg">
                    <img src="<c:url value="/images/main/person-img.png"/>">
                    <span>原始头像</span>
                </div><!--原始头像-->
            </div><!--头像设置-->
        </div>
    </div>
</div>
<script type="text/javascript">

    $(function(){

        /*判断主页中点击的选项，默认选中*/
        var setNum = ${setNum},
            value = setNum,
            nowLi = $(".setting_menu").find("ul").find("li"),
            setting_title = $(".setting_title").find("p"),
            show = $(".box_detail").find(".form");
        nowLi.eq(value).addClass("nowLi").siblings().removeClass("nowLi");
        setting_title.text(nowLi.eq(value).text());
        show.eq(value).addClass("active").siblings().removeClass("active");

        nowLi.click(function () {
            var index = $(this).index();
            $(this).addClass("nowLi").siblings().removeClass("nowLi");
            setting_title.text(nowLi.eq(index).text());
            show.eq(index).addClass("active").siblings().removeClass("active");
        })

        var saveNum=0;

        var saveInfo = $(".setting_menu").find("ul").find("li");
        saveInfo.click(function () {
            saveNum = $(this).index();
        });

        $("#saveInformation").click(function () {
            if(saveNum==0){
                $("form:eq("+setNum+")").submit();
                return false;
            }else{
                $("form:eq("+saveNum+")").submit();
                return false;
            }

        });

        /*返回主页*/
        $(".back_settingInfo").click( function () {
            window.history.back(-1);
            $(location).attr("href","${pageContext.request.contextPath}/security/enter");
        });

        $("#originalPassword").change(function(){
            var val = $(this).val();
            val = $.trim(val);
            if(val==null||val=="")
                return false;

            var url = "${pageContext.request.contextPath }/user/ajaxValidatePassword";
            var args = {"originalPassword":val,"date":new Date()};

            $.post(url, args, function(data){
                if(data=="0"){
                    $("#originalPasswordError").html("密码不正确！");

                }else if(data=="1"){
                    $("#originalPasswordError").html("");
                }else{
                    alert("网络或程序出错. ");
                }
            },"json");


        });

        $("#affirmPassword").change(function () {
            var firstPassword = $("#alertPassword").val();
            var secondPassword = $("#affirmPassword").val();
            if(firstPassword!=secondPassword)
                $("#affirmPasswordError").html("请再次确认密码！");
            else
                $("#affirmPasswordError").html("");
        });


        //获取canvas元素
        var cvs = document.getElementById("cvs");
        //创建image对象
        var imgObj = new Image();
        imgObj.src = "${pageContext.request.contextPath}/user/getPic/${user.userAccount}";
        //待图片加载完后，将其显示在canvas上
        imgObj.onload = function(){
            var ctx = cvs.getContext('2d');
            //ctx.drawImage(this, 0, 0);//this即是imgObj,保持图片的原始大小：470*480
            ctx.drawImage(this, 0, 0,150,200);//改变图片的大小到1024*768
        }

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
                ctx.drawImage(img,0,0,150,200);
                strDataURI = cvs.toDataURL();//获取canvas base64数据
            }
        }

    })
</script>
</body>
</html>