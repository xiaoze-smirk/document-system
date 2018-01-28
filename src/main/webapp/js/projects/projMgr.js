/**
 * Created by 傅彰炜 on 2018/1/17.
 */
$(document).ready(function() {
    /**************************跳转至某个项目详情页************************/
    /*点击项目名称进入一级目录*/
    $(".projMgr").on('click', 'tr td:nth-child(4) a', function () {
        var fPosition = $(this).text();/*获取当前点击的项目名称*/
        $(location).attr("href","${pageContext.request.contextPath}/../../folders/folder.html?fPosition="+fPosition);
    });

    /******************************进度条进度*****************************/
    $(document).ready(function () {
        var percentage = 0;
        var value=300;          /*停止的值*/
        var interval = setInterval(function () {
            if (percentage < value) {
                percentage++;
                var widthTemp = (percentage / 10).toFixed(1) + '%';
                $(".progressBar").css('width', widthTemp);
                $(".progressBar").text(widthTemp);
            } else {
                clearInterval(interval);
            }
        }, 10);
    });

    /*************************修改、删除按钮点击事件************************/
    $("button").click(function () {
        if ($(this).text() == "修 改") {
            $(location).attr("href","${pageContext.request.contextPath}/../../updateInfo/updateInfo.html");
        }
        if ($(this).text() == "删 除") {
            if (confirm("是否删除本行数据？")) {
                $(".box").find("table").find("tr").eq(index-1).remove();
            } else {
                alert("3");
            }
        }
        if ($(this).text() == "历 史") {
            $(location).attr("href","${pageContext.request.contextPath}/../../hVersion/hVersion.html");
        }
    });

    /**********************************翻页*******************************/
    /*获取当前数据信息*/
    var dataLength = ($("table").find("tr").length - 2)/2;
    $(".pageAll").text(dataLength); /*共有几条数据*/
    var checkValue = $(".number").val();    /*默认每页显示条数*/
    var pageNow = $(".pageNow").text();    /*当前所在页码*/
    /*最大页数*/
    var maxPage = Math.floor($(".pageAll").text() / checkValue) ;
    if($(".pageAll").text() % checkValue != 0)
        maxPage = maxPage + 1;
    /*初始显示的数据*/
    for(var i = 1 + (checkValue* (pageNow-1));i <= checkValue*pageNow;i ++ ) {
        $("table").find("tr").eq(i).addClass("trNow");
    }
    /*****************************首页尾页跳转按钮**************************/
    /*首页按钮*/
    $(".first").click(function () {
        checkValue = $(".number").val();
        pageNow = $(".pageNow").text();
        if( pageNow > 1){
            pageNow = 1;
            for(var i = 1 ;i <= dataLength;i ++){
                if( i <= checkValue){
                    $(".depMgr").find("tr").eq(i).addClass("trNow");
                } else {
                    $(".depMgr").find("tr").eq(i).removeClass("trNow");
                }
            }
        }
    })
    /*尾页按钮*/
    $(".last").click(function () {
        checkValue = $(".number").val();
        pageNow = $(".pageNow").text();
        if( pageNow < maxPage){
            $(".pageNow").text(maxPage);
            var start = checkValue * (maxPage-1) + 1;
            for(var i = 1;i <= dataLength;i ++){
                if( i < start){
                    $(".depMgr").find("tr").eq(i).removeClass("trNow");
                } else {
                    $(".depMgr").find("tr").eq(i).addClass("trNow");
                }
            }
        }
    })
    /*文本框获取焦点时*/
    $(".jumpTo").focus(function () {
        $(this).select();
    });
    /*文本框输入时按下回车键*/
    $(".jumpTo").bind('keypress',function(event){
        if(event.keyCode == "13")
        {
            $(this).blur();     /*使文本框失去焦点*/
            $(".go").click();
        }
    });
    /*跳转按钮*/
    $(".go").click(function () {
        var page = $(".jumpTo").val();      /*获取输入的页码*/
        if(page > 0 && page <= maxPage ){
            $(".pageNow").text(page);
            checkValue = $(".number").val();
            pageNow = $(".pageNow").text();
            for(var i = 1;i <= dataLength;i ++){
                if( i > checkValue *(pageNow-1) && i <= checkValue * pageNow ){
                    $(".depMgr").find("tr").eq(i).addClass("trNow");
                } else {
                    $(".depMgr").find("tr").eq(i).removeClass("trNow");
                }
            }
        } else {
            alert("输入错误！当前共有"+maxPage+"页，请重新输入页码！");
            $(".jumpTo").val("输入页码");       /*输入框回复默认值*/
        }
    })

    /********************************翻页按钮*****************************/
    $(".pageTurn").find("li").mouseover(function () {
        var index = $(this).index();
        if(index == 0){
            $(this).find("img").attr("src","../../images/prev_hover.png");
        }
        if(index == 2){
            $(this).find("img").attr("src","../../images/next_hover.png");
        }
    });
    $(".pageTurn").find("li").mouseleave(function () {
        var index = $(this).index();
        if(index == 0){
            $(this).find("img").attr("src","../../images/prev.jpg");
        }
        if(index == 2){
            $(this).find("img").attr("src","../../images/next.jpg");
        }
    });
    /*选择每页显示的条数*/
    $(".number").change(function(){
        checkValue = $(".number").find("option:selected").text();/*条数*/
        /*条数变化后页数更新*/
        maxPage = Math.floor($(".pageAll").text() / checkValue) ;
        if($(".pageAll").text() % checkValue != 0)
            maxPage = maxPage + 1;
        $(".pageNow").text("1");                 /*从第一页开始显示*/
        var startData = 1;                         /*第1条数据开始显示*/
        var endData = parseInt(checkValue) + 1;           /*第几条数据结束显示*/
        $(".jumpTo").val("输入页码");       /*输入框回复默认值*/
        for(var i = 1 ;i <= dataLength;i ++){
            if(i >= startData && i < endData){
                $(".depMgr").find("tr").eq(i).addClass("trNow");
            } else {
                $(".depMgr").find("tr").eq(i).removeClass("trNow");
            }
        }
    });
    /*上一页*/
    $(".prevBtn").click(function () {
        var prevPage = parseInt($(".pageNow").text()) - 1;
        if(prevPage > 0) {
            $(".pageNow").text(prevPage);
            var startData = 1 + (checkValue* (prevPage-1));
            var endData = checkValue*prevPage + 1;
            for(var i = 1 ;i <= dataLength;i ++){
                if(i >= startData && i < endData){
                    $(".depMgr").find("tr").eq(i).addClass("trNow");
                } else {
                    $(".depMgr").find("tr").eq(i).removeClass("trNow");
                }
            }
        }
    });
    /*下一页*/
    $(".nextBtn").click(function () {
        var nextPage = parseInt($(".pageNow").text()) + 1;
        if(nextPage <= maxPage) {
            $(".pageNow").text(nextPage);
            var startData = 1 + (checkValue* (nextPage-1));
            var endData = checkValue*nextPage + 1;
            for(var i = 1 ;i <= dataLength;i ++){
                if(i >= startData && i < endData){
                    $(".depMgr").find("tr").eq(i).addClass("trNow");
                } else {
                    $(".depMgr").find("tr").eq(i).removeClass("trNow");
                }
            }
        }
    });
})
