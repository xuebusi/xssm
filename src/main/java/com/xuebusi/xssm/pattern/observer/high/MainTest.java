package com.xuebusi.xssm.pattern.observer.high;

public class MainTest {
    public static void main(String[] args) {
        WeathData weathData = new WeathData();
        CurrentConditions currentConditions = new CurrentConditions();
        ForcastCongitions forcastCongitions = new ForcastCongitions();

        weathData.addObserver(currentConditions);
        weathData.addObserver(forcastCongitions);
//        weathData.deleteObserver(currentConditions);

        weathData.setData(20, 30, 40);
    }
}
