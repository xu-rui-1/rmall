/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package com.alipay.rmall.consumer;

import com.alibaba.fastjson.JSON;
import com.alipay.rmall.base.config.RabbitMqConfig;
import com.alipay.rmall.dto.ProductBaseInfoDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author ruitu.xr
 * @version MqConsumer.java, v 0.1 2023年05月25日 21:10 ruitu.xr Exp $
 */
@Component
@Slf4j
public class MqConsumer {

    @RabbitListener(queues = {RabbitMqConfig.DIRECT_QUEUE})
    public void receive(Message message) {
        ProductBaseInfoDTO baseInfoDTO = JSON.parseObject(JSON.parse(message.getBody()).toString(), ProductBaseInfoDTO.class);
        log.info(baseInfoDTO.toString());
    }
}
