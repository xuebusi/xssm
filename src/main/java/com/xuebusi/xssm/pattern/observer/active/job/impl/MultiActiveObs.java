package com.xuebusi.xssm.pattern.observer.active.job.impl;

import com.alibaba.fastjson.JSON;
import com.xuebusi.xssm.pattern.observer.active.job.IActiveObs;
import com.xuebusi.xssm.pattern.observer.active.job.IActiveService;

import java.util.Observable;

/**
 * 多人拼活动观察者
 */
public class MultiActiveObs<T> implements IActiveObs<T>, IActiveService {

    /**
     * 记录被观察者的数据
     */
    private T data;

    /**
     * 接收被观察者的数据，更新自己
     * @param o
     * @param data
     */
    @Override
    public void update(Observable o, Object data) {
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
