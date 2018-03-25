package com.xuebusi.xssm.test.list;

/**
 * 链表接口
 * @param <T>
 */
public interface Link<T> extends Collection<T> {

    boolean hasNext();

    T next();

}
