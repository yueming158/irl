package com.yueyang.config;

import com.yueyang.compent.SnowflakeId;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author pengyueyang
 * @create 2019/6/25 11:52
 */
@Configuration
@ConfigurationProperties(prefix = "snowflake")
@PropertySource(value = "config.properties")
public class SnowflakeIdConfig {

    private int dataCenterId;

    private int workerId;

    @Bean
    public SnowflakeId snowflakeId() {
        return new SnowflakeId(dataCenterId, workerId);
    }

    public int getDataCenterId() {
        return dataCenterId;
    }

    public void setDataCenterId(int dataCenterId) {
        this.dataCenterId = dataCenterId;
    }

    public int getWorkerId() {
        return workerId;
    }

    public void setWorkerId(int workerId) {
        this.workerId = workerId;
    }
}
