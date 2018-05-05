package com.xuebusi.xssm.test;

import com.xuebusi.xssm.test.iterator.Iterator;
import com.xuebusi.xssm.test.list.Link;
import com.xuebusi.xssm.test.list.impl.ArrayList;
import com.xuebusi.xssm.test.list.List;
import com.xuebusi.xssm.test.list.impl.LinkedList;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * 测试类
 */
public class AppTest {

    public static void main(String[] args) {
//        testIterator();

        // 手机号码格式校验
        if (!isPhone("17510639101")) {
            System.out.println("手机号码不合法");
            return;
        }
        System.out.println("校验成功");

    }

    public static boolean isPhone(String str)throws PatternSyntaxException {
        boolean result = false;
        try {
            result = isChinaPhone(str) || isHKPhone(str);
        } catch (PatternSyntaxException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static final String CHINA_PHONE_REGEX = "^((13[0-9])|(14[0-9])|(15[0-9])|(16[0-9])|(17[0-9])|(18[0-9])|(19[0-9]))\\d{8}$";
    public static final String CHINA_PHONE_REGEX2 = "^((13[0-9])|(15[0-9])|(18[0-9])|(17[0-9])|(147))\\d{8}$";

    public static final String HK_PHONE_REGEX = "^(5|6|8|9)\\d{7}$";

    public static boolean isChinaPhone(String str) throws PatternSyntaxException {
        Pattern p = Pattern.compile(CHINA_PHONE_REGEX);
        Matcher m = p.matcher(str);
        return m.matches();
    }

    public static boolean isHKPhone(String str)throws PatternSyntaxException {
        Pattern p = Pattern.compile(HK_PHONE_REGEX);
        Matcher m = p.matcher(str);
        return m.matches();
    }

    /**
     * 测试自定义迭代器
     */
    public static void testIterator() {
        Link<String> link = new LinkedList<>();
        link.add("a");
        link.add("b");
        link.add("c");
        link.add("d");
        link.add("e");

        Iterator it = link.iterator();
        while (it.hasNext()) {
            String next = (String) it.next();
            System.out.println(next);
        }
    }

    /**
     * 测试自定义LinkedList
     */
    public static void testLinkedList() {
        LinkedList<String> list = new LinkedList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("e");
        System.out.println(list.size());
        while (list.hasNext()) {
            String data = list.next();
            System.out.println(data);
        }
    }

    /**
     * 测试自定义ArrayList
     */
    public static void testArrayList(){
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 100; i ++) {
            list.add(i);
        }
        System.out.println("list长度=" + list.size());
        for (int i = 0; i < list.size(); i++) {
            Integer value = list.get(i);
            System.out.println(value);
        }
    }
}
