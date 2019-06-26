package com.yueyang.service;

/**
 * @author pengyueyang
 * @create 2019/6/19 10:08
 * 统一id基础服务
 */
public interface IdService {

    /**
     * 创建数据库主键id
     */
    Long getGenerateId();

}
