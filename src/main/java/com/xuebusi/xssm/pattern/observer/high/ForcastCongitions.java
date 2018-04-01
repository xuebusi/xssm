package com.xuebusi.xssm.pattern.observer.high;

import java.util.Observable;
import java.util.Observer;

/**
 * 昨天天气预报（实现java内置观察者接口）
 */
public class ForcastCongitions implements Observer {
    private float mTemperatrue;
    private float mPressure;
    private float mHumidity;

    @Override
    public void update(Observable observable, Object data) {
        if (observable instanceof WeathData) {
            WeathData weathData = (WeathData) observable;
            System.out.println("============weathData.getmHumidity():" + weathData.getmHumidity());
        }
        if (data instanceof WeathData.Data) {
            WeathData.Data wd = (WeathData.Data) data;
            this.mTemperatrue = wd.mTemperatrue;
            this.mPressure = wd.mPressure;
            this.mHumidity = wd.mHumidity;
            this.desplay();
        }
    }

    public void desplay() {
        System.out.println("ForcastCongitions: mTemperatrue=" + this.mTemperatrue);
        System.out.println("ForcastCongitions: mPressure=" + this.mPressure);
        System.out.println("ForcastCongitions: mHumidity" + this.mHumidity);
    }
}
