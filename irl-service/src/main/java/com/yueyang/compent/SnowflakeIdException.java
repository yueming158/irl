package com.yueyang.compent;

/**
 * @author pengyueyang
 * @create 2019/6/26 14:15
 *
 */
public class SnowflakeIdException extends RuntimeException {

    private static final long serialVersionUID = 2566244589987969242L;

    public SnowflakeIdException() {
        super();
    }

    public SnowflakeIdException(String message) {
        super(message);
    }

    public SnowflakeIdException(String message, Throwable cause) {
        super(message, cause);
    }

    public SnowflakeIdException(Throwable cause) {
        super(cause);
    }
}
