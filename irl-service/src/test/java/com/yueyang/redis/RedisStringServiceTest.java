package com.yueyang.redis;

import com.yueyang.Base;
import com.yueyang.service.redis.RedisStringService;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.TimeUnit;

/**
 * @author pengyueyang
 * @create 2019/6/24 17:26
 */
public class RedisStringServiceTest extends Base {

    @Autowired
    RedisStringService redisStringService;

    @Test
    public void testSet() {
        redisStringService.set("test","1");
    }

    @Test
    public void testSetIfAbsent() {
        Assert.assertEquals(true, redisStringService.setIfAbsent("test","1"));
    }

    @Test
    public void testSetIfAbsentTwo() {
        Assert.assertEquals(true, redisStringService.setIfAbsent("test","1",10L, TimeUnit.SECONDS));
    }
}
