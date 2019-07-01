package com.yueyang.service.redis;

import java.util.Set;

/**
 * @author pengyueyang
 * @create 2019/7/1 14:29 Redis Sort Set相关操作服务
 */
public interface RedisSortSetService {

    /**
     * 将元素及其分数值加入到有序集当中
     * 如果某个成员已经是有序集的成员，那么更新这个成员的分数值，并通过重新插入这个成员元素，来保证该成员在正确的位置上。
     */
    void add(String key, Double score, String value);

    /**
     * 返回有序集中，指定区间内的成员，从小到大
     */
    Set<String> range(String key, Long start, Long end);

    /**
     * 返回有序集中，指定分数区间内的成员，从小到大
     */
    Set<String> rangeWithScores(String key, Double start, Double end);

    /**
     * 返回有序集中，指定区间内的成员，从大到小
     */
    Set<String> revRange(String key, Long start, Long end);

    /**
     * 返回有序集中，指定分数区间内的成员，从大到小
     */
    Set<String> revRangeWithScores(String key, Double start, Double end);

    /**
     * 计算有序集合中指定分数区间的成员数量
     */
    long count(String key, double minScore, double maxScore);

    /**
     * 移除有序集中的一个或多个成员，不存在的成员将被忽略。
     */
    void delete(String key, String value);

    /**
     * 计算集合中元素的数量
     */
    long count(String key);

    /**
     * 返回有序集中，成员的分数值。
     */
    Set<String> score(String key);

    /**
     * 返回有序集中指定成员的排名 有小到大
     * @param key
     * @param member
     * @return
     */
    Long rank(String key, String member);
}
