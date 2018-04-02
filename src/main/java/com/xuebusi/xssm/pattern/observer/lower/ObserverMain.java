package com.xuebusi.xssm.pattern.observer.lower;

public class ObserverMain {
    public static void main(String[] args) {
        Secretary secretary = new Secretary();
        StockObserver observer1 = new StockObserver("张三", secretary);
        StockObserver observer2 = new StockObserver("李四", secretary);
        secretary.add(observer1);
        secretary.add(observer2);
        secretary.setAction("满血复活");
        secretary.notifyToObserver();
    }
}
