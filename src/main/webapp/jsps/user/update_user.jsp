<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/jsps/common/taglibs.jsp"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>修改用户信息</title>
    <link href="<c:url value="/css/home/home.css"/>" type="text/css" rel="stylesheet" />
    <link href="<c:url value="/css/users/update_user.css"/>" type="text/css" rel="stylesheet" />
    <script src="<c:url value="/js/jquery-3.2.1.min.js"/>"></script>
    <script src="<c:url value="/js/home/home.js"/>"></script>


</head>
<body>
<div class="title">
    <p>修改${user.userName}的信息</p>
</div>
<div class="box">

    <canvas id="cvs"  width="150" height="200" style="overflow: hidden">
        当前浏览器不支持canvas
        <!-- 如果浏览器支持canvas，则canvas标签里的内容不会显示出来 -->
    </canvas>
    <form:form class="formStyle" action="${pageContext.request.contextPath}/user/update" method="post" modelAttribute="user" enctype="multipart/form-data">
        <input type="hidden" name="_method" value="PUT"/>
        <div>
            <label class="labelFirst">账号:</label>
            <form:input path="userAccount"/>
        </div>
        <div>
            <label class="labelFirst">姓名:</label>
            <form:input path="userName"/>
        </div>
        <div>
            <label class="labelFirst">性别:</label>
            <%
                Map<String, String> status = new TreeMap<String, String>();
                status.put("M", "男");
                status.put("W", "女");

                request.setAttribute("status", status);
            %>
            <form:radiobuttons  path="userSex" items="${status}"/>
        </div>
        <div>
            <label class="labelFirst">手机号:</label>
            <form:input path="userPhone"/>
        </div>
        <div>
            <label class="labelFirst">邮箱:</label>
            <form:input path="userEmail"/>
        </div>
        <div>
            <label class="labelFirst">qq账号:</label>
            <form:input path="userQqAccount"/>
        </div>
        <div class="label">
            <label class="labelFirst">个人证照:</label>
            <input id="personPhoto" accept="image/*" style="margin-left: 10px;margin-top: 5.5px;" type="file" name="personPhoto" />
            <label class="labelSecond"></label>
        </div>
        <div>
            <label class="labelFirst">权限:</label>
            <form:select path="userAuthorityId">
                <form:options items="${user.authorities}" itemLabel="authorityChineseName" itemValue="authorityId" />
            </form:select>
        </div>
        <div>
            <div class="addStyle"><input type="submit" value="确定" /></div>
            <div class="addStyle"><input type="reset" id="rbutton" value="重置" /></div>
        </div>
    </form:form>
</div>

<script type="text/javascript">
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
</script>
</body>
</html>