package com.xuebusi.xssm.pattern.observer.high;

import java.util.Observable;
import java.util.Observer;

/**
 * 今天天气预报（实现java内置监听者接口）
 */
public class CurrentConditions implements Observer {
    private float mTemperatrue;
    private float mPressure;
    private float mHumidity;

    @Override
    public void update(Observable o, Object data) {
        if (data instanceof WeathData.Data) {
            WeathData.Data wd = (WeathData.Data) data;
            this.mTemperatrue = wd.mTemperatrue;
            this.mPressure = wd.mPressure;
            this.mHumidity = wd.mHumidity;
            this.desplay();
        }
    }

    public void desplay() {
        System.out.println("CurrentConditions: mTemperatrue=" + this.mTemperatrue);
        System.out.println("CurrentConditions: mPressure=" + this.mPressure);
        System.out.println("CurrentConditions: mHumidity" + this.mHumidity);
    }
}
