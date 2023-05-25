/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package com.alipay.rmall.service.impl;

import com.alipay.rmall.service.KafkaService;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.ExecutionException;

/**
 * kafka生产者业务服务
 *
 * @author ruitu.xr
 * @version KafkaServiceImpl.java, v 0.1 2023年05月22日 17:44 ruitu.xr Exp $
 */
@Service
@Slf4j
public class KafkaServiceImpl implements KafkaService {

    @Resource
    private KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public String sendMessage(ProducerRecord<String, String> record) throws ExecutionException, InterruptedException {
        SendResult<String, String> result = kafkaTemplate.send(record).get();
        RecordMetadata metadata = result.getRecordMetadata();
        String returnResult = metadata.topic() + "\t" + metadata.partition() + "\t" + metadata.offset();
        log.info("发送消息成功：" + returnResult);

        return returnResult;
    }
}
