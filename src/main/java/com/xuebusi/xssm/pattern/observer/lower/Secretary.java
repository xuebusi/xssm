package com.xuebusi.xssm.pattern.observer.lower;

import java.util.ArrayList;
import java.util.List;

/**
 * 被监听者
 */
public class Secretary {
    /**
     * 用于注册监听者
     */
    private List<StockObserver> observerList = new ArrayList<>();
    /**
     * 记录被监听者状态
     */
    private String action;

    /**
     * 添加一个监听者
     * @param stockObserver
     */
    public void add(StockObserver stockObserver) {
        this.observerList.add(stockObserver);
    }

    /**
     * 通知所有监听者
     */
    public void notifyToObserver() {
        for (StockObserver observer : observerList) {
            observer.update();
        }
    }

    /**
     * 获取被监听者状态
     * @return
     */
    public String getAction() {
        return action;
    }

    /**
     * 设置被监听者状态
     * @param action
     */
    public void setAction(String action) {
        this.action = action;
    }
}
