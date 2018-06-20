package com.xuebusi.xssm.common.util;

/**
 * 常用工具类
 *
 * @Author: syj
 * @CreateDate: 2018/3/30 13:54
 */
public class CommonUtil {

    /**
     * 判断对象能否转成Integer
     * @param number
     * @return
     */
    public static boolean isToInt(Object number) {
        try {
            Integer.valueOf(String.valueOf(number));
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    /**
     * 判断对象能否转成Long
     * @param number
     * @return
     */
    public static boolean isToLong(Object number) {
        try {
            Long.valueOf(String.valueOf(number));
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    /**
     * 对象转成Integer
     * @param number
     * @return
     */
    public static Integer toInt(Object number) {
        return CommonUtil.isToInt(number) ? Integer.valueOf(String.valueOf(number)) : null;
    }

    /**
     * 对象转成Long
     * @param number
     * @return
     */
    public static Long toLong(Object number) {
        return CommonUtil.isToLong(number) ? Long.valueOf(String.valueOf(number)) : null;
    }

    /**
     * 对象转成String
     * @param obj
     * @return
     */
    public static String valueOf(Object obj) {
        return obj != null ? String.valueOf(obj) : null;
    }

    /**
     * 在字符串首尾添加百分号
     * @param targetStr
     * @return
     */
    public static String addPercentSign(String targetStr) {
        return "%" + targetStr + "%";
    }

}
