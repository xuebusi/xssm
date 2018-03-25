package com.xuebusi.xssm.test.iterator;

/**
 * 迭代器接口
 */
public interface Iterator<T> {

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
}
