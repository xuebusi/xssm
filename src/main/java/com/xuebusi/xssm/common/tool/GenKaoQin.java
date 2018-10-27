package com.xuebusi.xssm.common.tool;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 考勤生成器
 *
 * @author: shiyanjun
 * @Date: 2018/10/27 下午9:04
 */
public class GenKaoQin {

    private static Random random = new Random();

    public static void main(String[] args) {
        List<String> list = gen(20, 9, 22, 56, 47);
        for (String s : list) {
            System.out.println(s);
        }
    }

    /**
     * 生成打卡记录
     *
     * @Author shiyanjun
     * @Date 2018/10/27 下午8:59
     * @Param days 生成天数
     * @Param amHour 生成早上小时部分
     * @Param pmHour 生成晚上小时部分
     * @Param amMaxMinute 生成早上分钟部分
     * @Param pmMaxMinute 生成晚上分钟部分
     * @Return
     * @Exception
     */
    public static List<String> gen(int days, int amHour, int pmHour, int amMaxMinute, int pmMaxMinute) {
        String h1 = String.valueOf(amHour);
        String h2 = String.valueOf(pmHour);

        List<String> list = new ArrayList<>();
        for (int i = 0; i < days; i++) {
            int r1 = random.nextInt(amMaxMinute);
            int r2 = random.nextInt(pmMaxMinute);

            String s1 = String.valueOf(r1);
            String s2 = String.valueOf(r2);

            String m1 = r1 < 10 ? "0" + s1 : s1;
            String m2 = r2 < 10 ? "0" + s2 : s2;

            StringBuilder sb = new StringBuilder();
            sb.append(h1).append(":").append(m1).append("-").append(h2).append(":").append(m2);
            list.add(sb.toString());
        }
        return list;
    }
}
