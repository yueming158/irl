package com.yueyang.service.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.util.Set;

import javax.annotation.PostConstruct;

/**
 * @author pengyueyang
 * @create 2019/7/4 17:18
 */
@Service
public class RedisSortSetServiceImpl implements RedisSortSetService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private ZSetOperations<String, String> zSetOperations;

    @PostConstruct
    public void init() {
        zSetOperations = stringRedisTemplate.opsForZSet();
    }

    @Override
    public Boolean add(String key, Double score, String value) {
        return zSetOperations.add(key, value, score);
    }

    @Override
    public Set<String> range(String key, Long start, Long end) {
        return zSetOperations.range(key, start, end);
    }

    @Override
    public Set<String> rangeWithScores(String key, Double start, Double end) {
        return zSetOperations.rangeByScore(key, start, end);
    }

    @Override
    public Set<String> revRange(String key, Long start, Long end) {
        return zSetOperations.reverseRange(key, start, end);
    }

    @Override
    public Set<String> revRangeWithScores(String key, Double start, Double end) {
        return zSetOperations.reverseRangeByScore(key, start, end);
    }

    @Override
    public long count(String key, double minScore, double maxScore) {
        return zSetOperations.count(key, minScore, maxScore);
    }

    @Override
    public Long delete(String key, String value) {
        return zSetOperations.remove(key, value);
    }

    @Override
    public long count(String key) {
        return zSetOperations.size(key);
    }

    @Override
    public Double score(String key, String member) {
        return zSetOperations.score(key, member);
    }

    @Override
    public Long rank(String key, String member) {
        return zSetOperations.rank(key, member);
    }

    @Override
    public Long revRank(String key, String member) {
        return zSetOperations.reverseRank(key,member);
    }
}
