package com.yueyang.compent;

/**
 * @author pengyueyang
 * @create 2019/6/25 8:34
 * 分布式锁解锁异常
 */
public class DistributedUnLockException extends RuntimeException {

    private static final long serialVersionUID = -1460436732216965571L;

    public DistributedUnLockException() {
        super();
    }

    public DistributedUnLockException(String message) {
        super(message);
    }

    public DistributedUnLockException(String message, Throwable cause) {
        super(message, cause);
    }

    public DistributedUnLockException(Throwable cause) {
        super(cause);
    }


}
