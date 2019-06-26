package com.yueyang.service.redis;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author pengyueyang
 * @create 2019/6/19 10:09
 * Redis Key相关操作服务
 */
public interface RedisKeyService {

    /**
     * 删除key
     */
    Boolean del(String key);

    /**
     * 获取数据类型
     */
    String type(String key);

    /**
     * 检测key是否存在
     */
    Boolean exists(String key);

    /**
     * 用时间戳的方式设置过期时间
     */
    Boolean expireAt(String key, Date date);

    /**
     * 给定 key 设置过期时间
     */
    Boolean expire(String key, long timeOut, TimeUnit timeUnit);

    /**
     * 返回给定 key 的剩余生存时间
     */
    Long ttl(String key, TimeUnit timeUnit);

    /**
     * 将当前数据库的 key 移动到给定的数据库 db 当中
     */
    Boolean move(String key, int dbIndex);

    /**
     * 修改key的名称
     */
    Boolean rename(String key, String newKey);

}
