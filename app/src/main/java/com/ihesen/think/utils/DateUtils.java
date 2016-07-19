package com.ihesen.think.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * author: ihesen on 2016/4/22 14:44
 * email: hesen@ichsy.com
 */
public class DateUtils {
    private static SimpleDateFormat matter1 = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 一天的秒数
     */
    private final static long A_DAY_SECOND = 1000 * 60 * 60 * 24;

    /**
     * 获取今天的日期
     *
     * @return
     */
    public static String getToday() {
        java.util.Date dt = new java.util.Date();
        return matter1.format(dt);
    }

    /**
     * 日期相减
     *
     * @param date
     * @param xxx
     * @return
     */
    public static String DateSub(String date, int xxx) {
        Calendar calendar = Calendar.getInstance();
        java.util.Date b = null;
        try {
            b = matter1.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        calendar.setTime(b);
        calendar.setTimeInMillis(calendar.getTimeInMillis() - xxx * A_DAY_SECOND);
        return matter1.format(calendar.getTime());
    }

    /**
     * 将date日加XXX天
     *
     * @param date
     * @param xxx
     * @return
     */
    public static String DateAdd(String date, int xxx) {
        Calendar calendar = Calendar.getInstance();
        java.util.Date b = null;
        try {
            b = matter1.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        calendar.setTime(b);
        calendar.setTimeInMillis(calendar.getTimeInMillis() + xxx * A_DAY_SECOND);
        return matter1.format(calendar.getTime());
    }

    /**
     * 判断指定的是不是今天
     *
     * @param date
     * @return
     */
    public static boolean isDoday(String date) {
        return date.equals(getToday());
    }

    /**
     * 获取今天是周几
     *
     * @return
     */
    public static int getTodayOfWeek() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new java.util.Date());
        return cal.get(Calendar.DAY_OF_WEEK) - 1;
    }

    /**
     * 获取一个时间的分秒部分并且转换成秒
     *
     * @param date
     * @return
     */
    public static int getTimeMSPart(String date) {
        int result = 0;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("时间：" + date);
        try {
            java.util.Date toDate = dateFormat.parse(date);
            Calendar c = Calendar.getInstance();
            result = c.MINUTE * 60 + c.SECOND;
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;
    }

    public static String getMS(int s) {
        StringBuilder sb = new StringBuilder();
        sb.append("00时");
        int mPort = s / 60;
        if (mPort < 10) {
            sb.append("0");
            sb.append(mPort);
        } else {
            sb.append(mPort);
        }
        sb.append("分");
        int sPort = s % 60;
        if (sPort < 10) {
            sb.append("0");
            sb.append(sPort);
        } else {
            sb.append(sPort);
        }
        sb.append("秒");
        return sb.toString();
    }

    /**
     * 获取当前时间, 时间格式 MM-dd HH:mm
     *
     * @return
     */
    public static String getCurrentTime() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA).format(new Date());
    }

    /**
     * 获取当前时间, 时间格式 MM-dd HH:mm
     *
     * @return
     */
    public static String getCurrentTimeBeforeAWeek() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA).format(System.currentTimeMillis() - 24 * 7 * 60 * 60 * 1000);
    }

    /**
     * 获取当前时间, 时间格式 MM-dd HH:mm
     *
     * @return
     */
    public static String getCurrentDate() {
        return new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA).format(new Date());
    }

    /**
     * 获取同步时间, 时间格式yyyy年MM月dd日 HH:mm
     *
     * @return
     */
    public static String getSyncDate() {
        return new SimpleDateFormat("yyyy年MM月dd日 HH:mm", Locale.CHINA).format(new Date());
    }

    /**
     * 勿乱用, 勿通用性, 获取相差 delta月的String, 格式为yyyy-MM
     *
     * @param dateString
     * @return
     */
    public static String getDeltaMonth(String dateString, int delta) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM", Locale.CHINA);
        Calendar calendar = Calendar.getInstance();
        try {
            Date parse = sdf.parse(dateString);
            calendar.setTime(parse);
            calendar.add(Calendar.MONTH, delta);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String formatDate = sdf.format(calendar.getTime());
        String date = formatDate.substring(0, 7);
        return date;
    }
}
