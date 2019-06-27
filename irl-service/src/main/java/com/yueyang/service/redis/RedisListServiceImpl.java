package com.yueyang.service.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

import javax.annotation.PostConstruct;

/**
 * @author pengyueyang
 * @create 2019/6/27 9:59
 */
@Service
public class RedisListServiceImpl implements RedisListService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private ListOperations<String, String> listOperations;

    @PostConstruct
    public void init() {
        listOperations = stringRedisTemplate.opsForList();
    }

    @Override
    public Long leftPush(String key, String value) {
        return listOperations.leftPush(key, value);
    }

    @Override
    public Long rightPush(String key, String value) {
        return listOperations.rightPush(key, value);
    }

    @Override
    public String leftPop(String key) {
        return listOperations.leftPop(key);
    }

    @Override
    public String rightPop(String key) {
        return listOperations.rightPop(key);
    }

    @Override
    public List<String> range(String key, Long start, Long end) {
        return listOperations.range(key, start, end);
    }

    @Override
    public Long length(String key) {
        return listOperations.size(key);
    }

    @Override
    public String get(String key, Long index) {
        return listOperations.index(key, index);
    }

    @Override
    public Long deleteByValueAndCount(String key, Long count, String value) {
        return listOperations.remove(key, count, value);
    }

    @Override
    public void trim(String key, Long start, Long end) {
        listOperations.trim(key, start, end);
    }

    @Override
    public Long insertBefore(String key, String existValue, String value) {
        return listOperations.leftPush(key, existValue, value);
    }

    @Override
    public Long insertAfter(String key, String existValue, String value) {
        return listOperations.rightPush(key, existValue, value);
    }

    @Override
    public String rightPopLeftPush(String sourceKey, String targetKey) {
        return listOperations.rightPopAndLeftPush(sourceKey, targetKey);
    }
}
