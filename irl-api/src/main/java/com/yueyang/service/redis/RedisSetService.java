package com.yueyang.service.redis;

import java.util.Set;

/**
 * @author pengyueyang
 * @create 2019/7/1 14:10
 * Redis Set相关操作服务
 */
public interface RedisSetService {

    /**
     * 命令将一个或多个成员元素加入到集合中
     */
    Long add(String key, String... values);

    /**
     * 移除集合中的一个或多个成员元素，不存在的成员元素会被忽略
     */
    Long delete(String key, String... values);

    /**
     * 返回集合中的所有的成员
     */
    Set<String> members(String key);

    /**
     * 返回集合中的一个随机元素,count为指定的个数
     */
    Set<String> randMembers(String key, Long count);

    /**
     * 判断成员元素是否是集合的成员，如果成员元素不是集合的成员，或 key 不存在，返回 false
     */
    Boolean isMembers(String key, String value);

    /**
     * 返回集合中元素的数量，集合 key 不存在时，返回 0
     * @param key
     * @return
     */
    Long size(String key);

    /**
     * 差集
     * @param key
     * @param otherKey
     * @return
     */
    Set<String> diff(String key, String... otherKey);

    /**
     * 交集
     * @param key
     * @param otherKey
     * @return
     */
    Set<String> inter(String key, String... otherKey);

    /**
     * 并集
     * @param key
     * @param otherKey
     * @return
     */
    Set<String> union(String key, String... otherKey);

    /**
     * 移除并返回集合中的一个随机元素
     * @param key
     * @return
     */
    String pop(String key);

}
