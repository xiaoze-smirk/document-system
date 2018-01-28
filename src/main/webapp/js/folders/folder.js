/**
 * Created by 傅彰炜 on 2018/1/17.
 */
$(document).ready(function() {
    /*进入一级目录*/
    $(".box_position").find("span").eq(1).html("项目文档管理系统");
    $(".box_position").find("img").eq(0).addClass("imgNow");
    $(".box_position").find("span").eq(1).addClass("posNow");
    $(".input-file").find("input").css("display","none");   /*不可上传*/

    /*****************************设置信息跳转****************************/
    /*跳转至个人信息*/
    var setInfo = $(".personal_info").find("ul").find("li");
    setInfo.click(function () {
        var setNum = $(this).index();
        $(this).find("a").attr("href", "settingInfo.html?setNum=" + setNum);
    })

    /*****************************当前位置***************************/
    /*点击事件*/
    var pos = $(".box_position").find("span");
    pos.click(function () {
        $(".input-file").find("input").css("display","none");   /*不可上传*/
        var index = $(this).index();
        if (index == 0) {
            $(location).attr("href","${pageContext.request.contextPath}/../../projects/projMgr.html");
            $(".box_position").find("img").removeClass("imgNow");
            $(".box_position").find("span").slice(0 - 3).removeClass("posNow");
            $(".fileDetail").find(".box2").eq(3 - 4).removeClass("now");
            $(".fileDetail").find(".box2").eq(2).addClass("now");
            $(".hVersion").css("display","none");
            imgChange();
        }
        if (index == 2) {
            $(".box_position").find("img").slice(0 - 2).removeClass("imgNow");
            $(".box_position").find("span").slice(1 - 3).removeClass("posNow");
            $(".fileDetail").find(".box2").slice(2 - 4).removeClass("now");
            $(".fileDetail").find(".box2").eq(2).addClass("now");
            $(".hVersion").css("display","none");
            imgChange();
        }
        if (index == 4) {
            $(".box_position").find("img").eq(2).removeClass("imgNow");
            $(".box_position").find("span").eq(3).removeClass("posNow");
            $(".fileDetail").find(".box2").eq(4).removeClass("now");
            $(".fileDetail").find(".box2").eq(3).addClass("now");
            $(".hVersion").css("display","none");
            imgChange();
        }
        if (index == 6) {
            $(".input-file").find("input").css("display","block");   /*可上传*/
        }
    })
    function imgChange() {
        bgImgBefore.unbind("mouseover",fnMyFunc);
        bgImgBefore.unbind("mouseleave",fnMyFunc1);
        bgImgBefore.removeClass("funcNow");
        bgImgBefore.removeClass("tFunc");
        for(var i = 0; i<bgImgBefore.length;i ++){
            bgImgBefore.eq(i).find("img").attr("src",ImgGray[i]);
        }
    }

    /**************************设置操作按钮背景***************************/
    var ImgGray = ["../../images/folders/upload_gray.png",
        "../../images/folders/download_gray.png",
        "../../images/folders/delete_gray.png"];        /*表示操作栏不可点击时*/
    var ImgPosition = ["../../images/folders/upload.png",
        "../../images/folders/download.png",
        "../../images/folders/delete.png"];    /*表示操作栏可点击时*/
    var ImgHover = ["../../images/folders/upload_hover.png",
        "../../images/folders/download_hover.png",
        "../../images/folders/delete_hover.png"];       /*表示操作栏点击时*/
    var fnMyFunc,fnMyFunc1;     //函数变量
    var bgImgBefore = $(".box_function").find(".function").find("span");
    $.each(ImgGray, function (n, value) {       /*操作栏初始化*/
        bgImgBefore.eq(n).find("img").attr('src', value);
    });

    /***************************文件夹单击事件**************************/
    /*双击重命名文件夹*/
// var rename = $(".box_file").find("span");
// rename.on("dblclick",function (element) {
//     var oldhtml = $(this).text();   //获得元素之前的内容
//     $(this).text("");　　 //设置元素内容为空
//     $(this).addClass("rename");
//     var addinput = $("<input type='text' class='addinput' style='width: 100px'/>"); //创建一个input元素
//     addinput.val(oldhtml);
//     $(this).after(addinput);
//     addinput.focus();   //获得焦点
//     //设置addinput失去焦点的事件
//     addinput.blur( function (){
//         //下面应该判断是否做了修改并使用ajax代码请求服务端将id与修改后的数据提交
//         //    alert(element.id);
//         //当触发时判断newobj的值是否为空，为空则不修改，并返回oldhtml
//         var newhtml = addinput.val();
//         $(".rename").val(newhtml ? newhtml : oldhtml);
//     });
// });/*修改文件名*/

    /*************************文件夹双击事件**************************/
    /*传递项目名*/
    // (function ($) {
    //     $.getUrlParam = function (name) {
    //         var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    //         var r = window.location.search.substr(1).match(reg);
    //         if (r != null) return unescape(r[2]); return null;
    //     }
    // })(jQuery);
    // var xx = $.getUrlParam('fPosition');
    // alert(xx);

    /*双击文件夹进入二级目录*/
    var firstindex;
    var sPosition;
    var file = $(".firstFile").find(".box_file");
    file.find("img").on("dblclick", function () {
        firstindex = $(this).parent().index();
        sPosition = $(this).parent().text();
        $(".fileDetail").find(".box2").eq(3).addClass("now").siblings().removeClass("now");
        $(".secondFile").find(".second").eq(firstindex).addClass("secondNow").siblings().removeClass("secondNow");
        $(".box_position").find("img").eq(1).addClass("imgNow");
        $(".box_position").find("span").eq(2).html(sPosition);
        $(".box_position").find("span").eq(2).addClass("posNow");
        $(".input-file").find("input").css("display","none");   /*不可上传*/
    })

    /*双击文件夹进入三级目录*/
    var file2 = $(".secondFile").find(".second");
    var tPosition;
    var tindex;
    file2.find("img").on("dblclick", function () {
        var secondindex = $(this).parent().index();
        tindex = firstindex * 2 + secondindex;
        tPosition = $(this).parent().text();
        $(".fileDetail").find(".box2").eq(4).addClass("now").siblings().removeClass("now");
        $(".thirdFile").find(".third").eq(tindex).addClass("thirdNow").siblings().removeClass("thirdNow");
        $(".box_position").find("img").eq(2).addClass("imgNow");
        $(".box_position").find("span").eq(3).html(tPosition);
        $(".box_position").find("span").eq(3).addClass("posNow");
        $(".hVersion").css("display","block");
        $(".input-file").find("input").css("display","block");      /*显示可上传*/

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

        /**********************第三级文件选择事件**********************/
        var show = new Array();    /*判断文件是否选中*/
        var t = new Array();      /*文件点击奇偶次数*/
        var num = 0;
        var tt = $(".thirdFile").find(".third").eq(tindex);
        for (var i=0;i<tt.children("div").length;i++){
            show[i] = true;
            t[i] = 0;
        };
        tt.find(".box_file").mouseover(function () {
            $(this).children(".imgSelect").css("opacity",1);
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
                $(this).attr("src","../../images/folders/selected.png");
                $(this).parent().addClass("fileNow");
                show[index] = false;
                t[index] = 1;
                num ++;
            } else {        /*未选中*/
                $(this).attr("src","../../images/folders/select.png");
                $(this).parent().removeClass("fileNow");
                show[index] = true;
                t[index] = 0;
                num --;
            }
        });

        /*下载*/
        $(".btn_download").click(function () {
            if(num == 0)
                alert("您还未选择任何文件！");
            else {
                var download = new Array();      /*记录被选中文件的位置*/
                var d = 0;
                for(var i = 0;i<bgImgBefore.length;i++){
                    if(t[i]== 1){
                        var value = $(".third").eq(tindex).find(".box_file").eq(i).find("span").text();
                        download[d] = value;
                        d ++;
                    }
                }
                if(confirm("您选中了"+download+"。是否下载？"))
                    alert("已下载！");
            }
        });

        /*删除*/
        $(".btn_delete").click(function () {
            if(num == 0)
                alert("您还未选择任何文件！");
            else {
                var del = new Array();      /*记录被选中文件的位置*/
                var de = 0;
                for(var i = 0;i<bgImgBefore.length;i++){
                    if(t[i]== 1){
                        var value = $(".third").eq(tindex).find(".box_file").eq(i).find("span").text();
                        del[de] = value;
                        de ++;
                    }
                }
                if(confirm("您选中了"+del+"。是否删除？"))
                    alert("已删除！");
            }
        });

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
        } else {
            edit.html("编辑");
            $(".labelInfo").find("input").attr("disabled",true);
            $(".labelInfo").find("input").css("border","1px solid #EDF8F1");
            $(".labelInfo").find("input").css("background-color","#EDF8F1");
        }
        return false;
    });

    /****************************查看历史版本***************************/
    $(".hVersion").click(function () {
        $(".fileDetail").css("display","none");
        $(".version").css("display","block");
        $(".edit").css("display","none");
        $(".hVersion").css("display","none");
    });
    $(".return").click(function () {
        $(".fileDetail").css("display","block");
        $(".version").css("display","none");
        $(".edit").css("display","block");
        $(".hVersion").css("display","block");
    });
    // $(".hVersion").click(function () {
    //     $(location).attr("href","${pageContext.request.contextPath}/../../hVersion/hVersion.html");
    // });
})