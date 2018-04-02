package com.xuebusi.xssm.test.list;

import com.xuebusi.xssm.test.iterator.Iterator;

/**
 * 集合顶级接口
 * @param <T>
 */
public interface Collection<T> {

    /**
     * 获取一个迭代器
     * @return
     */
    Iterator<T> iterator();

    /**
     * 是否有下一个元素
     * @return
     */
    boolean hasNext();

    /**
     * 获取下一个元素
     * @return
     */
    T next();

    /**
     * 添加元素
     * @param t
     */
    void add(T t);

    /**
     * 集合大小
     * @return
     */
    int size();

}
