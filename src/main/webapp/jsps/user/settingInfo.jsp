<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/jsps/common/taglibs.jsp"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>个人设置</title>
    <link href="<c:url value="/css/settingInfo.css"/>" rel="stylesheet" type="text/css">
    <script src="<c:url value="/js/jquery-1.11.0.min.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/js/cropbox.js"/>"></script>
</head>
<body>
<div class="box_title">
    <img src="${pageContext.request.contextPath}/images/logo.png" class="logo">
    <span class="title">项目文档管理系统</span>
    <div class="back_settingInfo"><span>返回</span></div>
</div>
<div class="box_setting">
    <div class="setting_menu">
        <div>
            <img onerror="this.src='${pageContext.request.contextPath}/images/main/person-img.png'" src="${user.userAvatar}" class="person-img">
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
                    <label id="originalPasswordError" class="secondLabel"></label>
                </div>
                <div>
                    <label class="firstLabel">修改密码：</label>
                    <input id="alertPassword" type="password" name="alertPassword">
                    <label id="orinalPasswordError" class="secondLabel" style="color: red"></label>
                </div>
                <div>
                    <label class="firstLabel">确认密码：</label>
                    <input id="affirmPassword" type="password" name="affirmPassword">
                    <label id="affirmPasswordError" class="secondLabel" style="color: red"></label>
                </div>
            </form:form><!--修改密码-->
            <div class="form">
                <div class="container">
                    <div class="imageBox">
                        <div class="thumbBox"></div>
                        <div class="spinner" style="display: none">Loading...</div>
                    </div>
                    <div class="action">
                        <div class="new-contentarea tc">
                            <a href="javascript:void(0)" class="upload-img">
                                <label for="upload-file">上传图像</label>
                            </a>
                            <input type="file" class="" name="upload-file" id="upload-file" />
                        </div>
                        <input type="button" id="btnCrop"  class="Btnsty_peyton" value="裁切">
                        <input type="button" id="btnZoomIn" class="Btnsty_peyton" value="+"  >
                        <input type="button" id="btnZoomOut" class="Btnsty_peyton" value="-" >
                    </div>
                    <div class="cropped"></div>
                </div>
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



        /*返回主页*/
        $(".back_settingInfo").click( function () {
            window.history.back(-1);
            $(location).attr("href","${pageContext.request.contextPath}/security/enter");
        });

        <%--$("#originalPassword").change(function(){--%>
        <%--var val = $(this).val();--%>
        <%--val = $.trim(val);--%>
        <%--if(val==null||val=="")--%>
        <%--return false;--%>

        <%--var url = "${pageContext.request.contextPath }/user/ajaxValidatePassword";--%>
        <%--var args = {"originalPassword":val,"date":new Date()};--%>

        <%--$.post(url, args, function(data){--%>
        <%--if(data=="0"){--%>
        <%--$("#originalPasswordError").html("密码不正确！");--%>

        <%--}else if(data=="1"){--%>
        <%--$("#originalPasswordError").html("");--%>
        <%--}else{--%>
        <%--alert("网络或程序出错. ");--%>
        <%--}--%>
        <%--},"json");--%>


        <%--});--%>

        // $("#affirmPassword").change(function () {
        //     var firstPassword = $("#alertPassword").val();
        //     var secondPassword = $("#affirmPassword").val();
        //     if(firstPassword!=secondPassword)
        //         $("#affirmPasswordError").html("请再次确认密码！");
        //     else
        //         $("#affirmPasswordError").html("");
        // });

        $(".form").find("input").blur(function(){
            var index = $(this).parent().index();    /*点击的文本框*/
            var value = $(this).val();     /*点击的文本框的值*/
            var length = value.replace(/[^/x00-\xff]/g,"**").length;    /*文本框输入的长度*/
            var right = "<img src='${pageContext.request.contextPath}/images/folders/selected.png' />";     /*输入正确时*/
            var wrong = "<img src='${pageContext.request.contextPath}/images/folders/wrong.png' />"+"<p>输入错误！请重新输入！</p>";       /*输入错误时*/
            if(index == 0){
                if(length > 6 && length < 10) {
                    $(this).parent().find(".secondLabel").html(right);
                } else
                    $(this).parent().find(".secondLabel").html(wrong);
            }
            if(index == 1){
                if (length > 2 && length < 9){
                    $(this).parent().find(".secondLabel").html(right);
                } else
                    $(this).parent().find(".secondLabel").html(wrong);
            }
            if(index == 2){
                var firstPwd = $("#alertPassword").val();
                var secondPwd = $("#affirmPassword").val();
                if (secondPwd == firstPwd){
                    $(this).parent().find(".secondLabel").html(right);
                } else
                    $(this).parent().find(".secondLabel").html(wrong);
            }
        });

        /********************************上传图片函数*******************************/
        /**
         * 将以base64的图片url数据转换为Blob
         * @param urlData
         *            用url方式表示的base64图片数据
         */
        function convertBase64UrlToBlob(urlData){

            var bytes=window.atob(urlData.split(',')[1]);        //去掉url的头，并转换为byte

            //处理异常,将ascii码小于0的转换为大于0
            var ab = new ArrayBuffer(bytes.length);
            var ia = new Uint8Array(ab);
            for (var i = 0; i < bytes.length; i++) {
                ia[i] = bytes.charCodeAt(i);
            }

            return new Blob( [ab] , {type : 'image/png'});
        }
        var options =
            {
                thumbBox: '.thumbBox',
                spinner: '.spinner',
                imgSrc: '${pageContext.request.contextPath}/images/avatar.jpg'
            }
        var cropper = $('.imageBox').cropbox(options);
        $('#upload-file').on('change', function(){
            var reader = new FileReader();
            reader.onload = function(e) {
                options.imgSrc = e.target.result;
                cropper = $('.imageBox').cropbox(options);
            }
            reader.readAsDataURL(this.files[0]);
            this.files = [];
        })
        $('#btnCrop').on('click', function(){
            var img = cropper.getDataURL();
            $('.cropped').html('');
            $('.cropped').append('<img src="'+img+'" align="absmiddle" style="width:64px;margin-top:4px;border-radius:64px;box-shadow:0px 0px 12px #7E7E7E;" ><p>64px*64px</p>');
            $('.cropped').append('<img src="'+img+'" align="absmiddle" style="width:128px;margin-top:4px;border-radius:128px;box-shadow:0px 0px 12px #7E7E7E;"><p>128px*128px</p>');
            $('.cropped').append('<img src="'+img+'" align="absmiddle" style="width:180px;margin-top:4px;border-radius:180px;box-shadow:0px 0px 12px #7E7E7E;"><p>180px*180px</p>');
        })
        $('#btnZoomIn').on('click', function(){
            cropper.zoomIn();
        })
        $('#btnZoomOut').on('click', function(){
            cropper.zoomOut();
        })

        $("#saveInformation").click(function () {
            if(saveNum==0){
                if(setNum!=2){
                    $("form:eq("+setNum+")").submit();
                    return false;
                }else{
                    // 提交用户头像的图片数据
                    var formData = new FormData();   //不需要提交其他参数可以直接FormData无参数的构造函数,如果需要提交form里面其他参数在form里面写
                    var base64Codes = cropper.getDataURL();
                    formData.append("fileName",String(parseInt(String(Math.random()*1000000)))+".jpg");
                    formData.append("file",convertBase64UrlToBlob(base64Codes));  //append函数的第一个参数是后台获取数据的参数名,和html标签的input的name属性功能相同

                    $.ajax({
                        url: 'http://localhost:8081/upload',
                        type: 'POST',
                        cache: false,
                        data: formData,
                        processData: false,
                        contentType: false,
                        success: function(data){

                            var avatarUrl = data;

                            // 保存头像更改到数据库
                            $.ajax({
                                url: "${pageContext.request.contextPath}/user/saveAvatar",
                                type: 'POST',
                                data: {"userAccount":${user.userAccount}, "avatarUrl":avatarUrl},
                                success: function(data){
                                    $(location).attr("href","${pageContext.request.contextPath}/user/returnPerson");
                                },
                                error : function() {
                                    alert("网络或程序出错1. ");
                                }
                            });
                        },
                        error : function() {
                            alert("网络或程序出错2. ");
                        }
                    })
                }

            }else{
                if(saveNum!=2){
                    $("form:eq("+saveNum+")").submit();
                    return false;
                }else{
                    // 提交用户头像的图片数据
                    var formData = new FormData();   //不需要提交其他参数可以直接FormData无参数的构造函数,如果需要提交form里面其他参数在form里面写
                    var base64Codes = cropper.getDataURL();
                    formData.append("fileName",String(parseInt(String(Math.random()*1000000)))+".jpg");
                    formData.append("file",convertBase64UrlToBlob(base64Codes));  //append函数的第一个参数是后台获取数据的参数名,和html标签的input的name属性功能相同

                    $.ajax({
                        url: 'http://localhost:8081/upload',
                        type: 'POST',
                        cache: false,
                        data: formData,
                        processData: false,
                        contentType: false,
                        success: function(data){

                            var avatarUrl = data;

                            // 保存头像更改到数据库
                            $.ajax({
                                url: "${pageContext.request.contextPath}/user/saveAvatar",
                                type: 'POST',
                                data: {"userAccount":${user.userAccount}, "avatarUrl":avatarUrl},
                                success: function(data){
                                    $(location).attr("href","${pageContext.request.contextPath}/user/returnPerson");
                                },
                                error : function() {
                                    alert("网络或程序出错1. ");
                                }
                            });
                        },
                        error : function() {
                            alert("网络或程序出错2. ");
                        }
                    })
                }
            }
        })




        /********************************Canvas图片函数*******************************/
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