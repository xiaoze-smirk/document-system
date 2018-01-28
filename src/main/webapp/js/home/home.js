/**
 * Created by 傅彰炜 on 2018/1/17.
 */
$(document).ready(function() {
    var bwidth = window.screen.width;
    $(".box").find("table").width(bwidth-200);
    $(".box").find("table").find("tr").width(bwidth-200);
    /*************************确认、重置按钮点击事件************************/

    $('input[type="reset"]').click(function (e) {
        if (confirm("是否清空表单数据？")) {

        } else
            e.preventDefault();
    });

})