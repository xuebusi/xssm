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
public class GenClock {

    private static Random random = new Random();

    public static void main(String[] args) {
        List<String> list = gen(30, 9, 22, 56, 32, 30, 12);
        for (String s : list) {
            System.out.println(s);
        }
    }

    /**
     * 生成打卡记录
     *
     * @author shiyanjun
     * @date 2018/10/27 下午8:59
     * @param days 生成天数
     * @param amHour 早上小时部分
     * @param pmHour 晚上小时部分
     * @param amMaxMinute 早上分钟最大值
     * @param amMinMinute 早上分钟最小值
     * @param pmMaxMinute 晚上分钟最大值
     * @param pmMinMinute 晚上分钟最小值
     * @return
     * @exception
     */
    public static List<String> gen(int days, int amHour, int pmHour, int amMaxMinute, int amMinMinute, int pmMaxMinute, int pmMinMinute) {
        String h1 = String.valueOf(amHour);
        String h2 = String.valueOf(pmHour);

        List<String> list = new ArrayList<>();
        for (int i = 0; i < days; i++) {

            int r1 = 0;
            while (r1 < amMinMinute) {
                r1 = random.nextInt(amMaxMinute);
            }

            int r2 = 0;
            while (r2 < pmMinMinute) {
                r2= random.nextInt(pmMaxMinute);
            }

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
