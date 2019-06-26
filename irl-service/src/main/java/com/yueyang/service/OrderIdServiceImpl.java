package com.yueyang.service;

import com.yueyang.compent.DistributedLock;
import com.yueyang.service.redis.RedisKeyService;
import com.yueyang.service.redis.RedisStringService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

/**
 * @author pengyueyang
 * @create 2019/6/25 11:14
 */
@Service
public class OrderIdServiceImpl implements OrderIdService {

    @Autowired
    RedisStringService redisStringService;

    @Autowired
    RedisKeyService redisKeyService;

    @Autowired
    private DistributedLock distributedLock;

    private final static String ORDER_KEY_PREFIX = "ORDER_KEY_";

    private final Integer MIN_TYPE = 0;
    private final Integer MAX_TYPE = 99;
    private final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");
    private final static String ORDER_LOCK_KEY_PREFIX = "ORDER_LOCK_KEY_";

    @Override
    public Long getOrderId(Integer type) {
        if (null == type || type < MIN_TYPE || type > MAX_TYPE) {
            throw new IllegalArgumentException();
        }
        String key = ORDER_KEY_PREFIX + type.toString();
        if (redisKeyService.exists(key)) {
            return redisStringService.increment(key);
        }
        return initOrderId(type);
    }

    private Long initOrderId(Integer type) {
        String key = ORDER_KEY_PREFIX + type.toString();
        String orderId;
        String localKey = ORDER_LOCK_KEY_PREFIX + key;
        String requestId = UUID.randomUUID().toString();
        try {
            distributedLock.tryLock(localKey,requestId);
            orderId = createOrderId(type);
            //double check
            if (redisKeyService.exists(key)) {
                return redisStringService.increment(key);
            }
            Duration timeOut = getTimeOut();
            redisStringService.setWithExpire(key, orderId, timeOut);
        } finally {
            distributedLock.unLock(localKey,requestId);
        }
        return Long.valueOf(orderId);
    }

    private Duration getTimeOut() {
        LocalDateTime startDate = LocalDateTime.now();
        LocalDateTime endDate = startDate.plusDays(1);
        return Duration.between(startDate,endDate);
    }

    private String createOrderId(Integer type) {
        return LocalDateTime.now().format(FORMATTER) + String.format("%02d%09d", type, 0);
    }
}
