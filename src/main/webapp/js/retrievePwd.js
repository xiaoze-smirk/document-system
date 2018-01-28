/**
 * Created by 傅彰炜 on 2018/1/21.
 */
$(document).ready(function() {
    $(".backToLogin").click(function () {
        $(location).attr("href","${pageContext.request.contextPath}/../login.html");
    })
});