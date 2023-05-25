/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package com.alipay.rmall.base.kafka;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alipay.rmall.base.constants.RedisConstants;
import com.alipay.rmall.base.enums.CanalEventTypeEnum;
import com.alipay.rmall.base.utils.RedisCacheUtils;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author ruitu.xr
 * @version KafkaCanalConsumer.java, v 0.1 2023年05月19日 18:36 ruitu.xr Exp $
 */
@Component
public class KafkaCanalConsumer {

    @Autowired
    private RedisCacheUtils redisCacheUtils;

    @KafkaListener(topics = {"example"})
    public void listen(ConsumerRecord<?, ?> record) {
        System.out.println(record.value());
        System.out.println(record.topic());
        String value = (String) record.value();
        JSONObject jsonObject = JSONObject.parseObject(value);
        String type = jsonObject.getString("type");
        JSONArray data = jsonObject.getJSONArray("data");
        for (int i = 0; i < data.size(); i++) {
            JSONObject dataObject = data.getJSONObject(i);
            System.out.println(dataObject);
            CanalEventTypeEnum eventType = CanalEventTypeEnum.getEnumByCode(type);
            String key = RedisConstants.PRODUCT_KEY_PREFIX + dataObject.get("id");
            switch (Objects.requireNonNull(eventType)) {
                case INSERT:
                case UPDATE:
                    redisCacheUtils.add(key, dataObject.toJSONString());
                    break;
                case DELETE:
                    redisCacheUtils.delete(key);
                    break;
                default:
                    throw new RuntimeException("UnKnow enum type error");
            }
        }
    }
}
