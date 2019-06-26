package com.yueyang;

import com.yueyang.compent.SnowflakeId;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author pengyueyang
 * @create 2019/6/25 16:22
 */
public class SnowflakeIdTest extends Base {

    @Autowired
    private SnowflakeId snowflakeId;

    @Test
    public void testInitSnowflakeId() {
        Assert.assertNotNull(snowflakeId);
    }
}
