package com.xuebusi.xssm.pattern.observer.middle.impl;

import com.xuebusi.xssm.pattern.observer.middle.Observer;
import com.xuebusi.xssm.pattern.observer.middle.Subject;

public class NbaObserver implements Observer {

    private String name;
    private Subject sub;

    public NbaObserver(String name, Subject sub) {
        this.name = name;
        this.sub = sub;
    }

    @Override
    public void update() {
        System.out.println(this.name + ":update====被观察者状态发生变化:" + sub.getAction());
    }


}
