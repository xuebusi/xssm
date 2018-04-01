package com.xuebusi.xssm.pattern.observer.lower;

import java.util.ArrayList;
import java.util.List;

/**
 * 被观察者
 */
public class Secretary {
    /**
     * 用于注册观察者
     */
    private List<StockObserver> observerList = new ArrayList<>();
    /**
     * 记录被观察者状态
     */
    private String action;

    /**
     * 添加一个观察者
     * @param stockObserver
     */
    public void add(StockObserver stockObserver) {
        this.observerList.add(stockObserver);
    }

    /**
     * 通知所有观察者
     */
    public void notifyToObserver() {
        for (StockObserver observer : observerList) {
            observer.update();
        }
    }

    /**
     * 获取被观察者状态
     * @return
     */
    public String getAction() {
        return action;
    }

    /**
     * 设置被观察者状态
     * @param action
     */
    public void setAction(String action) {
        this.action = action;
    }
}
