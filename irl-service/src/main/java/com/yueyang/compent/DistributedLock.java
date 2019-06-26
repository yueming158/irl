package com.yueyang.compent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.script.RedisScript;

import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import static java.util.Arrays.asList;

/**
 * @author pengyueyang
 * @create 2019/6/24 15:04
 */
public class DistributedLock {

    private final Logger logger = LoggerFactory.getLogger(DistributedLock.class);

    private StringRedisTemplate stringRedisTemplate;

    private ValueOperations<String, String> stringValueOperations;

    @PostConstruct
    public void init() {
        stringValueOperations = stringRedisTemplate.opsForValue();
    }

    /**
     * 默认解锁时间为30秒
     */
    private final Long DEFAULT_TIME_OUT = 30L;

    private final TimeUnit DEFAULT_TIME_UNIT = TimeUnit.SECONDS;

    private final Long DEFAULT_SLEEP_TIME = 100L;

    private final RedisScript<Long> SCRIPT = RedisScript.of("if redis.call('get', KEYS[1]) == " +
            "ARGV[1] then return redis" +
          ".call('del', KEYS[1]) else return 0 end",Long.class);


    //requestId为请求id可以使用uuid,加入请求id是为了防止其它人删除
    public boolean tryLock(String key, String requestId) {
        return tryLock(key, requestId, DEFAULT_TIME_OUT, DEFAULT_TIME_UNIT);
    }

    public boolean tryLock(String key, String requestId, Long timeOut, TimeUnit timeUnit) {
        if (null == key || key.trim().length() == 0) {
            throw new IllegalArgumentException();
        }
        if (null == requestId || requestId.trim().length() == 0) {
            throw new IllegalArgumentException();
        }
        logger.info("Try lock:{}:{}", key, requestId);
        while (true) {
            boolean result = stringValueOperations.setIfAbsent(key, requestId, timeOut, timeUnit);
            if (result) {
                logger.info("Get lock success:{}:{}", key, requestId);
                return true;
            }
            try {
                Thread.sleep(DEFAULT_SLEEP_TIME);
            } catch (InterruptedException e) {
                logger.info("Get lock error:{}:{}", key, requestId);
                e.printStackTrace();
                return false;
            }
        }
    }

    public DistributedLock(StringRedisTemplate stringRedisTemplate) {
        if (stringRedisTemplate == null) {
            throw new IllegalArgumentException();
        }
        this.stringRedisTemplate = stringRedisTemplate;
    }

    public boolean unLock(String key, String requestId) {
        logger.info("Try unlock:{}:{}", key, requestId);
        Long result = stringRedisTemplate.execute(SCRIPT, asList(key), requestId);
        if (null == result || result <= 0L) {
            logger.info("Try unlock error:{}:{} had unlock", key, requestId);
            throw new DistributedUnLockException();
        }
        logger.info("Try unlock success:{}:{}", key, requestId);
        return true;
    }


}
