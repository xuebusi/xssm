package com.xuebusi.xssm.pattern.observer.high;

import java.util.Observable;

/**
 * 气象数据（继承java内置被观察者）
 */
public class WeathData extends Observable {
    private float mTemperatrue;
    private float mPressure;
    private float mHumidity;

    public void dataChange() {
        // 必须先调用 setChanged()方法，否则下面的通知不生效
        this.setChanged();
        // 通知所有观察者
        this.notifyObservers(new Data(this.getmTemperatrue(), this.getmPressure(), this.getmHumidity()));
    }

    /**
     * 模拟被观察者数据状态变化
     * @param mTemperatrue
     * @param mPressure
     * @param mHumidity
     */
    public void setData(float mTemperatrue, float mPressure, float mHumidity) {
        this.mTemperatrue = mTemperatrue;
        this.mPressure = mPressure;
        this.mHumidity = mHumidity;
        this.dataChange();
    }

    public float getmTemperatrue() {
        return mTemperatrue;
    }

    public void setmTemperatrue(float mTemperatrue) {
        this.mTemperatrue = mTemperatrue;
    }

    public float getmPressure() {
        return mPressure;
    }

    public void setmPressure(float mPressure) {
        this.mPressure = mPressure;
    }

    public float getmHumidity() {
        return mHumidity;
    }

    public void setmHumidity(float mHumidity) {
        this.mHumidity = mHumidity;
    }

    public class Data{
        public float mTemperatrue;
        public float mPressure;
        public float mHumidity;

        public Data(float mTemperatrue, float mPressure, float mHumidity) {
            this.mTemperatrue = mTemperatrue;
            this.mPressure = mPressure;
            this.mHumidity = mHumidity;
        }
    }

}
