package com.meeting.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateCalculation {

    /**
     * 获取指定日期所在月份开始的时间戳
     * @param date 格式yyyy-MM
     * @return
     */
    public static Long getMonthBegin(String date){
        Date d = new Date();
        try {
            d = new SimpleDateFormat("yyyy-MM").parse(date);
        }catch (ParseException e) {
            e.printStackTrace();
        };
        return d.getTime();
    }

    /**
     * 获取指定日期所在月份结束的时间戳
     * @param date 格式yyyy-MM
     * @return
     */
    public static Long getMonthEnd(String date){
        String[] strings = date.split("-");
        int year = Integer.parseInt(strings[0]);
        int month = Integer.parseInt(strings[1]);
//        System.out.println(year+":"+month);
        if(month==12){
            year++;
            month=1;
        }else
            month++;
        Date d = new Date();
        try {
            date = String.valueOf(year)+"-"+String.valueOf(month);
            d = new SimpleDateFormat("yyyy-MM").parse(date);
        }catch (ParseException e) {
            e.printStackTrace();
        };
        return d.getTime()-1000;
    }
}