/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package com.alipay.rmall.base.listener;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alipay.rmall.base.config.RabbitMqConfig;
import com.alipay.rmall.base.constants.RedisConstants;
import com.alipay.rmall.base.enums.CanalEventTypeEnum;
import com.alipay.rmall.base.utils.RedisCacheUtils;
import com.alipay.rmall.dal.domain.ProductBaseInfoDO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author ruitu.xr
 * @version CanalRabbitListener.java, v 0.1 2023年05月25日 19:48 ruitu.xr Exp $
 */
@Component
@Slf4j
public class CanalRabbitListener {

    @Resource
    private RedisCacheUtils redisCacheUtils;

    private static final String CANAL_LISTEN_TABLE = "table";
    private static final String CANAL_LISTEN_DATA = "data";
    private static final String CANAL_LISTEN_TYPE = "type";
    private static final String TABLE_NAME = "product_base_info";

    @RabbitListener(queues = {RabbitMqConfig.CANAL_QUEUE})
    public void receiveCanalMessage(Message message) {
        String msg = new String(message.getBody());
        log.info("收到来自canal监听到的消息：{}", msg);
        JSONObject jsonObject = JSON.parseObject(msg);
        String table = jsonObject.getString(CANAL_LISTEN_TABLE);
        if (!TABLE_NAME.equalsIgnoreCase(table)) {
            log.warn("该数据不是来自product_base_info表，无需处理。该数据所在表：{}", table);
        }
        /**
         * 将数据同步至redis
         */
        String type = jsonObject.getString(CANAL_LISTEN_TYPE);
        JSONArray jsonArray = jsonObject.getJSONArray(CANAL_LISTEN_DATA);
        CanalEventTypeEnum eventType = CanalEventTypeEnum.getEnumByCode(type);
        switch (eventType) {
            case UPDATE:
            case INSERT:
                jsonArray.forEach(o -> {
                    ProductBaseInfoDO productBaseInfoDO = JSON.parseObject(o.toString(), ProductBaseInfoDO.class);
                    String key = RedisConstants.PRODUCT_KEY_PREFIX + productBaseInfoDO.getId();
                    redisCacheUtils.add(key, productBaseInfoDO);
                    log.info("更新redis缓存数据成功，数据值为：{}", productBaseInfoDO);
                });
                break;
            case DELETE:
                jsonArray.forEach(o->{
                    ProductBaseInfoDO productBaseInfoDO = JSON.parseObject(o.toString(), ProductBaseInfoDO.class);
                    String key = RedisConstants.PRODUCT_KEY_PREFIX + productBaseInfoDO.getId();
                    redisCacheUtils.delete(key);
                    log.info("删除redis缓存数据成功，删除的数据值为：{}", productBaseInfoDO);
                });
                break;
            default:
                throw new RuntimeException("不支持的消息监听类型:" + eventType.getCode());
        }
    }
}
