/**
 * Created by 傅彰炜 on 2018/1/23.
 */
$(document).ready(function() {
    $(".return").click(function () {
        $(location).attr("href","${pageContext.request.contextPath}/../../projects/projMgr.html");
    })
})