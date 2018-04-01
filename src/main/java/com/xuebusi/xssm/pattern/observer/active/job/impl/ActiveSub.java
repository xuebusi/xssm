package com.xuebusi.xssm.pattern.observer.active.job.impl;

import com.xuebusi.xssm.pattern.observer.active.job.IActiveSub;

import java.util.Observable;

/**
 * 活动被观察者
 */
public class ActiveSub<T> extends Observable implements IActiveSub<T> {

    /**
     * 通知所有观察者
     * @param data
     */
    @Override
    public void notifyToAll(T data) {
        this.setChanged();
        this.notifyObservers(data);
    }

}
