package edu.fjnu.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class StringUtil {

    public String[] strList(String str){
        return str.split("\\|");
    }

    public String str(String[] strList){
        StringBuilder builder = new StringBuilder();

        for (int i=0;i<strList.length;i++){
            if(i!=0)
                builder.append("|");
            builder.append(strList[i]);
        }
        return builder.toString();
    }

    //localDataTime转为string,获取年
    public String getYear(LocalDateTime localDateTime){
        int time = localDateTime.getYear();
        return String.valueOf(time);
    }

    //将数字+1，并且转为字符串
    public String getIntToStr(Integer i){
        return String.valueOf(i+1);
    }

    //获取五位字符串
    public String getFiveStr(int i){

        String str;
        if(i<10)
            str="0000"+ i;
        else if(i<100)
            str="000"+ i;
        else if(i<1000)
            str="00"+ i;
        else if(i<10000)
            str="0"+ i;
        else
            str=String.valueOf(i);
        return str;
    }

    public String downloadFileName(String str){
        String[] s=str.split("\\)");
        return s[0].substring(1,s[0].length())+"-"+s[1];
    }

}
