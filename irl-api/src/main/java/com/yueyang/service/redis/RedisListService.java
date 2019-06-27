package com.yueyang.service.redis;

import java.util.List;

/**
 * @author pengyueyang
 * @create 2019/6/19 16:25
 * Redis List相关操作服务
 */
public interface RedisListService {
    /**
     * 将一个值插入列表头部
     */
    Long leftPush(String key, String value);

    /**
     * 将一个值插入列表尾部
     */
    Long rightPush(String key, String value);

    /**
     * 移除并返回列表的第一个元素
     * @param key
     * @return
     */
    String leftPop(String key);

    /**
     * 移除并返回列表的最后一个元素
     * @param key
     * @return
     */
    String rightPop(String key);

    /**
     * 获取指定区间元素列表
     * @param key
     * @param start
     * @param end
     * @return
     */
    List<String> range(String key, Long start, Long end);

    /**
     * 返回列表的长度
     * @param key
     * @return
     */
    Long length(String key);

    /**
     * 通过索引获取列表中的元素
     * @param key
     * @param index
     * @return
     */
    String get(String key, Long index);

    /**
     * 更加参数 COUNT 的值，移除列表中与参数 VALUE 相等的元素
     * @param key
     * @param count
     * @return
     */
    Long deleteByValueAndCount(String key,Long count,String value);

    /**
     * 裁剪
     * @param key
     * @param start
     * @param end
     * @return
     */
    void trim(String key,Long start,Long end);

    /**
     * 在列表的给定值元素前插入元素
     * @param key
     * @param existValue
     * @param value
     * @return
     */
    Long insertBefore(String key,String existValue,String value);

    /**
     * 在列表的给定值元素后插入元素
     * @param key
     * @param existValue
     * @param value
     * @return
     */
    Long insertAfter(String key,String existValue,String value);

    /**
     * 移除列表的最后一个元素，并将该元素添加到另一个列表并返回
     * @param sourceKey
     * @param targetKey
     * @return
     */
    String rightPopLeftPush(String sourceKey,String targetKey);
}
