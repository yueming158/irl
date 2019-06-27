package com.yueyang.service.redis;

import java.util.Map;

/**
 * @author pengyueyang
 * @create 2019/6/27 10:48 Redis Hash相关操作服务
 */
public interface RedisHashService {

    /**
     * 为哈希表中的字段赋值。
     * 如果哈希表不存在，一个新的哈希表被创建并进行 HSET 操作。
     * 如果字段已经存在于哈希表中，旧值将被覆盖。
     * @param key
     * @param field
     * @param value
     */
    void set(String key, String field, String value);

    /**
     * 返回哈希表中指定字段的值
     * @param key
     * @param field
     * @return
     */
    String get(String key, String field);

    /**
     * 返回哈希表中，所有的字段和值
     * @param key
     * @return
     */
    Map<String,String> getAll(String key);

    /**
     * 删除哈希表 key 中的一个或多个指定字段 不存在的字段将被忽略
     * @param key
     * @param filed
     * @return
     */
    Long delete(String key, String ... filed);

    /**
     * 获取哈希表中字段的数量
     * @param key
     * @return
     */
    Long length(String key);


}
