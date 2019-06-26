package com.yueyang;

import com.yueyang.service.DistributedLockService;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

/**
 * @author pengyueyang
 * @create 2019/6/24 16:47
 */
public class DistributedLockTest extends Base {

    @Autowired
    private DistributedLockService distributedLockService;

    @Test
    public void testDistributedLockLock() {
        String requestId = UUID.randomUUID().toString();
        String key = "test";
        try  {
            distributedLockService.lock(key, requestId);
            try {
                Thread.sleep(31000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } finally {
            distributedLockService.unLock(key,requestId);
        }
    }
}
