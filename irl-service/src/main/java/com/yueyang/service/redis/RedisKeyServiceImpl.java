package com.yueyang.service.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author pengyueyang
 * @create 2019/6/19 14:31
 */
@Service
public class RedisKeyServiceImpl implements RedisKeyService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    @Override
    public Boolean del(String key) {
        return stringRedisTemplate.delete(key);
    }

    @Override
    public String type(String key) {
        return stringRedisTemplate.type(key).name();
    }

    @Override
    public Boolean exists(String key) {
        return stringRedisTemplate.hasKey(key);
    }

    @Override
    public Boolean expireAt(String key, Date date) {
        return stringRedisTemplate.expireAt(key, date);
    }

    @Override
    public Boolean expire(String key, long timeOut, TimeUnit timeUnit) {
        return stringRedisTemplate.expire(key, timeOut, timeUnit);
    }

    @Override
    public Long ttl(String key, TimeUnit timeUnit) {
        return stringRedisTemplate.getExpire(key, timeUnit);
    }

    @Override
    public Boolean move(String key, int dbIndex) {
        return stringRedisTemplate.move(key, dbIndex);
    }

    @Override
    public Boolean rename(String key, String newKey) {
        return stringRedisTemplate.renameIfAbsent(key,newKey);
    }
}
