package com.xuebusi.xssm.test.list.impl;

import com.xuebusi.xssm.test.list.List;

/**
 * 基于数组结构实现一个List集合
 * @param <T>
 */
public class ArrayList<T> implements List<T> {

    /**
     * 默认数组长度
     */
    int defaultSize = 1 << 4;

    /**
     * 初始化一个数组
     */
    Object[] arr = new Object[defaultSize];

    /**
     * 集合大小
     */
    int size = 0;

    /**
     * 添加元素
     * @param t
     */
    @Override
    public void add(T t) {
        if (size >= arr.length) {
            Object[] newArr = new Object[arr.length + defaultSize];
            System.arraycopy(arr, 0, newArr, 0, arr.length);
            arr = newArr;
        }
        arr[size] = t;
        size++;
    }

    /**
     * 根据索引获取元素
     * @param index
     * @return
     */
    @Override
    public T get(int index) {
        return (T) arr[index];
    }

    /**
     * 集合大小
     * @return
     */
    @Override
    public int size() {
        return size;
    }
}
