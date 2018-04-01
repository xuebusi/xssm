package com.xuebusi.xssm.pattern.observer.active.job.impl;

import com.alibaba.fastjson.JSON;
import com.xuebusi.xssm.pattern.observer.active.job.IActiveService;

import java.util.Observable;
import java.util.Observer;

/**
 * MultiActive监听者
 */
public class MultiActiveObs<T> implements Observer, IActiveService {

    /**
     * 记录被监听者的数据
     */
    private T data;

    /**
     * 接收被监听者的数据，更新自己
     * @param obs 被监听者
     * @param data 被监听者的数据
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
        System.out.println("MultiActiveObs:修改订单,被监听者状态:" + JSON.toJSONString(this.data));
    }

    /**
     * 立即发货
     */
    @Override
    public void sendGoods() {
        System.out.println("MultiActiveObs:立即发货,被监听者状态:" + JSON.toJSONString(this.data));
    }

    /**
     * 申请退款
     */
    @Override
    public void refund() {
        System.out.println("MultiActiveObs:申请退款,被监听者状态:" + JSON.toJSONString(this.data));
    }

    /**
     * 测试私有方法
     */
    private void sayHello() {
        System.out.println("MultiActiveObs:私有方法:sayHello()");
    }
}
