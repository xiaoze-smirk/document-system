package edu.fjnu.utils;

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

}
