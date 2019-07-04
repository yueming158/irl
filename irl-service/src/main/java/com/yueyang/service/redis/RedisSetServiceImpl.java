package com.yueyang.service.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;

/**
 * @author pengyueyang
 * @create 2019/7/4 17:17
 */
@Service
public class RedisSetServiceImpl implements RedisSetService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private SetOperations<String, String> setOperations;

    @PostConstruct
    public void init() {
        setOperations = stringRedisTemplate.opsForSet();
    }

    @Override
    public Long add(String key, String... values) {
        return setOperations.add(key, values);
    }

    @Override
    public Long delete(String key, String... values) {
        return setOperations.remove(key, values);
    }

    @Override
    public Set<String> members(String key) {
        return setOperations.members(key);
    }

    @Override
    public Set<String> randMembers(String key, Long count) {
        return setOperations.distinctRandomMembers(key, count);
    }

    @Override
    public Boolean isMembers(String key, String value) {
        return setOperations.isMember(key, value);
    }

    @Override
    public Long size(String key) {
        return setOperations.size(key);
    }

    @Override
    public Set<String> diff(String key, String... otherKey) {
        return setOperations.difference(key, getOtherKeyCollection(otherKey));
    }

    private Set<String> getOtherKeyCollection(String[] otherKey) {
        if (null != otherKey && otherKey.length > 0) {
            Set<String> set = new HashSet<>(otherKey.length);
            for (String key : otherKey) {
                set.add(key);
            }
            return set;
        }
        return Collections.emptySet();
    }

    @Override
    public Set<String> inter(String key, String... otherKey) {
        return setOperations.intersect(key, getOtherKeyCollection(otherKey));
    }

    @Override
    public Set<String> union(String key, String... otherKey) {
        return setOperations.union(key, getOtherKeyCollection(otherKey));
    }

    @Override
    public String pop(String key) {
        return setOperations.pop(key);
    }
}
