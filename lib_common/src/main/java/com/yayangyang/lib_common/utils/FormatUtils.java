package com.yayangyang.lib_common.utils;

import android.annotation.SuppressLint;
import android.text.TextUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FormatUtils {

    private static final long ONE_MINUTE = 60000L;
    private static final long ONE_HOUR = 3600000L;
    private static final long ONE_DAY = 86400000L;
    private static final long ONE_WEEK = 604800000L;

    private static final String ONE_SECOND_AGO = "秒前";
    private static final String ONE_MINUTE_AGO = "分钟前";
    private static final String ONE_HOUR_AGO = "小时前";
    private static final String ONE_DAY_AGO = "天前";
    private static final String ONE_MONTH_AGO = "月前";
    private static final String ONE_YEAR_AGO = "年前";

    private static SimpleDateFormat sdf = new SimpleDateFormat();
    public final static String FORMAT_DATE_TIME = "yyyy-MM-dd HH:mm:ss.SSS";

    /**
     * 获取当前日期的指定格式的字符串
     *
     * @param format 指定的日期时间格式，若为null或""则使用指定的格式"yyyy-MM-dd HH:mm:ss.SSS"
     * @return
     */
    public static String getCurrentTimeString(String format) {
        if (format == null || format.trim().equals("")) {
            sdf.applyPattern(FORMAT_DATE_TIME);
        } else {
            sdf.applyPattern(format);
        }
        return sdf.format(new Date());
    }

    /**
     * 根据时间字符串获取描述性时间，如3分钟前，1天前
     *
     * @param dateString 时间字符串
     * @return
     */
    public static String getDescriptionTimeFromDateString(String dateString) {
        LogUtils.e("dateString:"+dateString);
        if (TextUtils.isEmpty(dateString))
            return "";
        sdf.applyPattern(FORMAT_DATE_TIME);
        try {
            long wucha=8*60*60*1000;//追书神器服务器返回时间与真实值的误差(可能为8小时)
            Date parse = sdf.parse(formatZhuiShuDateString(dateString));
            parse.setTime(parse.getTime()+wucha);

            return getDescriptionTimeFromDate(parse);
        } catch (Exception e) {
            LogUtils.e("getDescriptionTimeFromDateString: " + e);
        }
        return "";
    }

    /**
     * 格式化追书神器返回的时间字符串
     *
     * @param dateString 时间字符串
     * @return
     */
    public static String formatZhuiShuDateString(String dateString) {
        return dateString.replaceAll("T", " ").replaceAll("Z", "");
    }

    /**
     * 根据Date获取描述性时间，如3分钟前，1天前
     *
     * @param date
     * @return
     */
    public static String getDescriptionTimeFromDate(Date date) {
        LogUtils.e(date.toString());
        long delta = new Date().getTime() - date.getTime();
        LogUtils.e(delta+"wwwwwwwwwwww");
        if (delta < 1L * ONE_MINUTE) {
            long seconds = toSeconds(delta);
            return (seconds <= 0 ? 1 : seconds) + ONE_SECOND_AGO;
        }
        if (delta < 45L * ONE_MINUTE) {
            long minutes = toMinutes(delta);
            return (minutes <= 0 ? 1 : minutes) + ONE_MINUTE_AGO;
        }
        if (delta < 24L * ONE_HOUR) {
            long hours = toHours(delta);
            return (hours <= 0 ? 1 : hours) + ONE_HOUR_AGO;
        }
        if (delta < 48L * ONE_HOUR) {
            return "昨天";
        }
        if (delta < 30L * ONE_DAY) {
            long days = toDays(delta);
            return (days <= 0 ? 1 : days) + ONE_DAY_AGO;
        }
        if (delta < 12L * 4L * ONE_WEEK) {
            long months = toMonths(delta);
            return (months <= 0 ? 1 : months) + ONE_MONTH_AGO;
        } else {
            long years = toYears(delta);
            return (years <= 0 ? 1 : years) + ONE_YEAR_AGO;
        }
    }

    private static long toSeconds(long date) {
        return date / 1000L;
    }

    private static long toMinutes(long date) {
        return toSeconds(date) / 60L;
    }

    private static long toHours(long date) {
        return toMinutes(date) / 60L;
    }

    private static long toDays(long date) {
        return toHours(date) / 24L;
    }

    private static long toMonths(long date) {
        return toDays(date) / 30L;
    }

    private static long toYears(long date) {
        return toMonths(date) / 365L;
    }

    public static String formatWordCount(int wordCount) {
        if (wordCount / 10000 > 0) {
            return (int) ((wordCount / 10000f) + 0.5) + "万字";
        } else if (wordCount / 1000 > 0) {
            return (int) ((wordCount / 1000f) + 0.5) + "千字";
        } else {
            return wordCount + "字";
        }
    }

    //-----------------------------------------------------------------
    @SuppressLint("SimpleDateFormat")
    public static String myFormatTime(Date date){
//        String s = date.getTime() + "";
//        String milliSsecond = s.substring(s.length() - 3, s.length());
//        return date.getYear()+1900+"-"+date.getMonth()+1+"-"+date.get
//                +"T"+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds()+"."
//                +milliSsecond+"Z";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(FORMAT_DATE_TIME);
        return simpleDateFormat.format(date);
    }

    public static String getStringByTimeStamp(String format,String timeStamp){
        SimpleDateFormat myFormat = new SimpleDateFormat(format);
        return myFormat.format(new Date(Long.parseLong(timeStamp)*1000L));
    }

    public static String getMeaninglessStringByStringLength(int length){
        String strz[]={"□","△","X"};
        int index= (int) (Math.random()*3);
        String str="";
        for(int i=0;i<length;i++){
            str+=strz[index];
        }
        return str;
    }

    /**
     * 字节-M
     * @param byteSize
     * @return
     */
    public static String byteToM(float byteSize){
        float v = byteSize / 1024 / 1024;
        return String.format("%.2f",v)+" MB";
    }
}