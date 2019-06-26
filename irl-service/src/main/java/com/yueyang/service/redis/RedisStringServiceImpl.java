package com.yueyang.service.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

/**
 * @author pengyueyang
 * @create 2019/6/19 15:41
 */
@Service
public class RedisStringServiceImpl implements RedisStringService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private ValueOperations<String, String> stringValueOperations;

    @PostConstruct
    public void init() {
        stringValueOperations = stringRedisTemplate.opsForValue();
    }

    @Override
    public Boolean setIfAbsent(String key, String value) {
        return stringValueOperations.setIfAbsent(key, value);
    }

    @Override
    public Boolean setIfAbsent(String key, String value, Long timeOut, TimeUnit timeUnit) {
        return stringValueOperations.setIfAbsent(key, value, timeOut, timeUnit);
    }

    @Override
    public void mSet(Map<String, String> map) {
        stringValueOperations.multiSet(map);
    }

    @Override
    public void set(String key, String value) {
        stringValueOperations.set(key, value);
    }

    @Override
    public void setWithExpire(String key, String value, long timeOut, TimeUnit timeUnit) {
        stringValueOperations.set(key, value, timeOut, timeUnit);
    }

    @Override
    public void setWithExpire(String key, String value, Duration timeOut) {
        stringValueOperations.set(key,value, timeOut);
    }

    @Override
    public String get(String key) {
        return stringValueOperations.get(key);
    }

    @Override
    public List<String> mGet(Set<String> key) {
        return stringValueOperations.multiGet(key);
    }

    @Override
    public String getSet(String key, String value) {
        return stringValueOperations.getAndSet(key, value);
    }

    @Override
    public Long decrement(String key) {
        return stringValueOperations.decrement(key);
    }

    @Override
    public Long decrementBy(String key, Long length) {
        return stringValueOperations.decrement(key, length);
    }

    @Override
    public Long increment(String key) {
        return stringValueOperations.increment(key);
    }

    @Override
    public Long incrementBy(String key, Long length) {
        return stringValueOperations.increment(key, length);
    }
}
