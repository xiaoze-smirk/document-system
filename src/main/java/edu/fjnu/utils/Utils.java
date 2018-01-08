package edu.fjnu.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

    public String dateToStr(Date date){
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        return sf.format(date);
    }

    public Date strToDate(String str){
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = sf.parse(str);
            return date;

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    public String timeToStr(Date date){
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sf.format(date);
    }

    public Date strToTime(String str){
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date = sf.parse(str);
            return date;

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

}
