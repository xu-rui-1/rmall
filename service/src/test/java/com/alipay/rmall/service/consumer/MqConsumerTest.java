/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package com.alipay.rmall.service.consumer;

import com.alibaba.fastjson.JSON;
import com.alipay.rmall.dto.ProductBaseInfoDTO;
import org.junit.Test;

/**
 * @author ruitu.xr
 * @version MqConsumerTest.java, v 0.1 2023年05月26日 09:54 ruitu.xr Exp $
 */
public class MqConsumerTest {
    @Test
    public void test() {
        String msg = "{\"operator\":\"user:5965\",\"productDetail\":\"string\",\"productName\":\"string\",\"productPrice\":0}";
        String jsonString = JSON.toJSONString(msg);
        System.out.println(jsonString);
        ProductBaseInfoDTO productBaseInfoDTO = JSON.parseObject(jsonString, ProductBaseInfoDTO.class);
        System.out.println(productBaseInfoDTO);
    }
}
