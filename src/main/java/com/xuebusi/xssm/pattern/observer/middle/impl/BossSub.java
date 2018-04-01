package com.xuebusi.xssm.pattern.observer.middle.impl;

import com.xuebusi.xssm.pattern.observer.middle.Observer;
import com.xuebusi.xssm.pattern.observer.middle.Subject;

import java.util.LinkedList;
import java.util.List;

public class BossSub implements Subject {
    private List<Observer> observerList = new LinkedList<>();
    private String action;

    @Override
    public void add(Observer observer) {
        observerList.add(observer);
    }

    @Override
    public void remove(Observer observer) {
        observerList.remove(observer);
    }

    @Override
    public void notifyToObs() {
        for (Observer observer : observerList) {
            observer.update();
        }
    }

    @Override
    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
