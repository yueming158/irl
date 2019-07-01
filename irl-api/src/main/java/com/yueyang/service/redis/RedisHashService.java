package com.yueyang.service.redis;

import java.util.Map;
import java.util.Set;

/**
 * @author pengyueyang
 * @create 2019/6/27 10:48 Redis Hash相关操作服务
 */
public interface RedisHashService {

    /**
     * 为哈希表中的字段赋值。
     * 如果哈希表不存在，一个新的哈希表被创建并进行 HSET 操作。
     * 如果字段已经存在于哈希表中，旧值将被覆盖。
     */
    void set(String key, String field, String value);

    /**
     * 返回哈希表中指定字段的值
     */
    String get(String key, String field);

    /**
     * 返回哈希表中，所有的字段和值
     */
    Map<String, String> getAll(String key);

    /**
     * 删除哈希表 key 中的一个或多个指定字段 不存在的字段将被忽略
     */
    Long delete(String key, String... filed);

    /**
     * 获取哈希表中字段的数量
     */
    Long length(String key);

    /**
     * 判断哈希表含有给定字段，key不存在也返回false
     */
    Boolean exists(String key, String field);

    /**
     * 哈希表中所有字段的列表
     */
    Set<String> keys(String key);

    /**
     * 哈希表中不存在的的字段赋值
     * @param key
     * @param field
     * @param value
     * @return
     */
    Boolean setIfAbsent(String key, String field, String value);
}
