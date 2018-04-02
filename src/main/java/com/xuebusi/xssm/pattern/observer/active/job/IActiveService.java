package com.xuebusi.xssm.pattern.observer.active.job;

/**
 * 活动业务接口
 */
public interface IActiveService {

    /**
     * 修改订单状态
     */
    void modifyOrderStatus();

    /**
     * 通知发货
     */
    void sendGoods();

    /**
     * 申请退款
     */
    void refund();
}
