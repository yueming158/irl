package com.yueyang;

import com.yueyang.service.IdService;
import com.yueyang.service.OrderIdService;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author pengyueyang
 * @create 2019/6/25 10:10
 */
public class IdServiceTest extends Base {

    @Autowired
    private OrderIdService orderIdService;

    @Autowired
    private IdService idService;

    @Test
    public void testGetOrderId() {
        Assert.assertNotNull(orderIdService.getOrderId(0));
    }

    @Test
    public void testGetGenerateId() {
        Long[] ids = new Long[4096];
        for (int i = 0; i < 4096; i++) {
            ids[i] = idService.getGenerateId();
        }
        for (Long id : ids) {
            System.out.println(Long.toBinaryString(id));
        }
    }

}
