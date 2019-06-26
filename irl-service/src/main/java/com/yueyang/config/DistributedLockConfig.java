package com.yueyang.config;

import com.yueyang.compent.DistributedLock;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @author pengyueyang
 * @create 2019/6/24 15:40
 */
@Configuration
public class DistributedLockConfig {

    @Bean
    public DistributedLock distributedLock(StringRedisTemplate stringRedisTemplate) {
        return new DistributedLock(stringRedisTemplate);
    }
}
