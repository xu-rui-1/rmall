/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package com.alipay.rmall.base.listener;

import com.alipay.rmall.base.config.RabbitMqConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


/**
 * @author ruitu.xr
 * @version CanalRabbitListener.java, v 0.1 2023年05月25日 19:48 ruitu.xr Exp $
 */
@Component
@Slf4j
public class CanalRabbitListener {

    @RabbitListener(queues = {RabbitMqConfig.CANAL_QUEUE})
    public void receiveCanalMessage(Message message) {
        String msg = new String(message.getBody());
        log.info("收到来自canal监听到的消息：{}", msg);
    }
}
