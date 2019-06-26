package com.yueyang.service;

/**
 * @author pengyueyang
 * @create 2019/6/25 11:12
 * 自增订单id基础服务
 */
public interface OrderIdService {
    /**
     * 创建自增订单id
     */
    Long getOrderId(Integer type);
}
