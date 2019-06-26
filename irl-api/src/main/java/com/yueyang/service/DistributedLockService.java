package com.yueyang.service;

import java.util.concurrent.TimeUnit;

/**
 * @author pengyueyang
 * @create 2019/6/26 15:46 分布式锁服务
 */
public interface DistributedLockService {

    /**
     * 加锁
     */
    boolean lock(String key, String requestId);

    /**
     * @param key
     * @param requestId
     * @param timeOut
     * @param timeUnit
     */
    boolean lock(String key, String requestId, Long timeOut, TimeUnit timeUnit);

    /**
     * 解锁
     * @param key
     * @param requestId
     */
    boolean unLock(String key, String requestId);
}
