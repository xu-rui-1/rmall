/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package com.alipay.rmall.base.listerner;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alipay.rmall.dal.domain.ProductBaseInfoDO;
import org.junit.Test;

/**
 * @author ruitu.xr
 * @version CanalRabbitListenerTest.java, v 0.1 2023年05月26日 11:09 ruitu.xr Exp $
 */
public class CanalRabbitListenerTest {

    @Test
    public void testCanal() {
        String msg = "{\"data\":[{\"id\":\"8\",\"product_name\":\"辣椒炒肉\",\"product_price\":\"28.80\",\"product_detail\":\"湖南小炒肉\","
                + "\"operator\":\"user:872\",\"gmt_create\":\"2023-05-22 12:07:10\",\"gmt_modified\":\"2023-05-22 12:07:10\"}],"
                + "\"database\":\"mydb\",\"es\":1685069475000,\"id\":5,\"isDdl\":false,\"mysqlType\":{\"id\":\"int(11) unsigned\","
                + "\"product_name\":\"varchar(128)\",\"product_price\":\"decimal(11,2)\",\"product_detail\":\"varchar(512)\","
                + "\"operator\":\"varchar(128)\",\"gmt_create\":\"timestamp\",\"gmt_modified\":\"timestamp\"},"
                + "\"old\":[{\"product_detail\":\"湖南小炒，好吃\"}],\"pkNames\":[\"id\"],\"sql\":\"\",\"sqlType\":{\"id\":4,"
                + "\"product_name\":12,\"product_price\":3,\"product_detail\":12,\"operator\":12,\"gmt_create\":93,\"gmt_modified\":93},"
                + "\"table\":\"product_base_info\",\"ts\":1685069475703,\"type\":\"UPDATE\"}";
        JSONObject jsonObject = JSON.parseObject(msg);
        JSONArray data = jsonObject.getJSONArray("data");
        data.forEach(o -> {
            ProductBaseInfoDO productBaseInfoDO = JSON.parseObject(o.toString(), ProductBaseInfoDO.class);
            System.out.println(productBaseInfoDO);
        });
    }
}
