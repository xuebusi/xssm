package com.xuebusi.xssm.pattern.observer.active.job.impl;

import com.xuebusi.xssm.pattern.observer.active.job.ICommonSub;

import java.util.Observable;

/**
 * 被监听者实现
 */
public class CommonSub<T> extends Observable implements ICommonSub<T> {

    /**
     * 通知监听者
     * @param data
     */
    @Override
    public void notifyObs(T data) {
        this.setChanged();
        this.notifyObservers(data);
    }

}
