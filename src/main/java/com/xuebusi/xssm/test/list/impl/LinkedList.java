package com.xuebusi.xssm.test.list.impl;

import com.xuebusi.xssm.test.iterator.Iterator;
import com.xuebusi.xssm.test.iterator.impl.CollectionIterator;
import com.xuebusi.xssm.test.list.Link;

/**
 * 基于链表实现一个List集合
 * @param <T>
 */
public class LinkedList<T> implements Link<T> {

    /**
     * 头结点
     */
    private Node startNode = null;

    /**
     * 尾结点
     */
    private Node endNode = null;

    /**
     * 下一个结点
     */
    private Node nextNode = null;

    /**
     * 节点数
     */
    private int size = 0;

    /**
     * 获取一个迭代器
     * @return
     */
    @Override
    public Iterator<T> iterator() {
        return new CollectionIterator<>(this);
    }

    /**
     * 追加元素
     * @param t
     */
    public void add(T t) {
        Node newNode = new Node(t, null);
        if (startNode == null) {
            startNode = newNode;
            endNode = newNode;
        }
        endNode.setNext(newNode);
        endNode = newNode;
        size++;
    }

    /**
     * 是否有下一个元素
     * @return
     */
    public boolean hasNext() {
        if (nextNode == null) {
            nextNode = startNode;
            if (nextNode != null) {
                return true;
            }
            return false;
        }
        nextNode = nextNode.getNext();
        if (nextNode != null) {
            return true;
        }
        return false;
    }

    /**
     * 获取下一个元素
     * @return
     */
    public T next() {
        return (T) nextNode.getData();
    }

    /**
     * 链表长度
     * @return
     */
    public int size() {
        return size;
    }
}
