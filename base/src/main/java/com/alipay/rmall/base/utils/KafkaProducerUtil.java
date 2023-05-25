/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package com.alipay.rmall.base.utils;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFutureCallback;

import javax.annotation.Resource;

/**
 * @author ruitu.xr
 * @version KafkaProducerUtil.java, v 0.1 2023年05月22日 16:27 ruitu.xr Exp $
 */
@Component
public class KafkaProducerUtil {
    @Resource
    private KafkaTemplate kafkaTemplate;

    public void sendSyncMessage(String topic, String message) {
        try {
            System.out.println("topic=" + topic + ", message=" + message);
            kafkaTemplate.send(topic, message).get();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void sendAsyncMessage(String topic, String message) {
        kafkaTemplate.send(topic, message).addCallback(new ListenableFutureCallback() {
            @Override
            public void onFailure(Throwable ex) {
                System.out.println(ex.getMessage());
            }

            @Override
            public void onSuccess(Object result) {
                System.out.println("sendAsyncMessage success!");
            }
        });
    }
}
