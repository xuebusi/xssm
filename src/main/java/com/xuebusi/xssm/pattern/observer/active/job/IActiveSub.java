package com.xuebusi.xssm.pattern.observer.active.job;

/**
 * 被观察者接口
 */
public interface IActiveSub<T> {

    /**
     * 通知所有观察者
     */
    void notifyToAll(T data);
}
