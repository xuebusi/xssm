package com.xuebusi.xssm.pattern.observer.lower;

/**
 * 监听者
 */
public class StockObserver {
    /**
     * 监听者名称
     */
    private String name;
    /**
     * 被监听者
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
        System.out.println("通知监听者[" + this.name + "], 被监听者状态发生变化:" + sub.getAction());
    }
}
