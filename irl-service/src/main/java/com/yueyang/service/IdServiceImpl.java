package com.yueyang.service;

import com.yueyang.compent.SnowflakeId;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author pengyueyang
 * @create 2019/6/19 15:41
 */
@Service
public class IdServiceImpl implements IdService {

    @Autowired
    private SnowflakeId snowflakeId;

    @Override
    public Long getGenerateId() {
        return snowflakeId.getGenerateId();
    }




}
