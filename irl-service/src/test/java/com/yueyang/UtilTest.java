package com.yueyang;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author pengyueyang
 * @create 2019/6/21 15:08
 */
public class UtilTest {
    @Test
    public void testMoveBit() {
        int i = 10;
        i = i >> 1;
        Assert.assertEquals(5,i);
    }

    @Test
    public void testLongMaxValue() {
        String maxLongValue = String.valueOf(Long.MAX_VALUE);
        System.out.println(maxLongValue+":"+maxLongValue.length());
    }

}
