/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package com.alipay.rmall.listener;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.DltHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.retrytopic.FixedDelayStrategy;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Component;


/**
 * @author ruitu.xr
 * @version ConsumerListener.java, v 0.1 2023年05月22日 18:10 ruitu.xr Exp $
 */
@Component
@Slf4j
public class ConsumerListener {
    public static int index = 0;

    @RetryableTopic(attempts = "4", backoff = @Backoff(delay = 10000, multiplier = 2), fixedDelayTopicStrategy = FixedDelayStrategy.SINGLE_TOPIC)
    @KafkaListener(topics = "${spring.kafka.topics.biz}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(ConsumerRecord<String, String> record) {
        log.info("消费异常。。。。{}", ++index);
        System.out.println(record.toString());
        throw new RuntimeException("kafka exception");
    }

    @DltHandler
    public void dltHandler(ConsumerRecord<String, String> record) {
        log.info("死信队列===》topic:{}, key:{}, value:{}", record.topic(), record.key(), record.value());
    }
}
