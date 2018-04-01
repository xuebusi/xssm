package com.xuebusi.xssm.pattern.observer.middle;

public interface Subject {
    String getAction();
    void add(Observer observer);
    void remove(Observer observer);
    void notifyToObs();
}
