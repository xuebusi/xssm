package com.xuebusi.xssm.test.iterator.impl;

import com.xuebusi.xssm.test.iterator.Iterator;
import com.xuebusi.xssm.test.list.Collection;

/**
 * 迭代器实现
 */
public class CollectionIterator<T> implements Iterator<T> {

    /**
     * 集合容器
     */
    private Collection<T> collection;

    /**
     * 基于集合容器构造一个迭代器
     * @param collection
     */
    public CollectionIterator(Collection<T> collection) {
        this.collection = collection;
    }

    /**
     * 是否有下一个元素
     * @return
     */
    @Override
    public boolean hasNext() {
        return collection.hasNext();
    }

    /**
     * 获取下一个元素
     * @return
     */
    @Override
    public T next() {
        return collection.next();
    }
}
