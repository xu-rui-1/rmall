/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package com.alipay.rmall.service.impl;

import com.alibaba.fastjson.JSON;
import com.alipay.rmall.base.config.RabbitMqConfig;
import com.alipay.rmall.service.RabbitService;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @author ruitu.xr
 * @version RabbitServiceImpl.java, v 0.1 2023年05月25日 20:53 ruitu.xr Exp $
 */
@Service
public class RabbitServiceImpl implements RabbitService {

    @Resource
    private RabbitTemplate rabbitTemplate;

    @Override
    public void sendMessage2Email(String message) {
        String msgId = UUID.randomUUID().toString().replaceAll("-", "");
        rabbitTemplate.convertAndSend(RabbitMqConfig.DIRECT_EXCHANGE, RabbitMqConfig.DIRECT_ROUTING_KEY, JSON.toJSONString(message), new CorrelationData(msgId));
    }
}
