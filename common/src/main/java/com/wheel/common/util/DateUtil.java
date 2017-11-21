package com.wheel.common.util;


import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 类DateUtil.java的实现描述：TODO 类实现描述
 *
 */
public class DateUtil {
    public static final String YYYY_YEAR_MM_MONTH_DD_DATE = "yyyy年MM月dd日";
    public static final String YYYY_MM_DD = "yyyy-MM-dd";
    public static final String YYYY_BIAS_MM_BIAS_DD = "yyyy/MM/dd";
    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    public static final String YYYY_MM_DD_HH_MM_SS_SSS = "yyyy-MM-dd HH:mm:ss.SSS";
    public static final String HH_MM_SS = "HH:mm:ss";
    public static final String YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";
    public static final String YYYY = "yyyy";
    public static final String MM = "MM";
    public static final String DD = "dd";
    public static final String HH = "HH";
    public static final String MI = "mm";
    public static final String SS = "ss";
    public static final String SIMPLE_YYYY_MM_DD = "yyyyMMdd";

    public static Map<String, String> numMap = new HashMap();

    static {
        numMap.put("0", "零");
        numMap.put("1", "一");
        numMap.put("2", "二");
        numMap.put("3", "三");
        numMap.put("4", "四");
        numMap.put("5", "五");
        numMap.put("6", "六");
        numMap.put("7", "七");
        numMap.put("8", "八");
        numMap.put("9", "九");
    }


    public static Date toDate(String date, String format) throws ParseException {
        if (date == null || date == "") {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        sdf.setLenient(false);
        return sdf.parse(date);
    }

    public static String toStr(Date date, String format) {
        if (date == null) {
            return null;
        }
        DateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(date);
    }

    public static Timestamp toTimestamp(Date date) {
        return new Timestamp(date.getTime());
    }

    /**
     * 将String类型的日期转换成TimeStamp</br>
     * Tip：日期参数的格式类型，必须和format参数的格式一致，否则抛出ParseException
     *
     * @param date   - 日期
     * @param format - 格式化类型
     * @return Timestamp
     * @throws ParseException
     */
    public static Timestamp toTimestamp(String date, String format) throws ParseException {
        return toTimestamp(toDate(date, format));
    }

    public static String toStr(String date, String original_format, String transform_format) throws ParseException {
        return toStr(toDate(date, original_format), transform_format);
    }

    /**
     * 将Timestamp类型的日期根据输入的format的格式转换成Sting类型
     *
     * @param timestamp 日期
     * @param format    format格式
     * @return String
     */
    public static String toStr(Timestamp timestamp, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(timestamp);
    }

    public static String dateToStr(Date date, String format) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    public static Timestamp getBeginTimeOfDay(Date calBeginDate) throws ParseException {
        String date = dateToStr(calBeginDate, YYYY_MM_DD);
        date = date + " 00:00:00.000";
        return toTimestamp(date, YYYY_MM_DD_HH_MM_SS_SSS);
    }

    public static Timestamp getEndTimeOfDay(Date calBeginDate) throws ParseException {
        String date = dateToStr(calBeginDate, YYYY_MM_DD);
        date = date + " 23:59:59.999";
        return toTimestamp(date, YYYY_MM_DD_HH_MM_SS_SSS);
    }

    public static Date getFirstDayOfMonth(String date, String format) {
        Date d = null;
        try {
            d = toDate(date, format);
            return calFirstDayOfMonth(d);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static Date getFirstDayOfMonth(Date date) {
        return calFirstDayOfMonth(date);
    }

    private static Date calFirstDayOfMonth(Date date) {
        Calendar firstDate = null;
        try {
            firstDate = Calendar.getInstance();
            firstDate.setTime(date);
            firstDate.set(Calendar.DATE, 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return firstDate.getTime();
    }

    public static Date getLastDayOfMonth(String date, String format) {
        Date d = null;
        try {
            d = toDate(date, format);
            return calLastDayOfMonth(d);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Date getLastDayOfMonth(Date date) {
        return calLastDayOfMonth(date);
    }

    private static Date calLastDayOfMonth(Date date) {
        Calendar lastDate = null;
        try {
            lastDate = Calendar.getInstance();
            lastDate.setTime(date);
            lastDate.set(Calendar.DATE, 1);// 设为当前月的1号
            lastDate.add(Calendar.MONTH, 1);// 加一个月，变为下月的1号
            lastDate.add(Calendar.DATE, -1);// 减去一天，变为当月最后一天
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lastDate.getTime();
    }

    public static int getDaysOfMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, Integer.valueOf(toStr(date, YYYY)));
        c.set(Calendar.MONTH, Integer.valueOf(toStr(date, MM)) - 1);
        return c.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    public static String parseToZhYear(String year) {

        char[] yearArray = year.toCharArray();
        return numMap.get(String.valueOf(yearArray[0])) + numMap.get(String.valueOf(yearArray[1])) + numMap.get(String.valueOf(yearArray[2])) + numMap.get(String.valueOf(yearArray[3]));
    }


    public static void main(String[] args) throws ParseException {
        String date = "14-09-02";
        String date2 = "2014-12-0";
        System.out.println(DateUtil.toDate(date, DateUtil.YYYY_MM_DD));
//        System.out.println(end);
//        System.out.println(getDaysOfMonth(begin) + "");
    }
}