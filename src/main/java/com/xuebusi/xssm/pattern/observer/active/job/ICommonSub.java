package com.xuebusi.xssm.pattern.observer.active.job;

/**
 * 被监听者接口
 */
public interface ICommonSub<T> {

    /**
     * 通知监听者
     */
    void notifyObs(T data);
}
