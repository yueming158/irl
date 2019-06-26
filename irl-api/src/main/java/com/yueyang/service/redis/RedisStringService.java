package com.yueyang.service.redis;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author pengyueyang
 * @create 2019/6/19 15:05 Redis String相关操作服务
 */
public interface RedisStringService {

    /**
     * 在key不存在是设置key的值
     */
    Boolean setIfAbsent(String key, String value);

    Boolean setIfAbsent(String key, String value, Long timeOut, TimeUnit timeUnit);

    /**
     * 同时设置一个或多个 key-value 对
     */
    void mSet(Map<String, String> map);

    /**
     * 设置指定key的值
     */
    void set(String key, String value);

    /**
     * 设置指定key的值并设置超时时间
     */
    void setWithExpire(String key, String value, long timeOut, TimeUnit timeUnit);
    /**
     * 设置指定key的值并设置超时时间段
     */
    void setWithExpire(String key, String value, Duration timeOut);
    /**
     * 获取指定 key 的值
     */
    String get(String key);

    /**
     * 获取所有(一个或多个)给定 key 的值
     */
    List<String> mGet(Set<String> key);

    /**
     * 将给定 key 的值设为 value ，并返回 key 的旧值(old value)
     */
    String getSet(String key, String value);

    /**
     * key 中储存的数字值减1
     */
    Long decrement(String key);

    /**
     * key 中储存的数字值减去给定的值
     */
    Long decrementBy(String key, Long length);

    /**
     * key 中储存的数字值加1
     */
    Long increment(String key);

    /**
     * key 中储存的数字值加上给定的值
     */
    Long incrementBy(String key, Long length);
}
