package com.yueyang.service;

import com.yueyang.compent.DistributedLock;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author pengyueyang
 * @create 2019/6/26 15:50
 */
@Service
public class DistributedLockServiceImpl implements DistributedLockService {

    @Autowired
    private ObjectFactory<DistributedLock> objectFactory;

    @Override
    public boolean lock(String key, String requestId) {
        DistributedLock lock = objectFactory.getObject();
        return lock.tryLock(key,requestId);
    }

    @Override
    public boolean lock(String key, String requestId, Long timeOut, TimeUnit timeUnit) {
        DistributedLock lock = objectFactory.getObject();
        return lock.tryLock(key,requestId,timeOut,timeUnit);
    }

    @Override
    public boolean unLock(String key, String requestId) {
        DistributedLock lock = objectFactory.getObject();
        return lock.unLock(key,requestId);
    }
}
