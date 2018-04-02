package com.xuebusi.xssm.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期工具类
 */
public class DateUtils {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    /**
     * 当前系统时间（毫秒）
     * @return
     */
    public static long getCurrentTimeMillis() {
        return System.currentTimeMillis();
    }

    /**
     * 毫秒转成Date
     * @param timeMillis
     * @return
     */
    public static Date toDate(long timeMillis) {
        return new Date(timeMillis);
    }

    /**
     * Date转成字符串
     * @param date
     * @return
     */
    public static String dateToStr(Date date) {
        return DATE_FORMAT.format(date);
    }

    /**
     * 几个小时之后的时间
     * @param date 指定日期
     * @param hours 间隔小时数
     * @return
     */
    public static long intervalHours(Date date, int hours) {
        return date.getTime() + hours * 60 * 60 * 1000;
    }

    /**
     * 相对于指定的时间，是否已过去几个小时
     * @param date 指定时间
     * @param hours 过去几个小时
     * @return
     */
    public static boolean pastHours(Date date, int hours) {
        return getCurrentTimeMillis() >= intervalHours(date, hours);
    }
}
