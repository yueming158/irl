package com.yueyang.service.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;

/**
 * @author pengyueyang
 * @create 2019/6/27 10:55
 */
public class RedisHashServiceImpl implements RedisHashService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private HashOperations<String, String, String> hashOperations;

    @PostConstruct
    public void init() {
        hashOperations = stringRedisTemplate.opsForHash();
    }

    @Override
    public void set(String key, String field, String value) {
        hashOperations.put(key, field, value);
    }

    @Override
    public String get(String key, String field) {
        return hashOperations.get(key, field);
    }

    @Override
    public Map<String, String> getAll(String key) {
        return hashOperations.entries(key);
    }

    @Override
    public Long delete(String key, String... filed) {
        return hashOperations.delete(key, filed);
    }

    @Override
    public Long length(String key) {
        return hashOperations.size(key);
    }

    @Override
    public Boolean exists(String key, String field) {
        return hashOperations.hasKey(key, field);
    }

    @Override
    public Set<String> keys(String key) {
        return hashOperations.keys(key);
    }

    @Override
    public Boolean setIfAbsent(String key, String field, String value) {
        return hashOperations.putIfAbsent(key, field, value);
    }
}
