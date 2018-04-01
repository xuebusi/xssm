package com.xuebusi.xssm.pattern.observer.lower;

/**
 * 观察者
 */
public class StockObserver {
    /**
     * 观察者名称
     */
    private String name;
    /**
     * 被观察者
     */
    private Secretary sub;

    public StockObserver(String name, Secretary sub) {
        this.name = name;
        this.sub = sub;
    }

    /**
     * 接收通知
     */
    public void update() {
        System.out.println("通知观察者[" + this.name + "], 被观察者状态发生变化:" + sub.getAction());
    }
}
