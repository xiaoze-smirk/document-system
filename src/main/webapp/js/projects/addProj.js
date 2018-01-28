/**
 * Created by 傅彰炜 on 2018/1/17.
 */
$(document).ready(function() {
    /*时间控件*/
    var myDate = new Date();
    var year = myDate.getFullYear();  //获取当前年
    var month = ("0" + (myDate.getMonth() + 1)).slice(-2);//获取当前月
    var day = ("0" + myDate.getDate()).slice(-2);//获取当前日，如果小于9，前面补0
    var today = year + "-" + month + "-" + day;
    $(".dateBegin").attr("value", today);
    $(".dateEnd").attr("value", today);
});