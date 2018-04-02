package com.xuebusi.xssm.test;

import com.xuebusi.xssm.test.iterator.Iterator;
import com.xuebusi.xssm.test.list.Link;
import com.xuebusi.xssm.test.list.impl.ArrayList;
import com.xuebusi.xssm.test.list.List;
import com.xuebusi.xssm.test.list.impl.LinkedList;

/**
 * 测试类
 */
public class AppTest {

    public static void main(String[] args) {
        testIterator();
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
