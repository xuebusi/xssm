package com.xuebusi.xssm.pattern.observer.active.job.impl;

import com.alibaba.fastjson.JSON;
import com.xuebusi.xssm.pattern.observer.active.job.IActiveService;

import java.util.Observable;
import java.util.Observer;

/**
 * 多人拼活动观察者
 */
public class MultiActiveObs<T> implements Observer, IActiveService {

    /**
     * 记录被观察者的数据
     */
    private T data;

    /**
     * 接收被观察者的数据，更新自己
     * @param obs 被观察者
     * @param data 被观察者的数据
     */
    @Override
    public void update(Observable obs, Object data) {
        this.data = (T) data;
        this.sendGoods();
        this.refund();
        this.modifyOrderStatus();
        this.sayHello();
    }

    /**
     * 修改订单
     */
    @Override
    public void modifyOrderStatus() {
        System.out.println("多人拼活动:修改订单,被观察者状态:" + JSON.toJSONString(this.data));
    }

    /**
     * 立即发货
     */
    @Override
    public void sendGoods() {
        System.out.println("多人拼活动:立即发货,被观察者状态:" + JSON.toJSONString(this.data));
    }

    /**
     * 申请退款
     */
    @Override
    public void refund() {
        System.out.println("多人拼活动:申请退款,被观察者状态:" + JSON.toJSONString(this.data));
    }

    /**
     * 测试私有方法
     */
    private void sayHello() {
        System.out.println("多人拼活动:私有方法:sayHello()");
    }
}
